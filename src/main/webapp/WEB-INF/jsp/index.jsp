<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp">
    <jsp:param name="pageTitle" value="Home"/>
</jsp:include>
<style>
    <%@include file="/WEB-INF/css/homestyle.css" %>
</style>

<section id="StoreDesc">
    <h1> Benvenuto, scopri la nostra selezione di libri </h1>
</section>

<div class="page">
    <div class="container">

        <h2 class="section-title"> Ultimi arrivi</h2>

        <section class="griglia">
            <c:forEach items="${ultimi}" var="prodotto">
                <article class="book" col="1/5">
                    <h4>
                        <a class="titolo" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out
                                value="${prodotto.titolo}"/> </a>
                    </h4>
                    <a class="pic" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img class="pic"
                                                                                            src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg"
                                                                                            alt="Libro_<c:out value="${prodotto.id}"/>">
                    </a>
                    <h5><a href="Autore?id=<c:out value="${prodotto.autore.id}"/>"> <c:out
                            value="${prodotto.autore.nome}"/> <c:out value="${prodotto.autore.cognome}"/></a></h5>
                    <h4 class="prezzo"><c:out value="${prodotto.prezzoEuro}"/> &euro; </h4>
                </article>
            </c:forEach>
        </section>
        <h2 class="section-title"> Catalogo</h2>

        <section class="griglia">
            <c:forEach items="${prodotti}" var="prodotto">
                <article class="book" col="1/5">
                    <h4>
                        <a class="titolo" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <c:out
                                value="${prodotto.titolo}"/> </a>
                    </h4>
                    <a class="pic" href="Prodotto?id=<c:out value="${prodotto.id}"/>"> <img class="pic"
                                                                                            src="img/prodotti/<c:out value="${prodotto.id}"/>.jpg"
                                                                                            alt="Libro_<c:out value="${prodotto.id}"/>">
                    </a>
                    <h5><a href="Autore?id=<c:out value="${prodotto.autore.id}"/>"> <c:out
                            value="${prodotto.autore.nome}"/> <c:out value="${prodotto.autore.cognome}"/></a></h5>
                    <h4> Prezzo: <c:out value="${prodotto.prezzoEuro}"/> &euro; </h4>
                </article>
            </c:forEach>
        </section>
    </div>

    <%@include file="footer.html" %>


