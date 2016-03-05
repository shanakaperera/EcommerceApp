/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.UserManage;
import com.certus.dbmodel.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "login2Action", urlPatterns = {"/login2Action"})
public class login2Action extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String password = request.getParameter("password");
       // System.out.println("name - "+user_name+" pass - "+password);
        UserManage loginManage = new UserManage();
       if (user_name != null && !user_name.isEmpty() && password != null && !password.isEmpty()) {
            if (loginManage.loginValidated(user_name, password)) {
                if (request.getSession().getAttribute("user") != null) {
                    User user = (User) request.getSession().getAttribute("user");
                    response.sendRedirect("my-account.jsp");
                } else {
                    request.getSession().setAttribute("user", loginManage.getUser());
                    response.sendRedirect("my-account.jsp");
                }

            } else {
                response.getWriter().write("Sorry ! Invalid credentials.");
            }

        }
    }

}
