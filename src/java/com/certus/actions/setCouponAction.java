/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Coupon;
import java.io.IOException;
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
@WebServlet(name = "setCouponAction", urlPatterns = {"/setCouponAction"})
public class setCouponAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("cp_code") != null) {

            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Coupon coupon = (Coupon) s.createCriteria(Coupon.class, "c")
                    .add(Restrictions.eq("c.couponCode", request.getParameter("cp_code")))
                    .uniqueResult();

            if (coupon != null) {
                request.getSession().setAttribute("coupon", coupon);
                s.close();
                response.getWriter().write("<div class='alert alert-success' role='alert'>Coupon Applied.</div>");
            } else {
                s.close();
                response.getWriter().write("<div class='alert alert-danger' role='alert'>Coupon is invalid or expired.</div>");
            }

        }
    }

}
