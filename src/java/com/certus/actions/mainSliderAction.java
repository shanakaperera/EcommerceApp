/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.SliderFacts;
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
@WebServlet(name = "mainSliderAction", urlPatterns = {"/mainSliderAction"})
public class mainSliderAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        String sliderImgPath = "";
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            sliderImgPath = (String) env1.lookup("sliderImgs");
        } catch (NamingException ex) {
            Logger.getLogger(mainSliderAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<SliderFacts> sfs = s.createCriteria(SliderFacts.class).list();
        int i = 0;
        for (SliderFacts sf : sfs) {
            i++;
            html += "<li class='media cusBoder'>"
                    + "<a class='pull-left'>"
                    + "<img class='media-object' id='img-" + i + "' src='" + sliderImgPath + sf.getImage() + "' width='200'>"
                    + "<button type='button' class='btn btn-default pull-right' data-dismiss='modal' onclick='replaceImgClk(" + i + ");' data-target='#imgeSliderModal' data-toggle='modal'>Replace</button>"
                    + "</a>"
                    + "<div class='media-body'>"
                    + "<div class='form-group'>"
                    + "<label class='control-label' for='formInput9'>Change Selected Product</label>"
                    + "<input type='hidden' id='selPro-" + i + "' value='" + sf.getProduct().getName() + "'/>"
                    + "<input type='hidden' id='sliderId-" + i + "' value='" + sf.getId() + "'/>"
                    + "<div id='proSelect-" + i + "'></div>"
                    + "</div>"
                    + "<div class='form-group'>"
                    + "<label class='control-label' for='formInput15'>Description</label>"
                    + "<textarea class='form-control' rows='3' id='sliderDesc-" + i + "'>" + sf.getDesc() + "</textarea>"
                    + "</div>"
                    + "</div>"
                    + "<div class='btn-group pull-right leaveLine'>"
                    + "<button type='button' class='btn btn-default' onclick='saveSlide(" + i + ");'>Save Changes</button>"
                    + "<button type='button' class='btn btn-default'>Delete</button>"
                    + "</div>"
                    + "</li>";
        }
        s.close();
        response.setContentType("application/json");
        response.getWriter().write("[{\"d1\":\"" + html + "\",\"d2\":\"" + i + "\"}]");
    }

}
