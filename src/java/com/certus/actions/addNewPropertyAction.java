/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "addNewPropertyAction", urlPatterns = {"/addNewPropertyAction"})
public class addNewPropertyAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("propName") != null
                && request.getParameter("descrip") != null && request.getParameter("pid") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            s.beginTransaction();
            Product p = (Product) s.load(Product.class, Integer.parseInt(request.getParameter("pid")));
            String html = "<table>" + p.getSpecs() + "</table>";
            Document doc = Jsoup.parse(html);
            Elements elements = doc.select("tbody");

            elements.last().append("<tr><td><strong>" + request.getParameter(
                    "propName") + "</strong></td><td>" + request.getParameter("descrip") + "</td></tr>");
            p.setSpecs(doc.body().select("table").html());
            s.update(p);
            s.getTransaction().commit();
            s.close();
           response.sendRedirect("edit_product.jsp?pid=" + request.getParameter("pid") + "&sid=" + request.getParameter("sid")); 

        }

    }
}
