<%-- 
    Document   : edit_order
    Created on : Feb 1, 2016, 7:17:22 PM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.Sales"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.certus.dbmodel.Messages"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.controllers.OrderInfo"%>
<%@page import="com.certus.controllers.AdminOrderEditor"%>
<%@page import="com.certus.dbmodel.Order"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Order Edit</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            .spacer{
                border-collapse:separate; 
                border-spacing:1em;
            }
            .notViewed{
                background-color: rgb(198, 210, 255);
            }

            .conversation-wrap{
                box-shadow: -2px 0 3px #ddd;
                padding:0;
                max-height: 400px;
                overflow: auto;
            }

            .conversation{
                padding:5px;
                border-bottom:1px solid #ddd;
                margin:0;

            }

            .message-wrap{
                box-shadow: 0 0 3px #ddd;
                padding:0;

            }

            .msg{
                padding:5px;
                margin:0;
            }

            .msg-wrap{
                padding:10px;
                max-height: 400px;
                overflow: auto;
            }

            .time{
                color:#000;
            }

            .send-wrap{
                border-top: 1px solid #eee;
                border-bottom: 1px solid #eee;
                padding:10px;
            }

            .send-message{
                resize: none;
            }

            .highlight{
                background-color: #f7f7f9;
                border: 1px solid #e1e1e8;
            }

            .send-message-btn{
                border-top-left-radius: 0;
                border-top-right-radius: 0;
                border-bottom-left-radius: 0;

                border-bottom-right-radius: 0;
            }

            .msg-wrap .media-heading{
                color:#003bb3;
                font-weight: 700;
            }

            .msg-date{
                background: none;
                text-align: center;
                color:#aaa;
                border:none;
                box-shadow: none;
                border-bottom: 1px solid #ddd;
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Order Information&nbsp;<small>View & Edit Order Infromation&nbsp;</small></h4> 
                </div>
            </div>
            <%
                int oid = Integer.parseInt(request.getParameter("oid"));

                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                Order ordr = (Order) s.load(Order.class, oid);
            %>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel"> 
                        <div class="panel-heading">
                            <h3>Order Summary</h3>
                            <hr />
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <table class="spacer">
                                        <tbody>
                                            <tr>
                                                <td><b>Order Id :</b></td>
                                                <td><%=ordr.getInvoNum()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Customer :</b></td>
                                                <td><%=ordr.getName()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Date Added :</b></td>
                                                <td><%=ordr.getDateOrdered()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Order Status :</b></td>
                                                <td><%=ordr.getStatus()%></td>
                                            </tr>
                                        </tbody>                                         
                                    </table>
                                </div>
                                <div class="col-md-6">
                                    <table class="spacer">
                                        <tbody>
                                            <tr>
                                                <td><b>Email :</b></td>
                                                <td> <%=ordr.getEmail()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Order Total :</b></td>
                                                <td>Rs. <%=ordr.getGrandTot()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Payment Method :</b></td>
                                                <td><%=ordr.getPayment_method()%></td>
                                            </tr>
                                            <tr>
                                                <td><b>Telephone :</b></td>
                                                <td> <%=ordr.getTel()%></td>
                                            </tr>
                                        </tbody>                                         
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>                     
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs"> 
                        <li class="active">
                            <a href="#tab1" data-toggle="tab">Details</a>
                        </li>                         
                        <li>
                            <a href="#tab2" data-toggle="tab">Order Address</a>
                        </li>                         
                        <li>
                            <a href="#tab3" data-toggle="tab">Payment Address</a>
                        </li>
                        <li>
                            <%
                                List<Messages> msgs = s.createCriteria(Messages.class, "msg")
                                        .createAlias("msg.order", "order")
                                        .add(Restrictions.eq("order.id", oid))
                                        .add(Restrictions.eq("msg.adminViewed", false))
                                        .list();

                            %>
                            <a href="#tab4" data-toggle="tab">Status & Comments&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <span class="badge" style="background-color: #d58154;"><%=msgs.size()%></span></a>
                        </li>                         
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <div class="panel">
                                <div class="panel-heading">
                                    <h3>Details</h3>
                                    <hr />
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12 littleSpace">
                                            <table class="table"> 
                                                <thead> 
                                                    <tr> 
                                                        <th>Product</th> 
                                                        <th>Quantity</th> 
                                                        <th>Unit Price</th> 
                                                        <th>Total</th> 
                                                    </tr>                                             
                                                </thead>                                         

                                                <tbody>
                                                    <% AdminOrderEditor editor = new AdminOrderEditor(ordr, s);
                                                        for (OrderInfo inf : editor.getOrder()) {
                                                            String href = "single-item.jsp?cat=" + inf.getPhs().getProduct().getSubCategory().getCategory().getId()
                                                                    + "&sub=" + inf.getPhs().getProduct().getSubCategory().getId()
                                                                    + "&pid=" + inf.getPhs().getProduct().getId();
                                                    %>
                                                    <tr> 
                                                        <td><a  target="_blank" href="<%=href%>"><%=inf.getPhs().getProduct().getName()%></a></td> 
                                                        <td><%=inf.getQnty()%></td> 
                                                        <td>Rs. <%=inf.getPhs().getPrice()%></td> 
                                                        <td>Rs. <%=inf.getPhs().getPrice() * inf.getQnty()%></td> 
                                                    </tr>
                                                    <%}%>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="col-md-4 col-md-offset-7">
                                            <table class="table">                                          

                                                <tbody> 
                                                    <tr> 
                                                        <td><h5>Sub Total : </h5></td> 
                                                        <td><h5>Rs.<%=ordr.getCoupon() != null ? (100 * ordr.getGrandTot()) / (100 - ordr.getCoupon().getDiscount()) : ordr.getGrandTot()%> </h5></td> 
                                                    </tr>
                                                    <tr> 
                                                        <td><h5>Coupon discount : </h5></td> 

                                                        <td><h5><%=ordr.getCoupon() != null ? ordr.getCoupon().getDiscount() : "0.0"%> %</h5></td> 
                                                    </tr>
                                                    <tr> 
                                                        <td><h5>Total : </h5></td> 
                                                        <td><h5>Rs.<%=ordr.getGrandTot()%></h5></td> 
                                                    </tr>                                                                                                                                       
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab2">

                            <div class="panel">
                                <div class="panel-heading">
                                    <h3>Order Address</h3>
                                    <hr />
                                </div>                                 
                                <div class="panel-body">
                                    <div class="row">
                                        <table class="spacer">
                                            <tr>
                                                <td>Name :</td>
                                                <td><%=ordr.getName()%></td>
                                            </tr>
                                            <tr>
                                                <td>Email :</td>
                                                <td><%=ordr.getEmail()%></td>
                                            </tr>
                                            <tr>
                                                <td>Telephone :</td>
                                                <td><%=ordr.getTel()%></td>
                                            </tr>
                                            <tr>
                                                <td>Address :</td>
                                                <td><%=ordr.getOrderAddress()%></td>
                                            </tr>
                                            <tr>
                                                <td>City :</td>
                                                <td><%=ordr.getCity()%></td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>                                 
                            </div>

                        </div>
                        <div class="tab-pane" id="tab3">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3>Payment Address</h3>
                                            <hr />
                                        </div>                                         
                                        <div class="panel-body">
                                            <div class="row">
                                                <table class="spacer">
                                                    <tr>
                                                        <td>Name :</td>
                                                        <td><%=ordr.getUser().getFName() + " " + ordr.getUser().getLName()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Email :</td>
                                                        <td><%=ordr.getUser().getEmail()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Telephone :</td>
                                                        <td><%=ordr.getUser().getTelephone()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Address :</td>
                                                        <td><%=ordr.getUser().getAddress()%></td>
                                                    </tr>

                                                </table>
                                            </div>
                                        </div>                                         
                                    </div>                                     
                                </div>
                            </div>

                        </div>
                        <div class="tab-pane" id="tab4">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="panel">
                                        <div class="panel-heading">
                                            <h3>Status & Comments </h3>
                                            <hr />
                                        </div>                                         
                                        <div class="panel-body">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <table>
                                                        <tr>
                                                            <td>Order Status : &nbsp;&nbsp; </td>
                                                            <td>
                                                                <select class="form-control" id="stSel">
                                                                    <option <%=ordr.getStatus().equals("Canceled") ? "selected='selected'" : ""%>  value="Canceled">Canceled</option>
                                                                    <option <%=ordr.getStatus().equals("Completed") ? "selected='selected'" : ""%> value="Completed">Completed</option>
                                                                    <option <%=ordr.getStatus().equals("Denied") ? "selected='selected'" : ""%> value="Denied">Denied</option>
                                                                    <option <%=ordr.getStatus().equals("Failed") ? "selected='selected'" : ""%> value="Failed">Failed</option>
                                                                    <option <%=ordr.getStatus().equals("Incomplete") ? "selected='selected'" : ""%> value="Incomplete">Incomplete</option>
                                                                    <option <%=ordr.getStatus().equals("Pending") ? "selected='selected'" : ""%> value="Pending">Pending</option>
                                                                    <option <%=ordr.getStatus().equals("Processing") ? "selected='selected'" : ""%> value="Processing">Processing</option>
                                                                    <option <%=ordr.getStatus().equals("Refunded") ? "selected='selected'" : ""%> value="Refunded">Refunded</option>
                                                                    <option <%=ordr.getStatus().equals("Reversed") ? "selected='selected'" : ""%> value="Reversed">Reversed</option>
                                                                    <option <%=ordr.getStatus().equals("Requested") ? "selected='selected'" : ""%> value="Requested">Requested</option>
                                                                </select>
                                                            </td>
                                                            <%
                                                                Sales sale = (Sales) s.createCriteria(Sales.class, "sale")
                                                                        .createAlias("sale.order", "order")
                                                                        .add(Restrictions.eq("order.id", ordr.getId())).uniqueResult();

                                                            %>
                                                            <td><button style="margin-left: 25px;" <%=sale == null ? "" : "disabled='disabled'"%>  id="updStatus" class="btn btn-danger">Update Status</button></td>
                                                        </tr>
                                                    </table>
                                                    <br/><br/>
                                                    <div class="msg-wrap" id="msgSec"></div>

                                                    <div class="send-wrap ">
                                                        <textarea class="form-control send-message" id='msgWrite' rows="3" placeholder="Write a reply..."></textarea>
                                                    </div>
                                                    <div class="btn-panel">
                                                        <button class="btn btn-success" id='sndMsg' type="button">Send Message</button>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>                                         
                                    </div>                                     
                                </div>
                            </div>

                        </div>
                    </div>                     
                </div>
            </div>
        </div>
        <div class="modal fade pg-show-modal" id="viewMsg" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header" data-pg-collapsed> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">View Message</h4> 
                    </div>             
                    <div class="modal-body" data-pg-collapsed>
                        <div class="row" data-pg-collapsed>
                            <div class="col-md-12">
                            </div>
                        </div>                 
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <style type="text/css">
                                #msgViewSec table tr td h5{
                                    color: #003bb3;
                                }
                            </style>
                            <div class="col-md-12">
                                <div id="msgViewSec" style="margin-top: -20px;">

                                </div>
                            </div>
                        </div>                 
                    </div>             
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                         
                        <button type="submit" class="btn btn-danger">Delete Message</button>                         
                    </div> 
                </div>         
            </div>     
        </div>                                                       

        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            function setAsViewed(mid) {
                var currentURL = window.location.href;
                var params = currentURL.extract();
                $.ajax({
                    url: "setAsViewedAction",
                    data: {mid: mid},
                    cache: false,
                    success: function (data) {
                        loadMsg(params);
                    },
                    error: function () {
                        alert('error');
                    }

                });
            }
            function viewMsg(mid) {
                $.ajax({
                    url: "viewMsgAction",
                    data: {msg_id: mid},
                    cache: false,
                    success: function (data) {
                        $('#msgViewSec').html(data);
                        setAsViewed(mid);
                    }, error: function () {
                        alert('error');
                    }
                });
            }

            function loadMsg(params) {

                $.ajax({
                    url: "LoadOrderMsgAction",
                    data: {oid: params.oid},
                    cache: false,
                    success: function (data) {
                        $('#msgSec').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
            $(document).ready(function () {
                var currentURL = window.location.href;
                var params = currentURL.extract();
                loadMsg(params);
            });
            $(document).on("click", "#sndMsg", function () {
                var currentURL = window.location.href;
                var params = currentURL.extract();
                if ($('#msgWrite').val() !== null && $('#msgWrite').val() !== '') {

                    $.ajax({
                        url: "SendAdminMsgAction",
                        data: {msg: $('#msgWrite').val(), oid: params.oid},
                        cache: false,
                        success: function (data) {
                            loadMsg(params);
                            $('#msgWrite').val('');
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            });
            $(document).on('click', "#updStatus", function () {
                var c = confirm('Are you sure you want to update order status ?');
                if (c === true) {
                    var currentURL = window.location.href;
                    var params = currentURL.extract();
                    $.ajax({
                        url: "updateOrderStatusAction",
                        data: {status: $('#stSel').val(), oid: params.oid},
                        cache: false,
                        success: function (data) {
                            location.reload(true);
                        },
                        error: function () {
                            alert('error');
                        }
                    });

                }
            });
        </script>
<!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
