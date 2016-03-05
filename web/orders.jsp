<%-- 
    Document   : orders
    Created on : Feb 1, 2016, 12:00:32 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Orders</title>
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Orders&nbsp;<small>View & Edit Customer Orders&nbsp;</small></h4> 
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group"> 
                                <label class="control-label" for="filterOid">Filter Order</label> 
                                <input type="text" id="filterOid" class="form-control"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group"> 
                                <label class="control-label">Filter Order By Date</label> 
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
                                <th>Order Id</th> 
                                <th>Customer Name</th>
                                <th>Status</th>
                                <th>Total</th> 
                                <th>View Order</th>
                                <th>Action</th> 
                            </tr>                             
                        </thead>                         
                        <tbody id="ordFilterTab"></tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <div class="paging" id="pagOrd"></div>
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
                    url: "orderFilterAction",
                    dataType: 'json',
                    cache: false,
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $('#ordFilterTab').html(value.d1);
                            $('#pagOrd').html(value.d2);
                        });
                    },
                    error: function () {
                        alert('error');
                    }
                });
            });

            $("#filterOid").keyup(function () {
                //split the current value of searchInput
                var data = this.value.split(" ");
                //create a jquery object of the rows
                var jo = $("#ordFilterTab").find("tr");
                if (this.value === "") {
                    jo.show();
                    return;
                }
                //hide all the rows
                jo.hide();

                //Recusively filter the jquery object to get results.
                jo.filter(function (i, v) {
                    var $t = $(this);
                    for (var d = 0; d < data.length; ++d) {
                        if ($t.is(":contains('" + data[d] + "')")) {
                            return true;
                        }
                    }
                    return false;
                })
                        //show the rows that match.
                        .show();
            }).focus(function () {
                this.value = "";
                $(this).css({
                    "color": "black"
                });
                $(this).unbind('focus');
            }).css({
                "color": "#C0C0C0"
            });

            $(document).on("click", "#pagOrd a", function (event) {
                event.preventDefault();
                var para = $(this).attr('href').match(/\d+/);
                var currentURL = $(this).attr('href');
                var params = currentURL.extract();
                if ($(this).attr('href').indexOf("startDate") >= 1) {
                    var urlVal = 'orderFilterAction?startDate=' + params.startDate + '&endDate=' + params.endDate;
                    $.ajax({
                        url: urlVal,
                        cache: false,
                        success: function (data) {
                            $.each(data, function (key, value) {
                                $('#ordFilterTab').html(value.d1);
                                $('#pagOrd').html(value.d2);
                            });
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                } else {

                    $.ajax({
                        url: 'orderFilterAction?pgIndex=' + para,
                        cache: false,
                        success: function (data) {
                            $.each(data, function (key, value) {
                                $('#ordFilterTab').html(value.d1);
                                $('#pagOrd').html(value.d2);
                            });
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            });

            $(document).on("click", "#goBtn1", function () {
                $.ajax({
                    url: 'orderFilterAction',
                    data: {startDate: $('#startDate').val(), endDate: $('#endDate').val()},
                    cache: false,
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $('#ordFilterTab').html(value.d1);
                            $('#pagOrd').html(value.d2);
                        });
                    },
                    error: function () {
                        alert('error');
                    }
                });
            });
        </script>
<!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->

    </body>
</html>
