/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import com.certus.dbmodel.ProductHasSize;
import com.certus.dbmodel.Review;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author shanaka
 */
public class SingleItemTypeAdapter implements JsonSerializer<ProductHasSize> {

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
        jo.add("price", new JsonPrimitive(phs.getPrice()));
        jo.add("disc_per", new JsonPrimitive(phs.getProduct().getDiscountPrice()));
        jo.add("brand", new JsonPrimitive(phs.getProduct().getBrand().getBrandName()));
        jo.add("desc", new JsonPrimitive(phs.getProduct().getDescription()));
        jo.add("img", new JsonPrimitive(path + phs.getProduct().getImageMain()));
        JsonArray sizesAry = new JsonArray();
        JsonArray pricesAry = new JsonArray();
        JsonArray qntyAry = new JsonArray();
        for (ProductHasSize pp : phs.getProduct().getProductHasSizes()) {
            sizesAry.add(new JsonPrimitive(pp.getSize().getSizeName()));
            pricesAry.add(new JsonPrimitive(pp.getPrice()));
            qntyAry.add(new JsonPrimitive(pp.getQnty()));
        }
        jo.add("sizes", sizesAry);
        jo.add("prices", pricesAry);
        jo.add("avl_qnty", qntyAry);
        JsonArray reviewsAry = new JsonArray();
        for (Review r : phs.getProduct().getReviews()) {
            JsonObject reviews = new JsonObject();
            reviews.add("comment", new JsonPrimitive(r.getComment()));
            reviews.add("date", new JsonPrimitive(new SimpleDateFormat("yyyy-MM-dd").format(r.getDateComnt())));
            reviews.add("user", new JsonPrimitive(r.getUser().getFName() + " " + r.getUser().getLName()));
            reviewsAry.add(reviews);
        }
        jo.add("reviews", reviewsAry);
        return jo;
    }

}
