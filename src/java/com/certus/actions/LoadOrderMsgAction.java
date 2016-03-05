/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Messages;
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
@WebServlet(name = "LoadOrderMsgAction", urlPatterns = {"/LoadOrderMsgAction"})
public class LoadOrderMsgAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int oid = Integer.parseInt(request.getParameter("oid"));
        List<Messages> msgList = s.createCriteria(Messages.class, "msg")
                .createAlias("msg.order", "order")
                .add(Restrictions.eq("order.id", oid))
                .addOrder(Order.asc("msg.id"))
                .list();
        String html = "";

        if (msgList != null) {
            for (Messages msg : msgList) {
                String img = msg.isAdminSent() ? "img/admin.png" : "img/guest.png";
                String adminSent = msg.isAdminSent() ? "Me" : msg.getOrder().getUser().getFName();
                String viewed = msg.isAdminViewed() ? "" : " notViewed";
                String btn = msg.isAdminSent() ? "" : "<button class='btn btn-default' onclick='viewMsg(" + msg.getId() + ");' type='button' data-dismiss='modal' data-target='#viewMsg' data-toggle='modal' title='View Message'><span class='glyphicon glyphicon-eye-open'></span></button>";
                html += "<div class='media msg " + viewed + "'>"
                        + "    <a class='pull-left'>"
                        + "        <img class='media-object img-circle' style='width: 32px; height: 32px;' src='" + img + "'>"
                        + "    </a>"
                        + "    <div class='media-body'>"
                        + "        <small class='pull-right time'><i class='fa fa-clock-o'></i>" + msg.getDateSent() + "</small>"
                        + "        <h5 class='media-heading'>" + adminSent + "</h5>"
                        + "        <small class='col-md-10 text-muted'>" + msg.getMessage() + "</small>"
                        + btn
                        + "    </div>"
                        + "</div>";
            }
            s.close();
            response.getWriter().write(html);
        } else {
            response.getWriter().write("<h3>No Messages available.</h3>");
        }
    }

}
