<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-27
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.albums"/></title>
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

        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <div class="buttons">

                <div class="add-album">
                    <form method="get" action="Controller">
                        <button name="command" value="sendArtistForAddAlbum" type="submit">
                            <fmt:message key="button.addAlbum"/>
                        </button>
                    </form>
                </div>

            </div>
        </c:if>

        <c:if test="${not empty requestScope.albums}">
            <div class="table">
                <table>
                    <thead>
                    <tr>
                        <th scope="col"><fmt:message key="th.title"/></th>
                        <th scope="col"><fmt:message key="th.artist"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="album" items="${requestScope.albums}">
                        <tr>
                            <td>${album.title}</td>
                            <td>${album.artistName}</td>
                            <td>
                                <form method="get" action="Controller">
                                    <label>
                                        <input type="hidden" name="albumId" value="${album.id}">
                                    </label>
                                    <button type="submit" value="sendAlbum" name="command">
                                        <fmt:message key="button.show"/>
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
