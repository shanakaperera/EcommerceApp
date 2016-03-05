/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.User;
import com.certus.dbmodel.WishList;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "addToWishListAction", urlPatterns = {"/addToWishListAction"})
public class addToWishListAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Product p = (Product) s.load(Product.class, Integer.parseInt(request.getParameter("pid")));
        User u = (User) request.getSession().getAttribute("user");
        if (p != null & u != null) {
            WishList list = new WishList();
            s.beginTransaction();
            list.setDateAdded(new Date(new java.util.Date().getTime()));
            list.setProduct(p);
            list.setUser(u);
            s.save(list);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("Successfully added to the wish-list !");
        } else if (u == null) {
            response.getWriter().write("You have to login first.");
        }
    }

}
