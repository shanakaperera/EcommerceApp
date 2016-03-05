/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Category;
import com.certus.dbmodel.SubCategory;
import java.io.IOException;
import java.util.Set;
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
@WebServlet(name = "loadCatSubAction", urlPatterns = {"/loadCatSubAction"})
public class loadCatSubAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("cid") != null) {
            try {
                int cid = Integer.parseInt(request.getParameter("cid"));
                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

                Category cat = (Category) s.load(Category.class, cid);
                Set<SubCategory> subCategories = cat.getSubCategories();
                String html = "";
                for (SubCategory su : subCategories) {
                    html += "<li class='media'>"
                            + "<h4 class='media-heading'>" + su.getSubCategoryName().substring(0,1).toUpperCase()+ su.getSubCategoryName().substring(1)+ "</h4>"
                            + "</li>";
                }
                s.close();
                response.getWriter().write(html);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
