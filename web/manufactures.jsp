<%-- 
    Document   : manufactures
    Created on : Jan 26, 2016, 12:36:59 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Manufactures</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <link rel="stylesheet" href="css/jquery-ui.min.css"/>
        <style type="text/css">
            #proSection{   
                width:550px;   
                height:416px;    
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Manufactures&nbsp;<small>View & Edit Manufactures&nbsp;</small></h4> 
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="button" class="btn btn-default" data-dismiss="modal" data-target="#brndSaveModal" data-toggle="modal">
                        <span class="glyphicon glyphicon-plus"></span>&nbsp;&nbsp;Add New
                    </button>                     
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <table class="table table-striped"> 
                        <thead> 
                            <tr> 
                                <th>Brand Name</th> 
                                <th>Status</th> 
                                <th>Product Count</th> 
                                <th>Action</th> 
                            </tr>                             
                        </thead>                         
                        <tbody id="brndTable"></tbody>
                    </table>                     
                </div>
            </div>
            <div class="row">

                <div class="col-md-9 col-sm-9">

                    <div class="paging" id="pagBrnd">

                    </div>
                </div>
            </div>
        </div>
        <%
            String path = application.getRealPath("uploads/brands").replace("build/", "");
            // System.out.println(path);
        %>
        <jsp:useBean id="upBrand" scope="session" class="javazoom.upload.UploadBean">
            <jsp:setProperty name="upBrand" property="folderstore" value="<%=path%>"/>
        </jsp:useBean>
        <div class="modal fade pg-show-modal" id="brndSaveModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <form method="POST" action="uploadBrandImageAction" name="upform" enctype="multipart/form-data">
                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         
                            <h4 class="modal-title">Manufactures</h4> 
                        </div>                     
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group"> 
                                        <label class="control-label" for="brndName">Brand Name :</label>
                                        <input type="text" class="form-control" name="brndName" required="required" id="brndName" placeholder="Brand Name Here">
                                    </div>
                                    <div class="col-xs-6 col-md-3"> 
                                        <a href="#" class="thumbnail"> 
                                            <img id='targetPrev' alt=""> 
                                        </a>                                     
                                    </div>
                                    <div style="position:relative;">
                                        <a class='btn btn-primary' href='javascript:;'>
                                            Choose File...
                                            <input type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="uploadfile" size="40"  onchange='readURL(this);'>
                                        </a>
                                        &nbsp;
                                        <span class='label label-info' id="upload-file-info"></span>
                                    </div>                                
                                </div>
                            </div>                         
                        </div>                     
                        <div class="modal-footer"> 
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                         
                            <button type="submit" class="btn btn-success">Save Brand</button>                         
                        </div>  
                    </form>
                </div>                 
            </div>


        </div>
        <div class="modal fade pg-show-modal" id="updateBndModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         
                        <h4 class="modal-title">Manufactures</h4> 
                    </div>                     
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group"> 
                                    <label class="control-label" for="brndName">Brand Name :</label>
                                    <input type="text" class="form-control" id="brndName" placeholder="Brand Name Here">
                                </div>
                                <div class="col-xs-6 col-md-3"> 
                                    <a href="#" class="thumbnail"> 
                                        <img src="file:///tmp/.org.chromium.Chromium.MKxlqA/placeholders/img4.jpg" alt=""> 
                                    </a>                                     
                                </div>
                                <button type="button" class="btn btn-default">Replace Image</button>                                 
                            </div>
                        </div>                         
                    </div>                     
                    <div class="modal-footer"> 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                         
                        <button type="button" class="btn btn-primary">Save Changes</button>                         
                    </div>                     
                </div>                 
            </div>             
        </div>

        <div class="modal fade pg-show-modal" id="ViewAllProModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                         
                        <h4 class="modal-title">Manufacture Products</h4> 
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

        <div id="dialog-form" title="Increase Stock Quantity">
            <p class="validateTips">All form fields are required.</p>
            <h4 id="pName"></h4>
            <form style="z-index: 1051;">
                <fieldset>
                    <div class="form-group"> 
                        <label class="control-label" for="size-sel">Select Size</label>
                        <select id="size-sel" name="size-sel" class="form-control"> 

                        </select>
                    </div>
                    <div class="form-group"> 
                        <label class="control-label" for="avlQnty">Already In Stock</label>
                        <input type="text" name="avlQnty" readonly="readonly" class="form-control" id="avlQnty">
                    </div>
                    <div class="form-group"> 
                        <label class="control-label" for="eachPrice">Each Price</label>
                        <div class="input-group"> 
                            <span class="input-group-addon">Rs.</span> 
                            <input type="text" name="eachPrice" class="form-control" id="eachPrice"> 
                        </div> 
                    </div>
                    <div class="form-group"> 
                        <label class="control-label" for="newQnty">Quantity Adding</label>
                        <input type="text" name="newQnty" class="form-control" id="newQnty">
                    </div>
                    <div class="form-group"> 
                        <label class="control-label" for="totPrice">Total Price</label>
                        <div class="input-group"> 
                            <span class="input-group-addon">Rs.</span> 
                            <input type="text"  name="totPrice" class="form-control" id="totPrice"> 
                        </div> 
                    </div>

                    <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
                </fieldset>
            </form>
        </div>

        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript" src="js/jquery-ui.min.js"></script>
        <script type="text/javascript">



                                                function loadBrands() {
                                                    $.ajax({
                                                        url: "loadBrandsAction",
                                                        dataType: 'json',
                                                        cache: false,
                                                        success: function (data) {

                                                            $.each(data, function (key, value) {
                                                                $('#brndTable').html(value.d1);
                                                                $('#pagBrnd').html(value.d2);
                                                            });
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                }
                                                $(document).ready(function () {
                                                    loadBrands();
                                                });
                                                function readURL(input) {
                                                    if (input.files && input.files[0]) {
                                                        var reader = new FileReader();
                                                        $('#targetPrev').hide();
                                                        reader.onload = function (e) {
                                                            $('#targetPrev')
                                                                    .attr('src', e.target.result)
                                                                    .width(150);
                                                            $('#targetPrev').fadeIn(3000);
                                                        };
                                                        reader.readAsDataURL(input.files[0]);
                                                        $("#upload-file-info").html($(input).val());
                                                    }
                                                }

                                                $(document).on("click", "#pagBrnd a", function (event) {
                                                    event.preventDefault();
                                                    var para = $(this).attr('href').match(/\d+/);
                                                    $.ajax({
                                                        url: 'loadBrandsAction?pgIndex=' + para,
                                                        cache: false,
                                                        success: function (data) {
                                                            $.each(data, function (key, value) {
                                                                $('#brndTable').html(value.d1);
                                                                $('#pagBrnd').html(value.d2);
                                                            });
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                });

                                                function getBrandId(val) {
                                                    $.ajax({
                                                        url: "loadBrandProAction",
                                                        data: {bid: val},
                                                        cache: false,
                                                        success: function (data) {
                                                            $('#mList').html(data);
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                }


                                                function onBtnClick(brnd, page) {

                                                    var c = confirm('Are you sure you want to update the status ?');
                                                    if (c === true) {
                                                        $.ajax({
                                                            url: "updateBrndStatAction",
                                                            data: {bid: brnd, stat: true},
                                                            cache: false,
                                                            success: function (data) {
                                                                if (data === 'Updated') {
                                                                    $.ajax({
                                                                        url: 'loadBrandsAction?pgIndex=' + page,
                                                                        cache: false,
                                                                        success: function (data) {
                                                                            $.each(data, function (key, value) {
                                                                                $('#brndTable').html(value.d1);
                                                                                $('#pagBrnd').html(value.d2);
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

                                                function offBtnClick(brnd, page) {
                                                    var c = confirm('Are you sure you want to update the status ?');
                                                    if (c === true) {
                                                        $.ajax({
                                                            url: "updateBrndStatAction",
                                                            data: {bid: brnd, stat: false},
                                                            cache: false,
                                                            success: function (data) {
                                                                if (data === 'Updated') {
                                                                    $.ajax({
                                                                        url: 'loadBrandsAction?pgIndex=' + page,
                                                                        cache: false,
                                                                        success: function (data) {
                                                                            $.each(data, function (key, value) {
                                                                                $('#brndTable').html(value.d1);
                                                                                $('#pagBrnd').html(value.d2);
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

        <script type="text/javascript">
            $(function () {
                var dialog, form,
                        eachPrice = $("#eachPrice"),
                        newQnty = $("#newQnty"),
                        totPrice = $("#totPrice"),
                        oldQnty = $('#avlQnty'),
                        sizeNpid = $('#size-sel'),
                        allFields = $([]).add(eachPrice).add(newQnty).add(totPrice),
                        tips = $(".validateTips");

                function updateTips(t) {
                    tips
                            .text(t)
                            .addClass("ui-state-highlight");
                    setTimeout(function () {
                        tips.removeClass("ui-state-highlight", 1500);
                    }, 500);
                }

                function checkLength(o, n, min, max) {
                    if (o.val().length > max || o.val().length < min) {
                        o.addClass("ui-state-error");
                        updateTips(n);
                        return false;
                    } else {
                        return true;
                    }
                }

                function checkRegexp(o, regexp, n) {
                    if (!(regexp.test(o.val()))) {
                        o.addClass("ui-state-error");
                        updateTips(n);
                        return false;
                    } else {
                        return true;
                    }
                }

                function addUser() {
                    var valid = true;
                    allFields.removeClass("ui-state-error");
                    valid = valid && checkLength(eachPrice, "Each Price cannot be empty", 3, 5);
                    valid = valid && checkLength(newQnty, "Quantity shouldn't be empty.", 1, 3);
                    valid = valid && checkLength(totPrice, "Total Price cannot be empty", 3, 5);

                    valid = valid && checkRegexp(eachPrice, /^([0-9])+$/, "Each Price field only allow : 0-9");
                    valid = valid && checkRegexp(newQnty, /^([0-9])+$/, "New Quantity field only allow : 0-9");

                    if (valid) {

                        $.ajax({
                            url: "addToStockAction",
                            data: {newQnty: newQnty.val(), total: totPrice.val(),
                                oldQnty: oldQnty.val(), pid: sizeNpid.val().split('-')[1],
                                size: sizeNpid.val().split('-')[0]},
                            cache: false,
                            success: function (data) {
                                getBrandId(data);
                                dialog.dialog("close");
                            }, error: function () {
                                alert('error');
                            }
                        });

                    }
                    return valid;
                }

                function getBrandId(val) {
                    $.ajax({
                        url: "loadBrandProAction",
                        data: {bid: val},
                        cache: false,
                        success: function (data) {
                            $('#mList').html(data);
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }

                dialog = $("#dialog-form").dialog({
                    autoOpen: false,
                    height: 530,
                    width: 400,
                    modal: true,
                    buttons: {
                        "Add To Stock": addUser,
                        Cancel: function () {
                            dialog.dialog("close");
                        }
                    },
                    close: function () {
                        form[ 0 ].reset();
                        allFields.removeClass("ui-state-error");
                    }
                });

                form = dialog.find("form").on("submit", function (event) {
                    event.preventDefault();
                    addUser();
                });

                $(document).on('change', '#size-sel', function () {
                    $.ajax({
                        url: "getQntyForSizeAction",
                        data: {param: $('#size-sel').val()},
                        cache: false,
                        success: function (data) {
                            $('#avlQnty').val(data);
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                });

                $('#newQnty').keyup(function (event) {
                    event.preventDefault();
                    var echPrice = $('#eachPrice').val();
                    if (echPrice !== null && echPrice !== '') {

                        $('#totPrice').val(parseInt(echPrice) * parseInt($('#newQnty').val()));
                    }
                });

                $(document).on('click', '#mList button', function () {
                    $('#dialog-form').parent().css("z-index", "1050");
                    $.ajax({
                        url: "getProDetailsForBrndAction",
                        dataType: 'json',
                        data: {pid: $(this).attr('id')},
                        cache: false,
                        success: function (data) {
                            $.each(data, function (key, value) {
                                $('#pName').html(value.product.name);
                                var ary = value.size;
                                var sizes = '';
                                for (var i = 0; i < ary.length; i++) {
                                    sizes += '<option value=' + ary[i] + "-" + value.product.id + '>' + ary[i] + '</option>';
                                }
                                $('#size-sel').html(sizes);
                                $('#avlQnty').val(value.qnty[0]);
                            });
                        }, error: function () {
                            alert('error');
                        }

                    });
                    dialog.dialog("open");
                    $('#dialog-form').parent().css("z-index", "1050");

                });
            });


        </script>
<!--        <script type="text/javascript" src="js/notification-bar-new.js"></script>-->

    </body>
</html>
