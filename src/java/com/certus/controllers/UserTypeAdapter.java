/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.User;
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
public class UserTypeAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User u, Type type, JsonSerializationContext jsc) {
        JsonObject object = new JsonObject();
        try {
            object.add("name", new JsonPrimitive(u.getFName()+" "+u.getLName()));
            object.add("email", new JsonPrimitive(u.getEmail()));
            object.add("tel", new JsonPrimitive(u.getTelephone()));
            object.add("address", new JsonPrimitive(u.getAddress()));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

}
