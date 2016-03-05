/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.AdminManage;
import com.certus.dbmodel.Administor;
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
@WebServlet(name = "adminLoginAction", urlPatterns = {"/adminLoginAction"})
public class adminLoginAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("user_name") != null && request.getParameter("password") != null) {
            String user_name = request.getParameter("user_name");
            String password = request.getParameter("password");
            AdminManage manageAdmin = new AdminManage();
            if (!user_name.isEmpty() && !password.isEmpty()) {
                if (manageAdmin.loginValidated(user_name, password)) {
                    if (request.getSession().getAttribute("admin") != null) {
                        Administor admin = (Administor) request.getSession().getAttribute("admin");
                        response.getWriter().write(admin.getFName());
                    } else {
                        request.getSession().setAttribute("admin", manageAdmin.getAdmin());
                        response.getWriter().write(manageAdmin.getAdmin().getFName());
                    }
                } else {
                    response.setStatus(500);
                }
            } else {
                response.setStatus(500);
            }

        }

    }

}
