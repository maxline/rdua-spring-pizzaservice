
<%--
  Created by IntelliJ IDEA.
  User: Serhii_Mykhliuk
  Date: 17-Nov-16
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--должны прятать тут в WEB-INF, чтобы исходники не были доступны--%>

<html>
<head>
    <title>JSP Page</title>
</head>
<body>
<h1>Hello from JSP</h1>

<h1>This is secured!</h1>
<%--http://localhost:8081/s-pizza-service/app/hello--%>
<p>
    Hello
    ${pageContext.request.remoteUser}
</p>

<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>


</body>
</html>
