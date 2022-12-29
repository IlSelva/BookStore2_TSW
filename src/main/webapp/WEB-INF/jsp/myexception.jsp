<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 26/06/2020
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Errore"/>
</jsp:include>

<div class="page-content">
    <section class="message">
        <h1><%= exception.getMessage() %>
        </h1>
    </section>
</div>
<%@include file="footer.html" %>
