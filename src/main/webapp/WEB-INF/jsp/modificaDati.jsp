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

<div class="page-content">.
    <section class="user-window">
        <div class="content">
            <h1 class="section-title">Modifica Dati Utente</h1>

            <form action="ModificaUtente" method="post">
                <input type="hidden" name="id" value="${utente.id}">
                <label>Nome</label>
                <input type="text" name="nome" value="${utente.nome}" required>
                <label>e-mail</label>
                <input type="text" name="email" value="${utente.email}" required>
                <c:if test="${utente != null}">
                    <input class="button" type="submit" name="modifica" value="Modifica">
                </c:if>
            </form>
        </div>
    </section>
</div>

<%@include file="footer.html" %>