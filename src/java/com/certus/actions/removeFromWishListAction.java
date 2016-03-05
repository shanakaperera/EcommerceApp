/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.WishList;
import java.io.IOException;
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
@WebServlet(name = "removeFromWishListAction", urlPatterns = {"/removeFromWishListAction"})
public class removeFromWishListAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        WishList wish = (WishList) s.load(WishList.class, Integer.parseInt(request.getParameter("wid")));
        s.beginTransaction();
        s.delete(wish);
        s.getTransaction().commit();
        s.close();
        response.getWriter().write("Removed Successfully !");
    }

}
