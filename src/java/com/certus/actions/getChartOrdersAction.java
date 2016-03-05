/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ChartsCriteria;
import com.certus.controllers.ChartsCriteriaAdapter;
import com.certus.controllers.ReportsController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
@WebServlet(name = "getChartOrdersAction", urlPatterns = {"/getChartOrdersAction"})
public class getChartOrdersAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestString = request.getParameter("orders");
        DateTime today = new DateTime();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(ChartsCriteria.class, new ChartsCriteriaAdapter()).create();

        switch (requestString) {
            case "week":

                DateTime startOfWeek = today.weekOfWeekyear().roundFloorCopy();
                DateTime endOfWeek = today.weekOfWeekyear().roundCeilingCopy();

                Map<String, Double> mapWeek = new ReportsController()
                        .getOrdersByWeek(startOfWeek.toDate(), endOfWeek.toDate());
                Map<String, Double> sortedMapWeek = new TreeMap<>(ReportsController.DAYS_OF_WEEK_COMPARATOR);
                sortedMapWeek.putAll(mapWeek);

                List<ChartsCriteria> orderListWeek = new ArrayList<>();
                for (Map.Entry<String, Double> m : sortedMapWeek.entrySet()) {
                    orderListWeek.add(new ChartsCriteria(m.getKey(), m.getValue()));
                }
                String elementWeek = gson.toJson(orderListWeek);
                response.setContentType("application/json");
                response.getWriter().write(elementWeek);
                break;

            case "month":

                DateTime startOfMonth = today.monthOfYear().roundFloorCopy();
                DateTime endOfMonth = today.monthOfYear().roundCeilingCopy();

                Map<String, Double> mapMonth = new ReportsController()
                        .getOrdersByMonth(startOfMonth.toDate(), endOfMonth.toDate());
                Map<String, Double> sortedMapMonth = new TreeMap<>(ReportsController.DAYS_OF_MONTH_COMPARATOR);
                sortedMapMonth.putAll(mapMonth);

                List<ChartsCriteria> orderListMonth = new ArrayList<>();
                for (Map.Entry<String, Double> m : sortedMapMonth.entrySet()) {
                    orderListMonth.add(new ChartsCriteria(m.getKey(), m.getValue()));
                }
                String elementMonth = gson.toJson(orderListMonth);
                response.setContentType("application/json");
                response.getWriter().write(elementMonth);
                break;

            case "year":
                DateTime startOfYear = today.year().roundFloorCopy();
                DateTime endOfYear = today.year().roundCeilingCopy();

                Map<String, Double> mapYear = new ReportsController()
                        .getOrdersByYear(startOfYear.toDate(), endOfYear.toDate());
                Map<String, Double> sortedMapYear = new TreeMap<>(ReportsController.MONTH_OF_YEAR_COMPARATOR);
                sortedMapYear.putAll(mapYear);
                List<ChartsCriteria> orderListYear = new ArrayList<>();
                for (Map.Entry<String, Double> m : sortedMapYear.entrySet()) {
                    orderListYear.add(new ChartsCriteria(m.getKey(), m.getValue()));
                }
                String elementYear = gson.toJson(orderListYear);
                response.setContentType("application/json");
                response.getWriter().write(elementYear);
                break;
            default:
                break;
        }
    }

}
