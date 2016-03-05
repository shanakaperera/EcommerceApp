/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.AdminNotifications;
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
@WebServlet(name = "notificationViewAction", urlPatterns = {"/notificationViewAction"})
public class notificationViewAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("notId") != null && !request.getParameter("notId").isEmpty()) {
            int notId = Integer.parseInt(request.getParameter("notId"));
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            AdminNotifications notifications = (AdminNotifications) s.load(AdminNotifications.class, notId);
            s.beginTransaction();
            notifications.setNotified(true);
            s.update(notifications);
            s.getTransaction().commit();
            String html = "";

            html += notifications.getMessage() + "<br/>"
                    + notifications.getDate();
            s.close();
            response.getWriter().write(html);
        }

    }

}
