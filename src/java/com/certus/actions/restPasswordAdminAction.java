/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.AdminNotifications;
import com.certus.dbmodel.Administor;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "restPasswordAdminAction", urlPatterns = {"/restPasswordAdminAction"})
public class restPasswordAdminAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPass = request.getParameter("confirmNew");
        String userId = request.getParameter("uid");
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
        if (newPass != null && userId != null && !newPass.isEmpty() && !userId.isEmpty()) {
            List<Administor> adminList = s.createCriteria(Administor.class).list();
            for (Administor admin : adminList) {
                System.out.println("User id - " + userId);
                if (encryptor.checkPassword(String.valueOf(admin.getId()), userId)) {
                    userId = String.valueOf(admin.getId());
                    System.out.println("work here..");
                }
            }

            Administor adminFound = (Administor) s.load(Administor.class, Integer.parseInt(userId));
            if (adminFound != null) {
                s.beginTransaction();
                adminFound.setPassword(encryptor.encryptPassword(newPass));
                s.update(adminFound);
                s.getTransaction().commit();
                setAdminNotification(s);
                s.close();
                response.getWriter().write("success");
            } else {
                System.out.println("Coudn't find the acount.");
            }
        }
    }

    private void setAdminNotification(Session ses) {
        AdminNotifications notifications = new AdminNotifications();
        ses.beginTransaction();
        notifications.setDate(new java.util.Date());
        notifications.setDescription("Admin Password reset.");
        notifications.setMessage("<h5>You have successfully reset your password. </h5>");
        ses.save(notifications);
        ses.getTransaction().commit();
    }

}
