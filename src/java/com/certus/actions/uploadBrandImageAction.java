/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import com.certus.dbmodel.Brand;
import java.io.IOException;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadFile;
import org.hibernate.Session;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "uploadBrandImageAction", urlPatterns = {"/uploadBrandImageAction"})
public class uploadBrandImageAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (MultipartFormDataRequest.isMultipartFormData(request)) {
            UploadBean bean = (UploadBean) request.getSession().getAttribute("upBrand");
            try {
                MultipartFormDataRequest mrequest = new MultipartFormDataRequest(request);
                String brndName = null;
                String uploadfile = null;
                if (mrequest != null) {
                    brndName = mrequest.getParameter("brndName");
                }
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
                    file.setFileName(brndName + "-" + randA + ".jpg");
                    if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                        if (fileSize <= 700000) {
                            bean.store(mrequest, "uploadfile");
                            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                            s.beginTransaction();
                            Brand b = new Brand();
                            b.setBrandName(brndName);
                            b.setImg(file.getFileName());
                            s.save(b);
                            s.getTransaction().commit();
                            s.close();
                            result = "File Uploaded with no errors...";

                            files.clear();
                            response.sendRedirect("manufactures.jsp");
                        } else {

                            result = "Please Upload the file size of less than 700 KB";
                        }
                    } else {
                        result = "Please upload a filetype of image/jpeg or image/png";
                    }
                    bean.store(mrequest, "uploadfile");
                }
            } catch (Exception e) {
            }

        }
    }

}
