<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${autore.nome.charAt(0)}.${autore.cognome}"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/autoreStyle.css" %>
</style>

<div class="page-content">
    <div class="author-main">
        <div class="author-image-container">
            <img class="author-image" src="${initParam['upload.location']}/autori/${autore.id}.jpg" alt="Autore_${prodotto.id}"/>
            <h3 style="font-weight: bold">${autore.nome} ${autore.cognome}</h3>
        </div>
        <div class="container-bookgrid">
            <section class="griglia">
                <c:forEach items="${prodotti}" var="prodotto">
                <article class="book">
                    <a class="pic" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img class="pic"
                                                                                            src="${initParam['upload.location']}/prodotti/<c:out value="${prodotto.id}"/>.jpg"
                                                                                            alt="Libro_<c:out value="${prodotto.id}"/>">
                    </a>
                    <h4 class="book-title">
                        <a class="titolo" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out
                                value="${prodotto.titolo}"/> </a>
                    </h4>
                    <h5 class="book-author">
                        <a class="autore" href="Autore?id=<c:out value="${prodotto.autore.id}"/>"> <c:out
                                value="${prodotto.autore.nome}"/> <c:out value="${prodotto.autore.cognome}"/></a>
                    </h5>
                    <h4 class="book-price"><c:out value="${prodotto.prezzoEuro}"/> &euro; </h4>
                </article>
                </c:forEach>
        </div>
        </section>
    </div>
</div>
<c:choose>
    <c:when test="${npag > 1}">
        <%@include file="footer-pages.jsp" %>
    </c:when>
    <c:otherwise>
        <%@include file="footer.html" %>
    </c:otherwise>
</c:choose>