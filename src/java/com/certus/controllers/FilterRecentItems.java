/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.ProductHasSize;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
public class FilterRecentItems {

    private final Session session;

    public FilterRecentItems() {
        this.session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

    }

    public Set<Integer> getRecentItems() {
        List<Order> oList = session.createCriteria(Order.class).list();
        String products = "";
        String productsAry[] = null;
        for (Order order : oList) {
            products += order.getProductIds() + ",";
        }
        productsAry = removeLastChar(products).split(",");
        Set<Integer> proSet = new HashSet<>();
        for (int i = 0; i < productsAry.length; i++) {
            proSet.add(Integer.parseInt(productsAry[i].trim()));

        }
        return proSet.stream().limit(8).collect(Collectors.toSet());

    }

    public String removeLastChar(String word) {
        return word.substring(0, word.length() - 1);
    }

    public double getPrice(Set<ProductHasSize> phs) {
        return phs.stream().min(Comparator.comparing(p -> p.getPrice())).get().getPrice();
    }

}
