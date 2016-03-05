/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class UserController {

    private Session s;

    public UserController() {
        s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    public List<Order> getOrderInfo(User u) {
        return s.createCriteria(Order.class, "order")
                .createAlias("order.user", "user")
                .add(Restrictions.eq("user.id", u.getId())).list();
    }
}
