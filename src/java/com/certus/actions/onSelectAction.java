/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ProductHasSizeTypeAdapter;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "onSelectAction", urlPatterns = {"/onSelectAction"})
public class onSelectAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<ProductHasSize> phsList = s.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product")
                .add(Restrictions.eq("product.id", Integer.parseInt(request.getParameter("pid"))))
                .list();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(ProductHasSize.class, new ProductHasSizeTypeAdapter()).create();
        String element = gson.toJson(phsList);
        s.close();
        response.getWriter().write(element);

    }

}
