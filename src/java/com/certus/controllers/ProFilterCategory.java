/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Brand;
import com.certus.dbmodel.Size;
import com.certus.dbmodel.SubCategory;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
public class ProFilterCategory {

    private Session ses;

    public ProFilterCategory() {
        ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    public List<SubCategory> getCatList() {
        List<SubCategory> list = ses.createCriteria(SubCategory.class).list();
        return list;
    }

    public List<Size> getSizes() {
        List<Size> list = ses.createCriteria(Size.class).list();
        return list;
    }

    public List<Brand> getBrands() {
        List<Brand> list = ses.createCriteria(Brand.class).list();
        return list;
    }

    public void closeConnection() {
        ses.close();
    }

}
