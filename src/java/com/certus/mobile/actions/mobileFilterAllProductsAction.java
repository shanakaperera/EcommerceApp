/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.ItemPage;
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

/**
 *
 * @author shanaka
 */
@WebServlet(name = "mobileFilterAllProductsAction", urlPatterns = {"/mobileFilterAllProductsAction"})
public class mobileFilterAllProductsAction extends HttpServlet {//mobileFilterAllProductsAction?sub_name=tops&cat=2

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemPage ip = new ItemPage();
        ip.filterProducts(request.getParameter("sub_name"), Integer.parseInt(request.getParameter("cat")), 0, 10);
        List<ProductHasSize> phs = ip.getProList();
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.registerTypeAdapter(ProductHasSize.class,
                new com.certus.mobile.controllers.FilterProductsAdapter()).create();
        String element = gson.toJson(phs);
        response.getWriter().write(element);

    }

}
