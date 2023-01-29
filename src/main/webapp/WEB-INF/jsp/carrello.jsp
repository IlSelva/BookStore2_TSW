<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/homeStyle.css" %>
    <%@include file="/WEB-INF/css/carrelloStyle.css" %>
</style>

<div class="page-content">
    <div claass="container">
        <div class="container-section-title">
            <h1 class="section-title">Carrello</h1>
        </div>
        <section class="griglia">
            <c:forEach items="${carrello.prodotti}" var="pq">
                <div class="book">
                    <a class="pic" href="Prodotto?id=<c:out value="${pq.prodotto.id}"/>">
                        <img class="pic"
                             src="${initParam['upload.location']}/prodotti/<c:out value="${pq.prodotto.id}"/>.jpg"
                             alt="Libro_<c:out value="${pq.prodotto.id}"/>">
                    </a>
                    <div class="book-info">
                        <h4 class="book-title">
                            <a class="titolo" href="Prodotto?id=<c:out value="${pq.prodotto.id}"/>"> <c:out
                                    value="${pq.prodotto.titolo}"/> </a>
                        </h4>
                        <h5 class="book-author">
                            <a class="autore" href="Autore?id=<c:out value="${pq.prodotto.autore.id}"/>"> <c:out
                                    value="${pq.prodotto.autore.nome}"/> <c:out value="${pq.prodotto.autore.cognome}"/></a>
                        </h5>
                        <div class="book-quantity-price">
                            <h5>Quantità: <c:out value="${pq.quantita}"/>,</h5>
                            <h5>Prezzo unit.: <c:out value="${pq.prodotto.prezzoEuro}"/> &euro;,F</h5>
                            <h5>Prezzo tot.: <c:out value="${pq.prezzoTotEuro}"/> &euro;</h5>
                        </div>
                        <form action="Carrello" method="post">
                            <input type="hidden" name="prodId" value="${pq.prodotto.id}">
                            <input type="hidden" name="setNum" value="0">
                            <input class="button" type="submit" value="Rimuovi">
                        </form>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty carrello.prodotti}">
                <div>Nessun prodotto nel carrello</div>
            </c:if>
        </section>
        <c:if test="${not empty carrello.prodotti}">
            <section class="carrello-end-section">
                <div class="container-end-section">
                    <div class="carrello-totale">
                        <h2>Totale: <c:out value="${carrello.prezzoTotEuro}"/> &euro;</h2>
                    </div>

                    <div class="carrello-acquisto">
                        <form action="AcquistoCompletato">
                            <input type="submit" value="Completa acquisto">
                        </form>
                    </div>
                </div>
            </section>
        </c:if>
    </div>
</div>
<%@include file="footer.html" %>
