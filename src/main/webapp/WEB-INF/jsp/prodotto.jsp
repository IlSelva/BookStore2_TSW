<%--
  Created by IntelliJ IDEA.
  User: silvi
  Date: 21/06/2020
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%><%@taglib prefix="c"
                                         uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.titolo}"/>
</jsp:include>
<style><%@include file="/WEB-INF/css/prodottoStyle.css"%></style>
<section id="sezione">
    <grid>

        <div col="1/3">
            <img src="img/prodotti/${prodotto.id}.jpg" alt="Libro_${prodotto.id}"/>
            <br />
            <p class="testo">
                Editore:
                <a href="Editore?id=<c:out value="${prodotto.editore}"/>"> <c:out value="${prodotto.editore}"/> </a>
            </p>
        </div>

        <div col="1/3">
            <h3>${prodotto.titolo}</h3>
            <p> Descrizione: ${prodotto.descrizione}</p>
            <p class="testo">Autore:
                <a href="Autore?id=${prodotto.autore.id}"> <c:out value="${prodotto.autore.nome} ${prodotto.autore.cognome}"/> </a>
            </p>
            <p class="testo">
                Genere:
                <a href="Genere?id=${prodotto.genere}"> <c:out value="${prodotto.genere}"/> </a>
            </p>
        </div>

        <div col="1/3">

            <c:if test="${utente.admin}">
                <form action="AdminProdotto" method="post">
                    <input type="hidden" name="id" value="${prodotto.id}">
                    <input type="submit" value="Modifica">
                    <input type="submit" name="rimuovi" value="Rimuovi">
                </form>
            </c:if>

            <h3>Prezzo: ${prodotto.prezzoEuro} &euro;</h3>
            <h5>Disponibili: ${prodotto.copie} </h5>
            <form action="Carrello" method="post">
                <label>Quantità:</label>
                <select name="addNum">
                    <c:forEach begin="1" end="${prodotto.copie}" varStatus="loop">
                        <option value="${loop.index}">${loop.index}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="prodId" value="${prodotto.id}">
                <input type="submit" value="Aggiungi al carrello">
            </form>
        </div>

    </grid>
</section>
<%@include file="footer.html"%>
