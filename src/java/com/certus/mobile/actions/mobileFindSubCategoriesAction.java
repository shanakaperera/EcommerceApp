/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.mobile.actions;

import com.certus.dbmodel.Category;
import com.certus.dbmodel.SubCategory;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
@WebServlet(name = "mobileFindSubCategoriesAction", urlPatterns = {"/mobileFindSubCategoriesAction"})
public class mobileFindSubCategoriesAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        String catName = request.getParameter("catName");
        if (catName != null) {
            Category category =(Category) s.createCriteria(Category.class, "cat")
                    .add(Restrictions.eq("cat.catName", catName))
                    .uniqueResult();
            
            JsonObject jo = new JsonObject();
            JsonArray subCatAry = new JsonArray();
            for(SubCategory sub :category.getSubCategories()){
                JsonObject subJo = new JsonObject();
                subJo.add("sub_id", new JsonPrimitive(sub.getId()));
                subJo.add("sub_name", new JsonPrimitive(sub.getSubCategoryName()));
                subCatAry.add(subJo);
            }
            jo.add("cat_id", new JsonPrimitive(category.getId()));
            jo.add("cat_name", new JsonPrimitive(category.getCatName()));
            jo.add("sub_categories", subCatAry);
            String element = new Gson().toJson(jo);
            response.getWriter().write(element);
        }
    }

}
