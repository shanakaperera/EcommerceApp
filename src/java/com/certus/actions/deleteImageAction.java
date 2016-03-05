/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProImg;
import java.io.File;
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
@WebServlet(name = "deleteImageAction", urlPatterns = {"/deleteImageAction"})
public class deleteImageAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("imgId") != null && request.getParameter("path") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProImg img = (ProImg) s.load(ProImg.class, Integer.parseInt(request.getParameter("imgId")));

            File file = new File(request.getParameter("path") + "/" + img.getImage());
            if (file.delete()) {
                s.beginTransaction();
                s.delete(img);
                s.getTransaction().commit();
                s.close();
                response.getWriter().write("Deleted Successfully !");

            } else {
                response.getWriter().write("Error");
            }
        }
    }

}
