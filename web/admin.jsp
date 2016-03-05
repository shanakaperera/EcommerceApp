<%-- 
    Document   : admin
    Created on : Jan 8, 2016, 6:42:15 PM
    Author     : shanaka
--%>

<%@page import="com.certus.controllers.AdminDashBoardFigures"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
    </head>


    <%
        if (session.getAttribute("admin") == null) {
            response.sendRedirect("adminAuth.jsp");
        }
    %>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>

            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Home&nbsp;<small>Dash board&nbsp;</small></h4> 
                </div>
            </div>
            <%
                AdminDashBoardFigures f = new AdminDashBoardFigures();
            %>
            <div class="row quickView">
                <div class="col-md-4 showOrders">
                    <h3 class="writeWhite">New Orders Today</h3>
                    <div class="row">
                        <div class="col-md-4">
                            <span class="glyphicon glyphicon-briefcase writeWhite" style="font-size:4.00em"></span> 
                        </div>
                        <div class="col-md-8">
                            <h3 class="pull-left writeWhite"><%=f.getOrdersToday()%></h3> 
                        </div>
                    </div>                     
                </div>
                <div class="col-md-4 showOrders">
                    <h3 class="writeWhite">Total Sales Today</h3>
                    <div class="row">
                        <div class="col-md-4">
                            <span class="glyphicon glyphicon-list-alt writeWhite" style="font-size:4.00em"></span> 
                        </div>
                        <div class="col-md-8">
                            <h3 class="pull-left writeWhite"><%=f.getSalesToday()%></h3> 
                        </div>
                    </div>                     
                </div>
                <div class="col-md-4 showOrders">
                    <h3 class="writeWhite">New Customers Today</h3>
                    <div class="row">
                        <div class="col-md-4">
                            <span class="glyphicon glyphicon-user writeWhite" style="font-size:4.00em"></span> 
                        </div>
                        <div class="col-md-8">
                            <h3 class="pull-left writeWhite"><%=f.getCustomersToday()%></h3> 
                        </div>
                    </div>                     
                </div>
            </div>

            <%@include file="WEB-INF/content/admin/admin-menu.jsp" %>

            <div class="row">
                <%@include file="WEB-INF/content/admin/last-orders-view.jsp" %> 
                <%@include file="WEB-INF/content/admin/last-registrations-view.jsp" %> 
            </div>
            <footer class="pg-empty-placeholder"></footer>
        </div>

        <div class="modal fade pg-show-modal" id="viewNotification" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header" data-pg-collapsed> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Notification Information</h4> 
                    </div>             
                    <div class="modal-body" data-pg-collapsed>
                        <div class="row" data-pg-collapsed>
                            <div class="col-md-12">
                            </div>
                        </div>                 
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="notificationInfo">

                                </div>
                            </div>
                        </div>                 
                    </div>             
                    <div class="modal-footer" data-pg-collapsed> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                    </div>             
                </div>         
            </div>     
        </div>

        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: 'LastRegistrationsAction',
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        $('#regTable').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                }
                );
            });

            $(document).ready(function () {
                $.ajax({
                    url: 'LastOrderViewAction',
                    type: 'POST',
                    cache: false,
                    success: function (data) {
                        $('#ordTable').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                }
                );
            });


        </script>
        <script type="text/javascript" src="js/notification-bar-new.js"></script>


    </body>

</body>
</html>
