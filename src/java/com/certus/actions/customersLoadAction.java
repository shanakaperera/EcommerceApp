/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Order;
import com.certus.dbmodel.User;
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
@WebServlet(name = "customersLoadAction", urlPatterns = {"/customersLoadAction"})
public class customersLoadAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int pageIndex = 0;
        int totalNumberOfRecords = 0;
        int numberOfRecordsPerPage = 9;

        String sPageIndex = request.getParameter("pgIndex");
        pageIndex = sPageIndex == null ? 1 : Integer.parseInt(sPageIndex);

        int s = (pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage;
        List<User> uList = ses.createCriteria(User.class)
                .setFirstResult(s)
                .setMaxResults(numberOfRecordsPerPage)
                .list();

        String s1 = "", s2 = "";
        for (User u : uList) {
            String avlOn = u.isAvailability() ? "btn-danger" : "btn-default";
            String avlOff = u.isAvailability() ? "btn-default" : "btn-danger";
            String orders = orderCount(u, ses) != 0 ? "<button title='view order' onclick='loadModal(" + loadOrders(u, ses) + ");' class='btn btn-default' data-dismiss='modal' data-target='#ViewOrdersModal' data-toggle='modal'>" + orderCount(u, ses) + "</button>" : "<span style='margin-left:10%;'>" + orderCount(u, ses) + "</span>";
            s1 += "<tr>"
                    + "<td>" + u.getFName() + " " + u.getLName() + "</td>"
                    + "<td>"
                    + "<div class='btn-group btn-toggle'>"
                    + "<button class='btn btn-xs " + avlOn + "' onclick='onBtnClick(" + u.getId() + "," + pageIndex + ");' type='button'>ON</button>"
                    + "<button class='btn btn-xs " + avlOff + "' onclick='offBtnClick(" + u.getId() + "," + pageIndex + ");' type='button'>OFF</button>"
                    + "</div>"
                    + "</td>"
                    + "<td>" + u.getEmail() + "</td>"
                    + "<td>" + orders + "</td>"
                    + "<td>"
                    + "<a href='edit_customer.jsp?uid=" + u.getId() + "'>"
                    + "    <span class='glyphicon glyphicon-edit'></span>"
                    + "</a>"
                    + "</td>"
                    + "</tr>"
                    + "<tr> ";
        }

        Criteria criteriaCount = ses.createCriteria(User.class);
        criteriaCount.setProjection(Projections.rowCount());
        totalNumberOfRecords = (int) (long) (Long) criteriaCount.uniqueResult();

        int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;

        if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
            noOfPages = noOfPages + 1;
        }

        for (int j = 1; j <= noOfPages; j++) {
            if (noOfPages > 1) {
                String myurl = "customers.jsp?pgIndex=" + j;
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

    public int orderCount(User u, Session s) {
        List<Order> oList = s.createCriteria(Order.class, "ord")
                .add(Restrictions.eq("ord.user", u)).list();
        return oList.size();
    }

    public String loadOrders(User u, Session s) {
        List<Order> oList = s.createCriteria(Order.class, "ord")
                .add(Restrictions.eq("ord.user", u)).list();
        String orders = "";
        for (Order order : oList) {
            orders += order.getId();
        }
        return orders;
    }

}
