<%-- 
    Document   : slider
    Created on : Dec 25, 2015, 8:31:16 AM
    Author     : shanaka
--%>

<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.SliderFacts"%>
<%@page import="org.hibernate.Session"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container flex-main">
    <div class="row">
        <div class="col-md-12">

            <div class="flex-image flexslider">
                <ul class="slides">
                    <%
                        Context envSlide = (Context) new InitialContext().lookup("java:comp/env");
                        String sliderImgPath = (String) envSlide.lookup("sliderImgs");

                        Session sez = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                        List<SliderFacts> sf = sez.createCriteria(SliderFacts.class).list();
                        for (SliderFacts slf : sf) {
                    %>

                    <li>
                        <!-- Image -->
                        <img src="<%=sliderImgPath + slf.getImage()%>" alt=""/>
                        <!-- Caption -->
                        <div class="flex-caption">
                            <!-- Title -->
                            <h3><%=slf.getProduct().getName()%> - <span class="color">Just Rs. <%=slf.getProduct().getProductHasSizes().stream().findAny().get().getPrice()%></span></h3>
                            <!-- Para -->
                            <p><%=slf.getDesc()%> </p>
                            <div class="button">
                                <a href="single-item.jsp?cat=<%=slf.getProduct().getSubCategory().getCategory().getId()%>&sub=<%=slf.getProduct().getSubCategory().getId()%>&pid=<%=slf.getProduct().getId()%>">Buy Now</a>
                            </div>
                        </div>
                    </li>

                    <%}
                        sez.close();
                    %>

                </ul>
            </div>

        </div>
    </div>
</div>