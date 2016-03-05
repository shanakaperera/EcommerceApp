/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import com.certus.dbmodel.SubCategory;
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
@WebServlet(name = "ChooseSubCatAction", urlPatterns = {"/ChooseSubCatAction"})
public class ChooseSubCatAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("cat_id") != null && request.getParameter("pid") != null) {
            Session session = com.certus.connection.HibernateUtil.getSessionFactory().openSession();

            List<SubCategory> lst = session.createCriteria(com.certus.dbmodel.SubCategory.class, "Sub_cat")
                    .createAlias("Sub_cat.category", "category")
                    .add(Restrictions.eq("category.id", Integer.parseInt(request.getParameter("cat_id"))))
                    .list();
            Product pro = (Product) session.load(Product.class, Integer.parseInt(request.getParameter("pid")));
            
            String sCats = "";
            for (SubCategory cat : lst) {
                String s;
                if (pro.getSubCategory().getId() == cat.getId()) {
                    s = " selected='selected'";
                } else {
                    s = "";
                }
                sCats += "<option value='" + cat.getId() + "' " + s + ">" + cat.getSubCategoryName().substring(0, 1).toUpperCase()
                        + cat.getSubCategoryName().substring(1) + "</option>";
            }
            session.close();
            response.getWriter().write(sCats);

        }
    }

}
