/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Sales;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "salesFilterAction", urlPatterns = {"/salesFilterAction"})
public class salesFilterAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int pageIndex = 0;
        int totalNumberOfRecords = 0;
        int numberOfRecordsPerPage = 9;

        String sPageIndex = request.getParameter("pgIndex");
        pageIndex = sPageIndex == null ? 1 : Integer.parseInt(sPageIndex);
        int s = (pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage;
        if (request.getParameter("startDate") != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sDate = null;
            Date eDate = null;
            try {

                sDate = sdf.parse(request.getParameter("startDate"));
                eDate = sdf.parse(request.getParameter("endDate"));
            } catch (Exception e) {
            }
            List<Sales> sList = session.createCriteria(Sales.class, "sale")
                    .add(Restrictions.between("sale.dateDone", sDate, eDate))
                    .setFirstResult(s)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
            String s1 = "", s2 = "";
            for (Sales sale : sList) {
                s1 += "<tr>"
                        + "<td>" + sale.getId() + "</td>"
                        + "<td>" + sale.getOrder().getUser().getFName() + " " + sale.getOrder().getUser().getLName() + "</td>"
                        + "<td>"
                        + sale.getStatus()
                        + "</td>"
                        + "<td> Rs. " + sale.getGrandTotal() + "</td>"
                        + "<td>"
                        + sale.getDateDone()
                        + "</td>"
                        + "<td>"
                        + "<button type='button' class='btn btn-default'><span class='glyphicon glyphicon-eye-open'></sapn></button>"
                        + "</td>"
                        + "</tr>";
            }
            Criteria criteriaCount = session.createCriteria(Sales.class, "sale")
                    .add(Restrictions.between("sale.dateDone", sDate, eDate));
            criteriaCount.setProjection(Projections.rowCount());
            totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();
            int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;
            if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
                noOfPages = noOfPages + 1;
            }

            for (int j = 1; j <= noOfPages; j++) {
                if (noOfPages > 1) {
                    String myurl = "sales.jsp?startDate=" + request.getParameter("startDate")
                            + "&endDate=" + request.getParameter("endDate") + "&pgIndex=" + j;
                    if (j == pageIndex) {
                        s2 += "<span class='current'>" + j + "</span>";
                    } else {
                        s2 += "<a href='" + myurl + "'>" + j + "</a>";
                    }

                }
            }
            session.close();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("[{\"d1\":\"" + s1 + "\",\"d2\":\"" + s2 + "\"}]");
        } else {
            List<Sales> sList = session.createCriteria(Sales.class)
                    .setFirstResult(s)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
            String s1 = "", s2 = "";
            for (Sales sale : sList) {
                s1 += "<tr>"
                        + "<td>" + sale.getId() + "</td>"
                        + "<td>" + sale.getOrder().getUser().getFName() + " " + sale.getOrder().getUser().getLName() + "</td>"
                        + "<td>"
                        + sale.getStatus()
                        + "</td>"
                        + "<td> Rs. " + sale.getGrandTotal() + "</td>"
                        + "<td>"
                        + sale.getDateDone()
                        + "</td>"
                        + "<td>"
                        + "<button type='button' class='btn btn-default'><span class='glyphicon glyphicon-eye-open'></sapn></button>"
                        + "</td>"
                        + "</tr>";
            }
            Criteria criteriaCount = session.createCriteria(Sales.class);
            criteriaCount.setProjection(Projections.rowCount());
            totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();
            int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;

            if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
                noOfPages = noOfPages + 1;
            }

            for (int j = 1; j <= noOfPages; j++) {
                if (noOfPages > 1) {
                    String myurl = "sales.jsp?pgIndex=" + j;
                    if (j == pageIndex) {
                        s2 += "<span class='current'>" + j + "</span>";
                    } else {
                        s2 += "<a href='" + myurl + "'>" + j + "</a>";
                    }

                }
            }
            session.close();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("[{\"d1\":\"" + s1 + "\",\"d2\":\"" + s2 + "\"}]");
        }
    }

}
