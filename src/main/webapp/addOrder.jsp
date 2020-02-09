<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-08
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Order</title>
    <link rel="stylesheet" href="stylescss/main1.css">
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
        <form action="Controller" method="post">
            <p>
                <label>
                    <input name="audioId" value="${requestScope.audioId}" type="hidden">
                </label>
            </p>
            <p>
                <label>
                    <input name="price" value="${requestScope.price}" type="hidden">
                </label>
            </p>
            <p>
                <label for="cardNum">Your card number</label>
                    <input name="cardNum" value="" type="text" id="cardNum" required>
            </p>
            <p>
                <button type="submit" value="addOrder" name="command">Pay</button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
