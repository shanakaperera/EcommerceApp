/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.controllers.CartItem;
import com.certus.controllers.ShoppingCart;
import com.certus.mobile.controllers.CartItemModel;
import com.certus.mobile.controllers.CartModel;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "mobileGetCartDetailsAction", urlPatterns = {"/mobileGetCartDetailsAction"})
public class mobileGetCartDetailsAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession(false).getAttribute("cart") != null) {
            String path = "";
            try {
                Context env1 = (Context) new InitialContext().lookup("java:comp/env");
                path += (String) env1.lookup("uploadpathproducts");
            } catch (Exception e) {
                e.printStackTrace();
            }
            ShoppingCart cart = (ShoppingCart) request.getSession(false).getAttribute("cart");
            CopyOnWriteArrayList<CartItem> cartItems = cart.getShoppingList();
            List<Double> tot = new ArrayList<>();
            CartModel model = new CartModel();
            List<CartItemModel> cartItemModels = new ArrayList<>();
            for (CartItem item : cartItems) {

                CartItemModel itemModel = new CartItemModel(item.getProduct_id(),
                        cart.getNameofProduct(item.getProduct_id()),
                        cart.getPriceofProduct(item.getProduct_id(), item.getSize()),
                        item.getSize(),
                        path + cart.getImageofProduct(item.getProduct_id()),
                        item.getQnty());
                cartItemModels.add(itemModel);
                tot.add(cart.getPriceofProduct(item.getProduct_id(), item.getSize()) * item.getQnty());

            }
            model.setCart_total(cart.grandTotal(tot));
            model.setTotal_items(cart.getTotalItemsOfTheCart());
            model.setItems(cartItemModels);
            Type type = new TypeToken<CartModel>() {
            }.getType();
            String element = new Gson().toJson(model, type);
            response.setContentType("application/json");
            response.getWriter().write(element);
        } else {
            response.getWriter().write("Error Session");
        }
    }

}
