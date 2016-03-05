/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

/**
 *
 * @author shanaka
 */
public class CartItem {

    private int product_id;
    private int qnty;
    private String size;

    public CartItem(int product_id, int qnty, String size) {
        this.product_id = product_id;
        this.qnty = qnty;
        this.size = size;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    boolean isSameCartItem(CartItem item) {
        return this.getProduct_id() == item.getProduct_id() && this.getSize().equals(item.getSize());
    }

}
