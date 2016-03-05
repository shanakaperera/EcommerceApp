/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.CartItem;
import com.certus.controllers.ShoppingCart;
import com.certus.dbmodel.ProductHasSize;
import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "addToCartAction", urlPatterns = {"/addToCartAction"})
public class addToCartAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("dom") == null) {
            if (request.getParameter("size") != null
                    && request.getParameter("pro_id") != null
                    && request.getParameter("qnty") != null) {
                //System.out.println("work to here");

                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                        .createAlias("phs.size", "size")
                        .createAlias("phs.product", "product")
                        .add(Restrictions.eq("size.sizeName", request.getParameter("size")))
                        .add(Restrictions.eq("product.id", Integer.parseInt(request.getParameter("pro_id"))))
                        .uniqueResult();
                if (phs.getQnty() < Integer.parseInt(request.getParameter("qnty"))) {
                    response.sendError(505);
                    return;
                } else if (request.getSession(false).getAttribute("cart") != null) {
                    // System.out.println("cart work fine...");
                    ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
                    int addedQnty = 0;
                    try {
                        addedQnty = shoppingCart.getShoppingList().stream()
                                .filter(p -> p.getProduct_id() == Integer.parseInt(request.getParameter("pro_id"))
                                        && p.getSize().equals(request.getParameter("size"))).findAny().get().getQnty();
                    } catch (NoSuchElementException e) {
                        addedQnty = 0;
                    }
                    //  System.out.println("Added qnty... - - " + addedQnty + " phs qnty - " + phs.getQnty());
                    if (addedQnty + Integer.parseInt(request.getParameter("qnty")) > phs.getQnty()) {
                        response.sendError(505);
                        return;
                    }
                    s.close();
                }
                CartItem item = null;
                try {
                    item = new CartItem(Integer.parseInt(request.getParameter("pro_id")),
                            Integer.parseInt(request.getParameter("qnty")), request.getParameter("size"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    // response.sendError(500);
                    return;
                }

                if (request.getSession().getAttribute("cart") != null) {
                    ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
                    shoppingCart.addItem(item);

                    response.getWriter().write("Item added to the cart !" + request.getParameter("pro_id"));
                } else {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.addItem(item);
                    request.getSession().setAttribute("cart", shoppingCart);

                    response.getWriter().write("Item added to the cart !" + request.getParameter("pro_id"));
                }
            }

        } else {
            if (request.getParameter("size") != null
                    && request.getParameter("pro_id") != null
                    && request.getParameter("qnty") != null) {
                System.out.println("work to here");
                CartItem item = new CartItem(Integer.parseInt(request.getParameter("pro_id")),
                        Integer.parseInt(request.getParameter("qnty")), request.getParameter("size"));

                if (request.getSession().getAttribute("cart") != null) {
                    ShoppingCart shoppingCart = (ShoppingCart) request.getSession().getAttribute("cart");
                    shoppingCart.addItem(item);

                    response.sendRedirect("cart.jsp");
                } else {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.addItem(item);
                    request.getSession().setAttribute("cart", shoppingCart);

                    response.sendRedirect("cart.jsp");
                }
            }

        }

    }

}
