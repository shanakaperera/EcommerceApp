/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;


import com.certus.dbmodel.TermsConditions;
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
@WebServlet(name = "loadSiteTermsAndConditionsAction", urlPatterns = {"/loadSiteTermsAndConditionsAction"})
public class loadSiteTermsAndConditionsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String html = "";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        TermsConditions conditions = (TermsConditions) s.load(TermsConditions.class, 1);
        html += "<tr>"
                + "    <td>Main Section : </td>"
                + "    <td>"
                + "        <textarea id=\"mainSec\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getMainSec() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>User Account, Password, and Security : </td>"
                + "    <td>\n"
                + "        <textarea id=\"securedSec\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getSecuritySec() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Services Offered : </td>"
                + "    <td>"
                + "        <textarea id=\"serviceOff\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getServiceOffered() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Privacy Policy : </td>"
                + "    <td>"
                + "        <textarea id=\"privcyPol\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getPrivacyPolicy() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>User Conduct and Rules : </td>"
                + "    <td>"
                + "        <textarea id=\"condRuls\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getUsrCnductRules() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>No Compensation Policy : </td>\n"
                + "    <td>"
                + "        <textarea id=\"comPolyT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getCompensationPolicy() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Exactness Not Guaranteed : </td>"
                + "    <td>"
                + "        <textarea id=\"extNotGurntT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getExtnessGrnteed() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Disclaimer Of Warranties/Limitation Of Liability : </td>"
                + "    <td>"
                + "        <textarea id=\"limitationT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getLimitationOfLiability() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Pricing : </td>"
                + "    <td>"
                + "        <textarea id=\"pricingT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getPricing() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Service Fees : </td>"
                + "    <td>"
                + "        <textarea id=\"serviceFeesT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getServiceFees() + "</textarea>"
                + "    </td>"
                + "</tr>"
                + "<tr>"
                + "    <td>Delivery : </td>"
                + "    <td>"
                + "        <textarea id=\"delivryT\" class=\"form-control\" rows=\"5\" cols='90'>" + conditions.getDelivery() + "</textarea>"
                + "    </td>"
                + "</tr>";
        s.close();
        response.getWriter().write(html);
    }

}
