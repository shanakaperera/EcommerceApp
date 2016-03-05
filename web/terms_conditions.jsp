<%-- 
    Document   : terms_conditions
    Created on : Feb 15, 2016, 10:29:57 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.TermsConditions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus- Terms and Conditions</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>
    <%
        Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
        TermsConditions conditions = (TermsConditions) s.load(TermsConditions.class, 1);
    %>
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2>TERMS AND CONDITIONS</h2>
                    <hr/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <%=conditions.getMainSec()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>User Account, Password, and Security : </h4>
                    <%=conditions.getSecuritySec()%>
                </div>

            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>Services Offered :</h4>
                    <%=conditions.getServiceOffered()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>Privacy Policy :</h4> 
                    <%=conditions.getPrivacyPolicy()%>
                </div>
            </div><hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>User Conduct and Rules :</h4>
                    <%=conditions.getUsrCnductRules()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>No Compensation Policy : </h4>
                    <%=conditions.getCompensationPolicy()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>Exactness Not Guaranteed :</h4>
                    <%=conditions.getExtnessGrnteed()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>Disclaimer Of Warranties/Limitation Of Liability :</h4>
                    <%=conditions.getLimitationOfLiability()%>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col-md-12">
                    <h4>Pricing : </h4>
                    <%=conditions.getPricing()%>
                </div>
            </div>
            <hr/>

            <div class="row">
                <div class="col-md-12">
                    <h4>Service Fees :  </h4>
                    <%=conditions.getServiceFees()%>
                </div>
            </div>
            <hr/>

            <div class="row">
                <div class="col-md-12">
                    <h4>Delivery :  </h4>
                    <%=conditions.getDelivery()%>

                </div>
            </div>
        </div>
        <%
            s.close();
        %>
        <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
        <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

        <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
</html>
