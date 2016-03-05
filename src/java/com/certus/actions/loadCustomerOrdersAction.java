/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "loadCustomerOrdersAction", urlPatterns = {"/loadCustomerOrdersAction"})
public class loadCustomerOrdersAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<Order> oList = s.createCriteria(Order.class, "order")
                .createAlias("order.user", "user")
                .add(Restrictions.eq("user.id", Integer.parseInt(request.getParameter("uid"))))
                .list();
        String html = "";
        if (!oList.isEmpty()) {

            html += "<table class='table table-striped tcart'>"
                    + "<thead>"
                    + "<tr>"
                    + "<th>Date</th>"
                    + "<th>Order ID</th>"
                    + "<th>Price</th>"
                    + "<th>Status</th>"
                    + "<th>View Details</th>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>";

            for (Order o : oList) {
                html += "<tr>"
                        + "<td>" + o.getDateOrdered() + "</td>"
                        + "<td>" + o.getInvoNum() + "</td>"
                        + "<td>Rs. " + o.getGrandTot() + "</td>"
                        + "<td>" + o.getStatus() + "</td>"
                        + "<td><button class='btn btn-default' type='button' data-dismiss='modal' data-target='#viewOrderInfo' data-toggle='modal' onclick='getOrderInfo(" + o.getId() + ");'><span class='glyphicon glyphicon-eye-open'></span></button></td>"
                        + "</tr>";
            }
            html += "</tbody></table>";
        } else {
            html += "<h4>No Recent Orders.</h4>";
        }
        s.close();
        response.getWriter().write(html);
    }

}
