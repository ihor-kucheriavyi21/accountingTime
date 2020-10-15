<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 23.09.2020
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../template/navbar.jspf" %>
<div class="container">

    <c:if test="${requestScope.logoutMessage!=null}">
        <p class="alert alert-success" role="alert"><fmt:message key="login_jsp.logoutMessage"/></p>
    </c:if>
    <c:if test="${errorMessage!=null}">
        <p class="alert alert-danger" role="alert"><fmt:message key="login_jsp.errorMessage"/></p>
    </c:if>
    <form method="post" action="<c:url value='login'/>">
        <div class="form-group row">
            <label class="col-sm-1 col-form-label"><fmt:message key="login_jsp.label.login"/></label>
            <div class="col-sm-4">

                <input type="text" class="form-control" name="username"/><br/>
            </div>
        </div>
        <div class="form-group row">

            <label class="col-sm-1 col-form-label"><fmt:message key="login_jsp.label.password"/> </label>
            <div class="col-sm-4">
                <input type="password" class="form-control" name="userpass"/><br/>
            </div>
        </div>
        <div class="row">
            <div class="col-4">
                <input type="submit" class="btn btn-outline-primary"
                       value='<fmt:message key="login_jsp.button.login"/>'/>
            </div>
            <div class="col-3">
                <a href="/registration"><fmt:message key="login_jsp.button.registration"/></a>
            </div>
        </div>
    </form>
    <br/>
    <%--<form method="get" action="/registration">
        <input type="submit"
               class="btn btn-primary"
               value='<fmt:message key="login_jsp.button.registration"/>'>
    </form>--%>
</div>
<%@ include file="../template/footer.jspf" %>