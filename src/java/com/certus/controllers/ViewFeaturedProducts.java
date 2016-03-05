/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Product;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class ViewFeaturedProducts {

    private Session s;

    public ViewFeaturedProducts() {
        this.s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    //admin view
    public List viewFeatured(int pid) {
         Product p = (Product) s.load(Product.class, pid);
        

        if (p.getFeaturedItems() != null && !p.getFeaturedItems().isEmpty()) {
            String array[] = removeLastChar(p.getFeaturedItems()).split(",");
            List<Product> proList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                String a = array[i];
                Product pGet = (Product) s.load(Product.class, Integer.parseInt(a));
                proList.add(pGet);
               

            }
            return proList;
        } else {
            return null;
        }
    }

    // fontstore view
    public List viewFeatured(int sub_id, int cat_id) {
        List<Product> proList = s.createCriteria(Product.class, "pro")
                .createAlias("pro.subCategory", "subCategory")
                .createAlias("subCategory.category", "category")
                .add(Restrictions.eq("subCategory.id", sub_id))
                .add(Restrictions.eq("category.id", cat_id))
                .add(Restrictions.eq("pro.availability", Boolean.TRUE))
                .list();
        if (!proList.isEmpty()) {
            StringBuilder sb = new StringBuilder();

            for (Product product : proList) {
                if (product.getFeaturedItems() != null
                        && !product.getFeaturedItems().isEmpty()) {
                    sb.append(product.getFeaturedItems());
                }
            }
            String pro_ids = removeLastChar(sb.toString());
            String[] pro_ids_asArray = pro_ids.split(",");
            // System.out.println(Arrays.asList(pro_ids_asArray));
            int ary[] = shuffleList(pro_ids_asArray).parallelStream()
                    .mapToInt(i -> i == null ? -1 : i).toArray();
            List<Product> productLst = new ArrayList<>();
            for (int i = 0; i < ary.length; i++) {
                int pid = ary[i];
                Product p = (Product) s.load(Product.class, pid);

                if (p.isAvailability()) {
                    productLst.add(p);
                }

            }
            return productLst;
        } else {
            return null;
        }
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length() - 1);
    }

    public List<Integer> shuffleList(String[] ary) {
        List<Integer> solution = new ArrayList<>();
        for (int i = 0; i < ary.length; i++) {
            if (!ary[i].equals("")) {
                solution.add(Integer.parseInt(ary[i].trim()));
            }
        }
        Collections.shuffle(solution);
        Set<Integer> noDups = new HashSet<>();
        noDups.addAll(solution);
        return new ArrayList<>(noDups);
    }

    public void closeConnection() {
        s.close();
    }
}
