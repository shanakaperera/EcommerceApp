/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import com.certus.dbmodel.ProductHasSize;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author shanaka
 */
public class FilterProductsAdapter implements JsonSerializer<ProductHasSize> {

    @Override
    public JsonElement serialize(ProductHasSize phs, Type type, JsonSerializationContext jsc) {

        String path = "";
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            path += (String) env1.lookup("uploadpathproducts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject jo = new JsonObject();
        jo.add("pid", new JsonPrimitive(phs.getProduct().getId()));
        jo.add("name", new JsonPrimitive(phs.getProduct().getName()));
        jo.add("brand", new JsonPrimitive(phs.getProduct().getBrand().getBrandName()));
        jo.add("price", new JsonPrimitive(phs.getPrice()));
        jo.add("dic_per", new JsonPrimitive(phs.getProduct().getDiscountPrice()));
        jo.add("size", new JsonPrimitive(phs.getSize().getSizeName()));
        jo.add("img", new JsonPrimitive(path + phs.getProduct().getImageMain()));
        return jo;
    }

}
