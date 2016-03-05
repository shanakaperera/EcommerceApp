/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

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
public class ProductHasSizeTypeAdapter implements JsonSerializer<ProductHasSize> {

    @Override
    public JsonElement serialize(ProductHasSize p, Type type, JsonSerializationContext jsc) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("size_id", p.getSize().getId());
        jsonObject.addProperty("size_name", p.getSize().getSizeName());
        jsonObject.addProperty("cat", p.getProduct().getSubCategory().getCategory().getId());
        jsonObject.addProperty("sub_cat", p.getProduct().getSubCategory().getId());
        jsonObject.addProperty("bnd_id", p.getProduct().getBrand().getId());
        return jsonObject;
    }

}
