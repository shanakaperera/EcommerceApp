/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.ProImg;
import com.certus.dbmodel.Product;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "uploadImageAction", urlPatterns = {"/uploadImageAction"})
public class uploadImageAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (MultipartFormDataRequest.isMultipartFormData(request)) {
           
            UploadBean bean = (UploadBean) request.getSession().getAttribute("upBean");
            try {
                MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
                String todo = null;
                String brand = null;
                String pName = null;
                int pid = 0;
                int sid = 0;
                if (mrequest != null) {
                    todo = mrequest.getParameter("todo");
                    brand = mrequest.getParameter("brnd");
                    pName = mrequest.getParameter("pName");
                    pid = Integer.parseInt(mrequest.getParameter("pid"));
                    sid = Integer.parseInt(mrequest.getParameter("sid"));
                }

                if ((todo != null) && (todo.equalsIgnoreCase("upload"))) {
                    Hashtable files = mrequest.getFiles();
                    if ((files != null) && (!files.isEmpty())) {
                        UploadFile file = (UploadFile) files.get("uploadfile");

                        String fileType;
                        String result;
                        long fileSize;
                        fileType = file.getContentType();
                        fileSize = file.getFileSize();

                        double randomA = Math.random() * 1000000000;
                        int randA = (int) randomA;
                        file.setFileName(brand + "-" + pName + "-" + randA + ".jpg");

                        if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                            if (fileSize <= 700000) {
                                bean.store(mrequest, "uploadfile");
                                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                                Product p = (Product) s.load(Product.class, pid);
                                s.beginTransaction();
                                ProImg img = new ProImg();
                                img.setImage(file.getFileName());
                                img.setProduct(p);
                                s.save(img);
                                s.getTransaction().commit();

                                if (p.getImageMain() == null || p.getImageMain().isEmpty()) {
                                    s.beginTransaction();
                                    p.setImageMain(file.getFileName());
                                    s.update(p);
                                    s.getTransaction().commit();
                                }

                                result = "File Uploaded with no errors...";

                                files.clear();
                                s.close();
                                response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);

                            } else {

                                result = "Please Upload the file size of less than 700 KB";
                            }
                        } else {
                            result = "Please upload a filetype of image/jpeg or image/png";
                        }
                        bean.store(mrequest, "uploadfile");
                        // response.sendRedirect("edit_product.jsp?pid=" + pid + "&sid=" + sid);
                    }
                }

            } catch (UploadException ex) {
                Logger.getLogger(uploadImageAction.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
