/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.SingleItem;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "mobileSingleItemAction", urlPatterns = {"/mobileSingleItemAction"})
public class mobileSingleItemAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SingleItem item = new SingleItem();
        ProductHasSize productDetials = item.getProductDetials(Integer.parseInt(request.getParameter("id")));
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.registerTypeAdapter(ProductHasSize.class,
                new com.certus.mobile.controllers.SingleItemTypeAdapter()).create();
        String element = gson.toJson(productDetials);
        response.getWriter().write(element);
    }

}
