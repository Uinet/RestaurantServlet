<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename = "messages" />
<html lang="${lang}">
    <head>
        <title>Main</title>
        <jsp:include page="fragment/includes.jsp"></jsp:include>
    </head>
    <body>
        <jsp:include page="fragment/header.jsp"></jsp:include>
        <div class="container">
            <h1><fmt:message key="main.title" /></h1>
            <img src="./img/home/main.jpg" class="img-fluid" alt="Responsive image">
        </div>
        <jsp:include page="fragment/footer.jsp"></jsp:include>
    </body>
</html>
