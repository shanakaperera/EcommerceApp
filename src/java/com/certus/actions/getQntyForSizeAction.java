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
@WebServlet(name = "getQntyForSizeAction", urlPatterns = {"/getQntyForSizeAction"})
public class getQntyForSizeAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("param") != null) {
            String size = request.getParameter("param").split("-")[0];
            int pid = Integer.parseInt(request.getParameter("param").split("-")[1]);
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                    .createAlias("phs.product", "product")
                    .createAlias("phs.size", "size")
                    .add(Restrictions.eq("product.id", pid))
                    .add(Restrictions.eq("size.sizeName", size)).uniqueResult();
            String element =""+phs.getQnty();
            s.close();
            response.getWriter().write(element);
        }
    }

}
