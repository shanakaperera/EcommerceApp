<%-- 
    Document   : resetPasswordAdmin
    Created on : Feb 8, 2016, 11:25:46 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Reset Password</title>
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
        if (request.getParameter("mdfk") == null) {
            response.sendRedirect("forgot_pass.jsp");
        }
    %>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>

            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Reset Admin Password&nbsp;<small>Provide new password to your account.&nbsp;</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel" id="panel-edit">
                        <div class="row">
                            <div class="col-md-12">
                                <h3>Reset Password</h3>
                                <hr /> 
                                <table id='formTable'>

                                    <tr>
                                        <td>New Password :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="password" size="50" id="passNew" class="form-control" value="">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Confirm Password :</td>
                                        <td>
                                            <div class="input-group">                                                          
                                                <input type="password" id="confirmNew" class="form-control" value="">
                                                <span class="input-group-addon" style='color:red;'>*</span> 
                                            </div>
                                        </td>
                                    </tr>

                                </table>
                            </div>
                        </div>
                        <div class="panel-footer" style="background-color: #FFF;">
                            <button type="button" onclick="passResetClicked();" class="btn btn-danger">
                                <span class="glyphicon glyphicon-refresh"></span>&nbsp;Reset
                            </button>


                        </div>                        


                    </div>
                </div>
            </div>
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            function passResetClicked() {
                if ($('#passNew').val() !== null && $('#confirmNew') !== null
                        && $('#passNew').val() !== '' && $('#confirmNew') !== '') {
                    var currentURL = window.location.href;
                    var params = currentURL.extract();
                    $.ajax({
                        url: "restPasswordAdminAction",
                        data: {confirmNew: $('#passNew').val(), uid: params.mdfk},
                        type: 'POST',
                        cache: false,
                        success: function (data) {
                            $('#panel-edit').html('<h5>You have successfully reset your password. You can go to this <a href="adminAuth.jsp">link</a> and login with your new password.</h5>');
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                } else {

                    alert('Sorry ! You have missed some fields.');
                }
            }
        </script>
        <script type="text/javascript" src="js/notification-bar-new.js"></script>

    </body>
</html>
