<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<style><%@include file="/WEB-INF/css/headerStyle.css"%>
<%@include file="/WEB-INF/css/footerStyle.css"%>
<%@include file="/WEB-INF/css/general.css"%>
</style>
<!DOCTYPE html>
<html>
<head>
    <title>Bookshop Store - ${param.pageTitle}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" type="text/css" href="/WEB-INF/css/general.css">
    <script src="ricerca.js"></script>
    <script src="https://kit.fontawesome.com/4367acb6a6.js" crossorigin="anonymous"></script>
</head>

<body>
<header>
    <div class="header-container">
        <div class="header-main">
            <div class="header-left">
                <div class="content-logo">
                    <a class="logo" href=".">
                        <picture>
                            <source media="(min-width:650px)" srcset="img/logo/svg/logo-black-no-background.svg">
                        <img src="img/logo/svg/bookstore-website-favicon-black.svg" alt="logo"/>
                        </picture>
                    </a>
                </div>
            </div>
            <div class="header-center">
                <div class="searchBox">
                    <label for="ricerca"></label>
                    <form id="ricerca" action="Ricerca" method="get">
                        <select class="category" name="category">
                            <option value="" label="" selected> generi</option>
                            <c:forEach items="${generi}" var="genere">
                                <option value="<c:out value="${genere.nome}"/>"><c:out value="${genere.nome}"/></option>
                            </c:forEach>
                        </select>
                        <input class="searchTerm" type="text" name="q" autocomplete="off" list="ricerca-datalist"
                               placeholder="Ricerca"
                               onkeyup="ricerca(this.value)" value="<c:out value="${param.q}" />">
                        <datalist id="ricerca-datalist"></datalist>
                        <button class="searchButton" type="submit">
                            <span class="fa fa-search" aria-hidden="true"></span>
                        </button>
                    </form>
                </div>
            </div>
            <div class="header-right">
                <div class="button-header" id="button-user">
                    <c:choose>
                        <c:when test="${utente == null}">
                            <a id="login" href="RegistrazioneForm">
                                <span class="fa fa-right-to-bracket" aria-hidden="true"> </span>
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a id="profile" href="Utente">
                                <span class="fa fa-user" aria-hidden="true"> </span>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="button-header" id="button-cart">
                    <a id="cart" href="Carrello">
                        <span class="fa fa-shopping-cart" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</header>