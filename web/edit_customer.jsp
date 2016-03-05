<%-- 
    Document   : edit_customer
    Created on : Jan 5, 2016, 1:57:05 PM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.User"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Edit Customers</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <link rel="stylesheet" href="css/jquery-ui.min.css"/>
        <style type="text/css">
            table{
                border-collapse:separate; 
                border-spacing:1em;
            }
            .leaveMargin{
                margin-left: 5%;
            }
            .input-group{
                border-spacing: 0;
            }
            #proSection{   
                width:550px;   
                height:300px;    
                overflow:auto; overflow-x:hidden;   
            } 
            .cusBoder{
                border-bottom: 1px solid #EEE;
                border-bottom-width: 1px;
                border-bottom-style: solid;
                border-bottom-color: #EEE;
            }
        </style>
    </head>
    <%
    if (session.getAttribute("admin") == null) {
        response.sendRedirect("adminAuth.jsp");
    }
    %>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>
            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Edit Customer&nbsp;<small>&nbsp;Edit customer details</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs"> 
                        <li class="active">
                            <a href="#tab1" data-toggle="tab">Customer Details</a>
                        </li>                         
                        <li>
                            <a href="#tab2" data-toggle="tab">Order Details</a>
                        </li>                         
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1">
                            <div class="panel">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3>Customer Details</h3>
                                        <hr /> 
                                        <table id='formTable'>
                                            <%
                                                int uid = Integer.parseInt(request.getParameter("uid"));
                                                Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                                                User u = (User) s.load(User.class, uid);
                                            %>

                                            <tr>
                                                <td>Status :</td>
                                                <td>
                                                    <%
                                                        String aval = u.isAvailability() ? "btn-danger" : "btn-default";
                                                        String notAval = u.isAvailability() ? "btn-default" : "btn-danger";
                                                    %>
                                                    <div class='btn-group btn-toggle'> 
                                                        <button class='btn btn-xs <%out.write(aval);%>' type='button' onclick="enabledClick(<%=u.getId()%>);">Active</button>
                                                        <button class='btn btn-xs <%out.write(notAval);%>' type='button' onclick="disabledClick(<%=u.getId()%>);">Disable</button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Login Name :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="text" size="50" id="logName" class="form-control" value="<%=u.getUserName()%>">
                                                        <span class="input-group-addon" style='color:red;'>*</span> 
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>First Name :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="text" id="fName" class="form-control" value="<%=u.getFName()%>">
                                                        <span class="input-group-addon" style='color:red;'>*</span> 
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Last Name :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="text" id="lName" class="form-control" value="<%=u.getLName()%>">
                                                        <span class="input-group-addon" style='color:red;'>*</span> 
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Email :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="email" id="emailAdd" class="form-control" value="<%=u.getEmail()%>">
                                                        <span class="input-group-addon" style='color:red;'>*</span> 
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Telephone :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="text" id="telNum" class="form-control" value='<%=u.getTelephone()%>'>
                                                        <span class="input-group-addon" style='color:red;'>*</span> 
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>Password :</td>
                                                <td>
                                                    <div class="input-group">                                                          
                                                        <input type="text" id="pass" class="form-control">

                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="panel-footer" style="background-color: #FFF;">
                                    <button type="button" onclick="onSaveClicked(<%=u.getId()%>);" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-saved"></span>&nbsp;Save
                                    </button>
                                    <button type="button" class="btn btn-default" onclick="location.reload(true);">
                                        <span class="glyphicon glyphicon-refresh"></span>&nbsp;Reset
                                    </button>
                                </div>                                 
                            </div>
                        </div>
                        <div class="tab-pane" id="tab2">
                            <div class="panel">
                                <div class="row">
                                    <div class="col-md-12"> 
                                        <h3>Order Details</h3>
                                        <hr /> 
                                        <div class="col-md-10" id="orderTable">

                                        </div>
                                    </div>
                                </div>                                 
                            </div>
                        </div>
                    </div>                     
                </div>
            </div>
        </div>

        <div class="modal fade pg-show-modal" id="viewOrderInfo" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header" data-pg-collapsed> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">View Order Information</h4> 
                    </div>             
                    <div class="modal-body" data-pg-collapsed>
                        <div class="row" data-pg-collapsed>
                            <div class="col-md-12">
                            </div>
                        </div>                 
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="proSection">
                                    <ul class="media-list" id="orderInf">  </ul>
                                </div>
                            </div>
                        </div>                 
                    </div>             
                    <div class="modal-footer" data-pg-collapsed> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

                    </div>             
                </div>         
            </div>     
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <style type="text/css">
            .borderR{
                border: 1px solid red;
            }
        </style>
        <script type="text/javascript">
        $(document).ready(function () {
        var currentURL = window.location.href;
        var params = currentURL.extract();
        $.ajax({
            url: "loadCustomerOrdersAction",
        data: {uid: params.uid},
        cache: false,
            success: function (data) {
$('#orderTable').html(data);
},
    error: function () {
                        alert('error');
                    }
    });
            });

            function enabledClick(uid) {
            var c = confirm("Are you realy want to update the customer status ?");
            if (c === true) {


            $.ajax({
                url: "customerEnAction",
            data: {uid: uid, enable: true},
            cache: false,
                success: function (data) {
        location.reload(true);
},
error: function () {
    alert('error');
                        }
                    });
    }

            }

            function disabledClick(uid) {
            var c = confirm("Are you realy want to update the customer status ?");                 if (c === true) {
            $.ajax({
                url: "customerEnAction",
            data: {uid: uid, enable: false},
            cache: false,
                success: function (data) {
        location.reload(true);
},
error: function () {
        alert('error');
                        }
        });
                }
        }

        function getOrderInfo(oid) {
                $.ajax({
            url: "getOrderInfoAction",
        data: {oid: oid},
        cache: false,
            success: function (data) {
$('#orderInf').html(data);
},
    error: function () {
                        alert('error');
                    }
    });
            }

        function onSaveClicked(uid) {
        var c = confirm('Do you realy need to update the current customer details ?');
                if (c === true) {

        var userName = $('#logName').val();
        var fName = $('#fName').val();
        var lName = $('#lName').val();
        var email = $('#emailAdd').val();
                var tel = $('#telNum').val();
            var pass = $('#pass').val();
                if (userName !== '' && fName !== '' && lName !== '' && email !== ''
                            && tel !== '') {
                        $.ajax({
                    url: "updateCustomerByAdminAction",
                data: {uid: uid, uName: userName, fName: fName, lName: lName,
                    email: email, tel: tel, pass: pass},
                type: 'POST',
                cache: false,
                    success: function (data) {
                location.reload(true);
            },
            error: function () {
        alert('error');
    }
});
                    } else {
                        alert('You have some empty fields.');
                    }
                }
            }
        </script>
<!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>

