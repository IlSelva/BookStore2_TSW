<div class="footer">
    <div class="footpages" txt="c" style="background-color:#E8E8E8">
        <a <c:if test="${pag > 1}">href="?id=${param.id}&pag=${pag - 1}&perpag=${perpag}&ord=${ord}"</c:if>>&larr;</a>
        &emsp;
        <c:forEach begin="1" end="${npag}" varStatus="loop">
            <c:choose>
                <c:when test="${loop.index == pag}">
                    <b>${loop.index}</b>
                </c:when>
                <c:otherwise>
                    <a href="?id=${param.id}&pag=${loop.index}&perpag=${perpag}&ord=${ord}">${loop.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        &emsp;
        <a <c:if test="${pag < npag}">href="?id=${param.id}&pag=${pag + 1}&perpag=${perpag}&ord=${ord}"</c:if>>&rarr;</a>
    </div>
    <div class="footercontainer">
        <div class="footerrow">
            <h6>About</h6>
            <p class="text-justify">
                BooShop Store, negozio di libri, progetto per il corso di Tecnologie software per il Web
            </p>
        </div>
        <hr>
    </div>
    <div class="footercontainer">
        <div class="footerrow">
            <p class="copyright-text">Copyright &copy; 2022 All Rights Reserved by
                <a href="#">Silvio Pastore</a>.
            </p>
            <ul class="social-icons">
                <li>
                    <a class="facebook" href="#"><i class="fa-brands fa-facebook"></i></a>
                </li>
                <li>
                    <a class="isntagram" href="#"><i class="fa-brands fa-instagram"></i></a>
                </li>
                <li>
                    <a class="linkedin" href="https://www.linkedin.com/in/silvio-pastore-12b890224/"><i
                            class="fa-brands fa-linkedin"></i></a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>

</html>
