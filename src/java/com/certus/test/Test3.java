/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.test;

import com.certus.controllers.ProductTypeAdapter;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class Test3 {

    public static void main(String[] args) {
        //    Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
//        List<Product> proList = s.createCriteria(Product.class).list();
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.registerTypeAdapter(Product.class, new CustomTypeAdapter()).create();
//        String element = gson.toJson(proList);
//        System.out.println(element);

//        A a = new A();
//        a.setAge("23");
//        a.setName("aaa");
//        a.setSchool("bbb college");
//        try {
//            Map<String, String> map = BeanUtils.describe(a);
//
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                System.out.println(entry.getKey() + "/" + entry.getValue());
//            }
//
//            Field[] allFields = a.getClass().getFields();
//            for (Field field : allFields) {
//                System.out.println(field.getName());
//            }
//
//        } catch (Exception e) {
//        }
        String pNameMatch = "top";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Criteria cr = s.createCriteria(ProductHasSize.class, "phs");
        cr.createAlias("phs.product", "product")
                .createAlias("phs.size", "size")
                .createAlias("product.brand", "brand")
                .add(Restrictions.eq("product.availability", true))
                .add(Restrictions.eq("brand.availability", true))
                .add(Restrictions.like("product.name", pNameMatch, MatchMode.ANYWHERE))
                .setProjection(Projections.groupProperty("product"));

        List<Product> phs = cr.list();
        for (Product ph : phs) {
            ProductHasSize pp = new Test3().productDetails(ph, s);
            String sl = pp.getPrice() + " - " + pp.getProduct().getName();
            System.out.println(sl);
        }
        //System.out.println(phs.toArray());
        //  Map stateMap = new HashMap();
//    for (Object[] obj : phs) {
//        DownloadState downloadState = (DownloadState) obj[0];
//        stateMap.put(downloadState.getDescription().toLowerCase() (Integer) obj[1]);
//    }

    }

    public ProductHasSize productDetails(Product p, Session s) {
        return (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                .add(Restrictions.eq("phs.product", p)).setMaxResults(1)
                .uniqueResult();

    }

}
