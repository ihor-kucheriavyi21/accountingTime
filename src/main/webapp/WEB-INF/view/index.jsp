<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf" %>
<div class="container">

    Hello Hokagam
    <br/>
    <table class="table table-striped">
        <thead>
        <th>Id</th>
        <th>Name</th>
        <th>Recording time</th>
        <th>Amount Of time</th>
        <th>Status</th>
        <th>Delete task</th>
        <th>Update task</th>
        </thead>
        <tbody>
        <c:forEach var="task" items="${requestScope.tasks}">
            <tr>
                <td>Id: <c:out value="${task.idTask}"/></td>
                <td>Name: <c:out value="${task.taskName}"/></td>
                <td>Recording time: <c:out value="${task.recordingTime}"/></td>
                <td>Amount of time: <c:out value="${task.amountOfTime}"/></td>
                <td>Status: <c:out value="${task.idStatus}"/></td>

                <td>
                    <form method="post" action="<c:url value='/delete-task'/>">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="submit"
                               class="btn btn-danger"
                                name="Delete">
                    </form>
                </td>
                <td>
                    <form method="get" action="/update-task">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="submit"
                               class="btn btn-info" name="Update">
                    </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>

    <form method="get" action="<c:url value='/add-task'/>">
        <input type="submit" class="btn btn-success"  name="add">
    </form>
</div>
<%@ include file="../template/footer.jspf" %>