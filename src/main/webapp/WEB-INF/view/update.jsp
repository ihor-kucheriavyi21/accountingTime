<%--
  Created by IntelliJ IDEA.
  User: kucher
  Date: 22.09.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf"%>

<li>Id: <c:out value="${requestScope.task.idTask}"/></li>
<li>Name: <c:out value="${requestScope.task.taskName}"/></li>
<li>Recording time: <c:out value="${requestScope.task.recordingTime}"/></li>
<li>Amount Of time: <c:out value="${requestScope.task.amountOfTime}"/></li>
<li>Category </li>
<form method="post" action="<c:url value='update-task'/> ">
    <input type="number" hidden name="id" value="${requestScope.task.idTask}">
    <label><input type="text" name="name"></label>Name task<br>
    <label><input type="text" name="time"></label>Amount of time<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
<%@ include file="../template/footer.jspf"%>