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
@WebServlet(name = "removeProductAction", urlPatterns = {"/removeProductAction"})
public class removeProductAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     if (request.getSession(false).getAttribute("cart") != null) {
            ShoppingCart cart = (ShoppingCart) request.getSession(false).getAttribute("cart");
            cart.removeItem(Integer.parseInt(request.getParameter("pdtId")),
                    request.getParameter("size"));
            response.getWriter().write("Removed Successfully !");
        }
    }



}
