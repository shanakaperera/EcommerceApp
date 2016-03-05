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
@WebServlet(name = "viewMsgAction", urlPatterns = {"/viewMsgAction"})
public class viewMsgAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Messages msg = (Messages) s.load(Messages.class, Integer.parseInt(request.getParameter("msg_id")));
        String html = "<table>"
                + "    <tr>"
                + "        <td><h5>Customer Name : &nbsp;&nbsp;</h5> </td>"
                + "        <td><h6>" + msg.getOrder().getUser().getFName() + " " + msg.getOrder().getUser().getLName() + "</h6></td>"
                + "    </tr>"
                + "    <tr>"
                + "        <td><h5>Message  &nbsp;&nbsp;:</h5> </td>"
                + "        <td><h6>" + msg.getMessage() + "</h6></td>"
                + "    </tr>"
                + "    <tr>"
                + "        <td><h5>Date Sent&nbsp;&nbsp; :</h5> </td>"
                + "        <td><h6>" + msg.getDateSent() + "</h6></td>"
                + "    </tr>"
                + "</table>";
        s.close();
        response.getWriter().write(html);

    }

}
