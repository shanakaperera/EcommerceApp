package com.certus.dbmodel;
// Generated Jan 24, 2016 12:46:38 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category  implements java.io.Serializable {


     private Integer id;
     private String catName;
     private boolean availability;
     private Set<SubCategory> subCategories = new HashSet<>();

    public Category() {
    }

	
    public Category(String catName, boolean availability) {
        this.catName = catName;
        this.availability = availability;
    }
    public Category(String catName, boolean availability, Set<SubCategory> subCategories) {
       this.catName = catName;
       this.availability = availability;
       this.subCategories = subCategories;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCatName() {
        return this.catName;
    }
    
    public void setCatName(String catName) {
        this.catName = catName;
    }
    public boolean isAvailability() {
        return this.availability;
    }
    
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public Set<SubCategory> getSubCategories() {
        return this.subCategories;
    }
    
    public void setSubCategories(Set<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }




}

