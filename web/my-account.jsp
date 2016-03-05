<%-- 
    Document   : my-account
    Created on : Dec 29, 2015, 6:39:47 PM
    Author     : shanaka
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.certus.dbmodel.Messages"%>
<%@page import="com.certus.dbmodel.Order"%>
<%@page import="com.certus.controllers.UserController"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-My Account</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>
        <style type="text/css">
            #proSection{   
                width:550px;   
                height:250px;    
                overflow:auto; overflow-x:hidden;   
            } 
            .cusBoder{
                border-bottom: 1px solid #EEE;
                border-bottom-width: 1px;
                border-bottom-style: solid;
                border-bottom-color: #EEE;
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

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>

    <!-------My Account Starts here------->
    <%
        if (request.getSession(false).getAttribute("user") == null) {
    %>

    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <%
                        Session sls = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                        SiteGeneral gen = (SiteGeneral) sls.load(SiteGeneral.class, 1);

                    %>
                    <!-- Some content -->
                    <h3 class="title">You have to Login to Shop to view your account <span class="color">!!!</span></h3>
                        <h4><%=gen.getUsrAcntDesc()%></h4>

                </div>


                <!-- Login form -->
                <div class="col-md-6">
                    <div class="formy well">
                        <h4 class="title">Login to Your Account</h4>
                        <div class="form">

                            <!--- Login  form --->
                            <form class="form-horizontal" action="login2Action" method="post">                                         
                                <!-- Username -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="user_name">Username</label>
                                    <div class="col-md-8">
                                        <input type="text" name="user_name" required="required" class="form-control" id="user_name">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="password">Password</label>
                                    <div class="controls col-md-8">
                                        <input type="password" required="required" name="password" class="form-control" id="password">
                                    </div>
                                </div>
                                <!-- Check-box -->
                                <div class="form-group">
                                    <div class="col-md-8 col-md-offset-3">
                                        <label class="checkbox-inline">
                                            <input type="checkbox"  id="inlineCheckbox3" value="agree"> Remember Password
                                        </label>
                                    </div>
                                </div> 

                                <!-- Buttons -->
                                <div class="form-group">
                                    <!-- Buttons -->
                                    <div class="col-md-8 col-md-offset-3">
                                        <button type="submit" id="loginBtn" class="btn btn-danger">Login</button>
                                        <button type="reset" class="btn btn-default">Reset</button><br/>
                                        <span style="color: red;" id="errorSpn"></span>
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

    <%  } else { %>
    <div class="items">
        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-3 hidden-xs">

                    <!-- Sidebar navigation -->
                    <h5 class="title">Pages</h5>
                    <!-- Sidebar navigation -->
                    <nav>
                        <%
                            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                            List<Messages> msgs = s.createCriteria(Messages.class, "msg")
                                    .createAlias("msg.order", "order")
                                    .add(Restrictions.eq("order.user", request.getSession().getAttribute("user")))
                                    .add(Restrictions.eq("msg.userViewed", false))
                                    .list();
                            String size = msgs.size() + "";
                        %>
                        <ul id="navi">
                            <li><a href="my-account.jsp">My Account</a></li>
                            <li><a href="wish-list.jsp?prm=wishList">Wish List</a></li>
                            <li><a href="order-history.jsp?prm=orderHistory">Messages <span class="badge pull-right" ><%=msgs.size() > 0 ? size : ""%></span></a></li>
                            <li><a href="edit-profile.jsp?prm=edit">Edit Profile</a></li>
                        </ul>
                    </nav>

                </div>

                <!-- Main content -->
                <div class="col-md-9 col-sm-9" id="main-cont">
                    <%                        User u = (User) request.getSession(false).getAttribute("user");

                    %>

                    <h5 class="title">My Account</h5>

                    <!-- Your details -->
                    <div class="address">
                        <address>
                            <!-- Your name -->
                            <strong><%=u.getFName() + " " + u.getLName()%></strong><br>
                            <!-- Address -->
                            <%
                                if (u.getAddress() != null) {

                            %>
                            <%=u.getAddress()%>
                            <br/>

                            <!-- Phone number -->
                            <abbr title="Phone">P:</abbr><%=u.getTelephone()%><br />
                            <%}%>
                            <a href="mailto:#"><%=u.getEmail()%></a>
                        </address>
                    </div>

                    <h5 class="title">My Recent Purchases</h5>

                    <%
                        UserController uc = new UserController();
                        List<Order> orderList = uc.getOrderInfo(u);
                        if (!orderList.isEmpty()) {
                    %>

                    <table class="table table-striped tcart">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Order ID</th>
                                <th>Price</th>
                                <th>Status</th>
                                <th>View Details</th>
                            </tr>
                        </thead>
                        <tbody>

                            <%
                                for (Order o : orderList) {
                            %>
                            <tr>
                                <td><%=o.getDateOrdered()%></td>
                                <td><%=o.getInvoNum()%></td>
                                <td>Rs. <%=o.getGrandTot()%></td>
                                <td><%=o.getStatus()%></td>
                                <td><button class='btn btn-default' type='button' data-dismiss='modal' data-target='#viewOrderInfo' data-toggle='modal' onclick='getOrderInfo(<%=o.getId()%>);'><span class='fa fa-info-circle'></span></button></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>

                    <%} else {%>
                    <h4>No Recent Purchases.</h4>
                    <%}%>
                </div>                                                                    



            </div>
        </div>
    </div>
    <%        }
    %>

    <!-------My Account Ends here------->

    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>


    <div class="modal fade pg-show-modal" id="viewOrderInfo" tabindex="-1" role="dialog" aria-hidden="true"> 
        <div class="modal-dialog"> 
            <div class="modal-content"> 
                <div class="modal-header" data-pg-collapsed> 
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">View Order Information</h4> 
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
                            <div id="proSection">
                                <ul class="media-list" id="orderInf">  </ul>
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

                </div> 
            </div>         
        </div>     
    </div>
    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#navi li a").click(function (event) {
                event.preventDefault();
                var url = $(this).attr("href");
                $.ajax({
                    url: 'myAccountLoadAction',
                    data: {parm: url},
                    cache: false,
                    success: function (data) {
                        $('#main-cont').html(data);

                    },
                    error: function () {
                        alert('error');
                    }
                }
                );
            });
        });

        function sendMsg(user_id) {
            $.ajax({
                url: "sendUserMsgAction",
                type: 'POST',
                data: {uid: user_id, msg: $('#msgWrite').val()},
                cache: false,
                success: function (data) {
                    if (data === 'success') {
                        $.ajax({
                            url: 'myAccountLoadAction',
                            data: {parm: "order-history.jsp?prm=orderHistory"},
                            cache: false,
                            success: function (data) {
                                $('#main-cont').html(data);

                            },
                            error: function () {
                                alert('error');
                            }
                        }
                        );
                    }
                },
                error: function () {
                    alert('error');
                }
            });
        }

        function removeFromList(wishId) {

            var c = confirm('Are you sure you want to remove this item from wish-list ?');
            if (c === true) {

                $.ajax({
                    url: "removeFromWishListAction",
                    data: {wid: wishId},
                    cache: false,
                    success: function (data) {
                        alert(data);
                        window.location.href = "my-account.jsp";
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
        }
        function getOrderInfo(oid) {
            $.ajax({
                url: "getOrderInfoAction",
                data: {oid: oid},
                cache: false,
                success: function (data) {
                    $('#orderInf').html(data);
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

        function setAsViewed(mid) {

            $.ajax({
                url: "setAsViewedUserAction",
                data: {mid: mid},
                cache: false,
                success: function (data) {

                },
                error: function () {
                    alert('error');
                }

            });
        }


    </script>

</html>