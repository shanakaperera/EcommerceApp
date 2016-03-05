<%-- 
    Document   : admin_editProfile
    Created on : Feb 8, 2016, 6:37:37 PM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.Administor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Profile Edit</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            table{
                border-collapse:separate; 
                border-spacing:1em;
            }
            .leaveMargin{
                margin-left: 5%;
            }
            .input-group{
                border-spacing: 0;
            }

        </style>
    </head>
    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminAuth.jsp");
        } else {
            Administor admin = (Administor) session.getAttribute("admin");
    %>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>

            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Admin Profile&nbsp;<small>Administrator profile edit details&nbsp;</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel">
                        <div class="row">
                            <div class="col-md-12">
                                <h3>Customer Details</h3>
                                <hr /> 
                                <table id='formTable'>

                                    <tr>
                                        <td>Login Name :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="text" required="required" size="50" id="logName" class="form-control" value="<%=admin.getUserName()%>">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>First Name :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="text" id="fName" required="required" class="form-control" value="<%=admin.getFName()%>">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Last Name :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="text" id="lName" required="required" class="form-control" value="<%=admin.getLName()%>">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Email :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="email" id="emailAdd" required="required" class="form-control" value="<%=admin.getEmail()%>">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>Password :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="password" id="pass" class="form-control">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Confirm Password :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="password" id="passConfirm" class="form-control">

                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"><span id="message"></span></td>
                                    </tr>

                                </table>
                            </div>
                        </div>
                        <div class="panel-footer" style="background-color: #FFF;">
                            <button type="button" onclick="saveBtnClick();" class="btn btn-danger">
                                <span class="glyphicon glyphicon-saved"></span>&nbsp;Save
                            </button>
                            <button type="button" class="btn btn-default" onclick="location.href = 'admin.jsp';">
                                <span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Cancel
                            </button>
                        </div>                                 
                    </div>
                </div>
            </div>

        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            $('#pass, #passConfirm').on('keyup', function () {
                if ($('#pass').val() === $('#passConfirm').val()) {
                    $('#message').html('Passowrds matched.').css('color', 'green');
                } else
                    $('#message').html("Whoops, passwords don't match").css('color', 'red');
            });
            function saveBtnClick() {
                $.ajax({
                    url: "adminEditProfileAction",
                    type: 'POST',
                    data: {logName: $('#logName').val(), fName: $('#fName').val(),
                        lName: $('#lName').val(), email: $('#emailAdd').val(),
                        pass: $('#pass').val(), conpass: $('#passConfirm').val()},
                    cache: false,
                    success: function (data) {
                        location.href='admin.jsp';
                    },
                    error: function () {
                        alert('error');
                    }

                });
            }
            
           
        </script>
<!--        <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
<%}%>