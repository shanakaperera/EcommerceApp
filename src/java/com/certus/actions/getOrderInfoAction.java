/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.OrderInfo;
import com.certus.dbmodel.Order;
import com.certus.dbmodel.ProductHasSize;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "getOrderInfoAction", urlPatterns = {"/getOrderInfoAction"})
public class getOrderInfoAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Order order = (Order) s.load(Order.class, Integer.parseInt(request.getParameter("oid")));
        String proIdArray[] = order.getProductIds().split(",");
        String qntiesArray[] = order.getQuantities().split(",");
        String sizeArray[] = order.getSizes().split(",");

        List<OrderInfo> oi = new ArrayList<>();
        for (int i = 0; i < proIdArray.length; i++) {
            ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "product")
                    .createAlias("phs.size", "size")
                    .add(Restrictions.eq("product.id", Integer.parseInt(proIdArray[i])))
                    .add(Restrictions.eq("size.sizeName", sizeArray[i]))
                    .uniqueResult();
            oi.add(new OrderInfo(phs, Integer.parseInt(qntiesArray[i])));
        }
        String html = "";
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            String productsPath = (String) env.lookup("uploadpathproducts");

            for (OrderInfo o : oi) {
                html += "<li class='media'>"
                        + "<a class='pull-left' data-pg-collapsed>"
                        + "<img class='media-object' src='" + productsPath+o.getPhs().getProduct().getImageMain() + "' width='100'>"
                        + "</a>"
                        + "<div class='media-body' data-pg-collapsed>"
                        + "<h4 class='media-heading'>" + o.getPhs().getProduct().getName() + "</h4>"
                        + "<p>Size : " + o.getPhs().getSize().getSizeName() + "</p>"
                        + "<p>Quantity : " + o.getQnty() + "</p>"
                        + "<p>Each Price : Rs. " + o.getPhs().getPrice() + "</p>"
                        + "</div>"
                        + "</li> ";
            }

        } catch (Exception e) {
        }
        response.getWriter().write(html);

    }

}
