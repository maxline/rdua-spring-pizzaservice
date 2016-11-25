<%--
  Created by IntelliJ IDEA.
  User: Serhii_Mykhliuk
  Date: 25-Nov-16
  Time: 11:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<%--должен быть повешен спрингСекюритиХендлер--%>
<form:form action="${pageContext.request.contextPath}/app/login" method="post">
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <!-- <input type="hidden"
    name="${_csrf.parameterName}"
    value="${_csrf.token}"/>-->
    <button type="submit" class="btn">Log in</button>
</form:form>
</body>
</html>
