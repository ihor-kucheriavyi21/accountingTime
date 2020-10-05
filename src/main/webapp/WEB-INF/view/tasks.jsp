<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 01.10.2020
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf" %>
<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Recording time</th>
    <th>Amount of time</th>
    <th>Status</th>

    </thead>
    <tbody>
    <c:forEach var="task" items="${requestScope.tasks}">
        <tr>
            <td>Id: <c:out value="${task.idTask}"/></td>
            <td>Name: <c:out value="${task.taskName}"/></td>
            <td>Recording time: <c:out value="${task.recordingTime}"/></td>
            <td>Amount Of time: <c:out value="${task.amountOfTime}"/></td>
            <td>Status: <c:out value="${task.idStatus}"/></td>

        </tr>

    </c:forEach>
    </tbody>
</table>
<%@ include file="../template/footer.jspf" %>
