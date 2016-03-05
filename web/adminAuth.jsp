<%-- 
    Document   : adminAuth
    Created on : Feb 8, 2016, 4:35:37 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Login</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>
        <link href="css/login-admin.css" type="text/css" rel="stylesheet"/>

    </head>
    <%
        if (session.getAttribute("admin") != null) {
            response.sendRedirect("admin.jsp");
        }

    %>
    <body>
        <div class="text-center" style="padding:50px 0">

            <div class="logo">
                <h1><a href="#">C<span class="color bold">ertus</span></a></h1>
                <p class="meta">Login</p>
            </div>
            <!-- Main Form -->
            <div class="login-form-1">
                <form id="login-form" class="text-left">
                    <div class="login-form-main-message"></div>
                    <div class="main-login-form">
                        <div class="login-group">
                            <div class="form-group">
                                <label for="lg_username" class="sr-only">Username</label>
                                <input type="text" class="form-control" id="lg_username" name="lg_username" placeholder="username">
                            </div>
                            <div class="form-group">
                                <label for="lg_password" class="sr-only">Password</label>
                                <input type="password" class="form-control" id="lg_password" name="lg_password" placeholder="password">
                            </div>
                            <div class="form-group login-group-checkbox">
                                <input type="checkbox" id="lg_remember" name="lg_remember">
                                <label for="lg_remember">remember</label>
                            </div>
                        </div>
                        <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
                    </div>
                    <div class="etc-login-form">
                        <p>forgot your password? <a href="forgot_pass.jsp">click here</a></p>

                    </div>
                </form>
            </div>
            <!-- end:Main Form -->
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script src="js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/login-admin.js" type="text/javascript"></script>
    </body>
</html>
