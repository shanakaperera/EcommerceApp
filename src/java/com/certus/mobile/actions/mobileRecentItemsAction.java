/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.FilterRecentItems;
import com.certus.dbmodel.Product;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import javax.naming.Context;
import javax.naming.InitialContext;
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
@WebServlet(name = "mobileRecentItemsAction", urlPatterns = {"/mobileRecentItemsAction"})
public class mobileRecentItemsAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session sess = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        FilterRecentItems im = new FilterRecentItems();
        String path = "";
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            path += (String) env1.lookup("uploadpathproducts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonArray ary = new JsonArray();
        for (Integer i : im.getRecentItems()) {
            Product pr = (Product) sess.load(Product.class, i);
            if (pr.isAvailability()) {
                JsonObject jo = new JsonObject();
                jo.add("pid", new JsonPrimitive(pr.getId()));
                jo.add("p_name", new JsonPrimitive(pr.getName()));
                jo.add("cat_id", new JsonPrimitive(pr.getSubCategory().getCategory().getId()));
                jo.add("subcat_id", new JsonPrimitive(pr.getSubCategory().getId()));
                jo.add("disc_Per", new JsonPrimitive(pr.getDiscountPrice()));
                jo.add("brand", new JsonPrimitive(pr.getBrand().getBrandName()));
                jo.add("image", new JsonPrimitive(path + pr.getImageMain()));
                ary.add(jo);
              //  Product pl = new Gson().fromJson(jo, Product.class);
            }
        }
        String element = new Gson().toJson(ary);
        sess.close();
        response.getWriter().write(element);

    }

}
