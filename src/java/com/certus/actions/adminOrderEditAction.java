/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
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
@WebServlet(name = "adminOrderEditAction", urlPatterns = {"/adminOrderEditAction"})
public class adminOrderEditAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s= com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int oid = Integer.parseInt(request.getParameter("oid"));
        Order order = (Order) s.load(Order.class, oid);
        
    }



}
