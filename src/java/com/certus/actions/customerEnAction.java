/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.User;
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
@WebServlet(name = "customerEnAction", urlPatterns = {"/customerEnAction"})
public class customerEnAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int uid = Integer.parseInt(request.getParameter("uid"));
        User u = (User) s.load(User.class, uid);
        s.beginTransaction();
        if (Boolean.valueOf(request.getParameter("enable"))) {
            u.setAvailability(true);
        } else {
            u.setAvailability(false);
        }
        s.update(u);
        s.getTransaction().commit();
        s.close();
        response.getWriter().write("success");
    }

}
