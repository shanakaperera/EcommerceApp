/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import com.certus.dbmodel.Expences;
import com.certus.dbmodel.Sales;
import com.google.common.collect.Ordering;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

/**
 *
 * @author shanaka
 */
public class ReportsController {

    private final Session s;
    public static Comparator<String> DAYS_OF_WEEK_COMPARATOR = Ordering.explicit("Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday");
    public static Comparator<String> DAYS_OF_MONTH_COMPARATOR = Ordering.explicit("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31");
    public static Comparator<String> MONTH_OF_YEAR_COMPARATOR = Ordering.explicit("January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December");

    public ReportsController() {
        s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
    }

    /*
    
     =============================SALES SECTION STARTAS=========================
    
     */
    public Map<String, Double> getSalesByWeek(Date startOfWeek, Date endOfWeek) {
        List<ChartsCriteria> wSale = new ArrayList<>();
        List<Sales> sList = s.createCriteria(Sales.class, "sale")
                .add(Restrictions.between("sale.dateDone", startOfWeek, endOfWeek))
                .addOrder(Order.asc("sale.dateDone"))
                .list();
        addDatesToListWeek(wSale);
        for (Sales sale : sList) {
            wSale.add(new ChartsCriteria(getDayByName(sale.getDateDone()), sale.getGrandTotal()));
        }

        return wSale.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }

    public Map<String, Double> getSalesByMonth(Date startOfMonth, Date endOfMonth) {
        List<ChartsCriteria> mSale = new ArrayList<>();
        List<Sales> sList = s.createCriteria(Sales.class, "sale")
                .add(Restrictions.between("sale.dateDone", startOfMonth, endOfMonth))
                .addOrder(Order.asc("sale.dateDone"))
                .list();
        addDatesToListMonth(mSale);
        for (Sales sale : sList) {
            mSale.add(new ChartsCriteria(getDayByDay(sale.getDateDone()), sale.getGrandTotal()));
        }

        return mSale.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }

    public Map<String, Double> getSalesByYear(Date startOfYear, Date endOfYear) {
        List<ChartsCriteria> ySale = new ArrayList<>();
        List<Sales> sList = s.createCriteria(Sales.class, "sale")
                .add(Restrictions.between("sale.dateDone", startOfYear, endOfYear))
                .addOrder(Order.asc("sale.dateDone"))
                .list();
        addMonthsToList(ySale);
        for (Sales sale : sList) {
            ySale.add(new ChartsCriteria(getMonthByDate(sale.getDateDone()), sale.getGrandTotal()));
        }

        return ySale.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }

    /*
    
     ===========================SALES SECTION ENDS=============================
    
     */
    /*
    
     =============================ORDER SECTION STARTAS=========================
    
     */
    public Map<String, Double> getOrdersByWeek(Date startOfWeek, Date endOfWeek) {
        List<ChartsCriteria> wOrder = new ArrayList<>();
        List<com.certus.dbmodel.Order> oList = s.createCriteria(com.certus.dbmodel.Order.class, "order")
                .add(Restrictions.between("order.dateOrdered", startOfWeek, endOfWeek))
                .addOrder(Order.asc("order.dateOrdered"))
                .list();
        addDatesToListWeek(wOrder);
        for (com.certus.dbmodel.Order order : oList) {
            wOrder.add(new ChartsCriteria(getDayByName(order.getDateOrdered()), order.getGrandTot()));
        }

        return wOrder.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }

    public Map<String, Double> getOrdersByMonth(Date startOfMonth, Date endOfMonth) {
        List<ChartsCriteria> mOrder = new ArrayList<>();
        List<com.certus.dbmodel.Order> oList = s.createCriteria(com.certus.dbmodel.Order.class, "order")
                .add(Restrictions.between("order.dateOrdered", startOfMonth, endOfMonth))
                .addOrder(Order.asc("order.dateOrdered"))
                .list();
        addDatesToListMonth(mOrder);
        for (com.certus.dbmodel.Order order : oList) {
            mOrder.add(new ChartsCriteria(getDayByDay(order.getDateOrdered()), order.getGrandTot()));
        }

        return mOrder.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }

    public Map<String, Double> getOrdersByYear(Date startOfYear, Date endOfYear) {
        List<ChartsCriteria> yOrder = new ArrayList<>();
        List<com.certus.dbmodel.Order> oList = s.createCriteria(com.certus.dbmodel.Order.class, "order")
                .add(Restrictions.between("order.dateOrdered", startOfYear, endOfYear))
                .addOrder(Order.asc("order.dateOrdered"))
                .list();
        addMonthsToList(yOrder);
        for (com.certus.dbmodel.Order order : oList) {
            yOrder.add(new ChartsCriteria(getMonthByDate(order.getDateOrdered()), order.getGrandTot()));
        }

        return yOrder.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));

    }
    /*
    
     =============================ORDER SECTION ENDS=========================
    
     */

    /*
    
     =============================EXPENCES SECTION STARTS======================
    
     */
    public Map<String, Double> getExpenesByWeek(Date startOfWeek, Date endOfWeek) {
        List<ChartsCriteria> wExpence = new ArrayList<>();
        List<Expences> eList = s.createCriteria(Expences.class, "exp")
                .add(Restrictions.between("exp.date", startOfWeek, endOfWeek))
                .addOrder(Order.asc("exp.date")).list();
        addDatesToListWeek(wExpence);
        for (Expences expence : eList) {
            wExpence.add(new ChartsCriteria(getDayByName(expence.getDate()), expence.getTotal()));
        }
        return wExpence.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));
    }

    public Map<String, Double> getExpenesByMonth(Date startOfMonth, Date endOfMonth) {
        List<ChartsCriteria> mExpence = new ArrayList<>();
        List<Expences> eList = s.createCriteria(Expences.class, "exp")
                .add(Restrictions.between("exp.date", startOfMonth, endOfMonth))
                .addOrder(Order.asc("exp.date")).list();
        addDatesToListMonth(mExpence);
        for (Expences expence : eList) {
            mExpence.add(new ChartsCriteria(getDayByDay(expence.getDate()), expence.getTotal()));
        }
        return mExpence.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));
    }

    public Map<String, Double> getExpenesByYear(Date startOfYear, Date endOfYear) {
        List<ChartsCriteria> yExpence = new ArrayList<>();
        List<Expences> eList = s.createCriteria(Expences.class, "exp")
                .add(Restrictions.between("exp.date", startOfYear, endOfYear))
                .addOrder(Order.asc("exp.date")).list();
        addMonthsToList(yExpence);
        for (Expences expence : eList) {
            yExpence.add(new ChartsCriteria(getMonthByDate(expence.getDate()), expence.getTotal()));
        }
        return yExpence.stream()
                .collect(Collectors
                        .groupingBy(d -> d.getDay(),
                                Collectors.summingDouble(a -> a.getAmount())));
    }

    /*
    
     =============================EXPENCES SECTION ENDS=======================
    
     */
    public void addDatesToListWeek(List<ChartsCriteria> wSale) {
        wSale.add(new ChartsCriteria("Monday", 0));
        wSale.add(new ChartsCriteria("Tuesday", 0));
        wSale.add(new ChartsCriteria("Wednesday", 0));
        wSale.add(new ChartsCriteria("Thursday", 0));
        wSale.add(new ChartsCriteria("Friday", 0));
        wSale.add(new ChartsCriteria("Saturday", 0));
        wSale.add(new ChartsCriteria("Sunday", 0));
    }

    public void addDatesToListMonth(List<ChartsCriteria> mSale) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        for (int i = 1; i < cal.getActualMaximum(Calendar.DAY_OF_MONTH) + 1; i++) {
            mSale.add(new ChartsCriteria(String.valueOf(i), 0));
        }
    }

    public void addMonthsToList(List<ChartsCriteria> ySale) {
        ySale.add(new ChartsCriteria("January", 0));
        ySale.add(new ChartsCriteria("February", 0));
        ySale.add(new ChartsCriteria("March", 0));
        ySale.add(new ChartsCriteria("April", 0));
        ySale.add(new ChartsCriteria("May", 0));
        ySale.add(new ChartsCriteria("June", 0));
        ySale.add(new ChartsCriteria("July", 0));
        ySale.add(new ChartsCriteria("August", 0));
        ySale.add(new ChartsCriteria("September", 0));
        ySale.add(new ChartsCriteria("October", 0));
        ySale.add(new ChartsCriteria("November", 0));
        ySale.add(new ChartsCriteria("December", 0));
    }

    public String getDayByName(Date date) {
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
    }

    public String getDayByDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));

    }

    public String getMonthByDate(Date date) {
        return new SimpleDateFormat("MMMM", Locale.ENGLISH).format(date);
    }

    public void closeConnection() {
        s.close();
    }

//    public static void main(String[] args) {
//        DateTime today = new DateTime();
//
//        DateTime startOfWeek = today.year().roundFloorCopy();
//        DateTime endOfWeek = today.year().roundCeilingCopy();
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.registerTypeAdapter(ChartsCriteria.class, new ChartsCriteriaAdapter()).create();
//
//        Map<String, Double> mapWeek = new ReportsController()
//                .getExpenesByYear(startOfWeek.toDate(), endOfWeek.toDate());
//       Map<String, Double> sortedMapWeek = new TreeMap<>(ReportsController.MONTH_OF_YEAR_COMPARATOR);
//        sortedMapWeek.putAll(mapWeek);
//        List<ChartsCriteria> orderListWeek = new ArrayList<>();
//        for (Map.Entry<String, Double> m : sortedMapWeek.entrySet()) {
//            orderListWeek.add(new ChartsCriteria(m.getKey(), m.getValue()));
//        }
//        String elementWeek = gson.toJson(orderListWeek);
//        System.out.println(elementWeek);
//    }
}
