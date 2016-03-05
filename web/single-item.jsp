<%-- 
    Document   : single-item
    Created on : Dec 27, 2015, 5:50:04 PM
    Author     : shanaka
--%>

<%@page import="com.certus.controllers.ViewFeaturedProducts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-View Product</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>
    <jsp:useBean id="items" scope="page" class="com.certus.controllers.ItemPage"/>
    <jsp:useBean id="item" scope="page" class="com.certus.controllers.SingleItem"/>
    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>

    <div class="items">
        <div class="container">
            <div class="row">

                <!-- Sidebar -->
                <div class="col-md-3 col-sm-3 hidden-xs">

                    <h5 class="title">Categories</h5>

                    <%@include file="WEB-INF/content/frontstore/side-nav.jsp" %>
                    <br />
                    <!-- Sidebar items (featured items)-->

                    <div class="sidebar-items">

                        <h5 class="title">Featured Items</h5>

                        <%
                            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
                            String productsPath1 = (String) env1.lookup("uploadpathproducts");
                            ViewFeaturedProducts fp = new ViewFeaturedProducts();
                            List<Product> pp = fp.viewFeatured(Integer.parseInt(request.getParameter("pid")));
                            if (pp != null) {
                                for (Product pro : pp) {
                        %>

                        <div class="sitem">
                            <!-- Don't forget the class "onethree-left" and "onethree-right" -->
                            <div class="onethree-left">
                                <!-- Image -->
                                <%
                                    String href = "single-item.jsp?cat=" + pro.getSubCategory().getCategory().getId() + "&sub=" + pro.getSubCategory().getId() + "&pid=" + pro.getId();
                                %>
                                <a href='<%=href%>'><img src="<%=productsPath1 + pro.getImageMain()%>" alt="" class="img-responsive" /></a>


                            </div>
                            <div class="onethree-right">
                                <!-- Title -->
                                <a href="<%=href%>"><%=pro.getName()%></a>
                                <!-- Para -->
                                <p><%=pro.getBrand().getBrandName()%></p>
                                <!-- Price -->
                                <p class="bold">
                                    <%
                                        if (pro.getDiscountPrice() != 0) {%>
                                    <del> Rs. <%=pro.getProductHasSizes().stream().findFirst().get().getPrice()%></del>
                                    <% } else {
                                    %>
                                    Rs. <%=pro.getProductHasSizes().stream().findFirst().get().getPrice()%>
                                    <%}%>
                                </p>
                                <%
                                    if (pro.getDiscountPrice() != 0) {%>
                                <p class="bold">Rs. <%=pro.getDiscountPrice(pro.getProductHasSizes().stream().findFirst().get().getPrice(), pro.getDiscountPrice())%></p>
                                <%}%>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <% }
                            }
                            fp.closeConnection();
                        %>
                    </div>


                </div>
                <div class="col-md-9 col-sm-9">
                    <%@include file="WEB-INF/content/frontstore/breadcrumb.jsp" %>

                    <%@include file="WEB-INF/content/frontstore/product-details.jsp" %>

                    <br />

                    <%@include file="WEB-INF/content/frontstore/product-description.jsp" %>

                    <div class="clearfix"></div>


                    <div class="row">



                    </div>

                </div>


            </div>
        </div>
    </div>
    <%@include file="WEB-INF/content/frontstore/recent_items.jsp" %>
    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
    <script type="text/javascript">
        /* attach a submit handler to the form */
        $("#reviewFrom").submit(function (event) {
            event.preventDefault();

            var $form = $(this),
                    url = $form.attr('action');

            var posting = $.post(url, {name: $('#name2').val(), rate: $('#rateBox').val(),
                comment: $('#comnt').val(), pro_id: $('#p_id').val()});

            posting.done(function (data) {
                if (data.indexOf('have to') >= 0) {
                    $('#login').modal('show');
                    $('#warningMsg').html(data);

                } else {
                    $('#reviewSection').append(data);
                    $("#reviewFrom").trigger("reset");

                }
            });
        });
    </script>
    <script type="text/javascript">
        $(document).on("click", "#addToCartBtn", function () {
            if ($('#sizeCombo').val() !== "Size") {
                $.ajax({
                    url: 'addToCartAction',
                    data: {size: $('#sizeCombo').val(), pro_id: $('#pdt_id').val(), qnty: $('#qntySel').val()},
                    type: 'get',
                    cache: false,
                    success: function (data) {
                        $('#msgAlrt').append("<strong>" + data + "</strong>&nbsp;");
                        $("#msgAlrt").fadeTo(2000, 500).slideUp(500, function () {
                            $("#msgAlrt").alert('close');
                        });
                        window.location.reload(true);
                    }
                    ,
                    error: function (jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status === 500) {
                            alert('Please enter valid quantity.');
                        } else
                        if (jqXHR.status === 505) {
                            alert('invalid Quantity.');
                        } else {
                            alert('error');
                        }
                    }
                }
                );
            } else {
                $('#availabl').html("<span style='color:red;'>Please select a size.</span>");
            }
        });

    </script>
    <script type="text/javascript">
        $(document).on("change", "#sizeCombo", function () {
            if ($('#sizeCombo').val() !== "Size") {
                $.ajax({
                    url: 'checkSizeAvailabilityAction',
                    data: {size: $('#sizeCombo').val(), pro_id: $('#pdt_id').val()},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        // alert(data);
                        if (parseInt(data) > 10) {
                            $('#availabl').html("<span style='color:green;'>Availability : In Stock</span>");
                            $('#addToCartBtn').prop("disabled", false);
                        } else if (parseInt(data) < 10) {
                            if (parseInt(data) === 0) {
                                $('#availabl').html("<span style='color:red;'>Availability : Empty Stock</span> ");
                                $('#addToCartBtn').prop("disabled", true);

                            } else {
                                $('#availabl').html("<span style='color:red;'>Availability : Only " + data + " in Stock</span> ");
                                $('#addToCartBtn').prop("disabled", false);
                            }
                        }

                    },
                    error: function () {
                        alert('error');
                    }
                }
                );
                $.ajax({
                    url: 'priceForSizeAction',
                    data: {size: $('#sizeCombo').val(), pro_id: $('#pdt_id').val()},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        if (data.indexOf('-') >= 0) {
                            var ary = data.split('-');
                            $('#priceSec').html(ary[0]).css('text-decoration', 'line-through');
                            $('#priceDiscSec').html(ary[1]);
                        } else {
                            $('#priceSec').html(data);
                        }

                    },
                    error: function () {
                        alert('error');
                    }
                }
                );
            }
        });

    </script>
</html>