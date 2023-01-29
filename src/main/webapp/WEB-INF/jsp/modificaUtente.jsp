<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Modifica Utente"/>
</jsp:include>

<style>
    <%@include file="/WEB-INF/css/profiloStyle.css" %>
</style>

<div class="page-content">
    <section class="user-window">
        <div class="content">
            <h1 class="section-title">Modifica Utente</h1>

            <form action="AdminUtenti" method="post">
                <input type="hidden" name="id" value="${cliente.id}">
                <label>Nome</label>
                <input type="text" name="nome" value="${cliente.nome}" required>
                <label>e-mail</label>
                <input type="text" name="email" value="${cliente.email}" required>
                <label>Passwordhash</label>
                <input type="text" name="passwordhash" value="${cliente.passwordhash}" disabled>
                <label> nuova password </label>
                <input type="text" name="password">
                <c:choose>
                    <c:when test="${cliente.admin}">
                        <input type="checkbox" name="admin" value=admin checked>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="admin" value=admin>
                    </c:otherwise>
                </c:choose>
                <label>Admin</label> <br>

                <c:if test="${cliente != null}">
                    <input type="submit" name="modifica" value="Modifica">
                </c:if>
            </form>
        </div>
    </section>
</div>

<%@include file="footer.html" %>