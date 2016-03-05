/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Expences;
import com.certus.dbmodel.ProductHasSize;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "addToStockAction", urlPatterns = {"/addToStockAction"})
public class addToStockAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        int newQnty = Integer.parseInt(request.getParameter("newQnty"));
        int total = Integer.parseInt(request.getParameter("total"));
        int oldQnty = Integer.parseInt(request.getParameter("oldQnty"));

        int pid = Integer.parseInt(request.getParameter("pid"));
        String size = request.getParameter("size");
        ProductHasSize phs = (ProductHasSize) s.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product")
                .createAlias("phs.size", "size")
                .add(Restrictions.eq("product.id", pid))
                .add(Restrictions.eq("size.sizeName", size)).uniqueResult();
        s.beginTransaction();
        phs.setQnty(newQnty + oldQnty);
        s.update(phs);
        s.getTransaction().commit();

        Expences expence = new Expences();
        s.beginTransaction();
        expence.setDate(new Date(new java.util.Date().getTime()));
        expence.setQnty(newQnty);
        expence.setTotal(total);
        expence.setStockqntyBfUpdate(oldQnty);
        expence.setProductHasSize(phs);
        s.save(expence);
        s.getTransaction().commit();
        int id = phs.getProduct().getBrand().getId();
        s.close();
        response.getWriter().write(id + "");
    }

}
