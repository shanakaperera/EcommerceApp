/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.Sales;
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
@WebServlet(name = "updateOrderStatusAction", urlPatterns = {"/updateOrderStatusAction"})
public class updateOrderStatusAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Order order = (Order) s.load(Order.class, Integer.parseInt(request.getParameter("oid")));
        s.beginTransaction();
        order.setStatus(request.getParameter("status"));
        s.update(order);
        s.getTransaction().commit();

        if (request.getParameter("status").equals("Completed")) {
            s.beginTransaction();
            Sales sale = new Sales();
            sale.setDateDone(new Date(new java.util.Date().getTime()));
            sale.setDescription("Successfully Completed the order.");
            sale.setOrder(order);
            sale.setStatus("Completed");
            sale.setGrandTotal(order.getGrandTot());
            s.save(sale);
            s.getTransaction().commit();
            s.close();
        }
        response.getWriter().write("success");
    }

}
