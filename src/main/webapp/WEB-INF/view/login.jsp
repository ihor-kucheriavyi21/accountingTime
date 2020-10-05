<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 23.09.2020
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jspf" %>

<c:if test="${logoutMessage!=null}">
    <p class="alert alert-success" role="alert"> ${logoutMessage}</p>
</c:if>
<c:if test="${errorMessage!=null}">
    <p class="alert alert-danger" role="alert"> ${errorMessage}</p>
</c:if>
<form method="post" action="<c:url value='login'/>">

    Name:<br> <input type="text" name="username"/><br/>
    Password:<br/> <input type="password" name="userpass"/><br/>
    <input type="submit" value="login"/>
</form>
<br/>
<form method="get" action="/registration"/>
<input type="submit"
       class="btn btn-info"
       value="Registration" name="registration">
</form>

<%--<form action="/login1.do" method="post">
    <p><font color="red">${errorMessage}</font></p>
    Enter your Name: <input type="text" name="name">
    Password: <input type="password" name="password">
    <input        type="submit" value="Login">
</form>--%>
<%@ include file="../template/footer.jspf" %>