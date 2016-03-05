/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.dbmodel;

import java.io.Serializable;

/**
 *
 * @author shanaka
 */
public class SliderFacts implements Serializable {

    private Integer id;
    private String image;
    private String desc;
    private Product product;

    public SliderFacts() {
    }

    
    public SliderFacts(Integer id, String image, String desc, Product product) {
        this.id = id;
        this.image = image;
        this.desc = desc;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
}
