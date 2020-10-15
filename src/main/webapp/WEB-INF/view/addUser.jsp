<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 29.09.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>

<form method="post" action="<c:url value='registration'/> ">
    <c:if test="${errorMessageFilling!=null}">
        <p class="alert alert-danger" role="alert"><fmt:message key="addUser_jsp.errorMessageFilling"/></p>
    </c:if>
    <c:if test="${errorMessageSame!=null}">
        <p class="alert alert-danger" role="alert"><fmt:message key="addUser_jsp.errorMessageSame"/></p>
    </c:if>
    <div class="container register-form">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="addUser_jsp.label.login"/></label>
            <div class="col-sm-4"><input type="text" class="form-control" name="login"
                                         placeholder='<fmt:message key="addUser_jsp.placeholder.login"/>'></div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="addUser_jsp.label.password"/></label>
            <div class="col-sm-4"><input type="password" class="form-control" name="pass"
                                         placeholder='<fmt:message key="addUser_jsp.placeholder.password"/>'></div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label" ><fmt:message key="addUser_jsp.label.repeatPassword"/></label>
            <div class="col-sm-4"><input type="password" class="form-control" name="repPass"
                                         placeholder='<fmt:message key="addUser_jsp.placeholder.password"/>'>
            </div>
            <br>
        </div>

        <input type="submit" class="btn btn-primary" value='<fmt:message key="addUser_jsp.button.addUser"/> ' name="Ok"><br>
    </div>
</form>
<%@ include file="../template/footer.jspf" %>
