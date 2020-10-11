<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 23.09.2020
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jspf" %>
<div class="container">
    <c:if test="${logoutMessage!=null}">
        <p class="alert alert-success" role="alert"> ${logoutMessage}</p>
    </c:if>
    <c:if test="${errorMessage!=null}">
        <p class="alert alert-danger" role="alert"> ${errorMessage}</p>
    </c:if>
    <form method="post" action="<c:url value='login'/>">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label">Login:</label>
            <div class="col-sm-4">

                <input type="text" class="form-control" name="username"/><br/>
            </div>
        </div>
        <div class="form-group row">

            <label class="col-sm-1 col-form-label">Password:</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="userpass"/><br/>
            </div>
        </div>
        <input type="submit" value="login"/>
    </form>
    <br/>
    <form method="get" action="/registration">
        <input type="submit"
               class="btn btn-primary"
               value="Registration">
    </form>
</div>
<%@ include file="../template/footer.jspf" %>