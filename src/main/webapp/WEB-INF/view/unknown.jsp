<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 28.09.2020
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jspf" %>
<div class="d-flex justify-content-center">

    <img src="https://www.gstatic.com/youtube/src/web/htdocs/img/monkey.png">
    <br/>
</div>
<div class="d-flex justify-content-center">

    <p>
        <fmt:message key="unknown_jsp.information"/>

    </p>
    <br/>
</div>
<div class="d-flex justify-content-center">

    <a href="/profile"> <fmt:message key="unknown_jsp.goToProfile"/> </a>
    <br/>
</div>

<%@ include file="../template/footer.jspf" %>