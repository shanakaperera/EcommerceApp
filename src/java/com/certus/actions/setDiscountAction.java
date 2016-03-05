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
@WebServlet(name = "setDiscountAction", urlPatterns = {"/setDiscountAction"})
public class setDiscountAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("discPre") != null) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int sid = Integer.parseInt(request.getParameter("sid"));
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Product p = (Product) s.load(Product.class, pid);
            s.beginTransaction();
            p.setDiscountPrice(Integer.parseInt(request.getParameter("discPre")));
            s.getTransaction().commit();
            s.close();
            response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
            
  
        }
    }

}
