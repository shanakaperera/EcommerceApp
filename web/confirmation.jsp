<%-- 
    Document   : confirmation
    Created on : Jan 27, 2016, 3:48:49 PM
    Author     : shanaka
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="com.certus.dbmodel.Messages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>

<%
    if (request.getSession(false).getAttribute("user") == null) {%>
<c:redirect url="login.jsp"/>

<% }%>
<%if (request.getParameter("oid") == null) {%>
<c:redirect url="cart.jsp"/>
<%  }
    Context env1 = (Context) new InitialContext().lookup("java:comp/env");
    String productsPath1 = (String) env1.lookup("uploadpathproducts");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Confirmation</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>
        <style type="text/css">
            #proSection{   
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

        </style>
    </head>
    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>
    <div class="items">
        <div class="container">
            <div class="row">

                <div class="col-md-3 col-sm-3 hidden-xs">

                    <!-- Sidebar navigation -->
                    <h5 class="title">Pages</h5>
                    <!-- Sidebar navigation -->
                    
                     <%
                            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                            List<Messages> msgs = s.createCriteria(Messages.class, "msg")
                                    .createAlias("msg.order", "order")
                                    .add(Restrictions.eq("order.user", request.getSession().getAttribute("user")))
                                    .add(Restrictions.eq("msg.userViewed", false))
                                    .list();
                            String size = msgs.size() + "";
                        %>
                    <nav>
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
                    <!-- Title -->
                    <h4 class="title">Confirmation</h4>

                    <h5>Thanks for buying from Certus!!</h5>
                    <p>Your Order #id is <strong><%=request.getParameter("oid")%></strong>. Say this Order while communicating further.</p>
                    <hr />
                    <!-- Some links -->
                    <div class="horizontal-links">
                        <h5>Take a look around our site</h5>
                        <a href="index.jsp">Home</a> <a href="#">About us</a> <a href="#">Support</a> <a href="#">Contact Us</a> <a href="#">Disclaimer</a>
                    </div>
                    <hr />
                    <!-- Search form -->
                    <div class="form">
                        <form class="form-inline" role="form">
                            <div class="form-group">
                                <input type="email" class="form-control" id="search" placeholder="">
                            </div>

                            <button type="submit" class="btn btn-default">Search</button>
                        </form>
                    </div>
                </div>                                                                    



            </div>
        </div>
    </div>


    <%@include file="WEB-INF/content/frontstore/recent_items.jsp" %>
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
    </script>
</html>
