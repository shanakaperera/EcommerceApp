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
public class ChartExpenseAdapter implements JsonSerializer<ChartExpenses> {

    @Override
    public JsonElement serialize(ChartExpenses exp, Type type, JsonSerializationContext jsc) {
        JsonObject jo = new JsonObject();
        jo.add("day", new JsonPrimitive(exp.getDay()));
        jo.add("sale", new JsonPrimitive(exp.getAmountSale()));
        jo.add("expense", new JsonPrimitive(exp.getAmountExpense()));
        return jo;
    }

}
