<%-- 
    Document   : breadcrumb
    Created on : Dec 26, 2015, 3:31:11 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- Breadcrumb -->
<ul class="breadcrumb">
    <li><a href="index.jsp">Home</a></li>
    <li><a><%=items.getSelectedCategoryById(request.getParameter("cat"))%></a></li>
    <li class="active"><%=items.getSelectedSubCategoryById(request.getParameter("sub"))%></li>
</ul>
