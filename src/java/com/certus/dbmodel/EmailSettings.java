/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.dbmodel;

import java.io.Serializable;

/**
 *
 * @author shanaka
 */
public class EmailSettings implements Serializable {

    private Integer id;
    private String host;
    private Integer port;
    private String password;
    private String user;

    public EmailSettings() {
    }

    public EmailSettings(Integer id, String host, Integer port, String password, String user) {
        this.id = id;
        this.host = host;
        this.port = port;
        this.password = password;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
