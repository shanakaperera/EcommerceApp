/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
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
@WebServlet(name = "LastOrderViewAction", urlPatterns = {"/LastOrderViewAction"})
public class LastOrderViewAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<Order> oList = ses.createCriteria(Order.class).list();
        for (Order ord : oList) {
            response.getWriter().write("<tr>"
                    + "<td>" + ord.getInvoNum() + "</td>"
                    + "<td>" + ord.getName() + "</td>"
                    + "<td>" + ord.getStatus() + "</td>"
                    + "<td> Rs. " + ord.getGrandTot() + "</td>"
                    + "<td>"
                    + "<a  href='edit_order.jsp?oid=" + ord.getId() + "'><span class='glyphicon glyphicon-edit'></span></a>"
                    + "</td>"
                    + "</tr>");
        }
    }

}
