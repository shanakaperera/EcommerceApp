/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import com.certus.controllers.CartItem;
import com.certus.controllers.ShoppingCart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author shanaka
 */
public class CartModelAdapter implements JsonSerializer<CartModel> {

    private CopyOnWriteArrayList<CartItem> cartItems;
    private ShoppingCart cart;
    private String path;

    public CartModelAdapter(ShoppingCart cart, String path) {
        this.cart = cart;
        this.cartItems = cart.getShoppingList();
        this.path = path;
    }

    @Override
    public JsonElement serialize(CartModel model, Type type, JsonSerializationContext jsc) {
        List<CartItemModel> cartItemModels = new ArrayList<>();
        List<Double> tot = new ArrayList<>();

        JsonObject jo = new JsonObject();

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
        JsonArray array = (JsonArray) new Gson().toJsonTree(cartItemModels,
                new TypeToken<List<CartItemModel>>() {
                }.getType());
        jo.add("cart_total", new JsonPrimitive(cart.grandTotal(tot)));
        jo.add("total_items", new JsonPrimitive(cart.getTotalItemsOfTheCart()));
        jo.add("items", array);
//        model.setCart_total(cart.grandTotal(tot));
//        model.setTotal_items(cart.getTotalItemsOfTheCart());
//        model.setItems(cartItemModels);
        return jo;
    }

}
