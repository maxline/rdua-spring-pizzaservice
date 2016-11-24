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
        <th>Type</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>

    <c:forEach items="${pizzaList}" var="pizza">
        <tr>
            <td><c:out value="${pizza.pizzaId}"></c:out></td>
            <td><c:out value="${pizza.name}"></c:out></td>
            <td><c:out value="${pizza.pizzaType}"></c:out></td>
            <td><c:out value="${pizza.price}"></c:out></td>
            <td>
                <form action="${pageContext.request.contextPath}/app/edit/${pizza.pizzaId}" method="get">
                    <button type="submit">EDIT</button>
                </form>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/app/delete/${pizza.pizzaId}" method="get">
                    <button type="submit">DELETE</button>
                </form>
            </td>

        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
