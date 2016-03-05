/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.Sales;
import com.certus.dbmodel.User;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
public class AdminDashBoardFigures {

    private Session s;

    public AdminDashBoardFigures() {
        s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    public int getOrdersToday() {
        return s.createCriteria(Order.class, "ordr")
                .add(Restrictions.eq("ordr.dateOrdered", new Date()))
                .list().size();
    }

    public int getSalesToday() {
        return s.createCriteria(Sales.class, "s")
                .add(Restrictions.eq("s.dateDone", new Date()))
                .list().size();
    }

    public int getCustomersToday() {
        return s.createCriteria(User.class, "u")
                .add(Restrictions.eq("u.dateSubmitted", new Date()))
                .list().size();
    }
}
