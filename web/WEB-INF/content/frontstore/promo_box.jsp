<%-- 
    Document   : promo_box
    Created on : Dec 25, 2015, 8:33:09 AM
    Author     : shanaka
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.Promotions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="promo">
    <div class="container">
        <div class="row">


            <%
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                String productsPath = (String) env.lookup("uploadpathproducts");

                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                List<Promotions> promoList = s.createCriteria(Promotions.class).list();
                for (Promotions promo : promoList) {
                    if (promo.getProId().isAvailability()) {
                        String url = "single-item.jsp?cat=" + promo.getProId().getSubCategory().getCategory().getId() + "&sub=" + promo.getProId().getSubCategory().getId() + "&pid=" + promo.getProId().getId();

            %>

            <div class="col-md-4">
                <!-- rcolor =  Red color -->
                <div class="pbox <%=promo.getColor()%>">
                    <div class="pcol-left">
                        <!-- Image -->
                        <a href="<%=url%>"><img src="<%=productsPath + promo.getProId().getImageMain()%>" alt="" /></a>
                    </div>
                    <div class="pcol-right">
                        <!-- Title -->
                        <p class="pmed"><a href="<%=url%>"><%=promo.getProId().getName()%></a></p>
                        <!-- Para -->
                        <p class="psmall"><a href="<%=url%>"><%=promo.getDescription()%></a></p>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>

            <%}
                }
                s.close();
            %>


        </div>
    </div>
</div>
