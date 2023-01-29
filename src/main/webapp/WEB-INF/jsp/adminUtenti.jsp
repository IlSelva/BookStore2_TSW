<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Utenti"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/adminUsersStyle.css" %>
</style>

<div class="page-content">

    <section class="users-list">
        <div class="container-flex">
            <h5 id="notifica">${notifica}</h5>
        </div>
        <table class="users-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>E-mail</th>
                <th>Admin</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${utenti}" var="utente">
                <tr>
                    <td>${utente.id}</td>
                    <td>${utente.nome}</td>
                    <td>${utente.email}</td>
                    <td>${utente.admin ? "Si" : "No"}</td>
                    <td>
                        <form action="AdminUtenti" method="post">
                            <input type="hidden" name="id" value="${utente.id}">
                            <input type="submit" value="Modifica">
                            <input type="submit" name="rimuovi" value="Rimuovi">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

</div>
<%@include file="footer.html" %>