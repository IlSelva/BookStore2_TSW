<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 28/07/2020
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Modifica Password"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/profiloStyle.css" %>
</style>

<div class="page-content">.
    <section class="user-window">
        <div class="content">
            <h1 class="section-title">Cambiare Password</h1>

            <form action="ModificaPassword" method="post">
                <input type="hidden" name="id" value="${utente.id}">
                <label>Vecchia password</label>
                <input type="password" name="password" required>
                <label>Nuova password</label>
                <input type="password" name="nuovapassword" required>

                <c:if test="${utente != null}">
                    <input class="button" type="submit" name="modifica" value="Modifica">
                </c:if>
            </form>
        </div>
    </section>
</div>

<%@include file="footer.html" %>
