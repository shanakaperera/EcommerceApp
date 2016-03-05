/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.ProductHasSize;
import java.io.Serializable;

/**
 *
 * @author shanaka
 */
public class OrderInfo implements Serializable{
    private ProductHasSize phs;
    private int qnty;

    public OrderInfo(ProductHasSize phs, int qnty) {
        this.phs = phs;
        this.qnty = qnty;
    }

    public ProductHasSize getPhs() {
        return phs;
    }

    public void setPhs(ProductHasSize phs) {
        this.phs = phs;
    }

    public int getQnty() {
        return qnty;
    }

    public void setQnty(int qnty) {
        this.qnty = qnty;
    }
    
}
