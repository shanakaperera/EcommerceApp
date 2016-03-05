/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Category;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import com.certus.dbmodel.SubCategory;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class ItemPage {

    private final Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    private Criteria criteria;
    private List<ProductHasSize> proList;

    public List<ProductHasSize> getProList() {
        return proList;
    }

    public void setProList(List<ProductHasSize> proList) {
        this.proList = proList;
    }

    public String getSelectedCategoryById(Object ob) {
        criteria = ses.createCriteria(Category.class).add(Restrictions.eq("id", Integer.parseInt(ob.toString())));
        Category category = (Category) criteria.uniqueResult();
        return category.getCatName();
    }

    public String getSelectedSubCategoryById(Object ob) {
        criteria = ses.createCriteria(SubCategory.class).add(Restrictions.eq("id", Integer.parseInt(ob.toString())));
        SubCategory sub_category = (SubCategory) criteria.uniqueResult();
        return sub_category.getSubCategoryName();
    }

    public void filterProducts(int subCat_id, int cat_id, int s, int numberOfRecordsPerPage) {

        criteria = ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "pro")
                .createAlias("phs.size", "size")
                .createAlias("pro.subCategory", "subCat")
                .createAlias("subCat.category", "cat").add(Restrictions.eq("cat.id", cat_id))
                .add(Restrictions.eq("subCat.id", subCat_id))
                .add(Restrictions.eq("pro.availability", true))
                .add(Restrictions.eq("size.availability", true))
                .setFirstResult(s).setMaxResults(numberOfRecordsPerPage);
        proList = criteria.list();
    }
        public void filterProducts(String subCat_name, int cat_id, int s, int numberOfRecordsPerPage) {

        criteria = ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "pro")
                .createAlias("phs.size", "size")
                .createAlias("pro.subCategory", "subCat")
                .createAlias("subCat.category", "cat")
                .add(Restrictions.eq("cat.id", cat_id))
                .add(Restrictions.eq("subCat.subCategoryName", subCat_name))
                .add(Restrictions.eq("pro.availability", true))
                .add(Restrictions.eq("size.availability", true))
                .setFirstResult(s).setMaxResults(numberOfRecordsPerPage);
        proList = criteria.list();
    }

    public void filterProducts(int subCat_id, int cat_id, String sort, int s, int numberOfRecordsPerPage) {
        criteria = ses.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "pro")
                .createAlias("phs.size", "size")
                .createAlias("pro.subCategory", "subCat")
                .createAlias("subCat.category", "cat").add(Restrictions.eq("cat.id", cat_id))
                .add(Restrictions.eq("subCat.id", subCat_id))
                .add(Restrictions.eq("pro.availability", true))
                .add(Restrictions.eq("size.availability", true))
                .setFirstResult(s)
                .setMaxResults(numberOfRecordsPerPage);
        proList = criteria.list();

        switch (sort) {
            case "nameAsc":
                proList = proList.stream().
                        sorted(Comparator.comparing(p -> p.getProduct().getName()))
                        .collect(Collectors.toList());
                break;
            case "nameDec":
                proList = proList.stream().
                        sorted(Comparator.comparing((ProductHasSize p) -> p.getProduct().getName()).reversed())
                        .collect(Collectors.toList());
                break;
            case "priceAsc":

                proList = proList.stream().sorted(Comparator.comparing(p -> p.getPrice()))
                        .collect(Collectors.toList());
                break;
            case "priceDec":

                proList = proList.stream().sorted(Comparator.comparing((ProductHasSize p) -> p.getPrice()).reversed())
                        .collect(Collectors.toList());

                break;
        }

    }

    public long totalResults(int subCat_id, int cat_id) {
        return (Long) ses.createCriteria(Product.class, "pro").createAlias("pro.subCategory", "subCat").
                createAlias("subCat.category", "cat").add(Restrictions.eq("cat.id", cat_id))
                .add(Restrictions.eq("pro.availability", Boolean.TRUE))
                .add(Restrictions.eq("subCat.id", subCat_id)).setProjection(Projections.rowCount()).uniqueResult();
    }

}
