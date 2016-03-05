<%-- 
    Document   : sales
    Created on : Feb 6, 2016, 12:16:53 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Sales</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <link rel="stylesheet" href="css/jquery-ui.min.css"/>
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Sales&nbsp;<small>View Product Sales&nbsp;</small></h4> 
                </div>
            </div>


            <div class="row">
                <div class="col-md-12">
                    <div class="row">

                        <div class="col-md-6">
                            <div class="form-group"> 
                                <label class="control-label">Filter Sale By Date</label> 
                                <div class="input-group"> 
                                    <span class="input-group-addon">Starting From</span> 
                                    <input type="text" name="startDate" class="form-control" id="startDate"> 
                                    <span class="input-group-addon">To</span> 
                                    <input type="text" name="endDate" class="form-control" id="endDate"> 
                                </div> 
                            </div>
                        </div>

                        <div class="col-md-3" style="margin-top: 2.9%;">
                            <button class="btn btn-default" id="goBtn1">Go</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped"> 
                        <thead> 
                            <tr> 
                                <th>Sale Id</th> 
                                <th>Customer Name</th>
                                <th>Status</th>
                                <th>Amount</th> 
                                <th>Date Done</th>
                                <th>View Sale</th>

                            </tr>                             
                        </thead>                         
                        <tbody id="saleFilterTab"></tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <div class="paging" id="pagSale"></div>
                </div>
            </div>
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $("#endDate").datepicker({dateFormat: 'yy-mm-dd'});
                $("#startDate").datepicker({dateFormat: 'yy-mm-dd'});
                $.ajax({
                    url: "salesFilterAction",
                    dataType: 'json',
                    cache: false,
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $('#saleFilterTab').html(value.d1);
                            $('#pagSale').html(value.d2);
                        });
                    },
                    error: function () {
                        alert('error');
                    }
                });
            });
            $(document).on("click", "#goBtn1", function () {
                $.ajax({
                    url: 'salesFilterAction',
                    data: {startDate: $('#startDate').val(), endDate: $('#endDate').val()},
                    cache: false,
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $('#saleFilterTab').html(value.d1);
                            $('#pagSale').html(value.d2);
                        });
                    },
                    error: function () {
                        alert('error');
                    }
                });
            });
        </script>
<!--        <script type="text/javascript" src="js/notification-bar-new.js"></script>-->


    </body>
</html>
