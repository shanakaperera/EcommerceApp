/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.Rate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class PopularDeals {

    private Session s;

    public PopularDeals() {
        s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    public List<ProWithRate> getPopularDeals() {
        List<ProWithRate> pwr = new ArrayList<>();
        Set<Map.Entry<Integer, Double>> products = getProducts();
        for (Map.Entry<Integer, Double> product : products) {
            Product pro = (Product) s.createCriteria(Product.class, "product")
                    .createAlias("product.productHasSizes", "productHasSizes")
                    .createAlias("productHasSizes.size", "size")
                    .add(Restrictions.eq("product.availability", true))
                    .add(Restrictions.eq("product.id", product.getKey()))
                    .add(Restrictions.eq("size.id", 1)).uniqueResult();

            if (pro != null) {
                pwr.add(new ProWithRate(pro, product.getValue(), pro.getProductHasSizes().stream()
                        .filter(p -> p.getSize().getId() == 1).findFirst().get().getPrice()));
            }
        }
        Collections.sort(pwr, Collections.reverseOrder());
        return pwr;

    }

    public Set<Map.Entry<Integer, Double>> getProducts() {

        List<Rate> rateList = s.createCriteria(Rate.class).list();
        Set<Map.Entry<Integer, Double>> entrySet = rateList.stream()
                .collect(Collectors.groupingBy(r -> r.getProduct().getId(),
                                Collectors.summingDouble(r -> r.getRate())))
                .entrySet();
        return entrySet;

    }

    public void closeConnection() {
        s.close();
    }

}
