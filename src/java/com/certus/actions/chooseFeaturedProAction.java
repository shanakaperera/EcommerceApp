/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
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
@WebServlet(name = "chooseFeaturedProAction", urlPatterns = {"/chooseFeaturedProAction"})
public class chooseFeaturedProAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // System.out.println(request.getParameter("pid") + " - " + request.getParameter("fpid"));
        int pid = Integer.parseInt(request.getParameter("pid"));
        int fpid = Integer.parseInt(request.getParameter("fpid"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        Product pro = (Product) s.load(Product.class, pid);
        if (pro.getFeaturedItems() != null && !pro.getFeaturedItems().isEmpty()) {
            pro.setFeaturedItems(pro.getFeaturedItems() + request.getParameter("fpid") + ",");
        } else {
            pro.setFeaturedItems(request.getParameter("fpid") + ",");
        }
        s.update(pro);
        s.getTransaction().commit();
        s.close();
        response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
    }

}
