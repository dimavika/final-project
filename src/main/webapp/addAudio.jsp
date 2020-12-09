<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-23
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${empty requestScope.audioId}">
                <fmt:message key="title.addAudio"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="title.updateAudio"/>
            </c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="stylescss/main1.css">
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

        <jsp:useBean id="artists" scope="request" type="java.util.List"/>
        <form action="Controller" method="post">
            <p>
                <label for="artist"><fmt:message key="label.artist"/></label>
                <select name="artist" id="artist">
                    <c:forEach var="artist" items="${artists}">
                        <option value="${artist.id}">${artist.name}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label for="title"><fmt:message key="label.title"/></label>
                <input name="title" value="" id="title" type="text" required>
            </p>
            <p>
                <label for="price"><fmt:message key="label.price"/></label>
                <input type="text" name="price" id="price"
                       pattern="[0-9]+([\.][0-9]+)?" placeholder="10.99"
                       title="This should be a number with up to 2 decimal places." required>
            </p>
            <p>
                <label for="genre"><fmt:message key="label.genre"/></label>
                <select name="genre" id="genre">
                    <option value="rap"><fmt:message key="option.rap"/></option>
                    <option value="rock"><fmt:message key="option.rock"/></option>
                    <option value="country"><fmt:message key="option.country"/></option>
                    <option value="classic"><fmt:message key="option.classic"/></option>
                </select>
            </p>
            <c:choose>
                <c:when test="${empty requestScope.audioId}">
                    <p>
                        <button type="submit" value="addAudio" name="command"><fmt:message
                                key="button.addAudio"/></button>
                    </p>
                </c:when>
                <c:otherwise>
                    <label>
                        <input type="hidden" value="${requestScope.audioId}" name="audioId">
                    </label>
                    <p>
                        <button type="submit" value="updateAudio" name="command"><fmt:message
                                key="button.updateAudio"/></button>
                    </p>
                </c:otherwise>
            </c:choose>
        </form>

    </div>

</div>
</body>
</html>
