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
import com.certus.dbmodel.Order;
import java.sql.Date;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "sendUserMsgAction", urlPatterns = {"/sendUserMsgAction"})
public class sendUserMsgAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("msg") != null && !request.getParameter("msg").isEmpty()) {

            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Order lastOrder = (Order) s.createCriteria(Order.class, "order")
                    .createAlias("order.user", "user")
                    .add(Restrictions.eq("user.id", Integer.parseInt(request.getParameter("uid"))))
                    .addOrder(org.hibernate.criterion.Order.desc("order.dateOrdered"))
                    .setMaxResults(1).uniqueResult();
            if (lastOrder != null) {
                Messages msg = new Messages();
                s.beginTransaction();
                msg.setMessage(request.getParameter("msg"));
                msg.setUserViewed(true);
                msg.setDateSent(new Date(new java.util.Date().getTime()));
                msg.setAdminSent(false);
                msg.setOrder(lastOrder);
                s.save(msg);
                s.getTransaction().commit();
                s.close();
                response.getWriter().write("success");
            }
        }

    }

}
