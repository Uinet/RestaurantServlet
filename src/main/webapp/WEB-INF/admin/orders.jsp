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
    <title>Orders</title>
    <jsp:include page="../../fragment/includes.jsp"></jsp:include>
</head>
<body>
    <jsp:include page="../../fragment/header.jsp"></jsp:include>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="orders.table.id" /></th>
                <th scope="col"><fmt:message key="orders.table.status" /></th>
                <th scope="col"><fmt:message key="orders.table.date" /></th>
                <th scope="col"><fmt:message key="orders.table.customer" /></th>
                <th scope="col"><fmt:message key="orders.table.dishes" /></th>
                <th scope="col"><fmt:message key="orders.table.sum" /></th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>