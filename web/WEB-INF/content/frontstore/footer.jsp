<%-- 
    Document   : footer
    Created on : Dec 25, 2015, 8:18:19 AM
    Author     : shanaka
--%>

<%@page import="com.certus.dbmodel.SiteGeneral"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-4">
                        <div class="widget">
                            <h5>Contact</h5>
                            <hr />
                            <%
                                Session sl = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                                SiteGeneral general = (SiteGeneral) sl.load(SiteGeneral.class, 1);
                            %>
                            <div class="social">
                                <a href="#"><i class="fa fa-facebook facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter twitter"></i></a>
                                <a href="#"><i class="fa fa-linkedin linkedin"></i></a>
                                <a href="#"><i class="fa fa-google-plus google-plus"></i></a> 
                            </div>
                            <hr />
                            <i class="fa fa-home"></i> &nbsp; <%=general.getSiteAddress()%>
                            <hr />
                            <i class="fa fa-phone"></i> &nbsp; <%=general.getTel()%>
                            <hr />
                            <i class="fa fa-envelope-o"></i> &nbsp; <a href="mailto:#"><%=general.getSiteMail()%></a>
                            <hr />
                            <div class="payment-icons">
                                <img src="img/payment/americanexpress.gif" alt="" />
                                <img src="img/payment/visa.gif" alt="" />
                                <img src="img/payment/mastercard.gif" alt="" />
                                <img src="img/payment/discover.gif" alt="" />
                                <img src="img/payment/paypal.gif" alt="" />
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="widget">
                            <h5>About Us</h5>
                            <hr />
                                <p><%=general.getAboutUsFooter()%></p> 
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="widget">
                            <h5>Links Goes Here</h5>
                            <hr />
                            <div class="two-col">
                                <div class="col-left">
                                    
                                    <ul>
                                        <li><a href="#">Condimentum</a></li>
                                        <li><a href="#">Etiam at</a></li>
                                        <li><a href="#">Fusce vel</a></li>
                                        <li><a href="#">Vivamus</a></li>
                                        <li><a href="#">Pellentesque</a></li>
                                        <li><a href="#">Vivamus</a></li>
                                    </ul>
                                </div>
                                <div class="col-right">
                                    <ul>
                                        <li><a href="#">Condimentum</a></li>
                                        <li><a href="#">Etiam at</a></li>
                                        <li><a href="#">Fusce vel</a></li>
                                        <li><a href="#">Vivamus</a></li>
                                        <li><a href="#">Pellentesque</a></li>
                                        <li><a href="#">Vivamus</a></li>
                                    </ul>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr />
                <!-- Copyright info -->
                <p class="copy">Copyright &copy; 2016 | <a href="#">Certus Shopping</a> - <a href="index.jsp">Home</a>  | <a href="terms_conditions.jsp">Terms & Conditions</a> | <a href="contact-us.jsp">Contact Us</a></p>
            </div>
        </div>
        <div class="clearfix"></div>
    </div>
</footer> 	
<!--/ Footer ends -->

<!-- Scroll to top -->
<span class="totop"><a href="#"><i class="fa fa-chevron-up"></i></a></span> 

