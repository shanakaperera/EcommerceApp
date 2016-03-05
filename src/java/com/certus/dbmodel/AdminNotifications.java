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
public class AdminNotifications implements Serializable {

    private Integer id;
    private Date date;
    private boolean notified;
    private String message;
    private String description;

    public AdminNotifications() {
    }

    public AdminNotifications(Date date, boolean notified, String message) {
        this.date = date;
        this.notified = notified;
        this.message = message;
    }

    public AdminNotifications(Date date, boolean notified, String message, String description) {
        this.date = date;
        this.notified = notified;
        this.message = message;
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isNotified() {
        return this.notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
