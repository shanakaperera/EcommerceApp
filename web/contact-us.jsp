<%-- 
    Document   : contact-us
    Created on : Feb 15, 2016, 11:38:42 AM
    Author     : shanaka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Certus-Contact us</title>
        <%@include file="WEB-INF/content/frontstore/stylesheets.jsp" %>

    </head>

    <%@include file="WEB-INF/content/frontstore/cart_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/login_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/register_model.jsp" %>
    <%@include file="WEB-INF/content/frontstore/header.jsp" %>
    <%@include file="WEB-INF/content/frontstore/navigation.jsp" %>

    <div class="content">
        <div class="container">
            <div class="row">
                <div class="page-head">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h2>Contact Us</h2>
                                <h4>something goes here</h4>
                            </div>
                        </div>
                    </div>
                    <div class="content contact-two">
                        <div class="container">
                            <div class="row"> 

                                <div class="col-md-6">
                                    <!-- Contact form -->
                                    <h4 class="title">Contact Form</h4>
                                    <div class="form">
                                        <!-- Contact form (not working)-->
                                        <form class="form-horizontal">
                                            <!-- Name -->
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="name1">Name</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" id="name1">
                                                </div>
                                            </div>
                                            <!-- Email -->
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="email1">Email</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" id="email1">
                                                </div>
                                            </div>
                                            <!-- Website -->
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="website">Website</label>
                                                <div class="col-md-9">
                                                    <input type="text" class="form-control" id="website">
                                                </div>
                                            </div>
                                            <!-- Comment -->
                                            <div class="form-group">
                                                <label class="control-label col-md-2" for="comment">Comment</label>
                                                <div class="col-md-9">
                                                    <textarea class="form-control" id="comment" rows="3"></textarea>
                                                </div>
                                            </div>
                                            <!-- Buttons -->
                                            <div class="form-group">
                                                <!-- Buttons -->
                                                <div class="col-md-9 col-md-offset-2">
                                                    <button type="submit" class="btn btn-default">Submit</button>
                                                    <button type="reset" class="btn btn-default">Reset</button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <hr />        
                                    <div class="center">
                                        <!-- Social media icons -->
                                        <strong>Get in touch:</strong>
                                        <div class="social">
                                            <a href="#"><i class="fa fa-facebook facebook"></i></a>
                                            <a href="#"><i class="fa fa-twitter twitter"></i></a>
                                            <a href="#"><i class="fa fa-linkedin linkedin"></i></a>
                                            <a href="#"><i class="fa fa-google-plus google-plus"></i></a>
                                            <a href="#"><i class="fa fa-pinterest pinterest"></i></a>
                                        </div>
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <h4 class="title">You can Find us here :</h4>
                                    <!-- Google maps -->
                                    <div class="gmap">
                                        <!-- Google Maps. Replace the below iframe with your Google Maps embed code -->

                                        <div style="width:500px;overflow:hidden;height:200px;max-width:100%;"><div id="gmap-display" style="height:100%; width:100%;max-width:100%;"><iframe style="height:100%;width:100%;border:0;" frameborder="0" src="https://www.google.com/maps/embed/v1/place?q=Highlevel+Road,+Nugegoda,+Western+Province,+Sri+Lanka&key=AIzaSyAN0om9mFmy1QN6Wf54tXAowK4eT0ZUPrU"></iframe></div><a class="google-map-enabler" href="http://www.bootstrapskins.com/themes/opencart" id="get-data-for-map">buy opencart themes</a><style>#gmap-display img{max-width:none!important;background:none!important;font-size: inherit;}</style></div><script src="https://www.bootstrapskins.com/google-maps-authorization.js?id=471101c0-c77e-3223-d79f-c4c143f251a9&c=google-map-enabler&u=1455526718" defer="defer" async="async"></script>

                                    </div>

                                    <hr />
                                    <!-- Address section -->
                                    <h4 class="title">Address</h4>
                                    <div class="address">
                                        <address>
                                              
                                            <!-- Company name -->
                                            <strong>Certus Shopping</strong><br>
                                            <!-- Address -->
                                            81/A, Highlevel Rd, Nugegoda, <br>
                                            Sri Lanka, 10100<br>
                                            <!-- Phone number -->
                                            <abbr title="Phone">P:</abbr> +94-112-821-289 
                                        </address>

                                        <address>
                                            <!-- Name -->
                                            <strong>Email</strong><br>
                                            <!-- Email -->
                                            <a href="mailto:#">certusshopping@gmail.com</a>
                                        </address> 
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
        <%@include file="WEB-INF/content/frontstore/news_letter.jsp" %>
        <%@include file="WEB-INF/content/frontstore/footer.jsp" %>


        <%@include file="WEB-INF/content/frontstore/scripts.jsp" %>
    </div>
</html>
