/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProImg;
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
@WebServlet(name = "setAsMainImgAction", urlPatterns = {"/setAsMainImgAction"})
public class setAsMainImgAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("imgId") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProImg img = (ProImg) s.load(ProImg.class, Integer.parseInt(request.getParameter("imgId")));
            int proId = img.getProduct().getId();
            Product product = (Product) s.load(Product.class, proId);
            s.beginTransaction();
            product.setImageMain(img.getImage());
            s.update(product);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("Updated Successfully !");
        } else {
            response.getWriter().write("Error");
        }
    }

}
