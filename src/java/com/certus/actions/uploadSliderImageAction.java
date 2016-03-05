/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.actions;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author shanaka
 */
@WebServlet(name = "uploadSliderImageAction", urlPatterns = {"/uploadSliderImageAction"})
public class uploadSliderImageAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getServletContext().getRealPath("img/slider").replace("build/", "");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
                List<FileItem> multiparts = upload.parseRequest(request);
                StringBuilder sb = null;
                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        double randomA = Math.random() * 1000000000;
                        int randA = (int) randomA;
                        String name = new File(item.getName()).getName();
                        sb = new StringBuilder(name);
                        sb.replace(0, name.length() - 4, "slider-" + randA);
                        item.write(new File(path + File.separator + sb));
                    }
                }
                String pathTo = path + File.separator + sb;
                response.getWriter().write(pathTo.substring(pathTo.lastIndexOf("img")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
