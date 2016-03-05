/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import java.util.Set;

/**
 *
 * @author shanaka
 */
public class ProWithRate implements Comparable<ProWithRate> {

    private Product product;
    private double rates;
    private double price;

    public ProWithRate(Product product, double rates, double price) {
        this.product = product;
        this.rates = rates;
        this.price = price;
    }

    public ProWithRate() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getRates() {
        return rates;
    }

    public void setRates(double rates) {
        this.rates = rates;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice(double price, int percentage) {
        return (int) (price - (price * (percentage / 100.0f)));
    }

    @Override
    public int compareTo(ProWithRate o) {
        return (int) (this.rates - o.rates);
    }

    public String getSize(Set<ProductHasSize> phs, int pid) {
        return phs.stream().filter(p -> p.getProduct().getId() == pid)
                .findFirst().get().getSize().getSizeName();
    }

}
