<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename = "messages" />
<html lang="${lang}">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" aria-label="Third navbar example">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Restaurant</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/api/" class="nav-link px-2 text-white"><fmt:message key="nav.home" /></a></li>
                    <c:if test="${sessionScope.role ne 'GUEST'}">
                        <li><a href="/api/app/user/menu" class="nav-link px-2 text-white"><fmt:message key="nav.menu" /></a></li>
                        <li><a href="/api/app/user/myorders" class="nav-link px-2 text-white"><fmt:message key="nav.myorders" /></a></li>
                    </c:if>
                    <c:if test="${sessionScope.role eq 'MANAGER'}">
                        <li><a href="/api/app/admin/orders" class="nav-link px-2 text-white"><fmt:message key="nav.orders" /></a></li>
                        <li><a href="/api/app/admin/users" class="nav-link px-2 text-white"><fmt:message key="nav.users" /></a></li>
                    </c:if>
                </ul>
                <div class="text-end">
                    <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-haspopup="true"
                                   aria-expanded="false">
                                    ENG
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                                    <li><a href="?lang=en" class="dropdown-item">Eng</a></li>
                                    <li><a href="?lang=ua" class="dropdown-item">Ua</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.role eq 'GUEST'}">
                            <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/api/app/login';"><fmt:message key="nav.login" /></button>
                            <button type="button" class="btn btn-warning" onclick="location.href='/api/app/registration';"><fmt:message key="nav.sign_in" /></button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" class="btn btn-outline-light me-2" onclick="location.href='/api/app/logout';"><fmt:message key="nav.logout" /></button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>
</html>
