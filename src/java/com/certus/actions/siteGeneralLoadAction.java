/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.SiteGeneral;
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
@WebServlet(name = "siteGeneralLoadAction", urlPatterns = {"/siteGeneralLoadAction"})
public class siteGeneralLoadAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        SiteGeneral general = (SiteGeneral) s.load(SiteGeneral.class, 1);

        html += "<tr>"
                + "     <td>Site Address : </td>"
                + "     <td>"
                + "         <div class=\"input-group\">"
                + "             <input type=\"text\" id=\"addrsG\" value=\"" + general.getSiteAddress() + "\" class=\"form-control\" >"
                + "             <span class=\"input-group-addon\" style='color:red;'>*</span>"
                + "         </div>"
                + "     </td>"
                + " </tr>"
                + " <tr>"
                + "     <td>Telephone Number : </td>"
                + "     <td>"
                + "         <div class=\"input-group\">"
                + "             <input type=\"text\" id=\"telG\" value=\"" + general.getTel() + "\" class=\"form-control\" >"
                + "             <span class=\"input-group-addon\" style='color:red;'>*</span>"
                + "         </div>"
                + "     </td>"
                + " </tr>"
                + " <tr>"
                + "     <td>Email Address : </td>"
                + "     <td>"
                + "         <div class=\"input-group\">"
                + "             <input type=\"text\"  id=\"mailG\" value=\"" + general.getSiteMail() + "\" class=\"form-control\" >"
                + "             <span class=\"input-group-addon\" style='color:red;'>*</span>"
                + "         </div>"
                + "     </td>"
                + " </tr>"
                + " <tr>"
                + "     <td>About Us Description Footer :</td>"
                + "     <td>"
                + "         <textarea class=\"form-control\" id=\"footerG\" rows=\"7\" cols='70'>" + general.getAboutUsFooter() + "</textarea>"
                + "     </td>"
                + " </tr>"
                + " <tr>"
                + "     <td>About Us Description Main :</td>"
                + "     <td>"
                + "         <textarea class=\"form-control\" id=\"mainG\" rows=\"10\" cols='70'>" + general.getAboutUsMain() + "</textarea>"
                + "     </td>"
                + " </tr>"
                + " <tr>"
                + "     <td>Your Account Description : </td>"
                + "     <td>"
                + "         <textarea class=\"form-control\" id=\"usrDescG\" rows=\"7\" cols='70'>" + general.getUsrAcntDesc() + "</textarea>"
                + "     </td>"
                + " </tr>";
        s.close();

        response.getWriter().write(html);

    }

}
