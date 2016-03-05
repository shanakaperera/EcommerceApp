/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

/**
 *
 * @author shanaka
 */
public class ChartsCriteriaAdapter implements JsonSerializer<ChartsCriteria> {

    @Override
    public JsonElement serialize(ChartsCriteria sale, Type type, JsonSerializationContext jsc) {
        JsonObject obj = new JsonObject();
        obj.add("day", new JsonPrimitive(sale.getDay()));
        obj.add("amount", new JsonPrimitive(sale.getAmount()));
        return obj;
    }

}
