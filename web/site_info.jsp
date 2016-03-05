<%-- 
    Document   : site_info
    Created on : Feb 14, 2016, 4:49:06 PM
    Author     : shanaka
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Site Information</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Site Information&nbsp;<small>&nbsp;View and Edit Site Information</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs"> 
                        <li class="active">
                            <a href="#tab1" data-toggle="tab">General Information</a>
                        </li>
                        <li>
                            <a href="#tab2" data-toggle="tab">Terms & Conditions</a>
                        </li>             
                        <li data-pg-collapsed>
                            <a href="#tab3" data-toggle="tab">Email Settings</a>
                        </li>             
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1" data-pg-collapsed>
                            <div class="panel" data-pg-collapsed>
                                <div class="row" data-pg-collapsed>
                                    <div class="col-md-12">
                                        <h3>General Information</h3>
                                        <hr /> 
                                        <table id="siteGeneralTable"></table>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <button type="button" onclick="saveGeneralInfoClicked();" class="btn btn-danger">
                                        <span class="glyphicon glyphicon-saved"></span>&nbsp;Save
                                    </button>
                                    <button type="button" class="btn btn-default">
                                        <span class="glyphicon glyphicon-refresh"></span>&nbsp;Reset
                                    </button>
                                    <span id="infoSpanGeneral" style="color: green;"></span>
                                </div>                     
                            </div>
                        </div>
                        <div class="tab-pane" id="tab2" data-pg-collapsed>
                            <div class="panel">
                                <div class="row">
                                    <div class="col-md-12">
                                        <h3>Terms & Conditions</h3>
                                        <hr />
                                        <table id="trmsConsTable"></table>
                                    </div>
                                </div>                     
                            </div>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-danger" onclick="saveSiteTermsAndConditions();">
                                    <span class="glyphicon glyphicon-saved"></span>&nbsp;Save
                                </button>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-refresh"></span>&nbsp;Reset
                                </button>
                                <span id="infoSpanTerms"></span>
                            </div>
                        </div>
                        <div class="tab-pane" id="tab3">
                            <div class="panel">
                                <div class="row">
                                    <div class="col-md-12" data-pg-collapsed> 
                                        <h3>Email Settings</h3>
                                        <hr />

                                        <table id="emailSettingsTable"></table>
                                    </div>
                                </div>                     
                            </div>
                            <div class="panel-footer">
                                <button type="button" class="btn btn-danger" onclick="saveEmailSettingsClicked();">
                                    <span  class="glyphicon glyphicon-saved"></span>&nbsp;Save
                                </button>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-refresh"></span>&nbsp;Reset
                                </button>
                                <span id="infoSpan" style="color: green;"></span>
                            </div>
                        </div>
                    </div>         
                </div>
            </div>

        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            function loadData() {
                $.ajax({
                    url: "emailSettingsAction",
                    type: 'POST',
                    cache: false,
                    success: function (data) {
                        $('#emailSettingsTable').html(data);
                        $('#infoSpan').html();
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
            function loadSiteGenaralData() {
                $.ajax({
                    url: "siteGeneralLoadAction",
                    type: 'POST',
                    cache: false,
                    success: function (data) {
                        $('#siteGeneralTable').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }
            ///
            function loadSiteTermsAndConditions() {
                $.ajax({
                    url: "loadSiteTermsAndConditionsAction",
                    type: 'POST',
                    cache: false,
                    success: function (data) {
                        $('#trmsConsTable').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }

            $(document).ready(function () {
                loadData();
                loadSiteGenaralData();
                loadSiteTermsAndConditions();
            });

            function saveEmailSettingsClicked() {
                var c = confirm('Are you sure you want to save the changes ?');
                if (c === true) {
                    $.ajax({
                        url: "saveImageSettingsAction",
                        type: 'POST',
                        cache: false,
                        data: {emailAdd: $('#emailAdd').val(), hostN: $('#hostN').val()
                            , portN: $('#portN').val(), psswrdN: $('#psswrdN').val()},
                        success: function (data) {
                            loadData();
                            $('#infoSpan').html(data);
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }

            function saveGeneralInfoClicked() {
                var c = confirm('Are you sure you want to save the changes ?');
                if (c === true) {
                    $.ajax({
                        url: "saveGeneralInfoAction",
                        type: 'POST',
                        cache: false,
                        data: {addrsG: $('#addrsG').val(), telG: $('#telG').val()
                            , mailG: $('#mailG').val(), footerG: $('#footerG').val(),
                            mainG: $('#mainG').val(), usrDescG: $('#usrDescG').val()},
                        success: function (data) {
                            loadSiteGenaralData();
                            $('#infoSpanGeneral').html(data);
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }
            function saveSiteTermsAndConditions() {
                var c = confirm('Are you sure you want to save the changes ?');
                if (c === true) {
                    $.ajax({
                        url: "saveSilteTermsAndConditionsAction",
                        type: 'POST',
                        cache: false,
                        data: {mainSec: $('#mainSec').val(), securedSec: $('#securedSec').val(),
                            serviceOff: $('#serviceOff').val(), privcyPol: $('#privcyPol').val(),
                            condRuls: $('#condRuls').val(), comPolyT: $('#comPolyT').val(),
                            extNotGurntT: $('#extNotGurntT').val(), limitationT: $('#limitationT').val(),
                            pricingT: $('#pricingT').val(), serviceFeesT: $('#serviceFeesT').val(),
                            delivryT: $('#delivryT').val()},
                        success: function (data) {
                            loadSiteTermsAndConditions();
                            $('#infoSpanTerms').html(data);
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }
        </script>
        <script type="text/javascript" src="js/notification-bar-new.js"></script>


    </body>
</html>
