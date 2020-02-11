<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-08
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.orders"/></title>
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
        <c:choose>
            <c:when test="${not empty requestScope.orders}">
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="th.audioTitle"/></th>
                            <th scope="col"><fmt:message key="th.price"/></th>
                            <th scope="col"><fmt:message key="th.date"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td>${order.audioTitle}</td>
                                <td>${order.price}</td>
                                <td>${order.dateTime}</td>
                                <td>
                                    <audio controls src="2Pac ft. Sade - Jezebel .mp3"></audio>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="p.noOrders"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>

