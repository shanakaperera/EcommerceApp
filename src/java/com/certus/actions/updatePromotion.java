/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Promotions;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "updatePromotion", urlPatterns = {"/updatePromotion"})
public class updatePromotion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           String promoDes = request.getParameter("promoupdateDes");
        String promoEnd = request.getParameter("promoupdateEnd");
        int promoPlace = Integer.parseInt(request.getParameter("promoupdatePlace"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int pid = Integer.parseInt(request.getParameter("pid"));
        int sid = Integer.parseInt(request.getParameter("sid"));
        Date parsed = null;
        try {
            parsed = format.parse(promoEnd);
        } catch (ParseException ex) {
            Logger.getLogger(savePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        Promotions promo = (Promotions) s.load(Promotions.class, promoPlace);
        s.beginTransaction();
        promo.setDateEnded(new java.sql.Date(parsed.getTime()));
        promo.setDescription(promoDes);
        s.update(promo);
        s.getTransaction().commit();
        s.close();
        response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
    }


}
