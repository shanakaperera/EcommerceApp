<%-- 
    Document   : last-registrations-view
    Created on : Jan 8, 2016, 10:04:17 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-6 secColor">
    <div class="row">
        <div class="col-md-6">
            <h5><span class="glyphicon glyphicon-open-file"></span>&nbsp; Last Registrations</h5> 
        </div>
        <div class="col-md-6">
            <a href="customers.jsp" class="text-right pull-right" style="margin-top:7px">All Customers</a> 
        </div>
    </div>
    <div class="row">
        <table class="table table-striped"> 
            <thead> 
                <tr> 
                    <th>Customer Name</th> 
                    <th>Email</th> 
                    <th>Action</th> 
                </tr>                                 
            </thead>                             
            <tbody id="regTable"></tbody>
        </table>
    </div>                     
</div>
