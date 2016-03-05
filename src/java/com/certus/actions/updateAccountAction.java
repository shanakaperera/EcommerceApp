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
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "updateAccountAction", urlPatterns = {"/updateAccountAction"})
public class updateAccountAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession(false).getAttribute("user");
        if (u != null) {
            String name = request.getParameter("name1");
            String email = request.getParameter("email1");
            String tel = request.getParameter("telephone");
            String address = request.getParameter("address");
            String user_name = request.getParameter("username2");
            String pass = request.getParameter("password2");
            String agreed = request.getParameter("agreed");
            BasicPasswordEncryptor encriptor = new BasicPasswordEncryptor();
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            if (name != null && email != null && tel != null && address != null
                    && user_name != null && pass != null && agreed != null) {
                response.getWriter().write("Worked");
                String[] nameAry = name.split(" ");
                User usr = (User) s.load(User.class, u.getId());
                s.beginTransaction();
                usr.setAddress(address);
                usr.setEmail(email);
                usr.setPassword(encriptor.encryptPassword(pass));
                usr.setUserName(user_name);
                usr.setTelephone(tel);
                usr.setFName(nameAry[0]);
                usr.setLName(nameAry[1]);
                s.getTransaction().commit();
                s.close();
                response.sendRedirect("index.jsp");
            }
        }
    }

}
