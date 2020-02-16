<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-28
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.album"/></title>
    <link rel="stylesheet" href="stylescss/main1.css">
    <link rel="stylesheet" href="stylescss/table1.css">
</head>
<body>

<header>
    <div class="locale">
        <form action="Controller" method="get">
            <input type="hidden" name="locale" value="en_US">
            <button type="submit" name="command" value="changeLocale">EN</button>
        </form>
        <form action="Controller" method="get">
            <input type="hidden" name="locale" value="ru_RU">
            <button type="submit" name="command" value="changeLocale">RU</button>
        </form>
    </div>
    <h1>Spotify</h1>
</header>

<div class="main">

    <div class="navbar-menu">
        <nav>
            <ul>
                <li>
                    <a href="<c:url value="/controller?command=main"/>"><fmt:message key="li.main"/></a>
                </li>

                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <li>
                        <a href="<c:url value="/controller?command=showUsers"/>"><fmt:message key="li.users"/></a>
                    </li>
                </c:if>

                <li>
                    <a href="<c:url value="/controller?command=audios"/>"><fmt:message key="li.audios"/></a>
                </li>

                <li>
                    <a href="<c:url value="/controller?command=sendAlbums"/>"><fmt:message key="li.albums"/></a>
                </li>

                <li>
                    <a href="<c:url value="/controller?command=sendOrders"/>"><fmt:message key="li.orders"/></a>
                </li>

                <li>
                    <a href="<c:url value="/controller?command=logout"/>"><fmt:message key="li.logout"/></a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="container">

        <div class="buttons">

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <div class="add-audio">
                    <form method="get" action="Controller">

                        <label>
                            <input type="hidden" value="${requestScope.albumId}" name="albumId">
                        </label>

                        <label>
                            <input type="hidden" value="${requestScope.artist}" name="artist">
                        </label>

                        <button type="submit" value="sendAudiosForChangeAlbum" name="command">
                            <fmt:message key="button.addAudio"/>
                        </button>

                    </form>
                </div>
            </c:if>

        </div>

        <c:if test="${not empty requestScope.audios}">
            <div class="tracks">
                <table>
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="th.title"/></th>
                        <th scope="col"><fmt:message key="th.artist"/></th>
                        <th scope="col"><fmt:message key="th.price"/></th>
                        <th scope="col"><fmt:message key="th.genre"/></th>
                        <th></th>
                        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                            <th></th>
                        </c:if>
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
                                    <button type="submit" value="sendReviews" name="command">
                                        <fmt:message key="button.reviews"/>
                                    </button>
                                </form>
                            </td>
                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <td>
                                    <form method="post" action="Controller">
                                        <label>
                                            <input type="hidden" value="${requestScope.albumId}" name="albumId">
                                        </label>

                                        <label>
                                            <input type="hidden" value="${audio.id}" name="audioId">
                                        </label>

                                        <label>
                                            <input type="hidden" value="${requestScope.artist}" name="artist">
                                        </label>

                                        <button type="submit" name="command" value="deleteAlbumAudio">
                                            <fmt:message key="button.delete"/>
                                        </button>
                                    </form>
                                </td>
                            </c:if>

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
