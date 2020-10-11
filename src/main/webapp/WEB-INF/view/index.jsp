<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf" %>

<div class="container">

    <h5>Welcome</h5>
    <br/>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th>Name</th>
            <th>Recording time</th>
            <th>Amount Of time</th>
            <th>Category</th>
            <th>Status</th>
            <th>Delete task</th>
            <th>Update task</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${requestScope.tasks}">
            <tr>
                <td>Id: <c:out value="${task.idTask}"/></td>
                <td>Name: <c:out value="${task.taskName}"/></td>
                <td>Recording time: <c:out value="${task.recordingTime}"/></td>
                <td>Amount of time: <c:out value="${task.amountOfTime}"/></td>
                <td>Category: <c:out value="${task.category.name}"/></td>
                <td>Status: <c:choose>
                    <c:when test="${task.idStatus == 1 }"><a style="color: grey">Verification</a></c:when>
                    <c:when test="${task.idStatus == 2 }"><a style="color: green">Confirmed</a></c:when>
                    <c:when test="${task.idStatus == 3 }"><a style="color: red">Rejected</a></c:when>
                    <c:otherwise>Undefined</c:otherwise>

                </c:choose>
                </td>

                <td>
                    <form method="post" action="<c:url value='/delete-task'/>">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="submit"
                               class="btn btn-danger"
                               value="Delete" name="Delete">
                    </form>
                </td>
                <td>
                    <form method="get" action="/update-task">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="submit"
                               class="btn btn-info" value="Update" name="Update">
                    </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
        <tfoot>
        <tr>

            <form method="post" action="<c:url value='/add-task'/> ">
                <td>New record</td>
                <td><label><input type="text" name="name" placeholder="SomeName"></label></td>
                <td></td>
                <td><label><input type="number" name="time" placeholder="CountSpendTime"></label></td>
                <td>
                    <select name="getCategory">

                        <option selected="selected">Choose category</option>
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category.id}"><c:out value="${category.name}"/></option>

                        </c:forEach>
                    </select></td>
                <td><input type="submit" class="btn btn-outline-success" value="Add task" name="add"></td>
                <td></td>
                <td></td>
            </form>
        </tr>
        </tfoot>
    </table>

</div>
<%@ include file="../template/footer.jspf" %>