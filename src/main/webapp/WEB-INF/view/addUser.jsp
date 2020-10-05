<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 29.09.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../template/header.jspf" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${errorMessage!=null}">
    <p class="alert alert-danger" role="alert"> ${errorMessage}</p>
</c:if>

<form method="post" action="<c:url value='registration'/> ">
        <div class="container register-form">
            <div class="form-group">
                <label>Login:</label><br>
                <label><input type="text" class="form-control" name="login" placeholder="Login"></label>
            </div>
            <div class="form-group">
                <label>Password:</label><br>
                <label><input type="text" class="form-control" name="pass" placeholder="Password"></label>
            </div>
            <div class="form-group">
                <label>Repeat password:</label><br>
                <label><input type="text" class="form-control" name="repPass" placeholder="Password"></label><br>
            </div>

            <input type="submit" class="btn btn-primary" name="Ok"><br>
        </div>
</form>
<%@ include file="../template/footer.jspf" %>
