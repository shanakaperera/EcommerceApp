/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author shanaka
 */
public class ProductWithSizes  implements Serializable{
    private Product product;
    private Set<String> size;
    private List<Integer> qnty;

    public ProductWithSizes() {
    }

    public ProductWithSizes(Product product, Set<String> size) {
        this.product = product;
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<String> getSize() {
        return size;
    }

    public void setSize(Set<String> size) {
        this.size = size;
    }

    public List<Integer> getQnty() {
        return qnty;
    }

    public void setQnty(List<Integer> qnty) {
        this.qnty = qnty;
    }

  
    
    
}
