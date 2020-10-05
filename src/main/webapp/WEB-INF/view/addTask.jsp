<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 29.09.2020
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf"%>

<form method="post" action="<c:url value='/add-task'/> ">
    <label><input type="text" name="name"></label>Name task<br>
    <label><input type="text" name="time"></label>Amount of time<br>
    <input type="submit" name="Ok"><br>
</form>
<%@ include file="../template/footer.jspf" %>
