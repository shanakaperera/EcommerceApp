/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import com.certus.dbmodel.ProductHasSize;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author shanaka
 */
public class MobileProductListAdapter implements JsonSerializer<ProductHasSize> {

    @Override
    public JsonElement serialize(ProductHasSize phs, Type type, JsonSerializationContext jsc) {
        JsonObject jo = new JsonObject();
        jo.add("pid", jo);
        return jo;
    }

}
