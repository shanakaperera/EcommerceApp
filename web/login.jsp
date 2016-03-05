<%-- 
    Document   : login
    Created on : Dec 28, 2015, 6:04:09 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<%
if(request.getSession().getAttribute("user")!=null){%>
<c:redirect url="index.jsp"/>
<%}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Login</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>


    <!-- Page content starts -->
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <!-- Some content -->
                    <h3 class="title">Login to Shop <span class="color">!!!</span></h3>
                    <h4 >Morbi tincidunt posuere turpis eu laoreet</h4>
                    <p>Nullam in est urna. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In ut nulla est. </p>
                    <h5>Maecenas hendrerit neque id</h5>
                    <div class="lists">
                        <ul>
                            <li>Etiam adipiscing posuere justo, nec iaculis justo dictum non</li>
                            <li>Cras tincidunt mi non arcu hendrerit eleifend</li>
                            <li>Aenean ullamcorper justo tincidunt justo aliquet et lobortis diam faucibus</li>
                            <li>Maecenas hendrerit neque id ante dictum mattis</li>
                            <li>Vivamus non neque lacus, et cursus tortor</li>
                        </ul>
                    </div>		
                    <p>Nullam in est urna. In vitae adipiscing enim. In ut nulla est. Nullam in est urna. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In ut nulla est. </p>

                </div>


                <!-- Login form -->
                <div class="col-md-6">
                    <div class="formy well">
                        <h4 class="title">Login to Your Account</h4>
                        <div class="form">

                            <!-- Login  form (not working)-->
                            <form class="form-horizontal">                                         
                                <!-- Username -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="username2">Username</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="username2">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="password2">Password</label>
                                    <div class="controls col-md-8">
                                        <input type="password" class="form-control" id="password2">
                                    </div>
                                </div>
                                <!-- Checkbox -->
                                <div class="form-group">
                                    <div class="col-md-8 col-md-offset-3">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="inlineCheckbox3" value="agree"> Remember Password
                                        </label>
                                    </div>
                                </div> 

                                <!-- Buttons -->
                                <div class="form-group">
                                    <!-- Buttons -->
                                    <div class="col-md-8 col-md-offset-3">
                                        <button type="submit" class="btn btn-danger">Login</button>
                                        <button type="reset" class="btn btn-default">Reset</button>
                                    </div>
                                </div>
                            </form>
                            <hr />
                            <h5>New Account</h5>
                            Don't have an Account? <a href="register.jsp">Register</a>
                        </div> 
                    </div>

                </div>
            </div>
        </div>
    </div>

    <!-- Page content ends -->

    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>


</html>
