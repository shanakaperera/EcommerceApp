/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Messages;
import com.certus.dbmodel.Order;
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
@WebServlet(name = "SendAdminMsgAction", urlPatterns = {"/SendAdminMsgAction"})
public class SendAdminMsgAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        if (request.getParameter("msg") != null) {
            Order ordr = (Order) s.load(Order.class, Integer.parseInt(request.getParameter("oid")));

            Messages msg = new Messages();
            s.beginTransaction();
            msg.setMessage(request.getParameter("msg"));
            msg.setOrder(ordr);
            msg.setAdminSent(true);
            msg.setAdminViewed(true);
            msg.setDateSent(new Date(new java.util.Date().getTime()));
            s.save(msg);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("success.");

        }
    }

}
