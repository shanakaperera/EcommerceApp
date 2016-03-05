/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.CartItem;
import com.certus.controllers.ShoppingCart;
import com.certus.dbmodel.Coupon;
import com.certus.dbmodel.Order;
import com.certus.dbmodel.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "setOrderAction", urlPatterns = {"/setOrderAction"})
public class setOrderAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
        User user = (User) request.getSession().getAttribute("user");
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Coupon coupon = (Coupon) request.getSession().getAttribute("coupon");
        Criteria criteria = s
                .createCriteria(Order.class)
                .setProjection(Projections.max("id"));
        Integer maxId = (Integer) criteria.uniqueResult();

        if (cart != null && user != null) {
            String invo_id = "ord-" + new java.util.Date().getYear() + String.format("%03d", maxId == null ? 1 : maxId);
            cart.setInvoiceNum(invo_id);
            String pids = "";
            String sizes = "";
            String qnties = "";
            Order order = new Order();
            s.beginTransaction();
            order.setUser(user);
            order.setDateOrdered(new Date(new java.util.Date().getTime()));

            List<Double> dList = new ArrayList<>();
            for (CartItem phs : cart.getShoppingList()) {
                pids += phs.getProduct_id() + ",";
                sizes += phs.getSize() + ",";
                qnties += phs.getQnty() + ",";
                dList.add(cart.getPriceofProduct(phs.getProduct_id(), phs.getSize()) * phs.getQnty());

            }
            cart.setTotal(dList.stream().mapToDouble(x -> x).sum());
            if (coupon != null) {
                order.setCoupon(coupon);
                cart.setCouponNum(coupon.getCouponCode());
                Double totalWithCoupon = cart.getTotalWithCoupon(coupon.getDiscount());
                cart.setTotal(totalWithCoupon);
            }

            order.setProductIds(removeLastChar(pids));
            order.setSizes(removeLastChar(sizes));
            order.setQuantities(removeLastChar(qnties));
            order.setInvoNum(cart.getInvoiceNum());
            order.setGrandTot(cart.getTotal());
            s.save(order);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("Ok");
        } else if (user == null) {
            response.getWriter().write("You have to login first !");
        }
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

}
