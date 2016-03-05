/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Category;
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
@WebServlet(name = "AddCategoryAction", urlPatterns = {"/AddCategoryAction"})
public class AddCategoryAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("status")) {
            case "save":
                if (!request.getParameter("cat_name").equals("")) {
                    Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Category category = new Category(request.getParameter("cat_name"),
                            Integer.parseInt(request.getParameter("aval")) != 0);
                    session.save(category);
                    session.getTransaction().commit();
                    session.close();
                } else {
                    response.getWriter().write("Category Name is missing !");
                }   break;
            case "update":
                if (!request.getParameter("cat_name").equals("")) {
                    Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Category category = (Category) session.load(Category.class, Integer.parseInt(request.getParameter("cat_id")));
                    category.setCatName(request.getParameter("cat_name").toLowerCase());
                    category.setAvailability(Integer.parseInt(request.getParameter("aval")) != 0);
                    session.update(category);
                    session.getTransaction().commit();
                    session.close();
                } else {
                    response.getWriter().write("Category Name is missing !");
            }   break;
        }
    }
}
