<%-- 
    Document   : product-summary
    Created on : Jan 18, 2016, 12:29:12 PM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page import="com.certus.controllers.SingleItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-md-12">
        <div class="panel"> 
            <div class="panel-heading">
                <h3>Product Summary</h3>
                <hr />
            </div>
            <div class="panel-body">
                
                <div class="row">
                    <div class="">
                        <div class="col-xs-6 col-md-3">
                            <a href="#" class="thumbnail"> 
                                <img src="<%=productsPath+phs.getProduct().getImageMain()%>" alt=""> 
                            </a>                                         
                        </div>                                     
                    </div>
                    <div class="col-md-4">
                        <div class="form-group"> 
                            <label class="control-label" for="formInput73">Product Name</label>
                            <input type="text" class="form-control" id="proNam" value="<%=phs.getProduct().getName()%>">
                        </div>
                        <div class="form-group"> 
                            <label class="control-label" for="proPrice">Price</label>
                            <input type="text" class="form-control" id="proPrice" value="<%=phs.getPrice()%>">
                        </div>
                        <div class="form-group"> 
                            <label class="control-label" for="proStatus">Product Status</label>
                            <input type="text" class="form-control" id="proStatus" value="<%=phs.getSize().isAvailability() == true ? "Available" : "Not Available"%>">
                        </div>                                     
                    </div>
                    <div class="col-md-4">
                        <div class="form-group"> 
                            <label class="control-label" for="proSize">Size </label>
                            <input type="text" class="form-control" id="proSize" value="<%=phs.getSize().getSizeName()%>">
                        </div>
                        <div class="form-group"> 
                            <label class="control-label" for="proId">Product Id</label>
                            <input type="text" class="form-control" id="proId" value="<%=phs.getId()%>">
                        </div>
                        <div class="form-group"> 
                            <label class="control-label" for="proQnty">Product Quantity</label>
                                   <input type="text" class="form-control" id="proQnty" value="<%=phs.getQnty()%>">
                        </div>                                     
                    </div>
                </div>
            </div>
        </div>                     
    </div>
</div>
