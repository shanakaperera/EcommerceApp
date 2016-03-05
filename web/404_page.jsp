<%-- 
    Document   : 404_page
    Created on : Feb 15, 2016, 3:26:00 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-404</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="content error-page">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4 col-sm-5">  
                                <!-- Big 404 text -->
                                <div class="big-text">404</div>
                                <hr />
                            </div>
                            <div class="col-md-5 col-sm-5 col-sm-offset-1 col-md-offset-1">
                                <h2>Oops<span class="color">!!!</span></h2>
                                <h4>Page Not Found</h4>
                                <hr />
                                <!-- Some site links -->
                                <div class="horizontal-links">
                                    <h5>Take a look around our site</h5>
                                    <a href="#">Home</a> <a href="#">About us</a> <a href="#">Support</a> <a href="#">Contact Us</a> <a href="#">Disclaimer</a>
                                </div>
                                <hr />
                                <!-- Search form -->
                                <div class="form">
                                    <form class="form-inline" role="form">
                                        <div class="form-group">
                                            <input type="email" class="form-control" id="search" placeholder="Search">
                                        </div>

                                        <button type="submit" class="btn btn-default">Search</button>
                                    </form>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>


    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
</html>
