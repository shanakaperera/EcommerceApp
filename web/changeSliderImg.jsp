<%-- 
    Document   : changeSliderImg
    Created on : Feb 9, 2016, 7:57:08 AM
    Author     : shanaka
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.certus.dbmodel.SliderFacts"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Update Slider</title>
        <%@include file="WEB-INF/content/admin/stylesheets.jsp" %>
        <style type="text/css">
            .cusBoder{
                border-bottom: 1px solid #B1B2B4;
            }
            .leaveLine{
                margin-bottom: 5px;
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
                    <h4 style="color:#FFF"><span class="glyphicon glyphicon-list" style="padding:10px 0 0 10px"></span>&nbsp;Slider Images&nbsp;<small>Change Slider Images&nbsp;</small></h4> 
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <ul class="media-list" id="mListt"></ul>                         
                            </div>
                        </div>
                    </div>  
                </div>
            </div>


        </div>

        <div class="modal fade pg-show-modal" id="imgeSliderModal" tabindex="-1" role="dialog" aria-hidden="true"> 
            <div class="modal-dialog">
                <form id='sliderUpload'>
                    <div class="modal-content"> 
                        <div class="modal-header"> 
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Select Image For Slider</h4> 
                        </div>             
                        <div class="modal-body">
                            <ul class="nav nav-tabs" data-pg-collapsed> 
                                <li class="active">
                                    <a href="#tab1" id="selSection" data-toggle="tab">Select Image</a>
                                </li>         

                                <li>
                                    <a href="#tab2" id="upSection" data-toggle="tab">Upload New Image</a>
                                </li>                           
                            </ul>
                            <div class="tab-content" data-pg-collapsed>
                                <div class="tab-pane active" id="tab1">
                                    <div class="row" data-pg-collapsed>
                                        <div class="col-md-12">
                                            <div id="imgChoose"></div> 

                                        </div>
                                    </div>
                                </div>
                                <div class="tab-pane" id="tab2">
                                    <div class="col-xs-6 col-md-3"> 
                                        <a href="#" class="thumbnail"> 
                                            <img id='targetPrev' alt=""> 
                                        </a>                                     
                                    </div>
                                    <div style="position:relative;">
                                        <a class='btn btn-primary' href='javascript:;'>
                                            Choose File...
                                            <input type="file" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' id='uploadfile' name="uploadfile" size="40"  onchange='readURL(this);'>
                                        </a>
                                        &nbsp;
                                        <span class='label label-info' id="upload-file-info"></span>
                                    </div> 
                                </div>
                            </div>     

                        </div>             
                        <div class="modal-footer" id="modFooter"> 
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-success" id="selBtn" >Select Image</button>
                        </div>             
                    </div>  
                </form>
            </div>     
        </div>

        <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
        <script src="js/ddselect.js" type="text/javascript"></script>
        <script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
        <script type="text/javascript">

                                                $(document).ready(function () {

                                                    var count;
                                                    $.ajax({
                                                        url: "mainSliderAction",
                                                        dataType: 'json',
                                                        cache: false,
                                                        success: function (data) {
                                                            $.each(data, function (key, value) {
                                                                $('#mListt').html(value.d1);
                                                                count = value.d2;
                                                            });
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });
                                                    $.ajax({
                                                        url: "getAllProductsJsonAction",
                                                        dataType: 'json',
                                                        cache: false,
                                                        success: function (data) {

                                                            for (i = 0; i <= count; i++) {
                                                                var productName = $('#selPro-' + i).val();
                                                                $('#proSelect-' + i).ddslick({
                                                                    data: data,
                                                                    width: 400,
                                                                    height: 200,
                                                                    imagePosition: "left",
                                                                    selectText: "Select Product to show in slider ",
                                                                    onSelected: function (data) {

                                                                    }
                                                                });
                                                                $('#proSelect-' + i + ' li').each(function (index) {
                                                                    var curValue = $(this).find('.dd-option-text').text();
                                                                    curValue = curValue.trim();
                                                                    if (curValue === productName) {
                                                                        $('#proSelect-' + i).ddslick('select', {index: $(this).index()});
                                                                    }
                                                                });
                                                            }
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }
                                                    });

                                                });
                                                var sid;
                                                function saveSlide(id) {

                                                    var c = confirm("Are you sure you want to save the changes ?");
                                                    if (c === true) {
                                                        var productName = $('#proSelect-' + id).data('ddslick').selectedData.text;
                                                        var description = $('#sliderDesc-' + id).val();
                                                        var imageName = $('#img-' + id).attr('src');
                                                        var sliderId = $('#sliderId-' + id).val();
                                                        //reload with save ajax
                                                        $.ajax({
                                                            url: "updateSliderImageAction",
                                                            data: {pName: productName, desc: description, img: imageName, sid: sliderId},
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
                                                function replaceImgClk(id) {
                                                    $.ajax({
                                                        url: "chooseImageSliderAction",
                                                        cache: false,
                                                        success: function (data) {
                                                            $('#imgChoose').html(data);
                                                            sid = id;
                                                        },
                                                        error: function () {
                                                            alert('error');
                                                        }

                                                    });
                                                }

                                                $('#selSection').on('click', function () {
                                                    $('#modFooter').html('<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
                                                            + '<button type="button" id="selBtn" class="btn btn-success">Select Image</button>');
                                                });
                                                $('#upSection').on('click', function () {
                                                    $('#modFooter').html('<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'
                                                            + '<button type="button" onclick="upBtnClicked(' + sid + ');" class="btn btn-success">Select Uploaded Image</button>');

                                                });


                                                $('#imgeSliderModal').on('shown.bs.modal', function (e) {
                                                    $('.nav-tabs a[href="#tab1"]').tab('show');
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

                                                function upBtnClicked(id) {
                                                    var sampleFile = document.getElementById("uploadfile").files[0];
                                                    var formdata = new FormData();
                                                    formdata.append("sampleFile", sampleFile);
                                                    var xhr = new XMLHttpRequest();
                                                    xhr.open("POST", "uploadSliderImageAction", true);
                                                    xhr.send(formdata);
                                                    xhr.onload = function (e) {
                                                        if (this.status === 200) {
                                                            $('#img-' + id).attr('src', this.responseText);
                                                            $('#imgeSliderModal').modal('toggle');

                                                            //more code here
                                                        }
                                                    };

                                                }
                                                var imageName;
                                                $('#selBtn').on('click', function () {
                                                    if (undefined !== imageName && undefined !== sid) {
                                                        imageName = imageName.substring(imageName.lastIndexOf("img"));
                                                        $('#img-' + sid).attr('src', imageName);
                                                        $('#imgeSliderModal').modal('toggle');

                                                        //more code here


                                                    } else {
                                                        alert('You have to select an image first.');
                                                    }
                                                });

                                                function imgClicked(val) {
                                                    imageName = val.src;
                                                }



        </script>
<!--         <script type="text/javascript" src="js/notification-bar-new.js"></script>-->
    </body>
</html>
