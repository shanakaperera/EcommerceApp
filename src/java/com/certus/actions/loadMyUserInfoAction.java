/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.UserTypeAdapter;
import com.certus.dbmodel.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "loadMyUserInfoAction", urlPatterns = {"/loadMyUserInfoAction"})
public class loadMyUserInfoAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        User u =(User) s.createCriteria(User.class,"user")
                .add(Restrictions.eq("user.id", Integer.parseInt(request.getParameter("uid"))))
                .uniqueResult();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserTypeAdapter()).create();
        String element = gson.toJson(u);
        s.close();
        response.setContentType("application/json");
        response.getWriter().write(element);
    }

}
