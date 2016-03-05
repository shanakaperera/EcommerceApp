/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.Review;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "loadReviewsForProductAction", urlPatterns = {"/loadReviewsForProductAction"})
public class loadReviewsForProductAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Product p = (Product) s.load(Product.class, Integer.parseInt(request.getParameter("pid")));
        String html = "";
        Set<Review> reviews = p.getReviews().stream().sorted(Comparator.comparing(d -> d.getDateComnt())).collect(Collectors.toSet());
        if (!reviews.isEmpty()) {
            for (Review r : reviews) {
                String aval=r.isAvailability()?"btn-danger":"btn-default";
                String not_aval=r.isAvailability()?"btn-default":"btn-danger";
                html += "<li class='list-group-item'>"
                        + "<div class='row'>"
                        + "<div class='col-md-10'>"
                        + "<h4>" + r.getUser().getFName() + " " + r.getUser().getLName() + "</h4><small>" + r.getDateComnt() + "</small>"
                        + "<p>" + r.getComment() + "</p>"
                        + "</div>"
                        + "<div class='col-md-2'>"
                        + "<div class='btn-group btn-toggle'>"
                        + "<button class='btn btn-xs "+aval+"' onclick='oNBtnClicked("+r.getId()+","+r.getProduct().getId()+");' type='button'>Enable&nbsp;</button>"
                        + "<button class='btn btn-xs "+not_aval+"' onclick='offBtnClicked("+r.getId()+","+r.getProduct().getId()+");' type='button'>Disable</button>"
                        + "</div>"
                        + "</div>"
                        + "</li> ";
            }
            s.close();
            response.getWriter().write(html);
        } else {
            html = "<li class='list-group-item'>"
                    + "<div class='row'>"
                    + "<div class='col-md-12'>"
                    + "<h3>No Reviews available for this product.</h3>"
                    + "</div>"
                    + "</div>"
                    + "</li> ";
            response.getWriter().write(html);
        }

    }

}
