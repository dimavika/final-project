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
