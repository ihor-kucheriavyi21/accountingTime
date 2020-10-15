<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 09.10.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>

<div class="container">
    <h5><fmt:message key="profile_jsp.label.yourName"/> ${sessionScope.user.name}</h5>
    <h6><fmt:message key="profile_jsp.label.informUser"/></h6>
    <c:if test="${errorMessage!=null}">
        <p class="alert alert-danger" role="alert"><fmt:message key="profile_jsp.label.errorMessage"/></p>
    </c:if>
    <c:if test="${errorMessageName!=null}">
        <p class="alert alert-danger" role="alert"><fmt:message key="profile_jsp.label.errorMessageName"/></p>
    </c:if>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="profile_jsp.label.changeName"/></label>
            <div class="col-sm-6">
                <input name="login" class="form-control"
                       placeholder='<fmt:message key="addUser_jsp.placeholder.login"/>'/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="profile_jsp.label.changePass"/></label>
            <div class="col-sm-6">
                <input type="password" name="pass" class="form-control" placeholder='<fmt:message
            key="addUser_jsp.placeholder.password"/>'/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"><fmt:message key="profile_jsp.label.repeatPass"/></label>
            <div class="col-sm-6">
                <input type="password" name="pass" class="form-control" placeholder='<fmt:message
                key="addUser_jsp.placeholder.password"/>'/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit"><fmt:message key="profile_jsp.button.updateProfile"/></button>
    </form>
</div>
<%@ include file="../template/footer.jspf" %>
