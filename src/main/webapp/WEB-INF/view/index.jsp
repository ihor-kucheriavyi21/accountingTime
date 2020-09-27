<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf"%>

Hello Hokagam
<br/>
<c:forEach var="task" items="${requestScope.tasks}">
    <ul>
        <li>Id: <c:out value="${task.idTask}"/></li>
        <li>Name: <c:out value="${task.taskName}"/></li>
        <li>Recording time: <c:out value="${task.recordingTime}"/></li>
        <li>Amount Of time: <c:out value="${task.amountOfTime}"/></li>
        <form method="post" action="<c:url value='/delete-task'/>">
            <input type="number" hidden name="id" value="${task.idTask}">
            <input type="submit" value="Delete" name="Delete">
        </form>
        <form method="get" action="/update-task">
            <input type="number" hidden name="id" value="${task.idTask}">
            <input type="submit" value="Update" name="Update">
        </form>

    </ul>
</c:forEach>
<form method="post" action="<c:url value='/add-task'/> ">

    <label><input type="text" name="name"></label>Name task<br>
    <label><input type="text" name="time"></label>Amount of time<br>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
<%@ include file="../template/footer.jspf"%>