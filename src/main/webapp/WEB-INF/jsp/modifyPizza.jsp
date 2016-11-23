<%--
  Created by IntelliJ IDEA.
  User: Serhii_Mykhliuk
  Date: 17-Nov-16
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<html>
<head>
    <title>Modify pizza</title>
</head>
<body>

<h1>Modify pizza</h1>
    <form action="${pageContext.request.contextPath}/app/modify" method="post">
        <input type="hidden" name="pizzaId" value="${pizza.pizzaId}">
        Name: <input type="text" name="name" value="${pizza.name}">
        Type: <input type="text" name="pizzaType" value="${pizza.pizzaType}">
        Price: <input type="text" name="price" value="${pizza.price}">

        <input type="submit"/>

    </form>
</body>
</html>
