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
@WebServlet(name = "saveGeneralInfoAction", urlPatterns = {"/saveGeneralInfoAction"})
public class saveGeneralInfoAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        SiteGeneral general = (SiteGeneral) s.load(SiteGeneral.class, 1);
        String addrsG = request.getParameter("addrsG");
        String telG = request.getParameter("telG");
        String mailG = request.getParameter("mailG");
        String footerG = request.getParameter("footerG");
        String mainG = request.getParameter("mainG");
        String usrDescG = request.getParameter("usrDescG");

        if (!addrsG.isEmpty() && !telG.isEmpty() && !mailG.isEmpty()
                && !footerG.isEmpty() && !mainG.isEmpty() && !usrDescG.isEmpty()) {
            s.beginTransaction();
            s.update(general);
            general.setAboutUsFooter(footerG);
            general.setAboutUsMain(mainG);
            general.setSiteAddress(addrsG);
            general.setUsrAcntDesc(usrDescG);
            general.setTel(telG);
            general.setSiteMail(mailG);
            s.getTransaction().commit();
            s.close();
            response.getWriter().write("You have successfully saved the chanages.");

        }

    }

}
