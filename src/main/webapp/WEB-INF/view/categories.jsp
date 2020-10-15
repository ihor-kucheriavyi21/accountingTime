<%--
  Created by IntelliJ IDEA.
  User: kuche
  Date: 14.10.2020
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../template/navbar.jspf" %>

<div class="container">
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>
                <fmt:message key="categories_jsp.table.id"/>
            </th>
            <th>
                <fmt:message key="categories_jsp.table.englishName"/>
            </th>
            <th>
                <fmt:message key="categories_jsp.table.russianName"/>
            </th>
            <th>
                <fmt:message key="categories_jsp.table.delete"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${requestScope.categories}">
            <tr>
                <td>
                    <c:out value="${category.id}"/>
                </td>
                <td>
                    <c:out value="${category.nameEng}"/>
                </td>
                <td>
                    <c:out value="${category.nameRu}"/>
                </td>
                <td>
                    <form method="post" action="<c:url value='/delete-category'/>">
                        <input type="number" hidden name="idCategory" value="${category.id}">
                        <input type="submit"
                               class="btn btn-danger"
                               value='<fmt:message key="categories_jsp.table.delete"/>' name="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr>
            <form method="post" action="<c:url value='add-category'/> ">
                <td>
                    <fmt:message key="categories_jsp.table.label.newCategory"/>
                </td>
                <td>
                    <input type="text" class="form-control" name="nameEng"
                           placeholder='<fmt:message key="categories_jsp.table.englishName"/>'>
                </td>
                <td>
                    <input type="text" class="form-control" name="nameRu"
                           placeholder='<fmt:message key="categories_jsp.table.russianName"/>'>
                </td>
                <td>
                    <input type="submit" class="btn btn-outline-success"
                           value='<fmt:message key="categories_jsp.table.button.add"/>' name="add">
                </td>
            </form>
        </tr>
        </tfoot>
    </table>
</div>
<%@ include file="../template/footer.jspf" %>