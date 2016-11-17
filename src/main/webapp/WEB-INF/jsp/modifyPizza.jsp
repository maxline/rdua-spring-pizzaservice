<%--
  Created by IntelliJ IDEA.
  User: Serhii_Mykhliuk
  Date: 17-Nov-16
  Time: 11:40 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <tr>
        <th>ID</th>
        <th>Name</th>
    </tr>

    <%--<tr>--%>
    <%--<td><c:out value="${pizza.id}"></c:out></td>--%>
    <%--<td><c:out value="${pizza.name}"></c:out></td>--%>

    <form action="addNew" method="post">
        <input type="hidden" name="pizzaId" value="${pizza.pizzaId}">
        Name: <input type="text" name="name" value="${pizza.name}">
        Type: <input type="text" name="type" value="${pizza.pizzaType}">

        <input type="submit"/>

    </form>


    <c:forEach items="${pizzaList}" var="pizza">
        <tr>
            <td><c:out value="${pizza.pizzaId}"></c:out></td>
            <td><c:out value="${pizza.name}"></c:out></td>
                <%--<td><c:out value="${customer.address}"></c:out></td>--%>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
