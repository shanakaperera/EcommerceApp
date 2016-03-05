/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.ProductHasSize;
import com.certus.dbmodel.Review;
import com.google.common.collect.Ordering;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class SingleItem {

    private final Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    private Criteria criteria;
    private ProductHasSize product;
    public static Comparator<String> SIZES_COMPARATOR = Ordering.explicit("S", "M", "L", "XL", "XXL");

    public ProductHasSize getProduct() {
        return product;
    }

    public void setProduct(ProductHasSize product) {
        this.product = product;
    }

    public ProductHasSize getProductDetials(int pid) {
        criteria = ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product")
                .createAlias("phs.size", "size")
                // .add(Restrictions.eq("size.id", 1))
                .add(Restrictions.eq("product.id", pid))
                .setMaxResults(1);
        product = (ProductHasSize) criteria.uniqueResult();
        return product;
    }

    public ProductHasSize getProductSummary(int pid, int sizeId) {
        criteria = ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product")
                .createAlias("phs.size", "size")
                // .add(Restrictions.eq("size.id", sizeId))
                .add(Restrictions.eq("product.id", pid))
                .setMaxResults(1);
        product = (ProductHasSize) criteria.uniqueResult();
        return product;
    }

    public Set<Review> getReviews() {
        return getProduct().getProduct().getReviews()
                .stream()
                .sorted(Comparator.comparing(Review::getDateComnt))
                .collect(Collectors.toSet());
    }

    public void saveReview(int u_id, int p_id, String comment, Date date) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_store", "root", "nbuser");
            PreparedStatement ps = con.prepareStatement("INSERT INTO review (user_id,Product_id,comment,date_comnt) VALUES(?,?,?,?)");
            ps.setInt(1, u_id);
            ps.setInt(2, p_id);
            ps.setString(3, comment);
            ps.setDate(4, date);
            ps.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SingleItem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveRate(int u_id, int p_id, int rate) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_store", "root", "nbuser");
            PreparedStatement ps = con.prepareStatement("INSERT INTO Rate (user_id,Product_id,rate) VALUES(?,?,?)");
            ps.setInt(1, u_id);
            ps.setInt(2, p_id);
            ps.setInt(3, rate);
            ps.execute();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SingleItem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int checkAvailability(int p_id, String size) {
        ProductHasSize phs = (ProductHasSize) ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product").createAlias("phs.size", "size")
                .add(Restrictions.eq("product.id", p_id))
                .add(Restrictions.eq("size.sizeName", size)).uniqueResult();
        return phs.getQnty();

    }

    public int getReviewCount(int pid) {
        return ses.createCriteria(Review.class, "rv")
                .createAlias("rv.product", "product")
                .add(Restrictions.eq("product.id", pid))
                .add(Restrictions.eq("rv.availability", true))
                .list().size();
    }

    public void closeConnection() {
        ses.close();
    }

//    public static void main(String[] args) {
//        SingleItem itm = new SingleItem();
//        ProductHasSize p = itm.getProductDetials(1);
//        System.out.println(p.getProduct().getName()+" - "+p.getPrice());
//    }
}
