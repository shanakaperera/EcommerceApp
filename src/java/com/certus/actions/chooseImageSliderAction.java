/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ImageFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
@WebServlet(name = "chooseImageSliderAction", urlPatterns = {"/chooseImageSliderAction"})
public class chooseImageSliderAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("img/slider").replace("build/", "");
        String html = "";
        String sliderImgPath = null;
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            sliderImgPath = (String) env1.lookup("sliderImgs");
        } catch (Exception e) {
            e.printStackTrace();
        }
        final File dir = new File(path);
        if (dir.isDirectory()) {
            for (final File f : dir.listFiles(ImageFilter.IMAGE_FILTER)) {
                BufferedImage img = null;

                try {
                    img = ImageIO.read(f);

                    html += "<div class='col-xs-6 col-md-3' data-pg-collapsed>"
                            + "<a href='#' class='thumbnail'>"
                            + "<img src='" + sliderImgPath + f.getName() + "' width='200' onclick='imgClicked(this);'>"
                            + "</a>"
                            + "</div>";
                   
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        

        response.getWriter().write(html);

    }

}
