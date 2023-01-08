<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${utente.nome}"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/profiloStyle.css" %>
</style>

<div class="page-content">
    <div class="container-flex">
        <h5 id="notifica">${notifica}</h5>
    </div>
    <section class="user-window">
        <div class="content">
            <h4> Nome: <c:out value="${utente.nome}"/></h4>
            <h4> E-mail: <c:out value="${utente.email}"/></h4>
            <h5> id: <c:out value="${utente.id}"/></h5>

            <div class="buttons">
                <div class="user-button">
                    <a class="button" href="Logout">
                        Logout
                    </a>

                    <a class="button" href="ModificaUtente">
                        Modifica informazioni
                    </a>

                    <a class="button" href="ModificaPassword">
                        Modifica password
                    </a>
                </div>

                <c:if test="${utente.admin == true}">
                    <br>
                    <div class="admin-buttons">
                        <a class="button" href="AdminUtenti">
                            Modifica utenti
                        </a>
                        <a class="button" href="UploadProdotto">
                            Inserimento prodotto
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
    </section>
</div>
<%@include file="footer.html" %>
