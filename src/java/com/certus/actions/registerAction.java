/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.UserManage;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "registerAction", urlPatterns = {"/registerAction"})
public class registerAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("r_name");
        String email = request.getParameter("email");
        String username1 = request.getParameter("username1");
        String password2 = request.getParameter("password2");
        UserManage saveNewUser = new UserManage();
        String[] nameAry = name.split(" ");

        if (saveNewUser.registerNewUser(username1, password2, nameAry[0].trim(),
                nameAry[1].trim(), email, new Date(new java.util.Date().getTime()))) {
            request.getSession().setAttribute("user", saveNewUser.getUser());
            saveNewUser.saveNotification(saveNewUser.getUser());
            saveNewUser.closeConnection();
            response.getWriter().write("Your account has been saved successfully!");

        } else {
            response.getWriter().write("Saving new account failed an internal server error!");
        }
    }

}
