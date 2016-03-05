/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import java.io.IOException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "productsForReviewsAction", urlPatterns = {"/productsForReviewsAction"})
public class productsForReviewsAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        String productsPath1 = "";
        try {
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            productsPath1 = (String) env1.lookup("uploadpathproducts");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (request.getParameter("category") != null) {

            List<Product> products = ses.createCriteria(Product.class, "pro")
                    .createAlias("pro.subCategory", "sub")
                    .createAlias("sub.category", "cat")
                    .add(Restrictions.eq("sub.subCategoryName", request.getParameter("sub_category")))
                    .add(Restrictions.eq("cat.catName", request.getParameter("category")))
                    .list();
            String html = "";
            if (!products.isEmpty()) {
                
                for (Product product : products) {
                    html += "<li class='media'>"
                            + "<a class='pull-left'>"
                            + "<img class='media-object' src='" + productsPath1 + product.getImageMain() + "' width='100'>"
                            + "</a>"
                            + "<div class='media-body'>"
                            + "<button type='button' class='btn btn-danger pull-right reviewBtn' onclick='viewReviews("+product.getId()+");' data-dismiss='modal' data-target='#modal1' data-toggle='modal' >View Reviews</button>"
                            + "<h4 class='media-heading'>" + product.getName() + "</h4>"
                            + "<p>Brand : " + product.getBrand().getBrandName() + "</p>"
                            + "<p>Ratings :</p>"
                            + "</div>"
                            + "</li>";
                }
                ses.close();
                response.getWriter().write(html);
            } else {
                html="<li class='media'>"
                            + "<div class='media-body'>"
                            + "<h2 style='text-align: center' class='media-heading'>No Content Available</h2>"
                            + "</div>"
                            + "</li>";
                response.getWriter().write(html);
            }
        }
    }

}
