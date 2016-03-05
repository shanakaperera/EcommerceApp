<%-- 
    Document   : edit_product
    Created on : Jan 18, 2016, 12:46:22 AM
    Author     : shanaka
--%>

<%@page import="com.certus.controllers.ProFilterCategory"%>
<%@page import="com.certus.dbmodel.SubCategory"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Admin Products</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Edit Product&nbsp;<small>View & Edit site Products&nbsp;</small></h4> 
                </div>
            </div>
            <%
                SingleItem item = new SingleItem();
                ProductHasSize phs = item.getProductSummary(Integer.parseInt(request.getParameter("pid")),
                        Integer.parseInt(request.getParameter("sid")));
                Context env = (Context) new InitialContext().lookup("java:comp/env");
                String productsPath = (String) env.lookup("uploadpathproducts");

            %>
            <%@include file="WEB-INF/content/admin/product-summary.jsp" %>
            <%@include file="WEB-INF/content/admin/edit-product-details.jsp" %>
        </div>
        <!-- ==================================modal=====================-->

        <%
//            String path = application.getRealPath("uploads/products").replace("build/", "");
            System.out.println(path);
        %>
        <jsp:useBean id="upBean" scope="session" class="javazoom.upload.UploadBean">
            <jsp:setProperty name="upBean" property="folderstore" value="<%=path%>"/>
        </jsp:useBean>
        <div class="modal fade pg-show-modal" id="modal1" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <form method="post" action="uploadImageAction" name="upform" enctype="multipart/form-data">

                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                             
                            <h4 class="modal-title">Upload Image</h4> 
                        </div>                         
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-4 col-md-offset-2">
                                    <a class="thumbnail">
                                        <img id="targetPrev" alt="your image"/> 
                                    </a>                                     
                                </div>
                                <div class="col-md-5 centerButtons">
                                    <div style="position:relative;">
                                        <a class='btn btn-primary' href='javascript:;'>
                                            Choose File...
                                            <input type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="uploadfile" size="40"  onchange='readURL(this);'>
                                        </a>
                                        &nbsp;
                                        <span class='label label-info' id="upload-file-info"></span>
                                    </div>

                                    <input type="hidden" name="todo" value="upload"/>
                                    <input type="hidden" name="pid" value="<%=phs.getProduct().getId()%>"/>
                                    <input type="hidden" name="sid" value="<%=phs.getSize().getId()%>"/>
                                    <input type="hidden" name="pName" value="<%=phs.getProduct().getName().replace(" ", "-")%>"/>
                                    <input type="hidden" name="brnd" value="<%=phs.getProduct().getBrand().getBrandName().replace(" ", "-")%>"/>
                                </div>
                            </div>                             
                        </div>                         
                        <div class="modal-footer"> 
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                             
                            <input class="btn btn-success" type="submit" name="Submit" value="Upload"/>                             
                        </div> 
                    </form>
                </div>                     
            </div>                 
        </div>

        <div class="modal fade pg-show-modal" id="modal2" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <form  class="form-horizontal" method="post" action="addNewPropertyAction">

                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>                             
                            <h4 class="modal-title">Add new Property</h4> 
                        </div>                         
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="control-label col-md-3" for="username">Property</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="propName" required="required">
                                </div>
                            </div>  
                            <div class="form-group">
                                <label class="control-label col-md-3" for="username">Description</label>
                                <div class="col-md-7">
                                    <input type="text" class="form-control" name="descrip" required="required">
                                    <input type="hidden" value="<%=phs.getProduct().getId()%>" name="pid"/>
                                    <input type="hidden" value="<%=phs.getSize().getId()%>" name="sid"/>
                                </div>
                            </div> 
                        </div>                         
                        <div class="modal-footer"> 
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                             
                            <input class="btn btn-success" type="submit" name="Submit" value="Add Property"/>                             
                        </div> 
                    </form>
                </div>                     
            </div>                 
        </div>                        
        <%
            item.closeConnection();
            ProFilterCategory pfc = new ProFilterCategory();
        %>
        <div class="modal fade pg-show-modal" id="modalFeature" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <div class="modal-content"> 
                    <div class="modal-header"> 
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Select As Featured Product</h4> 
                    </div>                     
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group"> 
                                    <label class="control-label" for="catSelct">Select Category</label>
                                    <select class="form-control" id="catSelct"> 
                                        <%
                                            for (SubCategory lst : pfc.getCatList()) {
                                        %>
                                        <option value="<%=lst.getCategory().getId() + " > " + lst.getId()%>">
                                            <%=lst.getCategory().getCatName() + " > " + lst.getSubCategoryName()%>
                                        </option>                                     

                                        <%}
                                            pfc.closeConnection();
                                        %>                                        
                                    </select>
                                </div>

                                <div id="proSection">
                                    <ul class="media-list" id="proMedia"></ul>  
                                </div>                               
                            </div>
                        </div>                         
                    </div>                     
                    <div class="modal-footer"> 
                        <form action="chooseFeaturedProAction">
                            <input type="hidden"  name="pid" value="<%=request.getParameter("pid")%>"/>
                            <input type="hidden"  name="sid" value="<%=request.getParameter("sid")%>"/>
                            <input type="hidden" name="fpid" id="feProId" value=""/>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success">Set As Featured Product</button> 
                        </form>
                    </div>                     
                </div>                 
            </div>             
        </div>

        <div class="modal fade pg-show-modal" id="modalPromo" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog"> 
                <form action="savePromotion" method="POST">
                    <div class="modal-content"> 
                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Set In Promotion Section</h4> 
                        </div>                     
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group"> 
                                        <label class="control-label" for="promoDes">Description</label>                                     
                                        <textarea class="form-control" name="promoDes" rows="3" id="promoDes"></textarea>
                                    </div>
                                    <div class="form-group"> 
                                        <label class="control-label" for="promoEnd">Promotion End Date</label>
                                        <input type="text" class="form-control" name="promoEnd" id="promoEnd" placeholder="date here">
                                    </div>
                                    <div class="form-group"> 
                                        <label class="control-label" for="promoPlace">Promotion Place</label>                                     
                                        <select id="promoPlace" name="promoPlace" class="form-control"> 
                                            <option value="1">1 place</option>                                         
                                            <option value="2">2 place</option>                                         
                                            <option value="3">3 place</option>                                         
                                        </select>
                                        <input type="hidden" name="pid" value="<%=request.getParameter("pid")%>"/>
                                        <input type="hidden" name="sid" value="<%=request.getParameter("sid")%>"/>
                                    </div>                                 
                                </div>
                            </div>                         
                        </div>                     
                        <div class="modal-footer"> 
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success">Save Promotion</button>                         
                        </div>                     
                    </div> 
                </form>
            </div>             
        </div>

        <%@include file="WEB-INF/content/admin/scripts.jsp" %>
        <script type="text/javascript">
            function ajaxFunc() {
                var currentURL = document.URL;
                var params = currentURL.extract();
                $.ajax({
                    url: "ChooseSubCatAction",
                    data: {cat_id: $('#formInput13 option:selected').val(), pid: params.pid},
                    cache: false,
                    success: function (data) {
                        $('#formInput15').html(data);
                    },
                    error: function () {
                        alert('error');
                    }

                });
            }
            $(document).ready(function () {
                ajaxFunc();
            });
            $(document).on("change", "#formInput13", function () {
                ajaxFunc();
            });
        </script>
        <script type = "text/javascript">

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
            function deleteImage(imageId) {
                var c = confirm('Are you sure you want to delete this image ?');
                if (c === true) {
                    $.ajax({
                        url: "deleteImageAction",
                        data: {imgId: imageId, path: $('#myPath').val()},
                        type: 'POST',
                        cache: false,
                        success: function (data) {
                            if (data !== "Error") {
                                window.location.reload(true);
                            }
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }
            function setAsMain(imageId) {
                var c = confirm('Are you sure you want to make this as main image ?');
                if (c === true) {
                    $.ajax({
                        url: "setAsMainImgAction",
                        data: {imgId: imageId},
                        type: 'POST',
                        cache: false,
                        success: function (data) {
                            if (data !== "Error") {
                                window.location.reload(true);
                            }
                        },
                        error: function () {
                            alert('error');
                        }
                    });
                }
            }

            function refresh() {
                window.location.reload(true);
            }
            function selectPro(fpid) {
                $('#feProId').val(fpid);
            }

            $(document).on("change", "#catSelct", function () {
                $.ajax({
                    url: "filterImgsFeaturedProAction",
                    data: {category: $('#catSelct').val()},
                    cache: false,
                    success: function (data) {
                        $('#proMedia').html(data);
                    },
                    error: function () {
                        alert('error');
                    }

                });
            });
        </script>
        <!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</body>
</html>
