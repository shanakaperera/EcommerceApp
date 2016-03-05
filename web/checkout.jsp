<%-- 
    Document   : checkout
    Created on : Jan 26, 2016, 3:32:39 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Cart Checkout</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>
    </head>
    <jsp:useBean id="items" scope="page" class="com.certus.controllers.ItemPage"/>
    <jsp:useBean id="item" scope="page" class="com.certus.controllers.SingleItem"/>
    <jsp:useBean id="cart" scope="session" class="com.certus.controllers.ShoppingCart"/>
    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>

    <%
        User u = (User) request.getSession(false).getAttribute("user");
        if (u == null) { %>
    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-6">

                    <!-- Some content -->
                    <h3 class="title">You have to Login to Shop to Proceed Check-out  <span class="color">!!!</span></h3>
                    <h4 >Morbi tincidunt posuere turpis eu laoreet</h4>
                    <p>Nullam in est urna. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In ut nulla est. </p>
                    <h5>Maecenas hendrerit neque id</h5>
                    <div class="lists">
                        <ul>
                            <li>Etiam adipiscing posuere justo, nec iaculis justo dictum non</li>
                            <li>Cras tincidunt mi non arcu hendrerit eleifend</li>
                            <li>Aenean ullamcorper justo tincidunt justo aliquet et lobortis diam faucibus</li>
                            <li>Maecenas hendrerit neque id ante dictum mattis</li>
                            <li>Vivamus non neque lacus, et cursus tortor</li>
                        </ul>
                    </div>		
                    <p>Nullam in est urna. In vitae adipiscing enim. In ut nulla est. Nullam in est urna. In vitae adipiscing enim. Curabitur rhoncus condimentum lorem, non convallis dolor faucibus eget. In ut nulla est. </p>

                </div>


                <!-- Login form -->
                <div class="col-md-6">
                    <div class="formy well">
                        <h4 class="title">Login to Your Account</h4>
                        <div class="form">

                            <!--- Login  form --->
                            <form class="form-horizontal">                                         
                                <!-- Username -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="username2">Username</label>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" id="username2">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="form-group">
                                    <label class="control-label col-md-3" for="password2">Password</label>
                                    <div class="controls col-md-8">
                                        <input type="password" class="form-control" id="password2">
                                    </div>
                                </div>
                                <!-- Check-box -->
                                <div class="form-group">
                                    <div class="col-md-8 col-md-offset-3">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="inlineCheckbox3" value="agree"> Remember Password
                                        </label>
                                    </div>
                                </div> 

                                <!-- Buttons -->
                                <div class="form-group">
                                    <!-- Buttons -->
                                    <div class="col-md-8 col-md-offset-3">
                                        <button type="submit" class="btn btn-danger">Login</button>
                                        <button type="reset" class="btn btn-default">Reset</button>
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
    <%
    } else {
    %>

    <div class="checkout">
        <div class="container">
            <div class="row">
                <form action="confirmOrderAction" method="POST">
                    <div class="col-md-12">

                        <!-- Checkout page title -->
                        <h4 class="title"><i class="fa fa-shopping-cart"></i> Checkout</h4>

                        <div class="checkbox">
                            <label>
                                <input type="checkbox" onclick="loadMyDetails(<%=u.getId()%>);" name="tome" id="tome"/>To my Address
                            </label>
                        </div>
                        <!-- Sub title -->
                        <h5 class="title">Shipping &amp; Billing Details</h5>
                        <!-- Address and Shipping details form -->
                        <div class="row">
                            <div class="col-md-9">
                                <div class="form form-small">
                                    <div class="form-horizontal">
                                        <!-- Name -->
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="name1">Name</label>
                                            <div class="col-md-8">
                                                <input type="text" name="name1" required="required" class="form-control" id="name1">
                                            </div>
                                        </div>   
                                        <!-- Email -->
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="email1">Email</label>
                                            <div class="col-md-8">
                                                <input type="email" name="email1" required="required" class="form-control" id="email1">
                                            </div>
                                        </div>
                                        <!-- Telephone -->
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="telephone">Telephone</label>
                                            <div class="col-md-8">
                                                <input type="text" name="telephone" required="required" class="form-control" id="telephone">
                                            </div>
                                        </div>  
                                        <!-- Address -->
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="address">Address</label>
                                            <div class="col-md-8">
                                                <textarea class="form-control" name="address" required="required" rows="5" id="address"></textarea>
                                            </div>
                                        </div>                           

                                        <!-- City -->
                                        <div class="form-group">
                                            <label class="control-label col-md-2" for="city">City</label>
                                            <div class="col-md-8">
                                                <input type="text" required="required" name="city" class="form-control" id="city">
                                            </div>
                                        </div>    
                                        <p class="bg-info">At the moment we accept orders only in Sri Lanka</p>

                                    </div>
                                </div> 
                            </div>

                            <div class="col-md-3">

                                <table class="table table-bordered">
                                    <tr>
                                        <td>Order Id :</td>
                                        <td style="text-align: right"><%=cart.getInvoiceNum()%></td>
                                    </tr>
                                    <tr>
                                        <td>Total Items Count:</td>
                                        <td style="text-align: right"><%=cart.getTotalItemsOfTheCart()%></td>
                                    </tr>
                                    <tr>
                                        <td>Coupon code:</td>
                                        <td style="text-align: right"><%=cart.getCouponNum() == null || cart.getCouponNum().isEmpty() ? "No coupon added" : cart.getCouponNum()%></td>
                                    </tr>
                                    <tr>
                                        <td><h4>Grand Total </h4></td>
                                        <td style="text-align: right"><h4>Rs.<%=cart.getTotal()%></h4></td>
                                    </tr>
                                </table>

                            </div>
                        </div>

                        <hr />


                        <!-- Payment details section -->
                        <!-- Title -->
                        <h5 class="title">Payment Details</h5>

                        <!-- Radio buttons to select payment type -->

                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="1" checked>
                            Debit Card
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="2">
                            Credit Card
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios3" value="3">
                            Internet Banking
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios4" value="4">
                            Cash On Delivery
                        </label>          
                        <label class="radio-inline">
                            <input type="radio" name="optionsRadios" id="optionsRadios5" value="5">
                            Paypal
                        </label>                    

                        <hr />

                        <!-- Confirm order button -->
                        <div class="row">
                            <div class="col-md-4 col-md-offset-8">
                                <div class="pull-right">
                                    <button  type="submit" class="btn btn-danger">Confirm Order</button>

                                </div>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>

    <%}%>
    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
    <script type="text/javascript">
        function loadMyDetails(uid) {
            if ($('#tome').prop('checked') === true) {
                $.ajax({
                    url: "loadMyUserInfoAction",
                    dataType: 'json',
                    data: {uid: uid},
                    cache: false,
                    success: function (data) {

                        $('#name1').val(data.name);
                        $('#email1').val(data.email);
                        $('#telephone').val(data.tel);
                        $('#address').val(data.address);

                    },
                    error: function () {
                        alert('error');
                    }
                });

            } else {
                $('#name1').val('');
                $('#email1').val('');
                $('#telephone').val('');
                $('#address').val('');
            }

        }
    </script>
</html>
