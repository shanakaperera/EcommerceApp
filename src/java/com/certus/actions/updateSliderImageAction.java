/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.SliderFacts;
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
@WebServlet(name = "updateSliderImageAction", urlPatterns = {"/updateSliderImageAction"})
public class updateSliderImageAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("pName") != null && request.getParameter("desc") != null
                && request.getParameter("img") != null && request.getParameter("sid") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            String imgName=request.getParameter("img");
            Product p = (Product) s.createCriteria(Product.class, "p")
                    .add(Restrictions.eq("p.name", request.getParameter("pName").trim()))
                    .uniqueResult();

            SliderFacts slf = (SliderFacts) s.load(SliderFacts.class, Integer.parseInt(request.getParameter("sid")));
            s.beginTransaction();
            slf.setDesc(request.getParameter("desc"));
            slf.setImage(imgName.substring(imgName.lastIndexOf("/")));
            slf.setProduct(p);
            s.update(slf);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("success");

        }
    }

}
