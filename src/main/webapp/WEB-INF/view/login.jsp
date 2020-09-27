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


<html>
<head>
    <title>Title</title>
</head>
<body>
<p><font color="green"> ${logoutMessage}</font></p>
<form method="post" action="<c:url value='login'/>">
    <p><font color="red">${errorMessage}</font></p>
    Name:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="userpass"/><br/><br/>
    <input type="submit" value="login"/>
</form>
<%--<form action="/login1.do" method="post">
    <p><font color="red">${errorMessage}</font></p>
    Enter your Name: <input type="text" name="name">
    Password: <input type="password" name="password">
    <input        type="submit" value="Login">
</form>--%>
</body>
</html>
<%@ include file="../template/footer.jspf" %>