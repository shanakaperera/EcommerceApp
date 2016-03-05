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
@WebServlet(name = "sizeSelAction", urlPatterns = {"/sizeSelAction"})
public class sizeSelAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getParameter("p_name") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "product")
                    .createAlias("product.subCategory", "subCategory")
                    .createAlias("subCategory.category", "category")
                    .createAlias("phs.size", "size")
                    .add(Restrictions.eq("product.name", request.getParameter("p_name")))
                    .add(Restrictions.eq("subCategory.id", Integer.parseInt(request.getParameter("sub_id"))))
                    .add(Restrictions.eq("category.id", Integer.parseInt(request.getParameter("cat_id"))))
                    .add(Restrictions.eq("size.id", Integer.parseInt(request.getParameter("size_id"))))
                    .uniqueResult();

            if (phs != null) {
                String element = "" + phs.getPrice();
                s.close();
                response.getWriter().write(element);
            }

        }
    }

}
