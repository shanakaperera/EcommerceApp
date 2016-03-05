/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Product;
import java.io.IOException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
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
@WebServlet(name = "filterImgsFeaturedProAction", urlPatterns = {"/filterImgsFeaturedProAction"})
public class filterImgsFeaturedProAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cat_id = Integer.parseInt(request.getParameter("category").split(">")[0].trim());
        int subcat_id = Integer.parseInt(request.getParameter("category").split(">")[1].trim());
        String productsPath = "";
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            productsPath = (String) env.lookup("uploadpathproducts");
        } catch (Exception e) {
        }
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<Product> proList = s.createCriteria(Product.class, "pro")
                .createAlias("pro.subCategory", "subCategory")
                .createAlias("subCategory.category", "category")
                .add(Restrictions.eq("category.id", cat_id))
                .add(Restrictions.eq("subCategory.id", subcat_id)).list();
        String html = "";

        for (Product prduct : proList) {
            html += "<li class='media cusBoder'>"
                    + "<a class='pull-left'>"
                    + "<img class='media-object' src='"
                    + productsPath + prduct.getImageMain() + "' width='100'>"
                    + "</a>"
                    + "<div class='media-body'>"
                    + "<h4 class='media-heading'>" + prduct.getBrand().getBrandName()
                    + " " + prduct.getName() + "</h4>"
                    + "<input type='checkbox' name='selImg' value='Select Image' onclick='selectPro("+prduct.getId()+");' />"
                    + "</div>"
                    + "</li>";
        }
        s.close();
        response.getWriter().write(html);
    }

}
