<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 01.10.2020
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<div class="container">
    <div>
        <form method="get" action="<c:url value='/allTask'/>">
            <div class="form-row">
                <div class="form-group col-md-3">

                    <select class="custom-select" name="sortNumber" onchange="this.form.submit()">
                        <option><fmt:message key="tasks_jsp.option.sort.chooseSort"/></option>
                        <option value="0"><fmt:message key="tasks_jsp.option.sort.byId"/></option>
                        <option value="1"><fmt:message key="tasks_jsp.option.sort.byName"/></option>
                        <option value="2"><fmt:message key="tasks_jsp.option.sort.byCategory"/></option>
                    </select>
                </div>
                <div class="form-group col-md-3">

                    <label class="badge badge-info">
                        <c:choose>
                            <c:when test="${requestScope.nameSort == 1 }">
                                <fmt:message
                                        key="tasks_jsp.label.sortByName"/>

                            </c:when>
                            <c:when test="${requestScope.nameSort == 2 }">
                                <fmt:message
                                        key="tasks_jsp.label.sortByCategory"/>
                            </c:when>
                            <c:otherwise><fmt:message
                                    key="tasks_jsp.label.sortById"/></c:otherwise>

                        </c:choose>
                    </label>

                </div>
            </div>


        </form>

    </div>
    <table class="table table-striped">
        <thead>
        <th><fmt:message key="table.col.id"/></th>
        <th><fmt:message key="tasks_jsp.table.col.idUser"/></th>
        <th><fmt:message key="table.col.name"/></th>
        <th><fmt:message key="table.col.recordingTime"/></th>
        <th><fmt:message key="table.col.amountOfTime"/></th>
        <th><fmt:message key="table.col.category"/></th>
        <th><fmt:message key="table.col.status"/></th>
        <th></th>
        <th></th>

        </thead>
        <tbody>
        <c:forEach var="task" items="${requestScope.tasks}">
            <tr>
                <td><c:out value="${task.idTask}"/></td>
                <td><c:out value="${task.idUser}"/></td>
                <td><c:out value="${task.taskName}"/></td>
                <td><c:out value="${task.recordingTime}"/></td>
                <td><c:out value="${task.amountOfTime}"/></td>
                <td><c:if test="${language.equals('ru_RU')}">
                    <c:out value="${task.category.nameRu}"/>
                </c:if>
                    <c:if test="${language.equals('en_EN')}">
                        <c:out value="${task.category.nameEng}"/>
                    </c:if></td>
                <td><c:choose>
                    <c:when test="${task.idStatus == 1 }"><a style="color: grey"><fmt:message
                            key="table.status.verification"/></a></c:when>
                    <c:when test="${task.idStatus == 2 }"><a style="color: green"><fmt:message
                            key="table.status.confirmed"/></a></c:when>
                    <c:when test="${task.idStatus == 3 }"><a style="color: red"><fmt:message
                            key="table.status.rejected"/></a></c:when>
                    <c:otherwise>Undefined</c:otherwise>
                </c:choose>
                </td>
                <td>
                    <form method="post" action="<c:url value='/verification'/>">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="number" hidden name="idStatus" value="3">
                        <input type="submit"
                               class="btn btn-danger"
                               value='<fmt:message key="tasks_jsp.button.reject"/>' name="Reject">
                    </form>
                </td>
                <td>
                    <form method="post" action="/verification">
                        <input type="number" hidden name="id" value="${task.idTask}">
                        <input type="number" hidden name="idStatus" value="2">
                        <input type="submit"
                               class="btn btn-success" value='<fmt:message key="tasks_jsp.button.confirm"/>'
                               name="Confirm">
                    </form>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../template/footer.jspf" %>
