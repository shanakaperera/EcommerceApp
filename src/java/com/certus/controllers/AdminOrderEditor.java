/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class AdminOrderEditor {

    private Order order;
    private Session s;

    public AdminOrderEditor(Order order, Session s) {
        this.order = order;
        this.s = s;
    }

    public Product[] getOrderProducts() {

        String arry[] = order.getProductIds().split(",");
        Product[] products = new Product[arry.length];
        for (int i = 0; i < arry.length; i++) {
            int proId = Integer.parseInt(arry[i].trim());
            Product p = (Product) s.load(Product.class, proId);
            products[i] = p;
        }
        return products;
    }

    public int[] getOrderQntities() {
        return Arrays.asList(order.getQuantities().split(","))
                .stream().mapToInt(Integer::parseInt).toArray();

    }

    public String[] getOrderSizes() {
        return order.getSizes().split(",");
    }

    public List<OrderInfo> getOrder() {
        Product[] pros = getOrderProducts();
        int[] qntities = getOrderQntities();
        String[] sizes = getOrderSizes();
        List<OrderInfo> orderInf = new ArrayList<>();
        for (int i = 0; i < pros.length; i++) {
            ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "product")
                    .createAlias("phs.size", "size")
                    .add(Restrictions.eq("size.sizeName", sizes[i].trim()))
                    .add(Restrictions.eq("product.id", pros[i].getId()))
                    .uniqueResult();
            orderInf.add(new OrderInfo(phs, qntities[i]));
        }

        return orderInf;
    }



}
