<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-01
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.reviews"/></title>
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

        <div class="reviews">
            <c:choose>
                <c:when test="${not empty requestScope.reviews}">
                    <c:forEach var="review" items="${requestScope.reviews}">
                        <div class="review">
                            <p>${review.user}, ${review.dateTime}<br>
                                    ${review.text}
                            </p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-review">
                        <p><fmt:message key="p.noReviews"/></p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</div>
</body>
</html>
