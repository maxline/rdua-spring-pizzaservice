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
    <title>Customers</title>
</head>
<body>
<h1>Customers</h1>

<table>
    <tbody>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
    </tr>
    <c:forEach items="${customerList}" var="customer">
        <tr>
            <td><c:out value="${customer.id}"></c:out></td>
            <td><c:out value="${customer.name}"></c:out></td>
            <%--<td><c:out value="${customer.address}"></c:out></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
