/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.EmailSettings;
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
@WebServlet(name = "saveImageSettingsAction", urlPatterns = {"/saveImageSettingsAction"})
public class saveImageSettingsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        String emailAdd = request.getParameter("emailAdd");
        String hostN = request.getParameter("hostN");
        String portN = request.getParameter("portN");
        String psswrdN = request.getParameter("psswrdN");

        if (!emailAdd.isEmpty() && !hostN.isEmpty() && !portN.isEmpty() && !psswrdN.isEmpty()) {
            EmailSettings es = (EmailSettings) s.load(EmailSettings.class, 1);
            s.beginTransaction();
            es.setHost(hostN);
            es.setPassword(psswrdN);
            es.setUser(emailAdd);
            es.setPort(Integer.parseInt(portN));
            s.update(es);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("You have successfully saved the changes.");
        }
    }

}
