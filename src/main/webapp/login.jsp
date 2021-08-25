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
    <title>Login</title>
    <meta charset="UTF-8">
    <jsp:include page="./fragment/includes.jsp"></jsp:include>
</head>
<body class="text-center">
<form method="post" class="form-login" action="${pageContext.request.contextPath}/app/login">
    <h1>Login</h1>
    <label for="username"><fmt:message key="login.username" /></label>
    <input type="text" class="login-input" id="username" name="username" placeholder="Login"></input>
    <label for="password"><fmt:message key="login.password" /></label>
    <input type="password" class="login-input" id="password" name="password" placeholder="Password"></input>
    <button type="submit" class="submit-button"><fmt:message key="login.login_button" /></button>
</form>
</body>
</html>
