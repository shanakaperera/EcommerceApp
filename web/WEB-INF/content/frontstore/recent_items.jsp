<%-- 
    Document   : recent_items
    Created on : Dec 25, 2015, 8:50:28 AM
    Author     : shanaka
--%>

<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="org.jsoup.nodes.Element"%>
<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.certus.controllers.FilterRecentItems"%>
<%@page import="com.certus.dbmodel.Product"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">

    <div class="rp">
        <!-- Recent News Starts -->
        <h4 class="title">Recent Items</h4>
        <div class="recent-news block">
            <!-- Recent Item -->
            <div class="recent-item">
                <div class="custom-nav">
                    <a class="prev"><i class="fa fa-chevron-left br-lblue"></i></a>
                    <a class="next"><i class="fa fa-chevron-right br-lblue"></i></a>
                </div>
                <div id="owl-recent" class="owl-carousel">
                    <!-- Item -->

                    <%
                        Session sess = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
                        FilterRecentItems im = new FilterRecentItems();
                        for (Integer i : im.getRecentItems()) {
                            Product pr = (Product) sess.load(Product.class, i);
                            if (pr.isAvailability()) {

                    %>
                    <div class="item">
                       <a href="single-item.jsp?cat=<%=pr.getSubCategory().getCategory().getId()%>&sub=<%=pr.getSubCategory().getId()%>&pid=<%=pr.getId()%>"><img src="<%=productsPath1 + pr.getImageMain()%>" alt="" class="img-responsive" /></a>
                        <!-- Heading -->
                        <h4><a href="single-item.jsp?cat=<%=pr.getSubCategory().getCategory().getId()%>&sub=<%=pr.getSubCategory().getId()%>&pid=<%=pr.getId()%>"><%=pr.getName()%> <span class="pull-right">Rs. <%=im.getPrice(pr.getProductHasSizes())%></span></a></h4>
                        <div class="clearfix"></div>
                        <!-- Paragraph -->
                        <%
                            if (pr.getDescription() != null) {
                                Matcher m1 = Pattern.compile("<p>(.+?)</p>").matcher(pr.getDescription());
                                boolean b1 = m1.find();

                        %>
                        <p><%=b1 ? m1.group(1) : ""%></p>
                        <%}%>
                    </div>
                    <%}
                        }
                        sess.close();
                    %>
                </div>
            </div>
        </div>

        <!-- Recent News Ends -->
    </div>

</div>
