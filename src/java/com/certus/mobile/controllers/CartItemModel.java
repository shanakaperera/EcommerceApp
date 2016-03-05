/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.controllers;

import java.io.Serializable;


/**
 *
 * @author shanaka
 */
public class CartItemModel implements Serializable{
    
    private int p_id;
    private String p_name;
    private double p_price;
    private String p_size;
    private String p_img;
    private int p_qnty;

    public CartItemModel() {
    }

    public CartItemModel(int p_id, String p_name, double p_price, String p_size, String p_img, int p_qnty) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_size = p_size;
        this.p_img = p_img;
        this.p_qnty = p_qnty;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getP_price() {
        return p_price;
    }

    public void setP_price(double p_price) {
        this.p_price = p_price;
    }

    public String getP_size() {
        return p_size;
    }

    public void setP_size(String p_size) {
        this.p_size = p_size;
    }

    public String getP_img() {
        return p_img;
    }

    public void setP_img(String p_img) {
        this.p_img = p_img;
    }

    public int getP_qnty() {
        return p_qnty;
    }

    public void setP_qnty(int p_qnty) {
        this.p_qnty = p_qnty;
    }
    
}
