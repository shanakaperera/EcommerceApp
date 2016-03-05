/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Administor;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
public class AdminManage {

    private final Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    private BasicPasswordEncryptor encriptor;
    private Administor admin;

    public Administor getAdmin() {
        return admin;
    }

    public void setAdmin(Administor admin) {
        this.admin = admin;
    }

    public boolean loginValidated(String user_name, String password) {
        boolean flag = false;
        admin = (Administor) ses.createCriteria(Administor.class)
                .add(Restrictions.eq("userName", user_name)).uniqueResult();
        encriptor = new BasicPasswordEncryptor();
        if (admin != null) {
            if (encriptor.checkPassword(password, admin.getPassword())) {
                flag = true;
            }
        }
        return flag;
    }

    public void closeConnection() {
        ses.close();
    }
}
