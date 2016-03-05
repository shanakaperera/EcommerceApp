/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.ProductHasSize;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author shanaka
 */
public class ProductFilterTypeAdapter implements JsonSerializer<ProductHasSize> {

    int i = 0;

    @Override
    public JsonElement serialize(ProductHasSize phs, Type type, JsonSerializationContext jsc) {
        i++;
        String productsPath1 = null;
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            productsPath1 = (String) env1.lookup("uploadpathproducts");
        } catch (NamingException ex) {
            Logger.getLogger(ProductFilterTypeAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonObject jo = new JsonObject();
        jo.add("text", new JsonPrimitive(phs.getProduct().getName()));
        jo.add("value", new JsonPrimitive(i));
        jo.add("selected", i == 1 ? new JsonPrimitive(Boolean.TRUE) : new JsonPrimitive(Boolean.FALSE));
        jo.add("description", new JsonPrimitive(phs.getProduct().getBrand().getBrandName()));
        jo.add("imageSrc", new JsonPrimitive(productsPath1 + phs.getProduct().getImageMain()));
        return jo;
    }

}
