<%-- 
    Document   : product-description
    Created on : Dec 27, 2015, 7:59:52 PM
    Author     : shanaka
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Comparator"%>
<%@page import="com.certus.dbmodel.Rate"%>
<%@page import="com.certus.dbmodel.Review"%>
<%@page import="com.certus.dbmodel.Product"%>
<%@page import="com.certus.dbmodel.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Description, specs and review -->

<%--<jsp:useBean id="item" scope="page" class="com.certus.controllers.SingleItem"/>--%>
<style type="text/css">
    #reviewSection{   
        width:600px;   
        height:300px;    
        overflow:auto; overflow-x:hidden;   
    } 

</style>
<ul class="nav nav-tabs">
    <!-- Use uniqe name for "href" in below anchor tags -->
    <li class="active"><a href="#tab1" data-toggle="tab">Description</a></li>
    <li><a href="#tab2" data-toggle="tab">Specs</a></li>
    <li><a href="#tab3" data-toggle="tab">Review (<%=item.getReviewCount(Integer.parseInt(request.getParameter("pid")))%>)</a></li>
</ul>
<%
    String brndp = (String) env1.lookup("uploadpathbrands");
%>
<!-- Tab Content -->
<div class="tab-content">
    <!-- Description -->
    <div class="tab-pane active" id="tab1">
        <%=item.getProduct().getProduct().getDescription()%><br/>
        <%if (item.getProduct().getProduct().getBrand().getImg() != null & !item.getProduct().getProduct().getBrand().getImg().equals("")) {%>
        <img src="<%=brndp + item.getProduct().getProduct().getBrand().getImg()%>"  class="img-thumbnail" width="140">
        <%}%>
    </div>

    <!-- Sepcs -->
    <div class="tab-pane" id="tab2">

        <h5 class="title">Product Specs</h5>
        <table class="table table-striped tcart">
            <%=item.getProduct().getProduct().getSpecs()%>
        </table>

    </div>

    <!-- Review -->
    <div class="tab-pane" id="tab3">
        <h5>Product Reviews</h5>
        <% if (!item.getReviews().isEmpty()) {%>
        <div id="reviewSection">
            <%

                for (Review pro : item.getReviews()) {
                    if (pro.isAvailability()) {
            %>

            <div class="item-review">
                <h5><%=pro.getUser().getFName() + " " + pro.getUser().getLName()%> - 
                    <span class="color"><%=(int) pro.getUser().getRates().stream().findFirst().get().getRate()%>/5</span>
                </h5>
                <p class="rmeta"><%=pro.getDateComnt()%></p>
                <p><%=pro.getComment()%></p>
            </div>

            <%}
                }%>
        </div>
        <%}%>
        <hr />
        <h5 class="title">Write a Review</h5>

        <div class="form form-small">

            <!-- Review form -->
            <form class="form-horizontal" id="reviewFrom" action="reviewAction" method="POST">                                         
                <!-- Name -->
                <div class="form-group">
                    <label class="control-label col-md-3" for="name2">Your Name</label>
                    <div class="col-md-6">

                        <%
                            String logedUser = "";

                            if (request.getSession().getAttribute("user") != null) {
                                User u = (User) request.getSession().getAttribute("user");
                                logedUser = u.getFName() + " " + u.getLName();
                            }
                        %>
                        <input type="text" class="form-control" id="name2" value="<%=logedUser%>"/>
                    </div>
                </div>
                <!-- Select box -->
                <div class="form-group">
                    <label class="control-label col-md-3">Rating</label>
                    <div class="col-md-6">                               
                        <select class="form-control" id="rateBox">
                            <option>&nbsp;</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>  
                    </div>
                </div>

                <!-- Review -->
                <div class="form-group">
                    <label class="control-label col-md-3" for="name">Your Review</label>
                    <div class="col-md-6">
                        <textarea class="form-control" id="comnt"></textarea>
                        <input type="hidden" id="p_id" value="<%=request.getParameter("pid")%>"/>
                    </div>
                </div>
                <!-- Buttons -->
                <div class="form-group">
                    <!-- Buttons -->
                    <div class="col-md-6 col-md-offset-3">
                        <button type="submit" class="btn btn-default">Post</button>
                        <button type="reset" class="btn btn-default">Reset</button>
                    </div>
                </div>
            </form>
        </div> 

    </div>

</div>
