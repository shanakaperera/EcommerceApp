/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "loadOrdersDetailAction", urlPatterns = {"/loadOrdersDetailAction"})
public class loadOrdersDetailAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

        if (request.getParameter("orders") != null) {
            List<Order> oList = new ArrayList<>();
            char orderIds[] = request.getParameter("orders").toCharArray();
            for (int i = 0; i < orderIds.length; i++) {
                int orderId = Integer.parseInt(String.valueOf(orderIds[i]));
                Order order = (Order) s.load(Order.class, orderId);
                oList.add(order);
            }

            String html = "";
            for (Order order : oList) {
                html += " <li class='media cusBoder'>"
                        + "     <h4 class='media-heading'>Order Id : " + order.getInvoNum() + "</h4>"
                        + "     <div class='media-body'>"
                        + "         <div class='row'>"
                        + "             <div class='col-md-8'>"
                        + "                 <table class='spacer'>"
                        + "                     <tr data-pg-collapsed>"
                        + "                         <td><h4>Date Added:</h4></td>"
                        + "                         <td>" + order.getDateOrdered() + "</td>"
                        + "                     </tr>"
                        + "                     <tr data-pg-collapsed>"
                        + "                         <td><h4>Order Status :</h4></td>"
                        + "                         <td>" + order.getStatus() + "</td>"
                        + "                     </tr>"
                        + "                     <tr data-pg-collapsed>"
                        + "                         <td><h4>Order Total :</h4></td>"
                        + "                         <td>Rs. " + order.getGrandTot() + "</td>"
                        + "                     </tr>"
                        + "                     <tr>"
                        + "                         <td><h4>Telephone :</h4></td>"
                        + "                         <td>" + order.getTel() + "</td>"
                        + "                     </tr>"
                        + "                 </table>"
                        + "             </div>"
                        + "             <div class='col-md-4' data-pg-collapsed>"
                        + "                 <a href='edit_order.jsp?oid=" + order.getId() + "' class='btn btn-danger'>View Order</a>"
                        + "             </div>"
                        + "         </div>"
                        + "     </div>"
                        + " </li>";
            }
            s.close();
            response.getWriter().write(html);
        }

    }

}
