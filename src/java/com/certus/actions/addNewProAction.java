/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Brand;
import com.certus.dbmodel.Category;
import com.certus.dbmodel.Product;
import com.certus.dbmodel.ProductHasSize;
import com.certus.dbmodel.Size;
import com.certus.dbmodel.SubCategory;
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
@WebServlet(name = "addNewProAction", urlPatterns = {"/addNewProAction"})
public class addNewProAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("proPrice") != null && request.getParameter("proName") != null) {
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Category c = (Category) s.load(Category.class, Integer.parseInt(request.getParameter("proCat").split(">")[0].trim()));
            SubCategory sub = (SubCategory) s.load(SubCategory.class, Integer.parseInt(request.getParameter("proCat").split(">")[1].trim()));
            Brand b = (Brand) s.load(Brand.class, Integer.parseInt(request.getParameter("selBrand").trim()));
            Size size = (Size) s.load(Size.class, Integer.parseInt(request.getParameter("proSize").trim()));
            String proName = request.getParameter("proName");
            double price = Double.parseDouble(request.getParameter("proPrice").trim());
            if (request.getParameter("esnHid").isEmpty()) {
                s.beginTransaction();
                Product pro = new Product();
                pro.setBrand(b);
                pro.setSpecs("<tbody></tbody>");
                pro.setSubCategory(sub);
                pro.getSubCategory().setCategory(c);
                pro.setName(proName);
                ProductHasSize phs = new ProductHasSize();
                phs.setProduct(pro);
                phs.setPrice(price);
                phs.setSize(size);
                s.save(phs);
                s.getTransaction().commit();
                s.close();
            } else {
                Product pro = (Product) s.createCriteria(Product.class, "pro")
                        .add(Restrictions.eq("pro.name", proName)).list().get(0);
                s.beginTransaction();
                ProductHasSize phs = new ProductHasSize();
                phs.setProduct(pro);
                phs.setPrice(price);
                phs.setSize(size);
                s.save(phs);
                s.getTransaction().commit();
                s.close();
            }
            response.sendRedirect("products.jsp");

        }

    }
}
