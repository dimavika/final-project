<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-27
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Album</title>
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
                    <a href="<c:url value="/controller?command=showUsers"/>">Orders</a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="container">

        <jsp:useBean id="artists" scope="request" type="java.util.List"/>
        <form action="Controller" method="get">
            <p>
                <label>
                    <select name="artist">
                        <c:forEach var="artist" items="${artists}">
                            <option value="${artist.id}">${artist.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </p>
            <p>
                <button type="submit" value="sendAudiosForAddAlbumSecond" name="command">Continue</button>
            </p>
        </form>

    </div>

</div>
</body>
</html>
