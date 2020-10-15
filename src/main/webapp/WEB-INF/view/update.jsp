<%--
  Created by IntelliJ IDEA.
  User: kucher
  Date: 22.09.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/header.jspf" %>
<div class="container">
        <ul class="list-group">
            <div class="col-sm-4">

                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <fmt:message key="table.col.id"/>
                    <span class="badge badge-primary badge-pill"><c:out value="${requestScope.task.idTask}"/></span>
                </li>
            </div>
            <div class="col-sm-4">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <fmt:message key="table.col.name"/>
                    <span class="badge badge-primary badge-pill"><c:out value="${requestScope.task.taskName}"/></span>
                </li>
            </div>
            <div class="col-sm-4">

                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <fmt:message key="table.col.recordingTime"/>
                    <span class="badge badge-primary badge-pill"><c:out
                            value="${requestScope.task.recordingTime}"/></span>
                </li>
            </div>
            <div class="col-sm-4">

                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <fmt:message key="table.col.amountOfTime"/>
                    <span class="badge badge-primary badge-pill"><c:out
                            value="${requestScope.task.amountOfTime}"/></span>
                </li>
            </div>
            <div class="col-sm-4">

                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <fmt:message key="table.col.category"/>
                    <span class="badge badge-primary badge-pill">
                    <c:if test="${language.equals('ru_RU')}">
                        <c:out value="${task.category.nameRu}"/>
                    </c:if>
                    <c:if test="${language.equals('en_EN')}">
                        <c:out value="${task.category.nameEng}"/>
                    </c:if>
                </span>
                </li>
            </div>

        </ul>
        <br/>
        <form method="post" action="<c:url value='update-task'/> ">
            <input type="number" hidden name="id" value="${requestScope.task.idTask}">
            <div class="form-group row">
                <label class="col-sm-2"><fmt:message key="update_jsp.label.nameTask"/></label>
                <div class="col-sm-3">
                    <input type="text" class="form-control" name="name"
                           placeholder='<fmt:message key="placeholder.someName"/>'><br/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2"><fmt:message key="update_jsp.label.amountOfTime"/></label>
                <div class="col-sm-3">
                    <input type="number" class="form-control" name="time"
                           placeholder='<fmt:message key="placeholder.countSpendTime"/> '><br/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <input class="btn btn-outline-primary" type="submit"
                           value='<fmt:message key="update_jsp.button.ok"/> '
                           name="Ok">
                    <a href="/main"><fmt:message key="update_jsp.href.backToMain"/></a>
                    <br>
                </div>
            </div>
        </form>

</div>
<%@ include file="../template/footer.jspf" %>