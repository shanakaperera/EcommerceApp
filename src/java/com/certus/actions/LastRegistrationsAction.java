/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.User;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "LastRegistrationsAction", urlPatterns = {"/LastRegistrationsAction"})
public class LastRegistrationsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<User> uList = ses.createCriteria(User.class).list();
        for (User user : uList) {
            response.getWriter().write("<tr> \n"
                    + "<td>" + user.getFName() + " " + user.getLName() + "</td> \n"
                    + "<td>" + user.getEmail() + "</td>\n"
                    + "<td>\n"
                    + "<a  href='edit_customer.jsp?uid="+user.getId()+"'><span class='glyphicon glyphicon-edit'></span></a>"
                    + "</td>"
                    + "</tr>");
        }
    }

}
