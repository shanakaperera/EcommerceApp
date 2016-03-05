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
@WebServlet(name = "updatePropertyAction", urlPatterns = {"/updatePropertyAction"})
public class updatePropertyAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("prop") != null && request.getParameter("desc") != null) {
            String propNew = request.getParameter("prop");
            String descNew = request.getParameter("desc");
            String propPre = request.getParameter("pre_prop");
            String descPre = request.getParameter("pre_desc");
            int pid = Integer.parseInt(request.getParameter("pid"));
            int sid = Integer.parseInt(request.getParameter("sid"));

            if (request.getParameter("updateBtn") != null) {
                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                Product p = (Product) s.load(Product.class, pid);
                String html = "<table>"
                        + p.getSpecs()
                        + "</table>";
                Document doc = Jsoup.parse(html);
                Elements e = doc.select("td > strong:contains(" + propPre + ")");
                e.html(e.html().replaceAll(propPre, propNew));
                Elements e1 = doc.select("td:contains(" + descPre.replace("`", "\"") + ")");
                e1.html(e1.html().replaceAll(descPre.replace("`", "\""), descNew.replace("`", "\"")));
                p.setSpecs(doc.body().select("table").html());
                s.update(p);
                s.getTransaction().commit();
                s.close();
                response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
                
            } else if (request.getParameter("removeBtn") != null) {
                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                s.beginTransaction();
                Product p = (Product) s.load(Product.class, pid);
                String html = "<table>"
                        + p.getSpecs()
                        + "</table>";
                Document doc = Jsoup.parse(html);
                
                Elements e = doc.select("td:contains(" + descPre.replace("`", "\"") + ")");
                e.parents().first().remove();
                p.setSpecs(doc.body().select("table").html());
                s.update(p);
                s.getTransaction().commit();
                s.close();
                response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
            }
        }
    }

}
