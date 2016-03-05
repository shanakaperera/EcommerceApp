/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.dbmodel;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author shanaka
 */
public class Messages implements Serializable {

    private Integer id;
    private String message;
    private boolean userViewed;
    private boolean adminViewed;
    private Order order;
    private Date dateSent;
    private boolean adminSent;

    public Messages() {
    }

    public Messages(Integer id, String message, boolean userViewed, boolean adminViewed, Order order, Date dateSent, boolean adminSent) {
        this.id = id;
        this.message = message;
        this.userViewed = userViewed;
        this.adminViewed = adminViewed;
        this.order = order;
        this.dateSent = dateSent;
        this.adminSent = adminSent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isUserViewed() {
        return userViewed;
    }

    public void setUserViewed(boolean userViewed) {
        this.userViewed = userViewed;
    }

    public boolean isAdminViewed() {
        return adminViewed;
    }

    public void setAdminViewed(boolean adminViewed) {
        this.adminViewed = adminViewed;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public boolean isAdminSent() {
        return adminSent;
    }

    public void setAdminSent(boolean adminSent) {
        this.adminSent = adminSent;
    }

}
