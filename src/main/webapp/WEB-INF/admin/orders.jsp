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
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <th scope="row">${order.getId()}</th>
                    <th>${order.getStatus()}</th>
                    <th></th>
                    <th>${order.getCustomerId()}</th>
                    <th>
                        <ul>
                            <c:forEach var="orderDish" items="${orderDishService.getOrderDishesByOrderId(order.getId())}">
                                <li>${orderDish.getDish().getName()} - ${orderDish.getQuantities()}</li>
                            </c:forEach>
                        </ul>
                    </th>
                    <th>${order.getSum()}</th>
                    <td>
                        <form method="post" action="/api/app/admin/orders/changeStatus?orderId=${order.getId()}&status=cooked">
                            <button type="submit" ${(order.getStatus() ne 'NEW') ? 'disabled' : ''} class="btn btn-primary"><fmt:message key="orders.cooking" /></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="/api/app/admin/orders/changeStatus?orderId=${order.getId()}&status=delivered">
                            <button type="submit" ${(order.getStatus() ne 'COOKED') ? 'disabled': ''} class="btn btn-secondary"><fmt:message key="orders.deliver" /></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="/api/app/admin/orders/changeStatus?orderId=${order.getId()}&status=closed">
                            <button type="submit" ${(order.getStatus() ne 'PAID') ? 'disabled': ''} class="btn btn-success"><fmt:message key="orders.complete" /></button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="/api/app/admin/orders/changeStatus?orderId=${order.getId()}&status=canceled">
                            <button type="submit" ${(order.getStatus() eq 'CLOSED') ? 'disabled': ''} class="btn btn-danger"><fmt:message key="orders.cancel" /></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="/api/app/admin/orders?page=${currentPage - 1}">Previous</a></li>
                </c:if>
                <c:forEach var="pageNumber" items="${pageNumbers}">
                    <li class="page-item ${(currentPage eq pageNumber) ? 'active': ''}"><a href="/api/app/admin/orders?page=${pageNumber}" class="page-link">${pageNumber}</a></li>
                </c:forEach>
                <c:if test="${currentPage < pageNumbers.size()}">
                    <li class="page-item"><a href="/api/app/admin/orders?page=${currentPage + 1}" class="page-link">Next</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
    <jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>