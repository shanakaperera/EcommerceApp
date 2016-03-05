<%-- 
    Document   : popular_deals
    Created on : Dec 25, 2015, 8:35:01 AM
    Author     : shanaka
--%>

<%@page import="java.util.Set"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.certus.controllers.ProWithRate"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page import="com.certus.controllers.PopularDeals"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div class="items">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <h3 class="title">Popular Deals</h3>
            </div>

            <%
                Context env1 = (Context) new InitialContext().lookup("java:comp/env");
                String productsPath1 = (String) env1.lookup("uploadpathproducts");
                PopularDeals pd = new PopularDeals();
                List<ProWithRate> pwr = pd.getPopularDeals();

                for (ProWithRate p : pwr) {
                    if (p.getProduct().isAvailability()) {
            %>


            <div class="col-md-3 col-sm-4">

                <div class="item">
                    <!-- Item image -->
                    <%  if (p.getProduct().getDiscountPrice() != 0) {
                    %>
                    <span class="ico pull-right"><img src="img/sale.png" alt="" /></span>
                        <%}%>

                    <div class="item-image">

                        <%
                            String url = "single-item.jsp?cat=" + p.getProduct().getSubCategory().getCategory().getId() + "&sub=" + p.getProduct().getSubCategory().getId() + "&pid=" + p.getProduct().getId();
                        %>
                        <a href="<%=url%>"><img src="<%=productsPath1 + p.getProduct().getImageMain()%>" alt="" class="img-responsive" /></a>

                        <%
                            boolean allZero = false;
                            Set<ProductHasSize> phs = p.getProduct().getProductHasSizes();
                            for (ProductHasSize product : phs) {
                                if (product.getQnty() > 0) {
                                    allZero = true;
                                    break;
                                }
                            }
                            if (!allZero) {
                        %>

                        <div class="carousel-caption" style="top: 30%;">
                            <h5 style="color: #FF6868;">Out of Stock</h5>
                        </div>
                        <%}%>
                    </div>
                    <!-- Item details -->
                    <div class="item-details">
                        <!-- Name -->
                        <!-- Use the span tag with the class "ico" and icon link (hot, sale, deal, new) -->
                        <div id="quickfit">
                            <h5 style="white-space: nowrap;overflow: hidden;"><a href="<%=url%>"><%=p.getProduct().getName()%></a></h5>
                        </div>
                        <div class="clearfix"></div>
                        <!-- Para. Note more than 2 lines. -->
                        <p><%=p.getProduct().getBrand().getBrandName()%></p>
                        <hr />
                        <!-- Price -->

                        <div class="item-price pull-left">

                            <%if (p.getProduct().getDiscountPrice() != 0.0) {%>
                            <del>Rs.&nbsp;<%=p.getPrice()%></del>
                            <p style="color: #F25758;">Rs.&nbsp;<%=p.getDiscountPrice(p.getPrice(), p.getProduct().getDiscountPrice())%></p>
                            <%} else {%>
                            Rs.&nbsp;<%=p.getPrice()%>
                            <%}%>

                        </div>

                        <!-- Add to cart -->
                        <form action="addToCartAction" method="GET">
                            <input type="hidden" name="pro_id" value="<%=p.getProduct().getId()%>"/>
                            <input type="hidden" name="size" value="<%=p.getSize(p.getProduct().getProductHasSizes(), p.getProduct().getId())%>"/>
                            <input type="hidden" name="qnty" value="1"/>
                            <input type="hidden" name="dom" value="tyt"/>
                            <div class="button pull-right">
                                <button class="btn" style="background-color: #F25758; color: #efd8d8;" <%=!allZero ? "disabled='disabled'" : ""%> type="submit">Add to Cart</button>
                            </div>
                        </form>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>

            <%}
                }
                pd.closeConnection();
            %>
        </div>
    </div>
</div>
