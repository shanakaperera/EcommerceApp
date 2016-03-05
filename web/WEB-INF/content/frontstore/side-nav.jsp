<%-- 
    Document   : side-nav
    Created on : Dec 26, 2015, 3:13:14 AM
    Author     : shanaka
--%>

<%@page import="java.util.Set"%>
<%@page import="com.certus.dbmodel.SubCategory"%>
<%@page import="java.util.List"%>
<%@page import="com.certus.dbmodel.Category"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav>
    <ul id="nav">
        <!-- Main menu. Use the class "has_sub" to "li" tag if it has submenu. -->
        <li><a href="index.jsp">Home</a></li>

        <%
            Session s = com.certus.connection.HibernateUtil.getSessionFactory().openSession();
            Criteria cr = s.createCriteria(Category.class);
            List<Category> catLst = cr.list();
            for (Category cate : catLst) {

        %>

        <li class="has_sub"><a href="items.jsp?cat=<%=cate.getId()%>"><%=cate.getCatName()%></a>
            <!-- Submenu -->
            <ul>
                <%
                    Set<SubCategory> subs = cate.getSubCategories();
                    for (SubCategory sub : subs) {

                %>
                <li><a href="items.jsp?cat=<%=cate.getId()%>&sub=<%=sub.getId()%>"><%=sub.getSubCategoryName()%></a></li>
                    <%}%>

            </ul>
        </li>
        <%}%>
    </ul>
</nav>
