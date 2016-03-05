<%-- 
    Document   : items
    Created on : Dec 26, 2015, 2:52:13 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.Product"%>
<%@page import="com.certus.controllers.ViewFeaturedProducts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Items</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>
    <jsp:useBean id="items" scope="page" class="com.certus.controllers.ItemPage"/>
    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>

    <div id="indexSec">
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
                                List<Product> pList = fp.viewFeatured(Integer.parseInt(request.getParameter("sub")),
                                        Integer.parseInt(request.getParameter("cat")));
                                if (pList != null) {
                                    for (Product pp : pList) {
                            %>
                            <div class="sitem">
                                <!-- Don't forget the class "onethree-left" and "onethree-right" -->
                                <div class="onethree-left">
                                    <!-- Image -->
                                    <%
                                        String href = "single-item.jsp?cat=" + pp.getSubCategory().getCategory().getId() + "&sub=" + pp.getSubCategory().getId() + "&pid=" + pp.getId();
                                    %>
                                    <a href='<%=href%>'><img src="<%=productsPath1 + pp.getImageMain()%>" alt="" class="img-responsive" /></a>
                                </div>
                                <div class="onethree-right">
                                    <!-- Title -->
                                    <a href="<%=href%>"><%=pp.getName()%></a>
                                    <!-- Para -->
                                    <p><%=pp.getBrand().getBrandName()%></p>
                                    <!-- Price -->
                                    <p class="bold">
                                        <%
                                            if (pp.getDiscountPrice() != 0) {%>
                                        <del> Rs. <%=pp.getProductHasSizes().stream().findFirst().get().getPrice()%></del>
                                        <% } else {
                                        %>
                                        Rs. <%=pp.getProductHasSizes().stream().findFirst().get().getPrice()%>
                                        <%}%>
                                    </p>
                                    <%
                                        if (pp.getDiscountPrice() != 0) {%>
                                    <p class="bold">Rs. <%=pp.getDiscountPrice(pp.getProductHasSizes().stream().findFirst().get().getPrice(), pp.getDiscountPrice())%></p>
                                    <%}%>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <%}
                                }
                                fp.closeConnection();
                            %>

                        </div>


                    </div>
                    <div class="col-md-9 col-sm-9">
                        <%@include file="WEB-INF/content/frontstore/breadcrumb.jsp" %>


                        <!-- Title -->
                        <!--                    <h4 class="pull-left">Apple iPhones</h4>-->


                        <!-- Sorting -->

                        <div class="form-group pull-right">    
                            <input type="hidden" value="<%=request.getParameter("cat")%>" id="category"/>
                            <input type="hidden" value="<%=request.getParameter("sub")%>" id="sub_category"/>
                            <select class="form-control" name="sortCombo" id="sortCombo">
                                <option>Sort By</option>
                                <option value="nameAsc">Name (A-Z)</option>
                                <option value="nameDec">Name (Z-A)</option>
                                <option value="priceAsc">Price (Low-High)</option>
                                <option value="priceDec">Price (High-Low)</option>
                                <option value="rates">Ratings</option>
                            </select>  

                        </div>


                        <div class="clearfix"></div>


                        <div class="row">

                            <%@include file="WEB-INF/content/frontstore/filter-section.jsp" %> 

                        </div>

                    </div>


                </div>
            </div>
        </div>
    </diV>
    <%@include file="WEB-INF/content/frontstore/recent_items.jsp" %>
    <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
    <%@include file="WEB-INF/content/frontstore/footer.jsp" %>

    <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>

    <script type="text/javascript">
        $(document).on("change", "#sortCombo", function () {
            $.ajax({
                url: 'sortProducts',
                data: {category: $('#category').val(), sub_category: $('#sub_category').val(), sort: $('#sortCombo').val()},
                type: 'get',
                cache: false,
                success: function (data) {
                    var split = data.split("-");
                    document.location.href = "http://localhost:8080/ECommerceApp/items.jsp?cat=" + split[0] + "&sub=" + split[1] + "&sort=" + split[2];

                },
                error: function () {
                    alert('error');
                }
            }
            );
        });
    </script>
    <!-- Sidebar navigation -->



</html>
