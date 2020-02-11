<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-27
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.addAlbum"/></title>
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

        <jsp:useBean id="audios" scope="request" type="java.util.List"/>
        <jsp:useBean id="artistId" scope="request" type="java.lang.Long"/>
        <form method="post" action="Controller">
            <p>
                <label for="albumTitle"><fmt:message key="label.title"/></label>
                    <input name="title" value="" id="albumTitle" type="text" required>
            </p>
            <p>
                <label for="price"><fmt:message key="label.price"/></label>
                    <input type="number" name="price" id="price"
                           pattern="[0-9]+([\.,][0-9]+)?" step="0.01"
                           title="This should be a number with up to 2 decimal places." required>
            </p>
            <c:forEach var="audio" items="${audios}">
                <c:if test="${audio.albumId eq 0}">
                    <p>
                        <label for="title">${audio.title}</label>
                        <input name="albumAudios" value="${audio.id}" id="title" type="checkbox">
                    </p>
                </c:if>
            </c:forEach>
            <p>
                <label>
                    <input name="artist" value="${artistId}" type="hidden">
                </label>
            </p>
            <p>
                <button type="submit" value="addAlbum" name="command"><fmt:message key="button.addAlbum"/></button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
