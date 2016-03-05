/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.Categories;
import com.certus.dbmodel.Category;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "CategoriesAction", urlPatterns = {"/CategoriesAction"})
public class CategoriesAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        List<Category> catList = ses.createCriteria(Category.class).list();
        Categories categories = new Categories();
        for (Category cat : catList) {
            String avlOn = cat.isAvailability() ? "btn-danger" : "btn-default";
            String avlOff = cat.isAvailability() ? "btn-default" : "btn-danger";
           response.getWriter().write("<tr> \n"
                    + " <td>" + cat.getCatName().toUpperCase() + "</td> \n"
                    + " <td>\n"
                    + "<div class='btn-group btn-toggle'>"
                    + "<button class='btn btn-xs " + avlOn + "' onclick='onBtnClick(" + cat.getId() + ");' type='button'>ON</button>"
                    + "<button class='btn btn-xs " + avlOff + "' onclick='offBtnClick(" + cat.getId() + ");' type='button'>OFF</button>"
                    + "</div>"
                    + " </td>\n"
                    + " <td>\n"
                    + "     <button type=\"button\" class=\"btn btn-default\" data-dismiss='modal' onclick='getCatId(" + cat.getId() + ");' data-target='#ViewAllProModal' data-toggle='modal'>\n"
                    + "         <span class=\"glyphicon glyphicon-gift\"></span>&nbsp;&nbsp;" + categories.ProductCount(cat.getId()) + " \n"
                    + "     </button>\n"
                    + " </td>\n"
                    + " <td>\n"
                    + "     <button type=\"button\" class=\"btn btn-default\" data-dismiss='modal' onclick='getCatIdForSub(" + cat.getId() + ");' data-target='#ViewAllSubModal' data-toggle='modal'>\n"
                    + "         <span class=\"glyphicon glyphicon-folder-open\"></span>&nbsp;&nbsp;" + categories.SubCategoryCount(cat.getId()) + " \n"
                    + "     </button>\n"
                    + " </td>\n"
                    + " <td>\n"
                    + "<button type=\"button\" id='" + cat.getCatName() + "-" + cat.getId() + "-" + cat.isAvailability() + "' class=\"btn btn-default myBtn\" data-dismiss=\"modal\" data-target=\"#modal2\" data-toggle=\"modal\">\n"
                    + " <span class=\"glyphicon glyphicon-edit\"></span>\n"
                    + "     </button>\n"
                    + " </td>\n"
                    + "</tr>");
        }
    }

}
