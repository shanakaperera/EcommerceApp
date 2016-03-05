/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import java.util.Comparator;

/**
 *
 * @author shanaka
 */
public class DaysComparator implements Comparator<ChartExpenses> {

    @Override
    public int compare(ChartExpenses o1, ChartExpenses o2) {
        return ReportsController.DAYS_OF_WEEK_COMPARATOR.compare(o1.getDay(), o2.getDay());
    }

}
