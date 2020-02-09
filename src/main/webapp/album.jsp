<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-28
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Album</title>
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
        <c:if test="${not empty requestScope.audios}">
            <div class="tracks">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Artist</th>
                        <th scope="col">Price</th>
                        <th scope="col">Genre</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="audio" items="${requestScope.audios}">
                        <tr>
                            <td>${audio.title}</td>
                            <td>${audio.artistName}</td>
                            <td>${audio.price}</td>
                            <td>${audio.genre}</td>
                            <td>
                                <form action="Controller" method="get">
                                    <label>
                                        <input type="hidden" value="${audio.id}" name="audioId">
                                    </label>
                                    <button type="submit" value="sendReviews" name="command">Reviews</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
