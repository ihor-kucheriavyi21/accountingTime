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
<div>
    Sorted by:
    <form method="get" action="<c:url value='/allTask'/>">

        <select name="sortNumber" onchange="this.form.submit()">
            <option>Choose sort</option>
            <option value="0">Sort by ID</option>
            <option value="1">Sort by name</option>
            <option value="2">Sort by category</option>
        </select>

    </form>
    <c:out value="${requestScope.nameSort}"/>
</div>
<table class="table table-striped">
    <thead>
    <th>Id</th>
    <th>Name</th>
    <th>Recording time</th>
    <th>Amount of time</th>
    <th>Category</th>
    <th>Status</th>
    <th></th>
    <th></th>

    </thead>
    <tbody>
    <c:forEach var="task" items="${requestScope.tasks}">
        <tr>
            <td>Id: <c:out value="${task.idTask}"/></td>
            <td>Name: <c:out value="${task.taskName}"/></td>
            <td>Recording time: <c:out value="${task.recordingTime}"/></td>
            <td>Amount Of time: <c:out value="${task.amountOfTime}"/></td>
            <td>Category: <c:out value="${task.category.name}"/></td>
            <td>Status: <c:choose>
                <c:when test="${task.idStatus == 1 }"><a style="color: grey">Verification</a></c:when>
                <c:when test="${task.idStatus == 2 }"><a style="color: green">Confirmed</a></c:when>
                <c:when test="${task.idStatus == 3 }"><a style="color: red">Rejected</a></c:when>
                <c:otherwise>Undefined</c:otherwise>
            </c:choose>
            </td>
            <td>
                <form method="post" action="<c:url value='/verification'/>">
                    <input type="number" hidden name="id" value="${task.idTask}">
                    <input type="number" hidden name="idStatus" value="3">

                    <input type="submit"
                           class="btn btn-danger"
                           value="Reject" name="Reject">
                </form>
            </td>
            <td>
                <form method="post" action="/verification">
                    <input type="number" hidden name="id" value="${task.idTask}">
                    <input type="number" hidden name="idStatus" value="2">
                    <input type="submit"
                           class="btn btn-success" value="Confirm" name="Confirm">
                </form>
            </td>
        </tr>

    </c:forEach>
    </tbody>
</table>
<%@ include file="../template/footer.jspf" %>
