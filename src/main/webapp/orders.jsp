<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-08
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <link rel="stylesheet" href="stylescss/main1.css">
    <link rel="stylesheet" href="stylescss/table1.css">
</head>
<body>
<header>
    <h1>Spotify</h1>
</header>
<div class="main">

    <div class="navbar-menu">
        <nav>
            <ul>
                <li>
                    <a href="<c:url value="/controller?command=main"/>">Home</a>
                </li>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <li>
                        <a href="<c:url value="/controller?command=showUsers"/>">Users</a>
                    </li>
                </c:if>
                <li>
                    <a href="<c:url value="/controller?command=audios"/>">Audios</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=sendAlbums"/>">Albums</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=sendOrders"/>">Orders</a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="container">
        <c:choose>
            <c:when test="${not empty requestScope.orders}">
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">AudioTitle</th>
                            <th scope="col">Price</th>
                            <th scope="col">Date</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td>${order.audioTitle}</td>
                                <td>${order.price}</td>
                                <td>${order.dateTime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p>There are no orders.</p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>

