<%-- 
    Document   : customers
    Created on : Feb 4, 2016, 10:50:56 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Customers</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <link rel="stylesheet" href="css/jquery-ui.min.css"/>
        <style type="text/css">
            .spacer{
                border-collapse:separate; 
                border-spacing:1em;
            }
            #ordSection{   
                width:550px;   
                height:300px;    
                overflow:auto; overflow-x:hidden;   
            } 
            .cusBoder{
                border-bottom: 1px solid #EEE;
                border-bottom-width: 1px;
                border-bottom-style: solid;
                border-bottom-color: #EEE;
            }
            .media .media-heading{
                color: #A44747;
            }
        </style>
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Customers&nbsp;<small>View & Edit Customer Details&nbsp;</small></h4> 
                </div>
            </div>

            <div class="row">
                <div class="col-md-3">
                    <div class="form-group"> 
                        <label class="control-label" for="filterName">Filter Name</label> 
                        <input type="text" id="filterName" placeholder="enter name here.." class="form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped"> 
                        <thead> 
                            <tr> 
                                <th>Customer Name</th>
                                <th>Status</th>
                                <th>Email</th> 
                                <th>Total Orders</th>
                                <th>Action</th> 
                            </tr>                             
                        </thead>                         
                        <tbody id="cusFilterTab">
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">

                <div class="col-md-9 col-sm-9">
                    <div class="paging" id="pagCus"></div>
                </div>
            </div>

        </div>



        <div class="modal fade pg-show-modal" id="ViewOrdersModal" tabindex="-1" role="dialog" aria-hidden="true" data-pg-collapsed>
            <div class="modal-dialog customModal"> 
                <div class="modal-content"> 
                    <div class="modal-header" data-pg-collapsed> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                 
                        <h4 class="modal-title">Customer Related Orders</h4> 
                    </div>             
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="ordSection">
                                    <ul class="media-list" id="oList"></ul>    
                                </div>
                            </div>
                        </div>                 
                    </div>  
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                                                  
                    </div>  
                </div>         
            </div>     
        </div>

        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax({
                    url: 'customersLoadAction',
                    dataType: 'json',
                    cache: false,
                    success: function (data) {
                        $.each(data, function (key, value) {
                            $('#cusFilterTab').html(value.d1);
                            $('#pagCus').html(value.d2);
                        });
                    },
                    error: function () {
                        alert('error');
                    }
                });
            });

            $("#filterName").keyup(function () {
                //split the current value of searchInput
                var data = this.value.split(" ");
                //create a jquery object of the rows
                var jo = $("#cusFilterTab").find("tr");
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

            function loadModal(orders) {
                $.ajax({
                    url: "loadOrdersDetailAction",
                    data: {orders: orders},
                    cache: false,
                    success: function (data) {
                        $('#oList').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
            //
            function onBtnClick(uid, page) {

                var c = confirm('Are you sure you want to update the status ?');
                if (c === true) {
                    $.ajax({
                        url: "customerEnAction",
                        data: {uid: uid, enable: true},
                        cache: false,
                        success: function (data) {
                            if (data === 'success') {
                                $.ajax({
                                    url: 'customersLoadAction?pgIndex=' + page,
                                    cache: false,
                                    success: function (data) {
                                        $.each(data, function (key, value) {
                                            $('#cusFilterTab').html(value.d1);
                                            $('#pagCus').html(value.d2);
                                        });
                                    },
                                    error: function () {
                                        alert('error');
                                    }
                                });
                            }
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }
            function offBtnClick(uid, page) {

                var c = confirm('Are you sure you want to update the status ?');
                if (c === true) {
                    $.ajax({
                        url: "customerEnAction",
                        data: {uid: uid, enable: false},
                        cache: false,
                        success: function (data) {
                            if (data === 'success') {
                                $.ajax({
                                    url: 'customersLoadAction?pgIndex=' + page,
                                    cache: false,
                                    success: function (data) {
                                        $.each(data, function (key, value) {
                                            $('#cusFilterTab').html(value.d1);
                                            $('#pagCus').html(value.d2);
                                        });
                                    },
                                    error: function () {
                                        alert('error');
                                    }
                                });
                            }
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }
        </script>
<!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
