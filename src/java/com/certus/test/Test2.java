/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.test;

import com.certus.dbmodel.Product;

import org.hibernate.Session;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author shanaka
 */
public class Test2 {

    public static void main(String[] args) {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
      //  TermsAndConditions conditions = (TermsAndConditions) s.load(TermsAndConditions.class, 1);
//        Product p = (Product) s.load(Product.class, 1);
//
//        String html = "<table>"
//                + p.getSpecs()
//                + "</table>";
//
//        Document doc = Jsoup.parse(html);
//        Elements elements = doc.select("tbody > tr");
//
//        for (Element e : elements) {
//            System.out.println(e.select("td").html());
//
//        }
       // System.out.println(conditions.getCompPolicy());
        
    }
}
