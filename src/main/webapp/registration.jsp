<%--
  User: Dmytro Maltsev
  Date: 20.08.2021
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
    <title>Registration</title>
    <meta charset="UTF-8">
    <jsp:include page="./fragment/includes.jsp"></jsp:include>
</head>
<body class="text-center">
<form method="post" class="form-login" action="${pageContext.request.contextPath}/app/registration">
    <h1>Registration</h1>
    <label for="name"><fmt:message key="registration.name" /></label>
    <input type="text" class="login-input" id="name" name="name" placeholder="Name"></input>
    <label for="username"><fmt:message key="registration.login" /></label>
    <input type="text" class="login-input" id="username" name="username" placeholder="Login"></input>
    <label for="password"><fmt:message key="registration.password" /></label>
    <input type="password" class="login-input" id="password" name="password" placeholder="Password"></input>
    <button type="submit" class="submit-button"><fmt:message key="registration.reg" /></button>
</form>
</body>

</html>
