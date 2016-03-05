/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.EmailSettings;
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
@WebServlet(name = "emailSettingsAction", urlPatterns = {"/emailSettingsAction"})
public class emailSettingsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "";

        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        EmailSettings stttings = (EmailSettings) s.createCriteria(EmailSettings.class).uniqueResult();
        html = "<tr>"
                + "<td>Mail Address Configured : </td>"
                + "<td>"
                + "<div class='input-group'>"
                + "    <input type=\"text\" id=\"emailAdd\" value=\"" + stttings.getUser() + "\" class=\"form-control\" size='70' >"
                + "<span class=\"input-group-addon\" style='color:red;'>*</span> "
                + "</div></td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Host : </td>"
                + "    <td>"
                + "<div class='input-group'>"
                + "        <input type=\"text\" id=\"hostN\" value=\"" + stttings.getHost() + "\"  class=\"form-control\" size='70'>"
                + "<span class=\"input-group-addon\" style='color:red;'>*</span> "
                + "</div></td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Port Number : </td>"
                + "<td>"
                + "<div class='input-group'>"
                + "        <input type=\"text\" id=\"portN\"  value=\"" + stttings.getPort() + "\"  class=\"form-control\" size='70'>"
                + "<span class=\"input-group-addon\" style='color:red;'>*</span> "
                + "</div></td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Password : </td>"
                + "    <td>"
                + "<div class='input-group'>"
                + "        <input type=\"text\" id=\"psswrdN\" value=\"" + stttings.getPassword() + "\"  class=\"form-control\" size='70'>"
                + "<span class=\"input-group-addon\" style='color:red;'>*</span> "
                + "</div></td>"
                + "</tr>";
        s.close();
        response.getWriter().write(html);
    }

}
