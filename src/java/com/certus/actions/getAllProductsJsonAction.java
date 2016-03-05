/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ProductFilterTypeAdapter;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "getAllProductsJsonAction", urlPatterns = {"/getAllProductsJsonAction"})
public class getAllProductsJsonAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

        Criteria cr = s.createCriteria(ProductHasSize.class, "phs");
        cr.createAlias("phs.product", "product")
                .createAlias("phs.size", "size")
                .createAlias("product.brand", "brand")
                .add(Restrictions.eq("product.availability", true))
                .add(Restrictions.eq("brand.availability", true))
                .setProjection(Projections.groupProperty("product"));

        List<Product> pros = cr.list();
        List<ProductHasSize> newPros = new ArrayList<>();
        for (Product pro : pros) {
            ProductHasSize ph = productDetails(pro, s);
            newPros.add(ph);
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.registerTypeAdapter(ProductHasSize.class, new ProductFilterTypeAdapter()).create();
        String element = gson.toJson(newPros);
        s.close();
        response.getWriter().write(element);

    }

    private ProductHasSize productDetails(Product pro, Session s) {
        return (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                .add(Restrictions.eq("phs.product", pro))
                .addOrder(Order.asc("phs.price"))
                .setMaxResults(1)
                .uniqueResult();
    }

}
