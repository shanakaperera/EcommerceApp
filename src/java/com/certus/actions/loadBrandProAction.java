/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Brand;
import com.certus.dbmodel.Product;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebServlet(name = "loadBrandProAction", urlPatterns = {"/loadBrandProAction"})
public class loadBrandProAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("bid") != null) {
            try {
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                String productsPath = (String) env.lookup("uploadpathproducts");
                int bid = Integer.parseInt(request.getParameter("bid"));
                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                Brand b = (Brand) s.load(Brand.class, bid);
                Set<Product> products = b.getProducts();
                String html = "";
                for (Product p : products) {
                    html += "<li class='media'>"
                            + "<a href='#' class='pull-left'>"
                            + "<img class='media-object' src='" + productsPath + p.getImageMain() + "' width='100'>"
                            + "</a>"
                            + "<div class='media-body'>"
                            + "<h4 class='media-heading'>" + p.getName() + "&nbsp;&nbsp;&nbsp;&nbsp;"
                           + "<button id='"+p.getId()+"' class='btn btn-danger'>Edit</button>"
                            + "</h4>"
                            + p.getDescription()
                            + "</div>"
                            + "</li>";
                }
                s.close();
                response.getWriter().write(html);
            } catch (NamingException ex) {
                Logger.getLogger(loadBrandProAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
