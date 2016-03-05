<%-- 
    Document   : navigation
    Created on : Dec 25, 2015, 8:30:43 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.Brand"%>
<%@page import="java.util.Set"%>
<%@page import="com.certus.dbmodel.SubCategory"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.Category"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<style type="text/css">
    #main-nav .nav li a {
        text-transform: uppercase;
    }

</style>
<div class="navbar bs-docs-nav" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".bs-navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <nav id="main-nav" class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">home</a></li>

                <%
                    Session ses = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                    Criteria criteria = ses.createCriteria(Category.class);
                    List<Category> catList = criteria.list();
                    for (Category category : catList) {

                %>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%=category.getCatName()%> <b class="caret"></b></a>
                    <ul class="dropdown-menu">

                        <%
                            Set<SubCategory> subCats = category.getSubCategories();
                            for (SubCategory subCat : subCats) {
                        %>
                        <li><a href="items.jsp?cat=<%=category.getId()%>&sub=<%=subCat.getId()%>"><%=subCat.getSubCategoryName()%></a></li>
                            <%}%>
                    </ul>
                </li>  
                <%}%>
                
                
             
                
                <li><a href="my-account.jsp">My Account</a></li>
                <li><a href="contact-us.jsp">Contact Us</a></li>
                <li><a href="terms_conditions.jsp">Terms and Conditions</a></li>
                
            </ul>
        </nav>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div id="msgAlrt" class="alert alert-success pull-right" data-alert="alert" style="display: none;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>

        </div>
    </div>
</div>
