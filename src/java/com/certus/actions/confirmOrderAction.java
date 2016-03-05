/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.CartItem;
import com.certus.controllers.ShoppingCart;
import com.certus.dbmodel.AdminNotifications;
import com.certus.dbmodel.Order;
import com.certus.dbmodel.ProductHasSize;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "confirmOrderAction", urlPatterns = {"/confirmOrderAction"})
public class confirmOrderAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name1 = request.getParameter("name1");
        String email1 = request.getParameter("email1");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String optionsRadios = request.getParameter("optionsRadios");
        String order_id = "";
        String payment = "";
        switch (optionsRadios) {
            case "1":
                payment = "Debit Card";
                break;
            case "2":
                payment = "Credit Card";
                break;
            case "3":
                payment = "Internet Banking";
                break;
            case "4":
                payment = "Cash On Delivery";
                break;
            case "5":
                payment = "Paypal";
                break;
            default:
                break;

        }

        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

        Order order = (Order) s.createCriteria(Order.class, "order")
                .add(Restrictions.eq("order.invoNum", cart.getInvoiceNum()))
                .uniqueResult();

        if (order != null) {

            order_id = cart.getInvoiceNum();
            s.beginTransaction();
            order.setOrderAddress(address);
            order.setStatus("Requested");
            order.setName(name1);
            order.setEmail(email1);
            order.setTel(telephone);
            order.setCity(city);
            order.setPayment_method(payment);
            s.update(order);
            s.getTransaction().commit();

            //update qnty.......
            CopyOnWriteArrayList<CartItem> shoppingList = cart.getShoppingList();
            for (CartItem sl : shoppingList) {

                ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                        .createAlias("phs.product", "product")
                        .createAlias("phs.size", "size")
                        .add(Restrictions.eq("size.sizeName", sl.getSize()))
                        .add(Restrictions.eq("product.id", sl.getProduct_id()))
                        .uniqueResult();
                s.beginTransaction();
                phs.setQnty(phs.getQnty() - sl.getQnty());
                s.update(phs);
                s.getTransaction().commit();

            }
            //add to admin notification....

            AdminNotifications notifications = new AdminNotifications();
            s.beginTransaction();
            notifications.setDate(new java.util.Date());
            notifications.setDescription("New Order has been added .");
            notifications.setMessage("<h5>New Order has been added to the order list. Order id is - <a href='edit_order.jsp?oid="+order_id+"'>" + order_id + "</a></h5>"
                    + "<br/>Order amount - Rs. " + cart.getTotal());
            s.save(notifications);
            s.getTransaction().commit();

            //updated............
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.removeAttribute("cart");
                session.removeAttribute("coupon");
            }
            s.close();
            response.sendRedirect("confirmation.jsp?oid=" + order_id);
        }

    }

}
