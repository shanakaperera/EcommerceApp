/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.controllers.EmailUtility;
import com.certus.dbmodel.Administor;
import com.certus.dbmodel.EmailSettings;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "emailValidateAdminAction", urlPatterns = {"/emailValidateAdminAction"})
public class emailValidateAdminAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emiilOfAdmin = request.getParameter("email");
        String resultMessage = "";
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        if (emiilOfAdmin != null) {
            Administor admin = (Administor) s.createCriteria(Administor.class, "admin")
                    .add(Restrictions.eq("admin.email", emiilOfAdmin))
                    .uniqueResult();
            if (admin != null) {
                EmailSettings es = (EmailSettings) s.load(EmailSettings.class, 1);
                try {
                    String adminId =  String.valueOf(admin.getId());
                    BasicPasswordEncryptor encript = new BasicPasswordEncryptor();
                    EmailUtility.sendEmail(es.getHost(), String.valueOf(es.getPort()),
                            es.getUser(), es.getPassword(), admin.getEmail(), "Reset Password", 
                            "http://localhost:8080/ECommerceApp/resetPasswordAdmin.jsp?mdfk=" + encript.encryptPassword(adminId));
                    resultMessage = "The e-mail was sent successfully to your email account . Please Check it.";
                    s.close();
                    response.getWriter().write(resultMessage);
                } catch (MessagingException ex) {
                    //Logger.getLogger(emailValidateAdminAction.class.getName()).log(Level.SEVERE, null, ex);
                    response.setStatus(500);
                }
            } else {
                response.setStatus(500);
                
            }

        }
    }

}
