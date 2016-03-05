/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProductHasSize;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "AdimnProductFilterAction", urlPatterns = {"/AdimnProductFilterAction"})
public class AdimnProductFilterAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int pageIndex = 0;
        int totalNumberOfRecords = 0;
        int numberOfRecordsPerPage = 9;

        String sPageIndex = request.getParameter("pgIndex");
        pageIndex = sPageIndex == null ? 1 : Integer.parseInt(sPageIndex);

        int s = (pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage;

        if (request.getParameter("category") != null) {
            List<ProductHasSize> phs = ses.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.size", "size")
                    .createAlias("phs.product", "pro")
                    .createAlias("pro.subCategory", "sub")
                    .createAlias("sub.category", "cat")
                    .add(Restrictions.eq("size.sizeName", request.getParameter("size")))
                    .add(Restrictions.eq("sub.subCategoryName", request.getParameter("sub_category")))
                    .add(Restrictions.eq("cat.catName", request.getParameter("category")))
                    .add(Restrictions.eq("pro.availability", Boolean.valueOf(request.getParameter("status"))))
                    .setFirstResult(s)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
            String s1 = "", s2 = "";
            for (ProductHasSize pro : phs) {
                String avlOn = pro.getProduct().isAvailability() ? "btn-danger" : "btn-default";
                String avlOff = pro.getProduct().isAvailability() ? "btn-default" : "btn-danger";
                s1 = s1 + "<tr>"
                        + "<td>" + pro.getProduct().getName() + "</td>"
                        + "<td>"
                        + "<div class='btn-group btn-toggle'>"
                        + "<button class='btn btn-xs " + avlOn + "' onclick='onBtnClick(" + pro.getProduct().getId() + "," + pageIndex + ");' type='button'>ON</button>"
                        + "<button class='btn btn-xs " + avlOff + "' onclick='offBtnClick(" + pro.getProduct().getId() + "," + pageIndex + ");' type='button'>OFF</button>"
                        + "</div>"
                        + "</td>"
                        + "<td>" + pro.getSize().getSizeName() + "</td>"
                        + "<td>" + pro.getQnty() + "</td>"
                        + "<td>Rs." + pro.getPrice() + "</td>"
                        + "<td>"
                        + "<a href='edit_product.jsp?pid=" + pro.getProduct().getId() + "&sid=" + pro.getSize().getId() + "'>"
                        + "    <span class='glyphicon glyphicon-edit'></span>"
                        + "</a>"
                        + "</td>"
                        + "</tr>"
                        + "<tr> ";

            }
            Criteria criteriaCount = ses.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.size", "size")
                    .createAlias("phs.product", "pro")
                    .createAlias("pro.subCategory", "sub")
                    .createAlias("sub.category", "cat")
                    .add(Restrictions.eq("size.sizeName", request.getParameter("size")))
                    .add(Restrictions.eq("sub.subCategoryName", request.getParameter("sub_category")))
                    .add(Restrictions.eq("cat.catName", request.getParameter("category")))
                    .add(Restrictions.eq("pro.availability", Boolean.valueOf(request.getParameter("status"))));
            criteriaCount.setProjection(Projections.rowCount());
            totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();

            int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;

            if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
                noOfPages = noOfPages + 1;
            }

            for (int j = 1; j <= noOfPages; j++) {
                if (noOfPages > 1) {
                    String myurl = "products.jsp?sizeName=" + request.getParameter("size") + "&"
                            + "subCategory=" + request.getParameter("sub_category") + "&"
                            + "catName=" + request.getParameter("category")
                            + "&status=" + request.getParameter("status")
                            + "&pgIndex=" + j;
                    if (j == pageIndex) {
                        s2 += "<span class='current'>" + j + "</span>";
                    } else {
                        s2 += "<a href='" + myurl + "'>" + j + "</a>";
                    }
                }

            }
            ses.close();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("[{\"d1\":\"" + s1 + "\",\"d2\":\"" + s2 + "\"}]");

        } else {
            List<ProductHasSize> phs = ses.createCriteria(ProductHasSize.class)
                    .setFirstResult(s)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
            String s1 = "", s2 = "";
            for (ProductHasSize pro : phs) {
                String avlOn = pro.getProduct().isAvailability() ? "btn-danger" : "btn-default";
                String avlOff = pro.getProduct().isAvailability() ? "btn-default" : "btn-danger";
                s1 = s1 + "<tr>"
                        + "<td>" + pro.getProduct().getName() + "</td>"
                        + "<td>"
                        + "<div class='btn-group btn-toggle'>"
                        + "<button class='btn btn-xs " + avlOn + "' onclick='onBtnClick(" + pro.getProduct().getId() + "," + pageIndex + ");' type='button'>ON</button>"
                        + "<button class='btn btn-xs " + avlOff + "' onclick='offBtnClick(" + pro.getProduct().getId() + "," + pageIndex + ");' type='button'>OFF</button>"
                        + "</div>"
                        + "</td>"
                        + "<td>" + pro.getSize().getSizeName() + "</td>"
                        + "<td>" + pro.getQnty() + "</td>"
                        + "<td>Rs." + pro.getPrice() + "</td>"
                        + "<td>"
                        + "<a href='edit_product.jsp?pid=" + pro.getProduct().getId() + "&sid=" + pro.getSize().getId() + "'>"
                        + "    <span class='glyphicon glyphicon-edit'></span>"
                        + "</a>"
                        + "</td>"
                        + "</tr>"
                        + "<tr> ";

            }

            Criteria criteriaCount = ses.createCriteria(ProductHasSize.class);
            criteriaCount.setProjection(Projections.rowCount());
            totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();

            int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;

            if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
                noOfPages = noOfPages + 1;
            }

            for (int j = 1; j <= noOfPages; j++) {
                if (noOfPages > 1) {
                    String myurl = "products.jsp?pgIndex=" + j;
                    if (j == pageIndex) {
                        s2 += "<span class='current'>" + j + "</span>";
                    } else {
                        s2 += "<a href='" + myurl + "'>" + j + "</a>";
                    }

                }
            }
            ses.close();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("[{\"d1\":\"" + s1 + "\",\"d2\":\"" + s2 + "\"}]");

        }
    }

}
