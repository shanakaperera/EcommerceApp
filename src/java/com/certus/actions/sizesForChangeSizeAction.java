/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProductHasSize;
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
@WebServlet(name = "sizesForChangeSizeAction", urlPatterns = {"/sizesForChangeSizeAction"})
public class sizesForChangeSizeAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<ProductHasSize> sizes = s.createCriteria(ProductHasSize.class, "phs")
                .createAlias("phs.product", "product")
                .add(Restrictions.eq("product.id", Integer.parseInt(request.getParameter("pid"))))
                .list();
        String html = "";
        for (ProductHasSize si : sizes) {
            html += "<option value='" + si.getSize().getSizeName() + "'>" + si.getSize().getSizeName() + "</option>";
        }
        s.close();
        response.getWriter().write(html);

    }
}
