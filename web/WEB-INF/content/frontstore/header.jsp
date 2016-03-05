<%-- 
    Document   : header
    Created on : Dec 25, 2015, 8:12:42 AM
    Author     : shanaka
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.controllers.ShoppingCart"%>
<%@page import="com.certus.controllers.CartItem"%>
<%@page import="com.certus.dbmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<header>
    <style type="text/css">
        #ico:hover{
            text-decoration: none;
        }
    </style>
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <!-- Logo. Use class "color" to add color to the text. -->
                <div class="logo">
                    <h1><a href="#">C<span class="color bold">ertus</span></a></h1>
                    <p class="meta">online shopping is fun!!!</p>
                </div>
            </div>
            <div class="col-md-5 col-md-offset-4">

                <!-- Search form -->
<!--                <form role="form">-->
                    <div class="input-group">
                        <input type="text" class="form-control" id="search1" placeholder="Search">
                        <span class="input-group-btn">
                            <button type="button"  onclick="searchBtnPressed();" class="btn btn-default">Search</button>
                        </span>
                    </div>
<!--                </form>-->

                <div class="hlinks">
                    <span>
                        <!-- item details with price -->
                        <%
                            if (request.getSession().getAttribute("cart") != null) {
                                ShoppingCart cart2 = (ShoppingCart) request.getSession().getAttribute("cart");
                                List<Double> total = new ArrayList<Double>();
                                for (CartItem ci : cart2.getShoppingList()) {
                                    total.add(cart2.getPriceofProduct(ci.getProduct_id(), ci.getSize()) * ci.getQnty());
                                }

                        %>
                        <a href="#cart" role="button" data-toggle="modal">
                            <%=cart2.getTotalItemsOfTheCart()%> Item(s) in your <i class="fa fa-shopping-cart"></i>
                        </a> -<span class="bold">Rs. <%=cart2.grandTotal(total)%></span> 
                        <% } else {
                        %>
                        <a href="#cart" role="button" data-toggle="modal">
                            0 Item(s) in your <i class="fa fa-shopping-cart"></i>
                        </a> -<span class="bold">Rs.0</span>
                        <%}%>
                    </span>
                    <!-- Login and Register link -->
                    <span class="lr">
                        <%
                            if (request.getSession().getAttribute("user") != null) {
                                User u = (User) request.getSession().getAttribute("user");

                        %>


                        <a href="#"><%=u.getFName()%></a>
                        <a id="ico"><i class="fa fa-user"> </i></a>
                        <a href="logoutAction"> Logout</a>

                        <%} else {%>
                        <a href="#login" role="button" data-toggle="modal">Login</a>
                        or <a href="#register" role="button" data-toggle="modal">Register</a>
                        <%}%>
                    </span>
                </div>

            </div>

        </div>

    </div>
</header>
