/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Messages;
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
@WebServlet(name = "setAsViewedUserAction", urlPatterns = {"/setAsViewedUserAction"})
public class setAsViewedUserAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Messages msg = (Messages) s.load(Messages.class, Integer.parseInt(request.getParameter("mid")));
        s.beginTransaction();
        msg.setUserViewed(true);
        s.update(msg);
        s.getTransaction().commit();
        s.close();
        response.getWriter().write("success");
    }

}
