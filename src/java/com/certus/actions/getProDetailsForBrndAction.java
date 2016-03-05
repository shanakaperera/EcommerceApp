/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ProductTypeAdapter;
import com.certus.controllers.ProductWithSizes;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@WebServlet(name = "getProDetailsForBrndAction", urlPatterns = {"/getProDetailsForBrndAction"})
public class getProDetailsForBrndAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        if (request.getParameter("pid") != null) {
            List<ProductHasSize> phs = s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "product")
                    .add(Restrictions.eq("product.id", Integer.parseInt(request.getParameter("pid"))))
                    .list();

            ProductWithSizes pws = new ProductWithSizes();
            Set<String> sizes = new HashSet<>();
            List<Integer> qnties = new ArrayList<>();
            Product p = null;
            for (ProductHasSize ph : phs) {
                sizes.add(ph.getSize().getSizeName());
                qnties.add(ph.getQnty());
                p = ph.getProduct();
            }
            pws.setProduct(p);
            pws.setSize(sizes);
            pws.setQnty(qnties);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Product.class, new ProductTypeAdapter()).create();
            String element = gson.toJson(pws);
            s.close();
            response.setContentType("application/json");
            response.getWriter().write("["+element+"]");

        }
    }

}
