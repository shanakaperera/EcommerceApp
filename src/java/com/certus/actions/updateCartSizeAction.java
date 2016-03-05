/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ShoppingCart;
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
@WebServlet(name = "updateCartSizeAction", urlPatterns = {"/updateCartSizeAction"})
public class updateCartSizeAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("sizeCombo") != null && request.getParameter("pro_id") != null
                && request.getParameter("sizePre") != null) {
            int pid = Integer.parseInt(request.getParameter("pro_id"));
            ShoppingCart cart = (ShoppingCart) request.getSession(false).getAttribute("cart");
            cart.updateProductSize(pid, request.getParameter("sizePre"), request.getParameter("sizeCombo"));
            response.sendRedirect("cart.jsp");
        }
    }

}
