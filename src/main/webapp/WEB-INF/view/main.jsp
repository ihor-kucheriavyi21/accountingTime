<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>

<div class="container">
    <c:if test="${requestScope.errorMessageAddTask!=null}">
        <p class="alert alert-danger" role="alert">${requestScope.errorMessageAddTask}</p>
    </c:if>
    <h5 align="middle"><fmt:message key="main_jsp.welcome"/></h5>
    <br/>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="table.col.id"/></th>
            <th><fmt:message key="table.col.name"/></th>
            <th><fmt:message key="table.col.recordingTime"/></th>
            <th><fmt:message key="table.col.amountOfTime"/></th>
            <th><fmt:message key="table.col.category"/></th>
            <th><fmt:message key="table.col.status"/></th>
            <th><fmt:message key="table.button.deleteTask"/></th>
            <th><fmt:message key="table.button.updateTask"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${requestScope.tasks}">
            <tr>
                <td><c:out value="${task.idTask}"/></td>
                <td><c:out value="${task.taskName}"/></td>
                <td><c:out value="${task.recordingTime}"/></td>
                <td><c:out value="${task.amountOfTime}"/></td>
                <td>
                    <c:if test="${language.equals('ru_RU')}">
                        <c:out value="${task.category.nameRu}"/>
                    </c:if>
                    <c:if test="${language.equals('en_EN')}">
                        <c:out value="${task.category.nameEng}"/>
                    </c:if></td>
                <td>
                    <c:choose>
                        <c:when test="${task.idStatus == 1 }">
                            <a style="color: grey"><fmt:message
                                    key="table.status.verification"/>
                            </a>
                        </c:when>
                        <c:when test="${task.idStatus == 2 }">
                            <a style="color: green">
                                <fmt:message
                                        key="table.status.confirmed"/>
                            </a>
                        </c:when>
                        <c:when test="${task.idStatus == 3 }">
                            <a style="color: red">
                                <fmt:message
                                        key="table.status.rejected"/>
                            </a>
                        </c:when>
                        <c:otherwise><fmt:message key="table.status.undefined"/></c:otherwise>

                    </c:choose>
                </td>
                <td>
                    <c:if test="${task.idStatus==1}">

                        <form method="post" action="<c:url value='/delete-task'/>">
                            <input type="number" hidden name="id" value="${task.idTask}">
                            <input type="submit"
                                   class="btn btn-danger"
                                   value='<fmt:message key="table.button.deleteTask"/>' name="Delete">
                        </form>
                    </c:if>
                </td>
                <td>
                    <c:if test="${task.idStatus==1}">
                        <form method="get" action="/update-task">
                            <input type="number" hidden name="id" value="${task.idTask}">
                            <input type="submit"
                                   class="btn btn-info" value='<fmt:message key="table.button.updateTask"/>'
                                   name="Update">
                        </form>
                    </c:if>
                </td>
            </tr>

        </c:forEach>
        </tbody>
        <tfoot>
        <tr>

            <form method="post" action="<c:url value='/add-task'/> ">
                <td><fmt:message key="main_jsp.newRecord"/></td>
                <td><label><input type="text" class="form-control" name="name"
                                  placeholder='<fmt:message key="placeholder.someName"/>'></label></td>
                <td></td>
                <td><label><input type="number" class="form-control" name="time"
                                  placeholder='<fmt:message key="placeholder.countSpendTime"/>'></label></td>
                <td>
                    <select name="getCategory">

                        <option selected="selected"><fmt:message key="main_jsp.select.chooseCategory"/></option>
                        <c:forEach var="category" items="${requestScope.categories}">
                            <option value="${category.id}">
                                <c:if test="${cookie.get('language').value.equals('ru_RU')}">
                                    <c:out value="meessage"/>
                                </c:if>
                                <c:if test="${language.equals('ru_RU')}">
                                    <c:out value="${category.nameRu}"/>
                                </c:if>
                                <c:if test="${language.equals('en_EN')}">
                                    <c:out value="${category.nameEng}"/>
                                </c:if></option>

                        </c:forEach>
                    </select></td>
                <td><input type="submit" class="btn btn-outline-success"
                           value='<fmt:message key="main_jsp.button.addTask"/>' name="add"></td>
                <td></td>
                <td></td>
            </form>
        </tr>
        </tfoot>
    </table>

</div>
<%@ include file="../template/footer.jspf" %>