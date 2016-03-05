/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.SingleItem;
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
@WebServlet(name = "checkSizeAvailabilityAction", urlPatterns = {"/checkSizeAvailabilityAction"})
public class checkSizeAvailabilityAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("pro_id") != null && request.getParameter("size") != null) {
            SingleItem item = new SingleItem();
            String element = "" + item.checkAvailability(Integer.parseInt(request.getParameter("pro_id")),
                    request.getParameter("size"));
            item.closeConnection();
            response.getWriter().write(element);
        }
    }

}
