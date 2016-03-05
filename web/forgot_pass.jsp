<%-- 
    Document   : forgot_pass
    Created on : Feb 8, 2016, 6:09:59 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Forgot Password</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>
        <link href="css/login-admin.css" type="text/css" rel="stylesheet"/>
    </head>
    <body>
        <div class="text-center" style="padding:50px 0">
            <div class="logo">
                <h1><a href="#">C<span class="color bold">ertus</span></a></h1>
                <p class="meta">Forgot Password</p>
            </div>
            <!-- Main Form -->
            <div class="login-form-1">
                <form id="forgot-password-form" class="text-left">
                    <div class="etc-login-form">
                        <p>When you fill in your registered email address, you will be sent instructions on how to reset your password.</p>
                    </div>
                    <div class="login-form-main-message"></div>
                    <div class="main-login-form">
                        <div class="login-group">
                            <div class="form-group">
                                <label for="fp_email" class="sr-only">Email address</label>
                                <input type="email" class="form-control" id="fp_email" name="fp_email" placeholder="email address">
                            </div>
                        </div>
                        <button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
                    </div>
                    <div class="etc-login-form">
                        <p>go to login section? <a href="adminAuth.jsp">login here</a></p>
                        
                    </div>
                </form>
            </div>
            <%@include file="WEB-INF/content/admin/scripts.jsp" %>
            <script src="js/jquery.validate.min.js" type="text/javascript"></script>
            <script src="js/login-admin.js" type="text/javascript"></script>
    </body>
</html>
