<%--
  User: Dmytro Maltsev
  Date: 24.08.2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename = "messages" />
<html lang="${lang}">
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <jsp:include page="../../fragment/includes.jsp"></jsp:include>
</head>
<body>
<fmt:setBundle basename = "messages" />
<jsp:include page="../../fragment/header.jsp"></jsp:include>
<div class="container">
<table class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="users.table.id" /></th>
        <th scope="col"><fmt:message key="users.table.name" /></th>
        <th scope="col"><fmt:message key="users.table.username" /></th>
        <th scope="col"><fmt:message key="users.table.role" /></th>
        <th scope="col"><fmt:message key="users.table.money" /></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <th scope="row">${user.getId()}</th>
                <td>${user.getName()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getRole()}</td>
                <td>${user.getMoney()}</td>
                <td>
                    <form method="post" action="/api/app/admin/users/replenishment">
                        <input type="number" id="money" step="0.01" min="0" name="money" placeholder="0.0"></input>
                        <input type="hidden" value="${user.getId()}" id="userId" name="userId">
                        <button type="submit" class="btn btn-primary"><fmt:message key="users.table.top_up_balance" /></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>
<jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>
