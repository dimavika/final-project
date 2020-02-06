<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-01
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Review</title>
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

        <div class="add-review">
            <form action="Controller" method="post">
                <label for="text">Text</label>
                <input type="text" name="text" value="" id="text" required>

                <label for="user"></label>
                <input type="text" name="userId" value="${sessionScope.user.id}" id="user" hidden>

                <label for="audioId"></label>
                <input type="text" name="audioId" value="${requestScope.audioId}" id="audioId" hidden>

                <button type="submit" value="addReview" name="command">Add Review</button>
            </form>
        </div>

    </div>

</div>
</body>
</html>
