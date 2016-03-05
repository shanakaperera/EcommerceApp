/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.AdminNotifications;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "statusBarLoadAction", urlPatterns = {"/statusBarLoadAction"})
public class statusBarLoadAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<AdminNotifications> notificationsAll = s.createCriteria(AdminNotifications.class)
                .addOrder(Order.desc("date"))
                .list();
        List<AdminNotifications> notificationsNotNotified = s.createCriteria(AdminNotifications.class, "ad")
                .add(Restrictions.eq("ad.notified", false))
                .addOrder(Order.desc("ad.date"))
                .list();
        html += "<ul class='dropdown-menu' role='menu'>";
        int i = 1;
        for (AdminNotifications notificationA : notificationsAll) {
            if (i <= 6) {
                html += "<li>"
                        + "<a onclick='func(" + notificationA.getId() + ");'>";
                if (!notificationA.isNotified()) {
                    html += "<span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp;&nbsp;";
                }
                html += notificationA.getDescription() + "</a>"
                        + "</li>";
            }
            i++;
        }

        if (notificationsAll.size() > 6) {
            html += "<li class='divider'></li>"
                    + "<li>"
                    + "<a>View All Messages</a>"
                    + "</li>";
        }

        html += "</ul>";
        int notSize = notificationsNotNotified.size();
        s.close();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("[{\"d1\":\"" + html + "\",\"d2\":\"" + notSize + "\"}]");
    }

}
