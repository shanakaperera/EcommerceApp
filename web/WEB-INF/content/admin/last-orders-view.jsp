<%-- 
    Document   : last-orders-view
    Created on : Jan 8, 2016, 10:03:07 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-6 secColor">
    <div class="row">
        <div class="col-md-6">
            <h5><span class="glyphicon glyphicon-usd"></span>&nbsp; Last 10 Orders</h5> 
        </div>
        <div class="col-md-6">
            <a href="orders.jsp" class="text-right pull-right" style="margin-top:7px">All Orders</a> 
        </div>
    </div>
    <div class="row">
        <table class="table table-striped"> 
            <thead> 
                <tr> 
                    <th>Order Id</th> 
                    <th>Customer Name</th> 
                    <th>Status</th> 
                    <th>Total</th> 
                    <th>Action</th> 
                </tr>                                 
            </thead>                             
            <tbody id="ordTable"> 
                                          
            </tbody>
        </table>
    </div>                     
</div>
