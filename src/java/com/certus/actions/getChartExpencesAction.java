/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ChartExpenseAdapter;
import com.certus.controllers.ChartExpenses;
import com.certus.controllers.DaysComparator;
import com.certus.controllers.MonthDaysComparator;
import com.certus.controllers.ReportsController;
import com.certus.controllers.YearMonthsComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "getChartExpencesAction", urlPatterns = {"/getChartExpencesAction"})
public class getChartExpencesAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestString = request.getParameter("expenses");
        //String requestString = "year";
        DateTime today = new DateTime();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(ChartExpenses.class, new ChartExpenseAdapter()).create();

        switch (requestString) {
            case "week":

                DateTime startOfWeek = today.weekOfWeekyear().roundFloorCopy();
                DateTime endOfWeek = today.weekOfWeekyear().roundCeilingCopy();

                Map<String, Double> mapWeekSale = new ReportsController()
                        .getSalesByWeek(startOfWeek.toDate(), endOfWeek.toDate());
                Map<String, Double> sortedMapWeekSale = new TreeMap<>(ReportsController.DAYS_OF_WEEK_COMPARATOR);
                sortedMapWeekSale.putAll(mapWeekSale);

                Map<String, Double> mapWeekExpense = new ReportsController()
                        .getExpenesByWeek(startOfWeek.toDate(), endOfWeek.toDate());
                Map<String, Double> sortedMapWeekExpense = new TreeMap<>(ReportsController.DAYS_OF_WEEK_COMPARATOR);
                sortedMapWeekExpense.putAll(mapWeekExpense);

                if (mapWeekExpense.size() != mapWeekSale.size()) {
                    try {
                        throw new Exception("Invalid size exception");
                    } catch (Exception ex) {
                        Logger.getLogger(getChartExpencesAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                Iterator itSale = mapWeekSale.entrySet().iterator();
                Iterator itExp = mapWeekExpense.entrySet().iterator();
                List<ChartExpenses> expList = new ArrayList<>();
                while (itSale.hasNext() || itExp.hasNext()) {
                    Map.Entry<String, Double> sale = null;
                    Map.Entry<String, Double> exp = null;
                    if (itSale.hasNext()) {
                        sale = (Map.Entry<String, Double>) itSale.next();

                    }
                    if (itExp.hasNext()) {
                        exp = (Map.Entry<String, Double>) itExp.next();
                    }

                    if (sale != null && exp != null) {
                        if (sale.getKey().equals(exp.getKey())) {
                            expList.add(new ChartExpenses(sale.getKey(),
                                    sale.getValue(), exp.getValue()));
                        }
                    }

                }

                expList.sort(new DaysComparator());
                String elementWeek = gson.toJson(expList);
                response.setContentType("application/json");
                response.getWriter().write(elementWeek);
                break;

            case "month":

                DateTime startOfMonth = today.monthOfYear().roundFloorCopy();
                DateTime endOfMonth = today.monthOfYear().roundCeilingCopy();

                Map<String, Double> mapMonthSale = new ReportsController()
                        .getSalesByMonth(startOfMonth.toDate(), endOfMonth.toDate());
                Map<String, Double> sortedMapMonthSale = new TreeMap<>(ReportsController.DAYS_OF_MONTH_COMPARATOR);
                sortedMapMonthSale.putAll(mapMonthSale);

                Map<String, Double> mapMonthExp = new ReportsController()
                        .getExpenesByMonth(startOfMonth.toDate(), endOfMonth.toDate());
                Map<String, Double> sortedMapMonthExp = new TreeMap<>(ReportsController.DAYS_OF_MONTH_COMPARATOR);
                sortedMapMonthExp.putAll(mapMonthExp);

                if (mapMonthSale.size() != mapMonthExp.size()) {
                    try {
                        throw new Exception("Invalid size exception");
                    } catch (Exception ex) {
                        Logger.getLogger(getChartExpencesAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                Iterator itSaleMonth = mapMonthSale.entrySet().iterator();
                Iterator itExpMonth = mapMonthExp.entrySet().iterator();
                List<ChartExpenses> expMonthList = new ArrayList<>();
                while (itSaleMonth.hasNext() || itExpMonth.hasNext()) {
                    Map.Entry<String, Double> sale = null;
                    Map.Entry<String, Double> exp = null;
                    if (itSaleMonth.hasNext()) {
                        sale = (Map.Entry<String, Double>) itSaleMonth.next();

                    }
                    if (itExpMonth.hasNext()) {
                        exp = (Map.Entry<String, Double>) itExpMonth.next();
                    }

                    if (sale != null && exp != null) {
                        if (sale.getKey().equals(exp.getKey())) {
                            expMonthList.add(new ChartExpenses(sale.getKey(), sale.getValue(), exp.getValue()));
                        }
                    }

                }
                expMonthList.sort(new MonthDaysComparator());
                String elementMonth = gson.toJson(expMonthList);
                response.setContentType("application/json");
                response.getWriter().write(elementMonth);
                break;

            case "year":
                DateTime startOfYear = today.year().roundFloorCopy();
                DateTime endOfYear = today.year().roundCeilingCopy();

                Map<String, Double> mapYearSale = new ReportsController()
                        .getSalesByYear(startOfYear.toDate(), endOfYear.toDate());
                Map<String, Double> sortedMapYear = new TreeMap<>(ReportsController.MONTH_OF_YEAR_COMPARATOR);
                sortedMapYear.putAll(mapYearSale);

                Map<String, Double> mapYearExp = new ReportsController()
                        .getExpenesByYear(startOfYear.toDate(), endOfYear.toDate());
                Map<String, Double> sortedMapYearExp = new TreeMap<>(ReportsController.MONTH_OF_YEAR_COMPARATOR);
                sortedMapYearExp.putAll(mapYearExp);

                if (mapYearExp.size() != mapYearSale.size()) {
                    try {
                        throw new Exception("Invalid size exception");
                    } catch (Exception ex) {
                        Logger.getLogger(getChartExpencesAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                Iterator itSaleYear = mapYearSale.entrySet().iterator();
                Iterator itExpYear = mapYearExp.entrySet().iterator();
                List<ChartExpenses> expYearList = new ArrayList<>();
                while (itSaleYear.hasNext() || itExpYear.hasNext()) {
                    Map.Entry<String, Double> sale = null;
                    Map.Entry<String, Double> exp = null;
                    if (itSaleYear.hasNext()) {
                        sale = (Map.Entry<String, Double>) itSaleYear.next();

                    }
                    if (itExpYear.hasNext()) {
                        exp = (Map.Entry<String, Double>) itExpYear.next();
                    }

                    if (sale != null && exp != null) {
                        if (sale.getKey().equals(exp.getKey())) {
                            expYearList.add(new ChartExpenses(sale.getKey(), sale.getValue(), exp.getValue()));
                        }
                    }

                }
                expYearList.sort(new YearMonthsComparator());
                String elementYear = gson.toJson(expYearList);
                response.setContentType("application/json");
                response.getWriter().write(elementYear);
                break;
            default:
                break;
        }
    }

}
