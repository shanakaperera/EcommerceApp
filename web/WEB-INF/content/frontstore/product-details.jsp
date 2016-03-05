<%-- 
    Document   : product-details
    Created on : Dec 27, 2015, 6:13:47 PM
    Author     : shanaka
--%>

<%@page import="com.certus.controllers.SingleItem"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page import="com.certus.dbmodel.Product"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.certus.dbmodel.ProImg"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Product details -->
<%--<jsp:useBean id="item" scope="page" class="com.certus.controllers.SingleItem"/>--%>
<div class="product-main">
    <div class="row">
        <div class="col-md-6 col-sm-6">

            <!-- Image. Flex slider -->
            <div class="product-slider">
                <div class="product-image-slider flexslider">
                    <ul class="slides">
                        <!-- Each slide should be enclosed inside li tag. -->

                        <%
                            Context env = (Context) new InitialContext().lookup("java:comp/env");
                            String productsPath = (String) env.lookup("uploadpathproducts");
                            item.getProductDetials(Integer.parseInt(request.getParameter("pid")));
                            for (ProImg imags : item.getProduct().getProduct().getProImgs()) {

                        %>
                        <li>
                            <img src="<%=productsPath + imags.getImage()%>" alt=""/>
                            <%
                                boolean allZero = false;
                                Set<ProductHasSize> phs = item.getProduct().getProduct().getProductHasSizes();
                                for (ProductHasSize product : phs) {
                                    if (product.getQnty() > 0) {
                                        allZero = true;
                                        break;
                                    }
                                }
                                if (!allZero) {
                            %>

                            <div class="carousel-caption" style="top: 30%;">
                                <h4 style="color: #FF6868;">Out of Stock</h4>
                            </div>
                            <%}%>
                        </li>
                        <%}%>


                    </ul>
                </div>
            </div>

        </div>

        <div class="col-md-6 col-sm-6">
            <!-- Title -->

            <h4 class="title"><%=item.getProduct().getProduct().getName()%></h4>
            <h5>Price : Rs.
                <%
                    if (item.getProduct().getProduct().getDiscountPrice() != 0) {
                %>
                <span id="priceSec"><del><%=item.getProduct().getPrice()%></del></span>
                &nbsp;
                <span id="priceDiscSec" style="color: #FF6868;"><%=item.getProduct().getDiscountPrice(item.getProduct().getPrice(), item.getProduct().getProduct().getDiscountPrice())%></span>
                <%} else {%>
                <span id="priceSec"><%=item.getProduct().getPrice()%></span>
                <%}%>
            </h5>
            <p>Shipping : Free</p>
            <p>Brand : <%=item.getProduct().getProduct().getBrand().getBrandName()%></p>

            <!-- Dropdown menu -->
            <div class="form-group">                               
                <select class="form-control" id="sizeCombo">
                    <option>Size</option>
                    <%
                        List<String> sortedSizes = new ArrayList<String>();
                        for (ProductHasSize pph : item.getProduct().getProduct().getProductHasSizes()) {
                            sortedSizes.add(pph.getSize().getSizeName());
                        }
                        sortedSizes.sort(item.SIZES_COMPARATOR);
                        for (String size : sortedSizes) {
                    %>
                    <option value="<%=size%>"><%=size%></option>
                    <%}%>
                </select>  
            </div>

            <p id="availabl"></p>
            <!-- Quantity and add to cart button -->

            <div class="row">
                <div class="col-md-6">
                    <div class="input-group">
                        <input type="number" min="1" max="9" value="1" class="form-control input-sm" id="qntySel">
                        <input type="hidden" id="pdt_id" value="<%=request.getParameter("pid")%>"
                               <span class="input-group-btn">
                            <button class="btn btn-default btn-sm" type="button" id="addToCartBtn">Add to Cart</button>
                        </span>								  
                    </div>
                </div>
            </div>

            <!-- Add to wish list -->
            <a style="cursor: pointer;" onclick="addtoWishList('<%=request.getParameter("pid")%>');">+ Add to Wish List</a>


        </div>
    </div>
</div>
<script type="text/javascript">
    function addtoWishList(pid) {
        $.ajax({
            url: "addToWishListAction",
            data: {pid: pid},
            cache: false,
            success: function (data) {
                alert(data);

            }, error: function () {
                alert('error');
            }

        });
    }

</script>