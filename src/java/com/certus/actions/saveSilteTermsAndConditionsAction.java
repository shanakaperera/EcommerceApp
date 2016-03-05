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
@WebServlet(name = "saveSilteTermsAndConditionsAction", urlPatterns = {"/saveSilteTermsAndConditionsAction"})
public class saveSilteTermsAndConditionsAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mainSec = request.getParameter("mainSec");
        String securedSec = request.getParameter("securedSec");
        String serviceOff = request.getParameter("serviceOff");
        String privcyPol = request.getParameter("privcyPol");
        String condRuls = request.getParameter("condRuls");
        String comPolyT = request.getParameter("comPolyT");
        String extNotGurntT = request.getParameter("extNotGurntT");
        String limitationT = request.getParameter("limitationT");
        String pricingT = request.getParameter("pricingT");
        String serviceFeesT = request.getParameter("serviceFeesT");
        String delivryT = request.getParameter("delivryT");
        System.out.println("work here");
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        TermsConditions conditions = (TermsConditions) s.load(TermsConditions.class, 1);
        if (!mainSec.isEmpty() && !securedSec.isEmpty() && !serviceOff.isEmpty() && !privcyPol.isEmpty()
                && !condRuls.isEmpty() && !comPolyT.isEmpty() && !extNotGurntT.isEmpty()
                && !extNotGurntT.isEmpty() && !limitationT.isEmpty() && !pricingT.isEmpty()
                && !serviceFeesT.isEmpty() && !delivryT.isEmpty()) {
            System.out.println("work here too");
            s.beginTransaction();
            conditions.setMainSec(mainSec);
            conditions.setSecuritySec(securedSec);
            conditions.setServiceFees(serviceFeesT);
            conditions.setServiceOffered(serviceOff);
            conditions.setPrivacyPolicy(privcyPol);
            conditions.setUsrCnductRules(condRuls);
            conditions.setCompensationPolicy(comPolyT);
            conditions.setExtnessGrnteed(extNotGurntT);
            conditions.setLimitationOfLiability(limitationT);
            conditions.setPricing(pricingT);
            conditions.setServiceFees(serviceFeesT);
            conditions.setDelivery(delivryT);
            s.update(conditions);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("You have successfully saved the changes.");
        }
    }

}
