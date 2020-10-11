<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 09.10.2020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>
<%@ include file="../template/header.jspf" %>

<div class="container">
    <h5>${sessionScope.user.name}</h5>
    <h4>To change the parameters fill fields more than 2 characters</h4>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Change name:</label>
            <div class="col-sm-6">
                <input name="login" class="form-control" placeholder="Some name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Change password:</label>
            <div class="col-sm-6">
                <input type="password" name="pass" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Repeat password:</label>
            <div class="col-sm-6">
                <input type="password" name="pass" class="form-control" placeholder="Password"/>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Update profile</button>
    </form>
</div>
<%@ include file="../template/footer.jspf" %>
