/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.User;
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
@WebServlet(name = "updateCustomerByAdminAction", urlPatterns = {"/updateCustomerByAdminAction"})
public class updateCustomerByAdminAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int uid = Integer.parseInt(request.getParameter("uid"));
        String uName = request.getParameter("uName");
        String fName = request.getParameter("fName");
        String lname = request.getParameter("lName");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String pass = request.getParameter("pass");
        User usr = (User) s.load(User.class, uid);
        s.beginTransaction();
        if (uName != null && fName != null && lname != null
                && email != null && tel != null && pass != null && !pass.isEmpty()) {
            usr.setUserName(uName);
            usr.setFName(fName);
            usr.setLName(lname);
            usr.setEmail(email);
            usr.setTelephone(tel);
            usr.setPassword(pass);
            s.update(usr);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("success");
        } else if (uName != null && fName != null && lname != null
                && email != null && tel != null) {
            usr.setUserName(uName);
            usr.setFName(fName);
            usr.setLName(lname);
            usr.setEmail(email);
            usr.setTelephone(tel);
            s.update(usr);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("success");

        }

    }

}
