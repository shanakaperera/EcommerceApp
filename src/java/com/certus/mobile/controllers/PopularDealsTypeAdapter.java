/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import com.certus.controllers.ProWithRate;
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
public class PopularDealsTypeAdapter implements JsonSerializer<ProWithRate> {

    @Override
    public JsonElement serialize(ProWithRate pwr, Type type, JsonSerializationContext jsc) {
        String path = "";
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            path += (String) env1.lookup("uploadpathproducts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonObject jo = new JsonObject();
        if (pwr.getProduct().isAvailability()) {
            
            jo.add("pid", new JsonPrimitive(pwr.getProduct().getId()));
            jo.add("cat_id", new JsonPrimitive(pwr.getProduct().getSubCategory().getCategory().getId()));
            jo.add("subcat_id", new JsonPrimitive(pwr.getProduct().getSubCategory().getId()));
            jo.add("disc_per", new JsonPrimitive(pwr.getProduct().getDiscountPrice()));
            jo.add("image", new JsonPrimitive(path + pwr.getProduct().getImageMain()));
            jo.add("price", new JsonPrimitive(pwr.getPrice()));
            jo.add("p_name", new JsonPrimitive(pwr.getProduct().getName()));
            jo.add("brand", new JsonPrimitive(pwr.getProduct().getBrand().getBrandName()));
          
        }
        return jo;
    }

}
