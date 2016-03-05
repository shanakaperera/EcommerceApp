/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Brand;
import com.certus.dbmodel.Category;
import com.certus.dbmodel.ProImg;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.SubCategory;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class Categories {

    private final Session session;

    public Categories() {
        this.session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

    }

    public int SubCategoryCount(int cat_Id) {
        return session.createCriteria(SubCategory.class, "sub_cat")
                .createAlias("sub_cat.category", "cat")
                .add(Restrictions.eq("cat.id", cat_Id)).list().size();
    }

    public int ProductCount(int cat_Id) {
        return session.createCriteria(Product.class, "pro")
                .createAlias("pro.subCategory", "sub_cat")
                .createAlias("sub_cat.category", "cat")
                .add(Restrictions.eq("cat.id", cat_Id)).list().size();
    }

    public List<Category> Categories() {
        return session.createCriteria(Category.class).list();
    }

    public List<Brand> Brands() {
        return session.createCriteria(Brand.class).list();
    }

    public List<ProImg> Images(int pro_id) {
        return session.createCriteria(ProImg.class, "img")
                .createAlias("img.product", "product")
                .add(Restrictions.eq("product.id", pro_id))
                .list();

    }
    public void closeConnection(){
        session.close();
    }

}
