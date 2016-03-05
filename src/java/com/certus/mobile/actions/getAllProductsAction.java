/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.CartItem;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
@WebServlet(name = "getAllProductsAction", urlPatterns = {"/getAllProductsAction"})
public class getAllProductsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        if (request.getParameter("cartList") != null) {
            String jsonList = request.getParameter("cartList");
            Type type = new TypeToken<List<CartItem>>() {
            }.getType();
            List<CartItem> conList = new Gson().fromJson(jsonList, type);
            List<ProductHasSize> myList = getCompleteProductCart(conList, s);

            String element = new Gson().toJson(myList, new TypeToken<List<ProductHasSize>>() {
            }.getType());
            response.getWriter().write(element);

        } else {
            JsonArray array = new JsonArray();
            JsonObject jo = new JsonObject();
            jo.add("error", new JsonPrimitive("Error response"));
            array.add(jo);
            String element = new Gson().toJson(array);
            response.getWriter().write(element);
        }

    }

    public List<ProductHasSize> getCompleteProductCart(List<CartItem> list, Session ses) {
        List<ProductHasSize> cartListOriginal = new ArrayList<>();

        for (CartItem crt : list) {
            ProductHasSize proHasSize = (ProductHasSize) ses.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.size", "size")
                    .createAlias("phs.product", "pro")
                    .add(Restrictions.eq("pro.id", crt.getProduct_id()))
                    .add(Restrictions.eq("size.sizeName", crt.getSize()))
                    .uniqueResult();
            cartListOriginal.add(proHasSize);
        }
        return cartListOriginal;
    }

}
