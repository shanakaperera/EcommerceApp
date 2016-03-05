/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Administor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "adminEditProfileAction", urlPatterns = {"/adminEditProfileAction"})
public class adminEditProfileAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        BasicPasswordEncryptor encriptor = new BasicPasswordEncryptor();
        String logName = request.getParameter("logName");
        String fName = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String conpass = request.getParameter("conpass");
        Administor ad = (Administor) request.getSession(false).getAttribute("admin");
        if (logName != null && !logName.isEmpty() && fName != null && !fName.isEmpty()
                && lname != null && !lname.isEmpty() && email != null && !email.isEmpty()) {
            Administor admin = (Administor) s.load(Administor.class, ad.getId());
            s.beginTransaction();
            admin.setEmail(email);
            admin.setFName(fName);
            admin.setLName(lname);
            admin.setUserName(logName);
            if (pass.equals(conpass) && !pass.isEmpty() && !conpass.isEmpty()) {
                admin.setPassword(encriptor.encryptPassword(pass));
            }
            s.update(admin);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("success");
        }
    }

}
