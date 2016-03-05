<%-- 
    Document   : reviews
    Created on : Jan 29, 2016, 9:40:09 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.SubCategory"%>
<%@page import="com.certus.controllers.ProFilterCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Reviews</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            #proSection{   
                width:900px;   
                height:600px;    
                overflow:auto; overflow-x:hidden;   
            } 
            #reviwSec{   
                width:550px;   
                height:400px;   
                overflow:auto; overflow-x:hidden;   
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>Customer&nbsp;Reviews<small>&nbsp;View Customer Reviews</small></h4> 
                </div>
            </div>
            <%
                ProFilterCategory pfc = new ProFilterCategory();
            %>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group"> 
                                <label class="control-label" for="catSel">Select Category</label>                                 
                                <select id="catSel" class="form-control"> 
                                    <%
                                        for (SubCategory lst : pfc.getCatList()) {
                                    %>
                                    <option value="<%=lst.getCategory().getCatName() + " > " + lst.getSubCategoryName()%>">
                                        <%=lst.getCategory().getCatName() + " > " + lst.getSubCategoryName()%>
                                    </option>                                     

                                    <%}%>
                                </select>
                            </div>                             
                        </div>
                    </div>
                    <hr />
                    <div id="proSection">
                        <ul class="media-list" id="severList"> </ul>
                    </div>                     
                </div>
            </div>
        </div>
        <!--            modal-->
        <div class="modal fade pg-show-modal" id="modal1" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         

                        <h4 class="modal-title">Product Reviews</h4> 
                    </div>                     

                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div id="reviwSec">
                                    <ul class="list-group" id='listItms'> 

                                    </ul>
                                </div>
                            </div>
                        </div>                                                  
                    </div>                     

                    <div class="modal-footer"> 
                        <button type="button"  class="btn btn-default" data-dismiss="modal">Close</button>                         
                    </div>                     
                </div>                 
            </div>             
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
    </body>
    <script type="text/javascript">
        $(document).ready(function () {
            var cat_ary = $('#catSel').val().split('>');
            $.ajax({
                url: "productsForReviewsAction",
                data: {category: cat_ary[0].trim(), sub_category: cat_ary[1].trim(),
                    size: $('#sizeSel').val()},
                cache: false,
                success: function (data) {
                    $('#severList').html(data);

                },
                error: function () {
                    alert('error');
                }
            });
        }
        );
        $(document).on('change', "#catSel", function () {
            var cat_ary = $('#catSel').val().split('>');
            $.ajax({
                url: "productsForReviewsAction",
                data: {category: cat_ary[0].trim(), sub_category: cat_ary[1].trim(),
                    size: $('#sizeSel').val()},
                cache: false,
                success: function (data) {
                    $('#severList').html(data);

                },
                error: function () {
                    alert('error');
                }
            });
        });
        function viewReviews(pid) {
            $.ajax({
                url: "loadReviewsForProductAction",
                data: {pid: pid},
                cache: false,
                success: function (data) {
                    $('#listItms').html(data);
                },
                error: function () {
                    alert('error');
                }
            });

        }
        function oNBtnClicked(rid, pid) {
            $.ajax({
                url: "updateProReviewStatsAction",
                data: {rid: rid, avl: true},
                cache: false,
                success: function (data) {
                    viewReviews(pid);
                },
                error: function () {
                    alert('error');
                }
            });
        }
        function offBtnClicked(rid, pid) {
            $.ajax({
                url: "updateProReviewStatsAction",
                data: {rid: rid, avl: false},
                cache: false,
                success: function (data) {
                    viewReviews(pid);
                },
                error: function () {
                    alert('error');
                }
            });
        }
    </script>
<!--            <script type="text/javascript" src="js/notification-bar-new.js"></script>-->


</html>
