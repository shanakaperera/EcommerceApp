<%-- 
    Document   : categories
    Created on : Jan 10, 2016, 11:10:36 PM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Category</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            #proSection{   
                width:550px;   
                height:300px;    
                overflow:auto; overflow-x:hidden;   
            } 
        </style>
        <%
            if (session.getAttribute("admin") == null) {
                response.sendRedirect("adminAuth.jsp");
            }
        %>
    </head>
    <body class="bg-info">
        <div class="container">
            <%@include file="WEB-INF/content/admin/header.jsp" %>
            <div class="row">
                <div class="page-header" style="background-color:#F25758"> 
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Categories&nbsp;
                        <small>View & Edit site Categories&nbsp;</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="button" class="btn btn-default" data-dismiss="modal" data-target="#modal1" data-toggle="modal">
                        <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Add New
                    </button>                     
                </div>
            </div>
            
                <div class="row">
                    <div class="col-md-12">
                        <table class="table table-striped"> 
                            <thead> 
                                <tr> 
                                    <th>Category Name</th> 
                                    <th>Status</th> 
                                    <th>Product Count</th> 
                                    <th>SubCategories Count</th>
                                    <th>Action</th> 
                                </tr>                             
                            </thead>                         
                            <tbody id="catTable"></tbody>
                        </table>                     
                    </div>
                </div>
           
        </div>
        <!--            =======Modal Add New===============-->
        <div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                             
                        <h4 class="modal-title">Add New Category</h4> 
                    </div>                         
                    <div class="modal-body">
                        <form role="form">
                            <div class="form-group"> 
                                <label class="control-label" for="catName">Category Name</label>                                     
                                <input type="text" class="form-control" required="required" id="catName" placeholder="Category Name Here"> 
                            </div>
                            <div class="form-group"> 
                                <label class="control-label" for="catSt">Category Status</label>
                                <select class="form-control" id="catSt"> 
                                    <option value="1">ON</option>                                         
                                    <option value="0">OFF</option>                                         
                                </select>
                            </div>                                 
                        </form>                             
                    </div>
                    <div class="modal-footer"> 
                        <span id="errorSpan" style="color: red;"></span>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>                             
                        <button type="button" class="btn btn-primary" id="saveCat">Save Category</button>                             
                    </div>                         
                </div>                     
            </div>                 
        </div>

        <!--            =======Modal  Update===============-->
        <div class="modal fade" id="modal2" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                             
                        <h4 class="modal-title">Update Category</h4> 
                    </div>                         
                    <div class="modal-body">
                        <form role="form">
                            <div class="form-group"> 
                                <label class="control-label" for="catName">Category Name</label>                                     
                                <input type="text" class="form-control" required="required" id="catNameUp" placeholder="Category Name Here"> 
                            </div>
                            <div class="form-group"> 
                                <label class="control-label" for="catSt">Category Status</label>
                                <select class="form-control" id="catStUp"> 
                                    <option value="1">ON</option>                                         
                                    <option value="0">OFF</option>                                         
                                </select>
                            </div>                                 
                        </form>                             
                    </div>
                    <div class="modal-footer"> 
                        <span id="errorSpanUp" style="color: red;"></span>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>                             
                        <button type="button" class="btn btn-primary" id="saveCatUp">Update Category</button>                             
                    </div>                         
                </div>                     
            </div>                 
        </div>

        <div class="modal fade pg-show-modal" id="ViewAllProModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         
                        <h4 class="modal-title">Products for Category</h4> 
                    </div>                     
                    <div class="modal-body">
                        <div id="proSection">
                            <ul class="media-list" id='mList'> </ul>
                        </div>
                    </div>                     
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                                                  
                    </div>                     
                </div>                 
            </div>             
        </div>

        <div class="modal fade pg-show-modal" id="ViewAllSubModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         
                        <h4 class="modal-title">Sub-categories for Category</h4> 
                    </div>                     
                    <div class="modal-body">
                        <div>
                            <ul id='mListCat'> </ul>
                        </div>
                    </div>                     
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                                                  
                    </div>                     
                </div>                 
            </div>             
        </div>
        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            var catNametoUpdate = "";
            function loadCat() {
                $.ajax({
                    url: 'CategoriesAction',
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        $('#catTable').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }

            $(document).ready(function () {
                loadCat();
            });

            $(document).on("click", '#catTable .myBtn', function () {
                catNametoUpdate = this.id.split('-');
                $('#catNameUp').val(catNametoUpdate[0]);
                var boolean = (catNametoUpdate[2] === "true");
                $('#catStUp').val(boolean ? "1" : "0");
            });

            $(document).on("click", "#saveCat", function () {
                $.ajax({
                    url: 'AddCategoryAction',
                    data: {cat_name: $('#catName').val(), aval: $('#catSt').val(), status: "save"},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        if (data !== null) {
                            $('#errorSpan').html(data);
                        }
                        else {
                            $('#errorSpan').html("");
                            loadCat();
                        }

                    },
                    error: function () {
                        alert('error');
                    }
                });

            });
            $(document).on("click", "#saveCatUp", function () {
                $.ajax({
                    url: 'AddCategoryAction',
                    data: {cat_id: catNametoUpdate[1], cat_name: $('#catNameUp').val(),
                        aval: $('#catStUp').val(), status: "update"},
                    type: 'post',
                    cache: false,
                    success: function (data) {
                        if (data !== null) {
                            $('#errorSpanUp').html(data);
                        }
                        else {
                            $('#errorSpanUp').html("");
                            loadCat();
                        }

                    },
                    error: function () {
                        alert('error');
                    }
                });

            });

            function getCatId(val) {
                $.ajax({
                    url: "loadCatProAction",
                    data: {cid: val},
                    cache: false,
                    success: function (data) {
                        $('#mList').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }

            function getCatIdForSub(val) {
                $.ajax({
                    url: "loadCatSubAction",
                    data: {cid: val},
                    cache: false,
                    success: function (data) {
                        //alert('hiii');
                        $('#mListCat').html(data);
                    },
                    error: function () {
                        alert('error');
                    }
                });
            }



        </script>
<!--        <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
