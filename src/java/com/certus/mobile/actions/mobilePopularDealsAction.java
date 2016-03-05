/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.PopularDeals;
import com.certus.controllers.ProWithRate;
import com.certus.mobile.controllers.PopularDealsTypeAdapter;
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
@WebServlet(name = "mobilePopularDealsAction", urlPatterns = {"/mobilePopularDealsAction"})
public class mobilePopularDealsAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            PopularDeals pd = new PopularDeals();
            List<ProWithRate> pwr = pd.getPopularDeals();
            GsonBuilder gb = new GsonBuilder();
            Gson gson = gb.registerTypeAdapter(ProWithRate.class, new PopularDealsTypeAdapter()).create();
            String element = gson.toJson(pwr);
            pd.closeConnection();
            response.getWriter().write(element);
        
    }


}
