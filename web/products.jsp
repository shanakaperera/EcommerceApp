<%-- 
    Document   : products
    Created on : Jan 12, 2016, 3:05:08 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.Brand"%>
<%@page import="com.certus.dbmodel.Size"%>
<%@page import="com.certus.dbmodel.SubCategory"%>
<%@page import="com.certus.controllers.ProFilterCategory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Products</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            .autocomplete-suggestions { border: 1px solid #999; background: #FFF; overflow: auto; }
            .autocomplete-suggestion { padding: 2px 5px; white-space: nowrap; overflow: hidden; }
            .autocomplete-selected { background: #F0F0F0; }
            .autocomplete-suggestions strong { font-weight: normal; color: #3399FF; }
            .autocomplete-group { padding: 2px 5px; }
            .autocomplete-group strong { display: block; border-bottom: 1px solid #000; }


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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Products&nbsp;<small>View & Edit site Products&nbsp;</small></h4> 
                </div>
            </div>

            <!--        ============Search=================-->
            <%
                ProFilterCategory pfc = new ProFilterCategory();
            %>
            <div class="row">
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-md-3">
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
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="control-label" for="sizeSel">Size </label>                                 
                                <select id="sizeSel" class="form-control"> 
                                    <%
                                        for (Size size : pfc.getSizes()) {
                                    %>
                                    <option value="<%=size.getSizeName()%>">
                                        <%=size.getSizeName()%>
                                    </option>                                     
                                    <%}%>
                                </select>
                            </div>                             
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label class="control-label" for="stSel">Enabled/Disabled </label>                                 
                                <select id="stSel" class="form-control"> 
                                    <option value="true">Enabled</option>                                     
                                    <option value="false">Disabled</option>                                     
                                </select>
                            </div>                             
                        </div>
                        <div class="proBtn col-md-3">
                            <button type="button" class="btn btn-default" id="goBtn">GO</button>
                            <button type="button" class="btn btn-default" onclick="window.location.reload(true);" id="resetBtn">
                                <span class="glyphicon glyphicon-refresh"></span>
                            </button>
                            <button type="button" class="btn btn-default" data-dismiss="modal" data-target="#modal3" data-toggle="modal"  id="addNewPro">
                                <span class="glyphicon glyphicon-plus"></span>&nbsp;Add New
                            </button>                             
                        </div>
                    </div>                     
                </div>
            </div>
            <!--=====================end Search=========================-->
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped"> 
                        <thead> 
                            <tr> 
                                <th>Product Name</th> 
                                <th>Status</th>
                                <th>Size</th>
                                <th>Quantity</th> 
                                <th>Price</th>
                                <th>Action</th> 
                            </tr>                             
                        </thead>                         
                        <tbody id="proFilterTab"></tbody>
                    </table>
                </div>
            </div>
            <div class="row">

                <div class="col-md-9 col-sm-9">
                    <div class="paging" id="pagId"></div>
                </div>
            </div>

            <!-- =======================     add new modal  ==================== -->

            <div class="modal fade pg-show-modal" id="modal3" tabindex="-1" role="dialog" aria-hidden="true"> 
                <div class="modal-dialog"> 
                    <div class="modal-content"> 
                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Add New Product</h4>                          
                        </div>                     
                        <form method="POST" action="addNewProAction">
                            <div class="modal-body">

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-group"> 
                                            <label class="control-label" for="proName">Product Name</label>
                                            <input type="text" class="form-control" required="required" size="70" id="proName"  name="proName" placeholder="Product Name here">
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12" id="avlSiz"></div>
                                        </div>
                                        <div class="form-group"> 
                                            <label class="control-label" for="proCat">Category</label>
                                            <select id="proCat"  name="proCat" class="form-control"> 
                                                <%
                                                    for (SubCategory lst : pfc.getCatList()) {
                                                %>
                                                <option value="<%=lst.getCategory().getId() + " > " + lst.getId()%>">
                                                    <%=lst.getCategory().getCatName() + " > " + lst.getSubCategoryName()%>
                                                </option>                                     

                                                <%}%>
                                            </select>
                                        </div>

                                        <div class="form-group"> 
                                            <label class="control-label" for="proBrand">Product Brand</label>
                                            <select class="form-control" id="selBrand" name="selBrand"> 
                                                <%
                                                    for (Brand b : pfc.getBrands()) {
                                                %>  
                                                <option value="<%=b.getId()%>"><%=b.getBrandName()%></option>
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group"> 
                                            <label class="control-label" for="proSize">Product Size</label>
                                            <select id="proSize"  name="proSize" class="form-control"> 
                                                <%
                                                    for (Size size : pfc.getSizes()) {
                                                %>
                                                <option value="<%=size.getId()%>">
                                                    <%=size.getSizeName()%>
                                                </option>                                     
                                                <%}%>
                                            </select>
                                        </div>
                                        <div class="form-group"> 
                                            <label class="control-label" for="proPrice">Product Price</label>
                                            <div class="input-group"> 
                                                <span class="input-group-addon">Rs.</span> 
                                                <input id="proPrice" type="text" name="proPrice" class="form-control"  required="required" placeholder="Product Price Here"> 
                                            </div>
                                        </div>                                                                  
                                    </div>
                                </div>                                                  
                            </div>                     
                            <input type="hidden" name="esnHid" value="" id="esnHid"/>
                            <div class="modal-footer"> 
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                         
                                <button type="submit" id="savBtn" class="btn btn-success">Save Product</button>                         
                            </div>  
                        </form>
                    </div>                 
                </div>             
            </div>



            <%@include file="WEB-INF/content/admin/scripts.jsp" %>

            <script type="text/javascript">
                //validate numbers...........................................................
                $("#proPrice").keydown(function (e) {
                    // Allow: backspace, delete, tab, escape, enter and .
                    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                            // Allow: Ctrl+A
                                    (e.keyCode == 65 && e.ctrlKey === true) ||
                                    // Allow: Ctrl+C
                                            (e.keyCode == 67 && e.ctrlKey === true) ||
                                            // Allow: Ctrl+X
                                                    (e.keyCode == 88 && e.ctrlKey === true) ||
                                                    // Allow: home, end, left, right
                                                            (e.keyCode >= 35 && e.keyCode <= 39)) {
                                                // let it happen, don't do anything
                                                return;
                                            }
                                            // Ensure that it is a number and stop the keypress
                                            if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                                                e.preventDefault();
                                            }
                                        });

                                $(document).ready(function () {
                                    $.ajax({
                                        url: 'AdimnProductFilterAction',
                                        dataType: 'json',
                                        cache: false,
                                        success: function (data) {
                                            $.each(data, function (key, value) {
                                                $('#proFilterTab').html(value.d1);
                                                $('#pagId').html(value.d2);
                                            });
                                        },
                                        error: function () {
                                            alert('error');
                                        }
                                    });

                                    function onSelectAction(pid) {
                                        $.ajax({
                                            url: 'onSelectAction',
                                            dataType: 'json',
                                            data: {pid: pid},
                                            cache: false,
                                            success: function (data) {
                                                var html = "";
                                                $.each(data, function (key, value) {
                                                    html += '<span class="label label-danger" style="margin-left:3%;">' + value.size_name + '</span>';
                                                    $('#selBrand').val(value.bnd_id);
                                                    $('#proCat').val(value.cat + " > " + value.sub_cat);
                                                });
                                                $('#avlSiz').html(html + "&nbsp;&nbsp; size/sizes available.");
                                            },
                                            error: function () {
                                                alert('error');
                                            }
                                        });
                                    }

                                    var info = [];


                                    $.ajax({
                                        url: 'loadProductsExistAction',
                                        dataType: 'json',
                                        cache: false,
                                        success: function (data) {
                                            console.log(data);
                                            $.each(data, function (key, value) {
                                                var person = {
                                                    value: value.name,
                                                    data: value.id
                                                };
                                                info.push(person);
                                            });
                                        },
                                        error: function () {
                                            alert('error');
                                        }
                                    });

                                    $('#proName').autocomplete({
                                        lookup: info,
                                        onSelect: function (suggestion) {
                                            // alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
                                            onSelectAction(suggestion.data);

                                        }
                                    });

                                });

                                $(document).on("change", "#proSize", function () {

                                    $.ajax({
                                        url: "sizeSelAction",
                                        data: {p_name: $('#proName').val(), cat_id: $('#proCat').val().split(">")[0].trim()
                                            , sub_id: $('#proCat').val().split(">")[1].trim(), size_id: $('#proSize').val()},
                                        cache: false,
                                        success: function (data) {
                                            $('#proPrice').val(data);
                                            if (data !== "") {
                                                $('#savBtn').prop('disabled', true);
                                                $('#esnHid').val('prog');
                                            } else if (data === "") {
                                                $('#savBtn').prop('disabled', false);
                                                $('#esnHid').val('');
                                            }

                                        },
                                        error: function () {
                                            alert('error');
                                        }
                                    });
                                });

                                $(document).on("click", "#pagId a", function (event) {
                                    event.preventDefault();
                                    var para = $(this).attr('href').match(/\d+/);
                                    var currentURL = $(this).attr('href');
                                    var params = currentURL.extract();
                                    if ($(this).attr('href').indexOf("catName") >= 1) {
                                        var urlVal = 'AdimnProductFilterAction?size=' + params.sizeName + '&sub_category=' + params.subCategory
                                                + '&category=' + params.catName + '&status=' + params.status + '&pgIndex=' + params.pgIndex;
                                        $.ajax({
                                            url: urlVal,
                                            cache: false,
                                            success: function (data) {
                                                $.each(data, function (key, value) {
                                                    $('#proFilterTab').html(value.d1);
                                                    $('#pagId').html(value.d2);
                                                });
                                            },
                                            error: function () {
                                                alert('error');
                                            }
                                        });
                                    } else {

                                        $.ajax({
                                            url: 'AdimnProductFilterAction?pgIndex=' + para,
                                            cache: false,
                                            success: function (data) {
                                                $.each(data, function (key, value) {
                                                    $('#proFilterTab').html(value.d1);
                                                    $('#pagId').html(value.d2);
                                                });
                                            },
                                            error: function () {
                                                alert('error');
                                            }
                                        });
                                    }
                                });

                                $(document).on("click", "#goBtn", function () {
                                    var cat_ary = $('#catSel').val().split('>');
                                    $.ajax({
                                        url: 'AdimnProductFilterAction',
                                        data: {category: cat_ary[0].trim(), sub_category: cat_ary[1].trim(),
                                            size: $('#sizeSel').val(), status: $('#stSel').val()},
                                        cache: false,
                                        success: function (data) {
                                            $.each(data, function (key, value) {
                                                $('#proFilterTab').html(value.d1);
                                                $('#pagId').html(value.d2);
                                            });
                                        },
                                        error: function () {
                                            alert('error');
                                        }
                                    });
                                });

                                function onBtnClick(prod, page) {

                                    var c = confirm('Are you sure you want to update the status ?');
                                    if (c === true) {
                                        $.ajax({
                                            url: "updateProStatAction",
                                            data: {pid: prod, stat: true},
                                            cache: false,
                                            success: function (data) {
                                                if (data === 'Updated') {
                                                    $.ajax({
                                                        url: 'AdimnProductFilterAction?pgIndex=' + page,
                                                        cache: false,
                                                        success: function (data) {
                                                            $.each(data, function (key, value) {
                                                                $('#proFilterTab').html(value.d1);
                                                                $('#pagId').html(value.d2);
                                                            });
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                }
                                            },
                                            error: function () {
                                                alert('error');
                                            }
                                        });
                                    }
                                }

                                function offBtnClick(prod, page) {
                                    var c = confirm('Are you sure you want to update the status ?');
                                    if (c === true) {
                                        $.ajax({
                                            url: "updateProStatAction",
                                            data: {pid: prod, stat: false},
                                            cache: false,
                                            success: function (data) {
                                                if (data === 'Updated') {
                                                    $.ajax({
                                                        url: 'AdimnProductFilterAction?pgIndex=' + page,
                                                        cache: false,
                                                        success: function (data) {
                                                            $.each(data, function (key, value) {
                                                                $('#proFilterTab').html(value.d1);
                                                                $('#pagId').html(value.d2);
                                                            });
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                }
                                            },
                                            error: function () {
                                                alert('error');
                                            }
                                        });
                                    }
                                }


            </script>
<!--            <script type="text/javascript" src="js/notification-bar-new.js"></script>-->

    </body>
</html>
