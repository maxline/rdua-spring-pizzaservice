<%--
  Created by IntelliJ IDEA.
  User: Serhii_Mykhliuk
  Date: 17-Nov-16
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<%--должны прятать тут в WEB-INF, чтобы исходники не были доступны--%>

<html>
<head>
    <title>Pizzas</title>
</head>
<body>
<h1>Pizzas</h1>

<table>
    <tbody>

    <form action="addNew" method="post">
        <input type="hidden" name="pizzaId" value="${pizza.pizzaId}">
        Name: <input type="text" name="name" value="${pizza.name}">
        Type: <input type="text" name="pizzaType" value="${pizza.pizzaType}">
        Price: <input type="text" name="price" value="${pizza.price}">
        <input type="submit"/>
        <sec:csrfInput/>

    </form>

    </tbody>
</table>

</body>
</html>
