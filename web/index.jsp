<%-- 
    Document   : index
    Created on : Dec 25, 2015, 7:36:08 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <!-- Title here -->
        <title>Certus</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>
    <div id="indexSec">
        <%@include file="WEB-INF/content/frontstore/slider.jsp" %>
        <%@include file="WEB-INF/content/frontstore/promo_box.jsp" %>
        <%@include file="WEB-INF/content/frontstore/popular_deals.jsp" %>
        <%@include file="WEB-INF/content/frontstore/recent_items.jsp" %>
    </div>
    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>


    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
</html>
