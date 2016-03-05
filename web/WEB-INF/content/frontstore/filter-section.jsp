<%-- 
    Document   : filters
    Created on : Dec 26, 2015, 8:02:15 AM
    Author     : shanaka
--%>

<%@page import="java.util.Set"%>
<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--<jsp:useBean id="items" scope="page" class="com.certus.controllers.ItemPage"/>--%>

<%
    Context env = (Context) new InitialContext().lookup("java:comp/env");
    String productsPath = (String) env.lookup("uploadpathproducts");
    int pageIndex = 0;
    int totalNumberOfRecords = 0;
    int numberOfRecordsPerPage = 9;
    
    String sPageIndex = request.getParameter("pgIndex");
    pageIndex = sPageIndex == null ? 1 : Integer.parseInt(sPageIndex);
    
    int s1 = (pageIndex * numberOfRecordsPerPage) - numberOfRecordsPerPage;
    
    if (request.getParameter("sort") != null) {
        // System.out.print("Works to here");
        items.filterProducts(Integer.parseInt(request.getParameter("sub")),
                Integer.parseInt(request.getParameter("cat")), request.getParameter("sort"),
                s1, numberOfRecordsPerPage);
    } else {
        items.filterProducts(Integer.parseInt(request.getParameter("sub")),
                Integer.parseInt(request.getParameter("cat")), s1, numberOfRecordsPerPage);
    }
    for (ProductHasSize p : items.getProList()) {
%>

<div class="col-md-4 col-sm-6">
    <!-- Each item should be enclosed in "item" class -->
    <div class="item">
        <!-- Item image -->
        <%  if (p.getProduct().getDiscountPrice() != 0) {
        %>
        <span class="ico pull-right"><img src="img/sale.png" alt="" /></span>
            <%}%>
        <div class="item-image">
            <a href="single-item.jsp?cat=<%=p.getProduct().getSubCategory().getCategory().getId()%>&sub=<%=p.getProduct().getSubCategory().getId()%>&pid=<%=p.getProduct().getId()%>"><img src="<%=productsPath + p.getProduct().getImageMain()%>" alt="" class="img-responsive" /></a>

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
            <h5 style="white-space: nowrap;overflow: hidden;"><a href="single-item.jsp?cat=<%=p.getProduct().getSubCategory().getCategory().getId()%>&sub=<%=p.getProduct().getSubCategory().getId()%>&pid=<%=p.getProduct().getId()%>"><%=p.getProduct().getName()%></a>
                <!--                if it is a hot product-->
                <!--                <span class="ico"><img src="img/hot.png" alt="" /></span>-->
            </h5>
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
    totalNumberOfRecords = (int) items.totalResults(Integer.parseInt(request.getParameter("sub")),
            Integer.parseInt(request.getParameter("cat")));
    int noOfPages = totalNumberOfRecords / numberOfRecordsPerPage;
    
    if (totalNumberOfRecords > (noOfPages * numberOfRecordsPerPage)) {
        noOfPages = noOfPages + 1;
    }
    

%>
<div class="col-md-9 col-sm-9">
    <%if (noOfPages > 1) {%>
    <div class="paging">
        <%for (int j = 1; j <= noOfPages; j++) {
                String myUrl = "";
                if (request.getParameter("sort") == null) {
                    myUrl = "items.jsp?cat=" + request.getParameter("cat") + "&sub=" + request.getParameter("sub") + "&pgIndex=" + j;
                    
                } else {
                    myUrl = "items.jsp?cat=" + request.getParameter("cat") + "&sub=" + request.getParameter("sub") + "&sort=" + request.getParameter("sort") + "&pgIndex=" + j;
                }
                if (j == pageIndex) {
        %>
        <span class='current'><%=j%></span>
        <%} else {%>

        <a href="<%=myUrl%>"><%=j%></a>
        <%}
            }%>
    </div>

    <%}%>
</div> 