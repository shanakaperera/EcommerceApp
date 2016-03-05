/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.SingleItem;
import com.certus.dbmodel.User;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "reviewAction", urlPatterns = {"/reviewAction"})
public class reviewAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            User u = (User)request.getSession().getAttribute("user");
            String name = request.getParameter("name");
            int rate = Integer.parseInt(request.getParameter("rate"));
            String comment = request.getParameter("comment");
            int product_id = Integer.parseInt(request.getParameter("pro_id"));
            SingleItem item = new SingleItem();
            item.saveReview(u.getId(), product_id, comment, new java.sql.Date(new Date().getTime()));
            item.saveRate(u.getId(), product_id, rate);
            item.closeConnection();
            response.getWriter().write("<div class=\"item-review\">\n"
                    + "                <h5>" + name + " - <span class=\"color\">" + rate + "/5</span></h5>\n"
                    + "                <p class=\"rmeta\">" + new java.sql.Date(new Date().getTime()) + "</p>\n"
                    + "                <p>" + comment + "</p>\n"
                    + "            </div>");
        } else {
            response.getWriter().write("You have to login first to write any reviews.");
        }
    }

}
