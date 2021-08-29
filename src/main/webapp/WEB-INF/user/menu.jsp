<%--
  User: Dmytro Maltsev
  Date: 24.08.2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename = "messages" />
<html lang="${lang}">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <jsp:include page="../../fragment/includes.jsp"></jsp:include>
</head>
<body>
    <fmt:setBundle basename = "messages" />
    <jsp:include page="../../fragment/header.jsp"></jsp:include>
    <div class="container">
        <h1><fmt:message key="menu.Title"/></h1>
        <div class="menu-button-group">
            <div class="dropdown filter" id="filter">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuCategoryButton" data-bs-toggle="dropdown" aria-expanded="false">
                    Category
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuCategoryButton">
                    <c:forEach var="category" items="${categories}">
                    <li><a class="dropdown-item" href="/api/app/user/menu?category=${category}">${category}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="dropdown filter" id="sort">
                <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuSortButton" data-bs-toggle="dropdown" aria-expanded="false">
                    Sort by
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuSortButton">
                    <li><a class="dropdown-item">Name Asc</a></li>
                    <li><a class="dropdown-item">Name Desc</a></li>
                    <li><a class="dropdown-item">Price Asc</a></li>
                    <li><a class="dropdown-item">Price Desc</a></li>
                    <li><a class="dropdown-item">Category Asc</a></li>
                    <li><a class="dropdown-item">Category Desc</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-9">
                <div class="row">
                    <c:forEach var="dish" items="${dishes}">
                        <div class="card col-3" style="width: 18rem;">
                            <img src="${pageContext.request.contextPath}/img/dish/${dish.getImg()}" class="card-img-top" alt="...">
                            <div class="card-body">
                                <h5 class="card-title">${dish.getName()}</h5>
                                <p class="card-text">${dish.getCategory()}</p>
                                <p class="card-text">${dish.getDescription()}</p>
                                <p class="card-text">${dish.getPrice()}</p>
                                <form method="post" action="${pageContext.request.contextPath}/app/user/addToCart?dishId=${dish.getId()}">
                                    <button class="btn btn-primary"><fmt:message key="menu.buy" /></button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="col-3">
                <div class="card" style="width: 18rem;">
                    <div class="card-header">
                        <h5 class="card-title"><fmt:message key="cart.name" /></h5>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <tbody>
                                <c:forEach var="orderDish" items="${sessionScope.orderDishes}">
                                    <tr>
                                        <th scope="row">${orderDish.dish.name}</th>
                                        <td>${orderDish.quantities} - <fmt:message key="cart.quantities" /></td>
                                        <td>
                                            <form method="post" action="${pageContext.request.contextPath}/app/user/addToCart?dishId=${orderDish.dish.id}">
                                                <button class="btn btn-primary">Inc</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="post" action="${pageContext.request.contextPath}/app/user/reduceQuantities?dishId=${orderDish.dish.id}">
                                                <button class="btn btn-primary">Dec</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form method="post" action="${pageContext.request.contextPath}/app/user/removeFromCart?dishId=${orderDish.dish.id}">
                                                <button class="btn-close"></button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-body">
                        <p><fmt:message key="cart.total"/> ${sessionScope.orderSum}$</p>
                        <form method="post" action="${pageContext.request.contextPath}/app/user/newOrder">
                            <button class="btn btn-primary"><fmt:message key="cart.button" /></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <nav>
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item"><a class="page-link" href="/api/app/user/menu?page=${currentPage - 1}">Previous</a></li>
                </c:if>
                <c:forEach var="pageNumber" items="${pageNumbers}">
                    <li class="page-item ${(currentPage eq pageNumber) ? 'active': ''}"><a href="/api/app/user/menu?page=${pageNumber}" class="page-link">${pageNumber}</a></li>
                </c:forEach>
                <c:if test="${currentPage < pageNumbers.size()}">
                    <li class="page-item"><a href="/api/app/user/menu?page=${currentPage + 1}" class="page-link">Next</a></li>
                </c:if>
            </ul>
        </nav>
    </div>
    <jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>
