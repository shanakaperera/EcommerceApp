/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author shanaka
 */
public class ProductTypeAdapter implements JsonSerializer<Product> {

    @Override
    public JsonElement serialize(Product p, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        try {
     
            jsonObject.addProperty("name", p.getName());
            jsonObject.addProperty("id", p.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }

}
