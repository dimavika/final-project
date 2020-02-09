<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-27
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Albums</title>
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
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <form method="get" action="Controller">
                <button name="command" value="sendArtistForAddAlbum" type="submit">Add Album</button>
            </form>
        </c:if>

        <c:if test="${not empty requestScope.albums}">
            <div class="table">
                <table>
                    <thead>
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Artist</th>
                        <th scope="col"></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="album" items="${requestScope.albums}">
                        <tr>
                            <td>${album.title}</td>
                            <td>${album.artistName}</td>
                            <td>${album.price}</td>
                            <td>
                                <form method="get" action="Controller">
                                    <label>
                                        <input type="hidden" name="albumId" value="${album.id}">
                                    </label>
                                    <button type="submit" value="sendAlbum" name="command">Show</button>
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
