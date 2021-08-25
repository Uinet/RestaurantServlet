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
                    <div class="card col-3" style="width: 18rem;">
                        <img class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"></h5>
                            <p class="card-text"></p>
                            <p class="card-text"></p>
                            <p class="card-text"></p>
                            <form method="post">
                                <button class="btn btn-primary"><fmt:message key="menu.buy" /></button>
                            </form>
                        </div>
                    </div>
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
                            </tbody>
                        </table>
                    </div>
                    <div class="card-body">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="../../fragment/footer.jsp"></jsp:include>
</body>
</html>
