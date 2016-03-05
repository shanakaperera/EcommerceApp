/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.dbmodel.SliderFacts;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
@WebServlet(name = "mobileSliderImagesAction", urlPatterns = {"/mobileSliderImagesAction"})
public class mobileSliderImagesAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sliderImgPath = "";
        try {
            Context envSlide = (Context) new InitialContext().lookup("java:comp/env");
            sliderImgPath = (String) envSlide.lookup("sliderImgs");
        } catch (NamingException ex) {
            Logger.getLogger(mobileSliderImagesAction.class.getName()).log(Level.SEVERE, null, ex);
        }

        Session sez = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<SliderFacts> sf = sez.createCriteria(SliderFacts.class).list();
        JsonArray array = new JsonArray();
        for (SliderFacts s : sf) {
            JsonObject jo = new JsonObject();
            jo.add("img", new JsonPrimitive(sliderImgPath + s.getImage()));
            array.add(jo);
        }
        String element = new Gson().toJson(array);
        response.getWriter().write(element);
    }

}
