/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.ShoppingCart;
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
@WebServlet(name = "updateProductAction", urlPatterns = {"/updateProductAction"})
public class updateProductAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession(false).getAttribute("cart") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.size", "size")
                    .createAlias("phs.product", "product")
                    .add(Restrictions.eq("size.sizeName", request.getParameter("size")))
                    .add(Restrictions.eq("product.id", Integer.parseInt(request.getParameter("pdtId"))))
                    .uniqueResult();

            ShoppingCart cart = (ShoppingCart) request.getSession(false).getAttribute("cart");

            if (Integer.parseInt(request.getParameter("newQty")) > phs.getQnty()) {
                response.sendError(505);

            } else {
                cart.updateItemCount(Integer.parseInt(request.getParameter("pdtId")),
                        Integer.parseInt(request.getParameter("newQty")),
                        request.getParameter("size"));
                s.close();
                response.getWriter().write("Updated Successfully !");
            }
        }
    }

}
