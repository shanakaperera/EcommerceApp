/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Brand;
import com.certus.dbmodel.Category;
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

/**
 *
 * @author shanaka
 */
@WebServlet(name = "updateGenaralProAction", urlPatterns = {"/updateGenaralProAction"})
public class updateGenaralProAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("saveGen") != null) {
            int phs_id = Integer.parseInt(request.getParameter("phs_id"));
            int sid = Integer.parseInt(request.getParameter("sid"));
            boolean dis_o_en = Boolean.parseBoolean(request.getParameter("dis_o_en"));
            String pName = request.getParameter("pName");
            String disc_short = request.getParameter("disc_short");
            String disc_long = request.getParameter("disc_long");
            int cat_sel_id = Integer.parseInt(request.getParameter("cat_sel"));
            int sub_cat_sel_id = Integer.parseInt(request.getParameter("sub_cat_sel"));
            int brnd_sel_id = Integer.parseInt(request.getParameter("brnd_sel"));
            double price = Double.parseDouble(request.getParameter("price"));

            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Brand b = (Brand) s.load(Brand.class, brnd_sel_id);
            SubCategory sub_category = (SubCategory) s.load(SubCategory.class, sub_cat_sel_id);
            Size size = (Size) s.load(Size.class, sid);
            Category category = (Category) s.load(Category.class, cat_sel_id);
            s.beginTransaction();
            ProductHasSize phs = (ProductHasSize) s.load(ProductHasSize.class, phs_id);
            phs.getProduct().setBrand(b);
            phs.getProduct().setSubCategory(sub_category);
            phs.setPrice(price);
            phs.setSize(size);
            phs.getProduct().getSubCategory().setCategory(category);
            phs.getProduct().setName(pName);
            phs.getProduct().setAvailability(dis_o_en);
            phs.getProduct().setDescription("<h5>" + disc_short + "</h5><p>" + disc_long + "</p>");
            s.update(phs);
            s.getTransaction().commit();
            int pid = phs.getProduct().getId();
            s.close();
            response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);

        }
    }

}
