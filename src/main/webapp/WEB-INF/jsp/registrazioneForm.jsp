<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrazione utente"/>
</jsp:include>
<style> <%@include file="/WEB-INF/css/registrazioneStyle.css" %> </style>

<div class="page-content">
    <div class="container">

            <div class="column">
                <div id="left">
                    <h1 class="testo"> Login </h1>
                    <form name="login" action="Login" method="post">
                        <label>E-mail </label>
                        <input type="text" name="email">
                        <label>Password </label>
                        <input type="password" name="password">
                        <input class="button" type="submit" value="Login">
                    </form>
                </div>
            </div>
            <div class="linea"></div>
            <div class="column">
                <div id="right">
                    <h1 class="testo"> Registrazione </h1>

                    <form name="registrazione" action="Registrazione" method="post">
                        <label>Email </label>
                        <input type="text" name="email" oninput="validaEmail()">
                        <label>Password (almeno 8 caratteri, una lettera maiuscola, una minuscola, un numero)</label>
                        <input type="password" name="password" oninput="validaPassword()">
                        <label>Password (conferma)</label>
                        <input type="password" name="passwordConferma" oninput="validaPassword()">
                        <label>Nome (solo lettere e spazi)</label>
                        <input type="text" name="nome" oninput="validaNome()">
                        <input class="button" id="registrami" type="submit" value="Registrami" disabled><span
                            id="registramimessaggio"></span>
                    </form>
                </div>
            </div>

    </div>
</div>

<script>
  var borderOk = '2px solid #080';
  var borderNo = '2px solid #f00';
  var emailOk = false;
  var passwordOk = false;
  var nomeOk = false;

  function validaPassword() {
    var inputpw = document.forms['registrazione']['password'];
    var inputpwconf = document.forms['registrazione']['passwordConferma'];
    var password = inputpw.value;
    if (password.length >= 8 && password.toUpperCase() != password
        && password.toLowerCase() != password && /[0-9]/.test(password)) {
      inputpw.style.border = borderOk;

      if (password == inputpwconf.value) {
        inputpwconf.style.border = borderOk;
        passwordOk = true;
      } else {
        inputpwconf.style.border = borderNo;
        passwordOk = false;
      }
    } else {
      inputpw.style.border = borderNo;
      inputpwconf.style.border = borderNo;
      passwordOk = false;
    }
    cambiaStatoRegistrami();
  }

  function validaNome() {
    var input = document.forms['registrazione']['nome'];
    if (input.value.trim().length > 0
        && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
      input.style.border = borderOk;
      nomeOk = true;
    } else {
      input.style.border = borderNo;
      nomeOk = false;
    }
    cambiaStatoRegistrami();
  }

  function validaEmail() {
    var input = document.forms['registrazione']['email'];
    if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
      input.style.border = borderOk;
      emailOk = true;
    } else {
      input.style.border = borderNo;
      emailOk = false;
    }
    cambiaStatoRegistrami();
  }

  function cambiaStatoRegistrami() {
    if (passwordOk && nomeOk && emailOk) {
      document.getElementById('registrami').disabled = false;
      document.getElementById('registramimessaggio').innerHTML = '';
    } else {
      document.getElementById('registrami').disabled = true;
      document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
    }
  }
</script>
<%@include file="footer.html" %>
