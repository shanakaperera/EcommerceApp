package com.certus.dbmodel;
// Generated Jan 24, 2016 12:46:38 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

    private Integer id;
    private String userName;
    private String password;
    private String LName;
    private String FName;
    private String image;
    private Date dateSubmitted;
    private boolean availability;
    private String email;
    private String telephone;
    private String address;
    private Set<Review> reviews = new HashSet<>();
    private Set<WishList> wishLists = new HashSet<>();
    private Set<Order> orders = new HashSet<>();
    private Set<Rate> rates = new HashSet<>();

    public User() {
    }

    public User(String userName, String password, String LName, String FName, Date dateSubmitted, boolean availability, String email) {
        this.userName = userName;
        this.password = password;
        this.dateSubmitted = dateSubmitted;
        this.availability = availability;
        this.email = email;
        this.LName = LName;
        this.FName = FName;
    }

    public User(String userName, String password, String LName, String FName, Date dateSubmitted, boolean availability, String email, String telephone, String address) {
        this.userName = userName;
        this.password = password;
        this.LName = LName;
        this.FName = FName;
        this.dateSubmitted = dateSubmitted;
        this.availability = availability;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
    }

    public User(String userName, String password, String LName, String FName, String image, Date dateSubmitted, boolean availability, String email, Set<Review> reviews, Set<WishList> wishLists, Set<Order> orders, Set<Rate> rates) {
        this.userName = userName;
        this.password = password;
        this.LName = LName;
        this.FName = FName;
        this.image = image;
        this.dateSubmitted = dateSubmitted;
        this.availability = availability;
        this.email = email;
        this.reviews = reviews;
        this.wishLists = wishLists;
        this.orders = orders;
        this.rates = rates;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLName() {
        return this.LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public String getFName() {
        return this.FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDateSubmitted() {
        return this.dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public boolean isAvailability() {
        return this.availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<WishList> getWishLists() {
        return this.wishLists;
    }

    public void setWishLists(Set<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Rate> getRates() {
        return this.rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}