/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.controllers;

import java.io.Serializable;

/**
 *
 * @author shanaka
 */
public class ChartExpenses implements Serializable {

    private String day;
    private double amountSale;
    private double amountExpense;

    public ChartExpenses(String day, double amountSale, double amountExpense) {
        this.day = day;
        this.amountSale = amountSale;
        this.amountExpense = amountExpense;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public double getAmountSale() {
        return amountSale;
    }

    public void setAmountSale(double amountSale) {
        this.amountSale = amountSale;
    }

    public double getAmountExpense() {
        return amountExpense;
    }

    public void setAmountExpense(double amountExpense) {
        this.amountExpense = amountExpense;
    }



}
