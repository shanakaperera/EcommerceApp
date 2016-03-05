/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import java.io.IOException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
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
@WebServlet(name = "loadCatProAction", urlPatterns = {"/loadCatProAction"})
public class loadCatProAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("cid") != null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                String productsPath = (String) env.lookup("uploadpathproducts");
                int cid = Integer.parseInt(request.getParameter("cid"));
                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                List<Product> products = s.createCriteria(Product.class, "pro")
                        .createAlias("pro.subCategory", "subCategory")
                        .createAlias("subCategory.category", "category")
                        .add(Restrictions.eq("category.id", cid)).list();
                String html = "";
                for (Product p : products) {
                    html += "<li class='media'>"
                            + "<a class='pull-left'>"
                            + "<img class='media-object' src='" + productsPath + p.getImageMain() + "' width='100'>"
                            + "</a>"
                            + "<div class='media-body'>"
                            + "<h4 class='media-heading'>" + p.getName() + "</h4>"
                            + p.getDescription()
                            + "</div>"
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
