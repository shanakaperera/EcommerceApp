/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Brand;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "loadBrandsAction", urlPatterns = {"/loadBrandsAction"})
public class loadBrandsAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int pageIndex = 0;
        int totalNumberOfRecords = 0;
        int numberOfRecordsPerPage = 9;

        String sPageIndex = request.getParameter("pgIndex");
        pageIndex = sPageIndex == null ? 1 : Integer.parseInt(sPageIndex);

        int s = (pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage;
        List<Brand> b = session.createCriteria(Brand.class)
                .setFirstResult(s)
                .setMaxResults(numberOfRecordsPerPage)
                .list();
        String s1 = "", s2 = "";
        for (Brand brnd : b) {
            String avlOn = brnd.isAvailability() ? "btn-danger" : "btn-default";
            String avlOff = brnd.isAvailability() ? "btn-default" : "btn-danger";
            s1 += "<tr>"
                    + "<td>" + brnd.getBrandName() + "</td>"
                    + "<td>"
                    + "<div class='btn-group btn-toggle'>"
                    + "<button class='btn btn-xs " + avlOn + "' onclick='onBtnClick(" + brnd.getId() + "," + pageIndex + ");' type='button'>ON</button>"
                    + "<button class='btn btn-xs " + avlOff + "' onclick='offBtnClick(" + brnd.getId() + "," + pageIndex + ");' type='button'>OFF</button>"
                    + "</div>"
                    + "</td>"
                    + "<td>"
                    + "<button ype='button' class='btn btn-default' data-dismiss='modal' onclick='getBrandId(" + brnd.getId() + ");' data-target='#ViewAllProModal' data-toggle='modal'>"
                    + "<span class='glyphicon glyphicon-gift'></span>&nbsp;&nbsp;"
                    + brnd.getProducts().stream().count()
                    + "</button>"
                    + "</td>"
                    + "<td>"
                    + "<button type='button' class='btn btn-default' data-dismiss='modal' data-target='#updateBndModal' data-toggle='modal'>"
                    + "<span class='glyphicon glyphicon-edit'></span>"
                    + "</button>"
                    + "</td>"
                    + "</tr>";
        }
        Criteria criteriaCount = session.createCriteria(Brand.class);
        criteriaCount.setProjection(Projections.rowCount());
        totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();
        int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;

        if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
            noOfPages = noOfPages + 1;
        }

        for (int j = 1; j <= noOfPages; j++) {
            if (noOfPages > 1) {
                String myurl = "manufactures.jsp?pgIndex=" + j;
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
