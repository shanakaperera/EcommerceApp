/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "mainSearchBarAction", urlPatterns = {"/mainSearchBarAction"})
public class mainSearchBarAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            String pNameMatch = request.getParameter("pName");
            // System.out.println("Start");
            Criteria cr = s.createCriteria(ProductHasSize.class, "phs");
            cr.createAlias("phs.product", "product")
                    .createAlias("phs.size", "size")
                    .createAlias("product.brand", "brand")
                    .add(Restrictions.eq("product.availability", true))
                    .add(Restrictions.eq("brand.availability", true))
                    .add(Restrictions.like("product.name", pNameMatch, MatchMode.ANYWHERE))
                    .setProjection(Projections.groupProperty("product"));
            // System.out.println("End");

            List<Product> pros = cr.list();

            String html = "";
            html += "<div class=\"items\">\n"
                    + " <div class=\"container\">\n"
                    + "	<div class=\"row\">\n"
                    + "	<div class=\"col-md-12\">\n"
                    + "	<h3 class=\"title\">Search Results</h3>\n"
                    + "	  </div>";

            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            String productsPath1 = (String) env1.lookup("uploadpathproducts");

            for (Product pro : pros) {
                ProductHasSize ph = productDetails(pro, s);

                html += "<div class='col-md-3 col-sm-4'>"
                        + " <div class=\"item\">\n"
                        + "     <!-- Item image -->\n"
                        + "     \n";
                if (ph.getProduct().getDiscountPrice() != 0) {

                    html += "<span class=\"ico pull-right\"><img src=\"img/sale.png\" alt=\"\"></span>\n";
                }
                html += "   <div class=\"item-image\">\n"
                        + "       \n"
                        + "       <a href=\"single-item.jsp?cat=" + ph.getProduct().getSubCategory().getCategory().getId() + "&sub=" + ph.getProduct().getSubCategory().getId() + "&pid=" + ph.getProduct().getId() + "\"><img src=\"" + productsPath1 + ph.getProduct().getImageMain() + "\" alt=\"\" class=\"img-responsive\"></a>\n"
                        + "   </div>\n"
                        + "   <!-- Item details -->\n"
                        + "   <div class=\"item-details\">\n"
                        + " <!-- Name -->\n"
                        + " <!-- Use the span tag with the class \"ico\" and icon link (hot, sale, deal, new) -->\n"
                        + " <div id=\"quickfit\">\n"
                        + "     <h5 style=\"white-space: nowrap;overflow: hidden;\"><a href=\"single-item.jsp?cat=" + ph.getProduct().getSubCategory().getCategory().getId() + "&sub=" + ph.getProduct().getSubCategory().getId() + "&pid=" + ph.getProduct().getId() + "\">" + ph.getProduct().getName() + "</a></h5>\n"
                        + " </div>\n"
                        + " <div class=\"clearfix\"></div>\n"
                        + " <!-- Para. Note more than 2 lines. -->\n"
                        + " <p>" + ph.getProduct().getBrand().getBrandName() + "</p>\n"
                        + " <hr>\n"
                        + " <!-- Price -->\n"
                        + "       <div class=\"item-price pull-left\">\n";
                if (ph.getProduct().getDiscountPrice() != 0.0) {
                    html += "   <del>Rs.&nbsp;" + ph.getPrice() + "</del>\n"
                            + "   <p style=\"color: #F25758;\">Rs.&nbsp;" + ph.getDiscountPrice(ph.getPrice(), ph.getProduct().getDiscountPrice()) + "</p>\n";
                } else {
                    html += "Rs " + ph.getPrice();
                }
                html += "</div>\n"
                        + " <form action=\"addToCartAction\" method=\"GET\">\n"
                        + " <input name=\"pro_id\" value=\"" + ph.getProduct().getId() + "\" type=\"hidden\">\n"
                        + " <input name=\"size\" value=\"" + ph.getSize(ph.getProduct().getProductHasSizes(), ph.getProduct().getId()) + "\" type=\"hidden\">\n"
                        + " <input name=\"qnty\" value=\"1\" type=\"hidden\">\n"
                        + " <input name=\"dom\" value=\"tyt\" type=\"hidden\">\n"
                        + " <div class=\"button pull-right\">\n"
                        + "     <button class=\"btn\" style=\"background-color: #F25758; color: #efd8d8;\" type=\"submit\">Add to Cart</button>\n"
                        + " </div>\n"
                        + "        </form>\n"
                        + "        <div class=\"clearfix\"></div>\n"
                        + "    </div>\n"
                        + "</div>\n"
                        + "</div>";
            }

            html += "</div></div>";
            s.close();
            response.getWriter().write(html);
        } catch (NamingException ex) {
            Logger.getLogger(mainSearchBarAction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ProductHasSize productDetails(Product p, Session s) {
        return (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                .add(Restrictions.eq("phs.product", p))
                .addOrder(Order.asc("phs.price"))
                .setMaxResults(1)
                .uniqueResult();

    }

}
