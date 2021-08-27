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
    <title>MyOrders</title>
    <jsp:include page="../../fragment/includes.jsp"></jsp:include>
</head>
<body>
    <fmt:setBundle basename = "messages" />
    <jsp:include page="../../fragment/header.jsp"></jsp:include>
    <div class="container">
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="my.orders.table.id" /></th>
                <th scope="col"><fmt:message key="my.orders.table.status" /></th>
                <th scope="col"><fmt:message key="my.orders.table.date" /></th>
                <th scope="col"><fmt:message key="my.orders.table.dishes" /></th>
                <th scope="col"><fmt:message key="my.orders.table.sum" /></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <th scope="row">${order.getId()}</th>
                        <th>${order.getStatus()}</th>
                        <th></th>
                        <th>
                            <ul>
                                <c:forEach var="orderDish" items="${orderDishService.getOrderDishesByOrderId(order.getId())}">
                                    <li>${orderDish.getDish().getName()} - ${orderDish.getQuantities()}</li>
                                </c:forEach>
                            </ul>
                        </th>
                        <th>${order.getSum()}</th>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>
