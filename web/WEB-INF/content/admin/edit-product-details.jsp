<%-- 
    Document   : edit-product-details
    Created on : Jan 18, 2016, 12:39:21 PM
    Author     : shanaka
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.certus.dbmodel.Promotions"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.Product"%>
<%@page import="com.certus.controllers.ViewFeaturedProducts"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.select.Elements"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.certus.dbmodel.ProImg"%>
<%@page import="com.certus.dbmodel.Brand"%>
<%@page import="com.certus.dbmodel.Category"%>
<%@page import="com.certus.controllers.Categories"%>
<%@page import="com.certus.dbmodel.ProductHasSize"%>
<%@page import="com.certus.controllers.SingleItem"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style type="text/css">
    .tag {
        font-size: 14px;
        padding: .3em .4em .4em;
        margin: 0 .1em;
    }
    .tag a {
        color: #bbb;
        cursor: pointer;
        opacity: 0.6;
    }
    .tag a:hover {
        opacity: 1.0
    }
    .tag .remove {
        vertical-align: bottom;
        top: -3px;
    }
    .tag a {
        margin: 0 0 0 .3em;
    }
    .tag a .glyphicon-white {
        color: #fff;
        margin-bottom: 2px;
    }
</style>
<div class="row">
    <div class="col-md-12">
        <ul class="nav nav-tabs"> 
            <li class="active">
                <a href="#tab1" data-toggle="tab">General</a>
            </li>                         
            <li>
                <a href="#tab2" data-toggle="tab">Media</a>
            </li>                         
            <li>
                <a href="#tab3" data-toggle="tab">Promotions</a>
            </li>
            <li>
                <a href="#tab4" data-toggle="tab">Specifications</a>
            </li>
            <li>
                <a href="#tab5" data-toggle="tab">Featured Products</a>
            </li> 
            <li>
                <a href="#tab6" data-toggle="tab">Discounts</a>
            </li> 
        </ul>
        <%
            //  SingleItem item = new SingleItem();
            // ProductHasSize phs = item.getProductSummary(Integer.parseInt(request.getParameter("pid")),
            //   Integer.parseInt(request.getParameter("sid")));
            Context env1 = (Context) new InitialContext().lookup("java:comp/env");
            String addNewPathImg = (String) env1.lookup("addNewImg");

        %>
        <div class="tab-content">
            <div class="tab-pane active" id="tab1">
                <div class="panel">
                    <div class="row" style="padding:3% 5% 2% 3%;">
                        <div class="col-md-12 littleSpace">
                            <form action="updateGenaralProAction" method="POST">
                                <table style="border-collapse:separate; 
                                       border-spacing:1em;">
                                    <tr>
                                        <td> 
                                            <label class="control-label">Status : &nbsp;</label>
                                        </td>
                                        <td> 
                                            <%String avlOn = phs.getProduct().isAvailability() ? "btn-danger" : "btn-default";
                                                String avlOff = phs.getProduct().isAvailability() ? "btn-default" : "btn-danger";
                                            %>
                                            <div class='btn-group btn-toggle'> 
                                                <button class='btn btn-xs <%=avlOn%>' type='button'>ON</button>
                                                <button class='btn btn-xs <%=avlOff%>' type='button'>OFF</button>
                                            </div>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 
                                            <label class="control-label">Product Name : &nbsp;</label>
                                        </td>
                                        <td>
                                            <input type="text" class="form-control" size="70" required="required" name="pName" value="<%=phs.getProduct().getName()%>"> 
                                        </td>
                                    </tr>
                                    <tr>

                                        <td> 
                                            <label class="control-label">Description : &nbsp;</label>
                                        </td>
                                        <%
                                            Matcher m = null, m1 = null;
                                            boolean b = false, b1 = false;
                                            if (phs.getProduct().getDescription() != null) {
                                                m = Pattern.compile("<h5>(.+?)</h5>").matcher(phs.getProduct().getDescription());
                                                b = m.find();
                                                m1 = Pattern.compile("<p>(.+?)</p>").matcher(phs.getProduct().getDescription());
                                                b1 = m1.find();
                                            }
                                        %>
                                        <td colspan="2">
                                            <input type="text" name="disc_short" required="required" class="form-control" placeholder="Ex :Lara Karen Elasticated Neck Top" value="<%=b ? m.group(1) : ""%>"> 
                                        </td>
                                        <td>
                                            <textarea class="form-control" required="required" name="disc_long" rows="3" cols="70" placeholder="EX: Flaunt your stylish look wearing this multicoloured top from Lara Karen. Made from 100% polyester, this off-shoulder top will stay extremely soft against the skin."><%=b1 ? m1.group(1) : ""%></textarea>                                                 
                                        </td>

                                    </tr>
                                    <tr>
                                        <td> 
                                            <label class="control-label">Product Category : &nbsp;</label>
                                        </td>
                                        <td>
                                            <div class="form-group"> 
                                                <select id="formInput13" class="form-control" name="cat_sel"> 
                                                    <%
                                                        Categories c = new Categories();
                                                        for (Category cat : c.Categories()) {
                                                    %>
                                                    <option value="<%=cat.getId()%>" <%=phs.getProduct().getSubCategory().getCategory().getId() == cat.getId() ? "selected='selected'" : ""%>>
                                                        <%=cat.getCatName().substring(0, 1).toUpperCase() + cat.getCatName().substring(1)%>
                                                    </option>    
                                                    <%}
                                                       // c.closeConnection();
                                                    %>

                                                </select>
                                            </div>                                                 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 
                                            <label class="control-label">Product Sub Category : &nbsp;</label>
                                        </td>
                                        <td>
                                            <div class="form-group"> 
                                                <select id="formInput15" class="form-control" name="sub_cat_sel"></select>
                                            </div>                                                 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 
                                            <label class="control-label">Product Brand : &nbsp;</label>
                                        </td>
                                        <td>
                                            <div class="form-group"> 
                                                <select id="formInput14" class="form-control" name="brnd_sel"> 
                                                    <%
                                                        for (Brand brd : c.Brands()) {
                                                    %>
                                                    <option value="<%=brd.getId()%>" <%=phs.getProduct().getBrand().getId() == brd.getId() ? "selected='selected'" : ""%>><%=brd.getBrandName()%></option>                                                         
                                                    <%}
                                                      //  c.closeConnection();
                                                    %>                                                  
                                                </select>
                                            </div>                                                 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td> 
                                            <label class="control-label">Price : &nbsp;Rs.</label>
                                        </td>
                                        <td>
                                            <!-----Price------->
                                            <input type="text" id="priceD" onkeypress='return event.charCode >= 48 && event.charCode <= 57' name="price" required="required" class="form-control"  value="<%=phs.getPrice()%>"/> 
                                        </td>
                                    </tr>
                                </table>
                                <div class="row pull-right">
                                    <input type="hidden" value="<%=phs.getId()%>" name="phs_id"/>
                                    <input type="hidden" value="<%=phs.getSize().getId()%>" name="sid"/>
                                    <button type="button" class="btn" onclick="window.location.reload(true);" style="background-color: #F25758; color: #efd8d8;">
                                        <span class="glyphicon glyphicon-remove"></span>&nbsp;Cancel Updates
                                    </button>
                                    <button type="submit" class="btn" name="saveGen" style="background-color: #F25758; color: #efd8d8;">
                                        <span class="glyphicon glyphicon-floppy-save"></span>&nbsp;Save Updates
                                    </button>
                                </div>
                            </form>
                        </div>

                    </div>

                </div>

            </div>
            <div class="tab-pane" id="tab2">
                <div class="panel">
                    <div class="panel-heading">
                        <h3>Images</h3>
                        <hr />
                    </div>                                 
                    <div class="panel-body">
                        <div class="row">


                            <%
                                String path = application.getRealPath("uploads/products").replace("build/", "");
                                for (ProImg img : c.Images(Integer.parseInt(request.getParameter("pid")))) {
                            %>

                            <div class="col-xs-6 col-md-3">
                                <a class="thumbnail">
                                    <img src="<%=productsPath + img.getImage()%>" alt="img">
                                    <div class="btn-group btnUpImg"> 
                                        <button type="button" class="btn btn-default" data-toggle="tooltip" title="Replace">
                                            <span class="glyphicon glyphicon-open"></span>
                                        </button>
                                        <button type="button" class="btn btn-default" data-toggle="tooltip" <%if (img.getProduct().getImageMain().equals(img.getImage())) {
                                                out.write("disabled='disabled'");
                                            }%> title="Make as Main Image" onclick="setAsMain(<%=img.getId()%>);">
                                            <span class="glyphicon glyphicon-flag"></span>
                                        </button>
                                        <input type="hidden" value="<%=path%>" id="myPath"/>
                                        <button type="button" class="btn btn-default" data-toggle="tooltip" <%if (img.getProduct().getImageMain().equals(img.getImage())) {
                                                out.write("disabled='disabled'");
                                            }%> title="Delete" onclick="deleteImage(<%=img.getId()%>);">
                                            <span class="glyphicon glyphicon-trash"></span>
                                        </button>                                                     
                                    </div>                                                                                                  
                                </a>                                                                                          
                            </div>
                            <%}
                                c.closeConnection();
                            %>

                            <div class="col-xs-6 col-md-3"> 
                                <a data-dismiss="modal" data-target="#modal1" data-toggle="modal" class="thumbnail"> 
                                    <img src="<%=addNewPathImg%>"  alt="addNew"> 
                                </a>                                             
                            </div>
                        </div>
                    </div>                                 
                </div>

            </div>
            <div class="tab-pane" id="tab3">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3>Promotions</h3>
                                <hr />
                            </div>                                         

                            <div class="panel-body">
                                <%
                                    Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                                    Promotions p = (Promotions) s.createCriteria(Promotions.class, "promo")
                                            .createAlias("promo.proId", "proId")
                                            .add(Restrictions.eq("proId.id", Integer.parseInt(request.getParameter("pid"))))
                                            .uniqueResult();
                                    if (p != null) {
                                %>
                                <table class="table table-bordered">
                                    <tr>
                                        <td>Description</td>
                                        <td><%=p.getDescription()%></td>
                                    </tr>
                                    <tr>
                                        <td>Date Ended :</td>
                                        <td><%=p.getDateAdded()%></td>
                                    </tr>
                                    <tr>
                                        <td>Date Added :</td>
                                        <td><%=p.getDateEnded()%></td>
                                    </tr>
                                </table>
                                <%}
                                    s.close();
                                %>
                                <button type='button' data-dismiss="modal" data-target="<%=p == null ? "#modalPromo" : "#modalUpdatePromo"%>" data-toggle="modal" class='btn' style='background-color: #F25758; color: #efd8d8;'><%=p == null ? "Set In Promotion Section" : "Update Promotion"%>
                            </div>                                         
                        </div>                                                                          
                    </div>
                </div>

            </div>
            <div class="tab-pane" id="tab4">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3>Specifications</h3>
                                <hr />
                            </div>                                         


                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <table style="border-collapse:separate;border-spacing:1em;" id="apndTable">
                                            <thead>
                                                <tr>
                                                    <th>Property</th>
                                                    <th>Description</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    String html = "<table>" + phs.getProduct().getSpecs() + "</table>";
                                                    Document doc = Jsoup.parse(html);
                                                    Elements elements = doc.select("tbody > tr");

                                                    for (Element e : elements) {

                                                %>

                                                <tr> 
                                            <form action="updatePropertyAction" method="POST">
                                                <td>
                                                    <input type="text" class="form-control" name='prop' required="required" placeholder="Property" value="<%=e.select("td > strong").text().trim()%>">
                                                    <input type="hidden" name="pre_prop" value="<%=e.select("td > strong").text().trim()%>"/>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" name="desc" required="required" placeholder="Property Description" size="70" value="<%=e.select("td").text().replace("\"", "`")
                                                            .replace(e.select("td > strong").text(), "").trim()%>">
                                                    <input type="hidden" name="pre_desc" value="<%=e.select("td").text().replace("\"", "`")
                                                            .replace(e.select("td > strong").text(), "").trim()%>"/>
                                                </td>
                                                <td>
                                                    <input type="hidden" name="pid" value="<%=phs.getProduct().getId()%>"/>
                                                    <input type="hidden" name="sid" value="<%=phs.getSize().getId()%>"/>
                                                    <button type="submit" class="btn btn-default" name="updateBtn">
                                                        <span class="glyphicon glyphicon-adjust"></span>
                                                        &nbsp;&nbsp;Update
                                                    </button>
                                                    <button type="submit" class="btn btn-default" name="removeBtn">
                                                        <span class="glyphicon glyphicon-remove"></span>
                                                        Remove
                                                    </button>
                                                </td>
                                            </form>
                                            </tr>
                                            <%}%>
                                            </tbody>
                                        </table>
                                        <div>
                                            <button type='button' data-dismiss="modal" data-target="#modal2" data-toggle="modal" class='btn' style='background-color: #F25758; color: #efd8d8;'>Add New</button>   

                                            <button type="button" class="btn pull-right" onclick="refresh();" style="background-color: #F25758; color: #efd8d8; margin-right: 2%;">
                                                <span class="glyphicon glyphicon-remove"></span>&nbsp;Cancel Updates
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>                                         
                        </div>                                                                          
                    </div>
                </div>

            </div>
            <div class="tab-pane" id="tab5">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3>Featured Products</h3>
                                <hr />
                            </div>                                         

                            <div class="panel-body">
                                <div class="row">
                                    <%
                                        ViewFeaturedProducts fp = new ViewFeaturedProducts();
                                        List<Product> pp = fp.viewFeatured(Integer.parseInt(request.getParameter("pid")));
                                        if (pp != null) {

                                            for (Product pro : pp) {
                                    %>

                                    <div class="col-xs-6 col-md-3">
                                        <a class="thumbnail">
                                            <div class="pull-right"> 

                                                <button type="button" class="btn btn-default" data-toggle="tooltip" title="Delete" onclick="deleteImage();">
                                                    <span class="glyphicon glyphicon-trash"></span>
                                                </button>                                                     
                                            </div>
                                            <img src="<%=productsPath + pro.getImageMain()%>" alt="img">

                                        </a>                                                                                          
                                    </div>
                                    <%}
                                        }
                                        fp.closeConnection();
                                    %>
                                    <div class="col-xs-6 col-md-3"> 
                                        <a data-dismiss="modal" data-target="#modalFeature" data-toggle="modal" class="thumbnail"> 
                                            <img src="<%=addNewPathImg%>"  alt="addNew"> 
                                        </a>                                             
                                    </div>
                                </div>

                            </div>                                         
                        </div>                                                                          
                    </div>
                </div>

            </div>  

            <div class="tab-pane" id="tab6">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel">
                            <div class="panel-heading">
                                <h3>Product Discount</h3>
                                <hr />
                            </div>                                         

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <%
                                            if (phs.getProduct().getDiscountPrice() != 0) {%>
                                        <h2>

                                            <span class="tag label label-danger">
                                                <span><%=phs.getProduct().getDiscountPrice()%> %</span>
                                                <a title="Remove Discount" onclick="removeDiscount(<%=phs.getProduct().getId()%>);"><i class="remove glyphicon glyphicon-remove-sign glyphicon-white"></i></a> 
                                            </span> 
                                        </h2>


                                        <%  } else {
                                        %>

                                        <button type="button" class="btn" name="addDisc" data-dismiss="modal" data-target="#addDiscModal" data-toggle="modal" style="background-color: #F25758; color: #efd8d8;">
                                            <span class="glyphicon glyphicon-ice-lolly-tasted"></span>&nbsp;Add Discount
                                        </button>
                                        <%}%>
                                    </div>
                                </div>

                            </div>                                         
                        </div>                                                                          
                    </div>
                </div>

            </div> 
        </div>                     
    </div>
</div>
<%if (p != null) {%>
<div class="modal fade pg-show-modal" id="modalUpdatePromo" tabindex="-1" role="dialog" aria-hidden="true"> 
    <div class="modal-dialog"> 
        <form action="updatePromotion" method="POST">
            <div class="modal-content"> 
                <div class="modal-header"> 
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Update Current Promotion</h4> 
                </div>                     
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group"> 
                                <label class="control-label" for="promoupdateDes">Description</label>                                     
                                <textarea class="form-control" name="promoupdateDes" rows="3" id="promoupdateDes"><%=p.getDescription()%></textarea>
                            </div>
                            <div class="form-group"> 
                                <label class="control-label" for="promoupdateEnd">Promotion End Date</label>
                                <input type="text" class="form-control"  value="<%=p.getDateEnded()%>" name="promoupdateEnd" id="promoupdateEnd" placeholder="date here">
                            </div>

                            <input type="hidden" name="pid" value="<%=request.getParameter("pid")%>"/>
                            <input type="hidden" name="sid" value="<%=request.getParameter("sid")%>"/>
                            <input type="hidden" name="promoupdatePlace" value="<%=p.getId()%>"/>
                        </div>
                    </div>                         
                </div>                     
                <div class="modal-footer"> 
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Update Promotion</button>                         
                </div>                     
            </div> 
        </form>
    </div>             
</div>
<%}%>

<div class="modal fade pg-show-modal" id="addDiscModal" tabindex="-1" role="dialog" aria-hidden="true"> 
    <div class="modal-dialog"> 
        <form action="setDiscountAction" method="POST">
            <div class="modal-content"> 
                <div class="modal-header"> 
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Set Discount to the Product</h4> 
                </div>                     
                <div class="modal-body">
                    <div class="row">
                        <div class="form-group">
                            <div class="col-md-12">
                                <label class="control-label" for="discPre">Discount</label>
                                <div class="input-group"> 
                                    <input type="text" class="form-control" required="required" placeholder="discount goes here as a percentage" name="discPre">
                                    <span class="input-group-addon" >%</span>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="pid" value="<%=request.getParameter("pid")%>"/>
                        <input type="hidden" name="sid" value="<%=request.getParameter("sid")%>"/>
                    </div>
                </div>  
                <div class="modal-footer"> 
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-success">Save Discount</button>                         
                </div> 
            </div>                     
        </form>
    </div> 

</div>             
</div>
<script type="text/javascript">
    function removeDiscount(val) {
        var c = confirm('Are you sure you want to remove the current discount ?');
        if (c === true) {

            $.ajax({
                url: 'removeDiscountAction',
                data: {pid: val},
                cache: false,
                success: function (data) {
                    if (data === 'removed') {
                        window.location.reload(true);
                    }
                },
                error: function () {
                    alert('error');
                }
            });
        }

    }
    $("#priceD").keydown(function (e) {
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
</script>