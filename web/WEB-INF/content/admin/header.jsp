<%-- 
    Document   : header
    Created on : Jan 8, 2016, 7:04:07 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style type="text/css">

    .dropdown {
        position: static;
    }
    .dropdown-menu {
        left: auto;
        position: absolute;
    }
    .fill-width > .dropdown-menu > li > a {
        white-space: normal; 
    }

</style>
<div class="row bg-success">
    <div class="col-md-6">
        <h1><span class="text-muted">C</span><span class="text-danger">ertus</span><small class="">&nbsp; Admin</small></h1>
    </div>
    <div class="col-md-6" style="margin-top:20px">
        <div class="btn-group pull-right">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"> 
                <span class="glyphicon glyphicon-user" style="font-size:2.0em"></span> 
            </button>                         
            <ul class="dropdown-menu" role="menu"> 
                <li>
                    <a href="#"></a>
                </li>                             
                <li>
                    <a href="admin_editProfile.jsp"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;&nbsp;Edit Profile</a>
                </li>                             
                <li>
                    <a href="site_info.jsp"><span class="glyphicon glyphicon-info-sign"></span>&nbsp;&nbsp;&nbsp;Edit Site Information</a>
                </li>
                <li>
                    <a href="admin.jsp"><span class="glyphicon glyphicon-dashboard"></span>&nbsp;&nbsp;&nbsp;DashBoard</a>
                </li>
                <li>
                    <a href="changeSliderImg.jsp"><span class="glyphicon glyphicon-th"></span>&nbsp;&nbsp;&nbsp;Edit Slider</a>
                </li>
                <li class="divider"></li>                             
                <li>
                    <a href="adminLogoutAction"><span class="glyphicon glyphicon-log-out"></span>&nbsp;&nbsp;&nbsp;Logout</a>

                </li>
            </ul>                         
        </div>
        <button type="button" class="btn btn-default pull-right" onclick="location.href = 'index.jsp';" data-toggle="tooltip" title="Go to Store Front">
            <span class="glyphicon glyphicon-new-window"  style="font-size:2.0em"></span>
        </button>
        <div class="btn-group pull-right fill-width" id="status-bar">
            <button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown'>
                <span class='glyphicon glyphicon-envelope' style='font-size:2.0em'></span>
                <span id="badge-id" class='badge'></span>
            </button>
        </div>                     
    </div>
</div>
