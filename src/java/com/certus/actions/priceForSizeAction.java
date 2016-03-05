/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProductHasSize;
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
@WebServlet(name = "priceForSizeAction", urlPatterns = {"/priceForSizeAction"})
public class priceForSizeAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("size") != null && request.getParameter("pro_id") != null) {
            Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProductHasSize prohs = (ProductHasSize) session.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "pro").createAlias("phs.size", "size")
                    .add(Restrictions.eq("size.sizeName", request.getParameter("size")))
                    .add(Restrictions.eq("pro.id", Integer.parseInt(request.getParameter("pro_id"))))
                    .uniqueResult();
            if (prohs.getProduct().getDiscountPrice() != 0) {
                String element = prohs.getPrice() + "-" + prohs.getDiscountPrice(prohs.getPrice(), prohs.getProduct().getDiscountPrice());
                session.close();
                response.getWriter().write(element);
            } else {
                String element = "" + prohs.getPrice();
                session.close();
                response.getWriter().write(element);
            }
        }
    }

}
