<%--
  User: Dmytro Maltsev
  Date: 20.08.2021
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login</h1><br/>
    <form method="post" action="${pageContext.request.contextPath}/app/login">

        <input type="text" name="name"><br/>
        <input type="password" name="pass"><br/><br/>
        <input class="button" type="submit" value="Войти">

    </form>
</body>
</html>
