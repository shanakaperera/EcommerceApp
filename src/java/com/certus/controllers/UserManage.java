/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.AdminNotifications;
import com.certus.dbmodel.User;
import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
public class UserManage {

    private final Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    private BasicPasswordEncryptor encriptor;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean loginValidated(String user_name, String password) {
        boolean flag = false;
        user = (User) ses.createCriteria(User.class)
                .add(Restrictions.eq("userName", user_name))
                .add(Restrictions.eq("availability", true))
                .uniqueResult();
        encriptor = new BasicPasswordEncryptor();
        if (encriptor.checkPassword(password, user.getPassword())) {
            flag = true;
        }
        return flag;
    }

    public boolean registerNewUser(String user_name, String passowrd, String f_name, String l_name, String email, Date registed) {
        encriptor = new BasicPasswordEncryptor();
        boolean flag;
        try {
            ses.beginTransaction();
            user = new User(user_name, encriptor.encryptPassword(passowrd), l_name, f_name, registed, true, email);

            ses.save(user);
            ses.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public void saveNotification(User u) {
        AdminNotifications notifications = new AdminNotifications();
        ses.beginTransaction();
        notifications.setDate(new java.util.Date());
        notifications.setDescription("New Customer Registered.");
        notifications.setMessage("<h5>New User has been registered to the customer list. User name is - <a href='edit_customer.jsp?uid=" + u.getId() + "'>" + u.getFName() + "</a></h5>"
                + "<br/><h6>Email address is " + u.getEmail() + "</h6>");
        ses.save(notifications);
        ses.getTransaction().commit();
    }

    public void closeConnection() {
        ses.close();
    }

}
