<%-- 
    Document   : cart
    Created on : Dec 28, 2015, 1:29:28 PM
    Author     : shanaka
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashSet"%>
<%@page import="com.certus.controllers.CartItem"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-View Cart</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>
    <jsp:useBean id="cart" scope="session" class="com.certus.controllers.ShoppingCart"/>
    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>


    <!-- Cart starts -->
    <%
        if (cart.getTotalItemsOfTheCart() != 0) {

    %>
    <div class="cart">
        <div class="container">
            <div class="row">
                <div class="col-md-12">

                    <!-- Title with number of items in shopping kart -->
                    <h3 class="title"><i class="fa fa-shopping-cart"></i> Items in your cart [<span class="color">
                            <%=cart.getTotalItemsOfTheCart()%></span>]</h3>
                    <br />

                    <div class="table-responsive">
                        <!-- Table -->
                        <table class="table table-striped tcart">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Image</th>
                                    <th>Quantity</th>
                                    <th>Unit Price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>

                                <!-- Product details -->

                                <%
                                    int i = 1;
                                    List<Double> total = new ArrayList<Double>();
                                    Context env = (Context) new InitialContext().lookup("java:comp/env");
                                    String productsPath = (String) env.lookup("uploadpathproducts");
                                    for (CartItem ci : cart.getShoppingList()) {
                                %>
                                <tr>
                                    <!-- Index -->
                                    <td><%=i%></td>
                                    <!-- Product  name -->
                                    <td><a href="<%=cart.getUrlProductById(ci.getProduct_id())%>">
                                            <%=cart.getNameofProduct(ci.getProduct_id())%></a><br>

                                        <button type="button" class="btn btn-default" data-dismiss="modal" data-target="#newSize" data-toggle="modal" onclick="changeSize('<%=ci.getProduct_id()%>', '<%=ci.getSize()%>');" title="Change size"><i class="fa fa-adjust"></i></button>
                                        <span>Size : <%=ci.getSize()%></span>
                                    </td>
                                    <!-- Product image -->
                                    <td><a href="<%=cart.getUrlProductById(ci.getProduct_id())%>">
                                            <img src="<%=productsPath + cart.getImageofProduct(ci.getProduct_id())%>" alt="" />
                                        </a>
                                    </td>
                                    <!-- Quantity with refresh and remove button -->
                                    <td class="item-input">
                                        <div class="input-group" id="cartStff">
                                            <input class="form-control" min="1" max="9" type="number" id="qtyField<%=i%>" value="<%=ci.getQnty()%>">
                                            <input type="hidden" id="pro_id<%=i%>" value="<%=ci.getProduct_id()%>"/>
                                            <input type="hidden" id="pro_size<%=i%>" value="<%=ci.getSize()%>"/>
                                            <span class="input-group-btn">
                                                <button class="btn btn-default" id="updateQnty<%=i%>" type="button"><i class="fa fa-refresh"></i></button>
                                                <button class="btn btn-danger" id="removePrdty<%=i%>" type="button"><i class="fa fa-times"></i></button>     
                                            </span>
                                        </div>
                                    </td>
                                    <!-- Unit price -->
                                    <td>Rs.<%=cart.getPriceofProduct(ci.getProduct_id(), ci.getSize())%></td>
                                    <!-- Total cost -->
                                    <td>Rs. <%=cart.getPriceofProduct(ci.getProduct_id(), ci.getSize()) * ci.getQnty()%></td>
                                </tr>

                                <%
                                        total.add(cart.getPriceofProduct(ci.getProduct_id(), ci.getSize()) * ci.getQnty());
                                        i++;
                                    }%>


                                <!-- ----------------------------   total description----------------->
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                    <th><h3>Total</h3></th>
                            <th><h3>Rs. <%=cart.grandTotal(total)%></h3></th>
                            </tr>

                            </tbody>
                        </table>
                    </div>


                    <div class="form-inline">
                        <!-- Discount Coupen -->
                        <h5 class="title">Discount Coupon</h5>
                        <div class="form-group">
                            <input type="text" class="form-control" id="cupn_field" placeholder="Discount Coupon">
                        </div>

                        <button type="button" onclick="setDiscount();" class="btn btn-default">Apply</button>
                        <div class="row">
                            <div class="col-md-4" id="cupn_sec">

                            </div>
                        </div>
                        <br />
                        <br />

                    </div>  

                    <!-- Button s-->
                    <div class="row">
                        <div class="col-md-4 col-md-offset-8">
                            <div class="pull-right">
                                <a href="index.jsp" class="btn btn-default">Continue Shopping</a>
                                <a onclick="goToCheckOut();" class="btn btn-danger">CheckOut</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <%} else {%>
    <div class="row">
        <div class="col-md-12">
            <div style="text-align: center;">
                <span style="font-size:5em; color:#cfbfbf;" class="glyphicon glyphicon-shopping-cart "></span>
                <h2>YOUR SHOPPING CART IS EMPTY</h2>
                <p>You can add items to your shopping cart and try again.</p>
                <a href="index.jsp" class="btn btn-danger">Continue Shopping</a>
            </div>

        </div>

    </div>

    <%}%>
    <!-- Cart ends -->

    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

    <div class="modal fade" id="newSize" tabindex="-1" role="dialog" aria-hidden="true"> 
        <div class="modal-dialog"> 
            <div class="modal-content"> 
                <form action="updateCartSizeAction" method="POST">
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                             
                        <h4 class="modal-title">Change Size</h4> 
                    </div>                         
                    <div class="modal-body">


                        <div class="form-group"> 
                            <label class="control-label" for="catSt">Select Size</label>
                            <select class="form-control" id="sizeCombo" name="sizeCombo"> 

                            </select>
                            <input type="hidden" id="pro_id" name="pro_id"/>
                            <input type="hidden" id="sizePre" name="sizePre"/>
                        </div>                                 

                    </div>
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>                             
                        <button type="submit" class="btn btn-success">Update Size</button>                             
                    </div>   
                </form>
            </div>                     
        </div>                 
    </div>

    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>

    <script type="text/javascript">
        $(document).on("click", "#cartStff button", function () {
            var idd = this.id.match(/\d+/);
            if (this.id.contains("updateQnty")) {
                $.ajax({
                    url: 'updateProductAction',
                    data: {newQty: $('#qtyField' + idd).val(), pdtId: $('#pro_id' + idd).val(), size: $('#pro_size' + idd).val()},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        window.location.reload(true);
                    },
                    error: function () {
                        alert('Invalid Quantity.');
                    }
                }
                );

            }

        });
        $(document).on("click", "#cartStff button", function () {
            var idd = this.id.match(/\d+/);
            if (this.id.contains("removePrdty")) {
                $.ajax({
                    url: 'removeProductAction',
                    data: {pdtId: $('#pro_id' + idd).val(), size: $('#pro_size' + idd).val()},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        window.location.reload(true);
                    },
                    error: function () {
                        alert('error');
                    }
                }
                );

            }

        });

        function changeSize(pid, sName) {
            $('#pro_id').val(pid);
            $('#sizePre').val(sName);
            $.ajax({
                url: "sizesForChangeSizeAction",
                data: {pid: pid},
                cache: false,
                success: function (data) {
                    $('#sizeCombo').html(data);
                },
                error: function () {
                    alert('error');
                }
            });
        }
        function setDiscount() {
            if ($('#cupn_field').val().length !== 0) {
                $('#cupn_field').css('border-color', '');
                $.ajax({
                    url: "setCouponAction",
                    data: {cp_code: $('#cupn_field').val()},
                    cache: false,
                    success: function (data) {
                        $('#cupn_sec').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            } else {
                $('#cupn_field').css('border-color', 'red');
            }

        }
        function goToCheckOut() {
            $.ajax({
                url: "setOrderAction",
                cache: false,
                success: function (data) {
                    if (data.indexOf('first') > 0) {
                       // alert(data);
                       $('#login').modal({show:true});
                        //$('#login').show();
                    } else {

                        window.location.href = "checkout.jsp";
                    }
                },
                error: function () {
                    alert('error');
                }
            });
        }
    </script>
</html>
