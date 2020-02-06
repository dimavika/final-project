<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-21
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Audio</title>
    <link rel="stylesheet" href="stylescss/main1.css">
    <link rel="stylesheet" href="stylescss/table1.css">
    <link rel="stylesheet" href="stylescss/search_input1.css">
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
        <div class="search">
            <form action="Controller" method="post">
                <div class="search-input">
                    <label for="search-input"></label>
                    <input class="form-control" type="text" name="input" id="search-input"
                           placeholder="Type name of track or artist...">
                </div>

                <div class="submit-btn">
                    <button type="submit" name="command" value="showAudios">Search</button>
                </div>
            </form>
        </div>
        <div class="buttons">

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <div class="add-track">
                    <form method="post" action="Controller">
                        <button type="submit" value="audio" name="command">Add Audio</button>
                    </form>
                </div>
            </c:if>

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <div class="add-artist">
                    <form method="post" action="Controller">
                        <button type="submit" value="showAddArtist" name="command">Add Artist</button>
                    </form>
                </div>
            </c:if>

        </div>

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
                                    <form action="Controller" method="post">
                                        <label>
                                            <input type="text" value="${audio.id}" name="audioId" hidden readonly>
                                        </label>
                                        <button type="submit" value="deleteAudio" name="command">Delete</button>
                                        <button type="submit" value="sendReviews" name="command">Reviews</button>
                                        <button type="submit" value="sendAudioIdForAddReview" name="command">Add Review</button>
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
