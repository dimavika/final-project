<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-23
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Audio</title>
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
        <form action="Controller" method="post">
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
                <label for="title">Title</label>
                <input name="title" value="" id="title" type="text" required>
            </p>
            <p>
                <label for="price">Price</label>
                <input type="number" name="price" id="price"
                       pattern="[0-9]+([\.,][0-9]+)?" step="0.01"
                       title="This should be a number with up to 2 decimal places." required>
            </p>
            <p>
                <label>
                    <select name="genre">
                        <option value="rap">Rap</option>
                        <option value="rock">Rock</option>
                        <option value="country">Country</option>
                        <option value="classic">Classic</option>
                    </select>
                </label>
            </p>
            <p>
                <button type="submit" value="addAudio" name="command">Add Audio</button>
            </p>
        </form>

    </div>

</div>
</body>
</html>
