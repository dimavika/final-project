<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-21
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.audios"/></title>
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

        <div class="search">
            <form action="Controller" method="get">
                <div class="search-input">
                    <label for="search-input"></label>
                    <input class="form-control" type="text" name="input" id="search-input"
                           placeholder="<fmt:message key="input.searchPlaceholder"/>">
                </div>

                <div class="submit-btn">
                    <button type="submit" name="command" value="showAudios"><fmt:message key="button.search"/></button>
                </div>
            </form>
        </div>

        <div class="buttons">

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <div class="add-track">
                    <form method="get" action="Controller">
                        <button type="submit" value="audio" name="command"><fmt:message key="button.addAudio"/></button>
                    </form>
                </div>
            </c:if>

            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                <div class="add-artist">
                    <form method="get" action="Controller">
                        <button type="submit" value="showAddArtist" name="command">
                            <fmt:message key="button.addArtist"/>
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
                            <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                                <td>
                                    <form action="Controller" method="post">
                                        <label>
                                            <input type="hidden" value="${audio.id}" name="audioId">
                                        </label>
                                        <button type="submit" value="deleteAudio" name="command">
                                            <fmt:message key="button.delete"/>
                                        </button>
                                    </form>
                                </td>
                            </c:if>
                            <td>
                                <form action="Controller" method="get">
                                    <label>
                                        <input type="hidden" value="${audio.id}" name="audioId">
                                    </label>
                                    <label>
                                        <input type="hidden" value="${audio.price}" name="price">
                                    </label>
                                    <button type="submit" value="sendReviews" name="command">
                                        <fmt:message key="button.reviews"/>
                                    </button>
                                    <button type="submit" value="sendAudioIdForAddReview" name="command">
                                        <fmt:message key="button.addReview"/>
                                    </button>
                                    <button type="submit" value="sendAudioIdAndPriceForAddOrder" name="command">
                                        <fmt:message key="button.buy"/>
                                    </button>
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
