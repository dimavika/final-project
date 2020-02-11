<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-17
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.users"/></title>
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
                    <a href="<c:url value="/controller?command=main"/>"><fmt:message key="li.main"/> </a>
                </li>

                <li>
                    <a href="<c:url value="/controller?command=showUsers"/>"><fmt:message key="li.users"/></a>
                </li>

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
            <c:when test="${not empty requestScope.users}">
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="th.login"/></th>
                            <th scope="col"><fmt:message key="th.role"/></th>
                            <th scope="col"><fmt:message key="th.blocked"/></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${requestScope.users}">
                            <tr>
                                <td>${user.login}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.role eq 'ADMIN'}">
                                            <fmt:message key="td.admin"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="td.user"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.isBlocked eq true}">
                                            <fmt:message key="td.yes"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="td.no"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form method="post" action="Controller">
                                        <label>
                                            <input type="hidden" name="userId" value="${user.id}">
                                        </label>
                                        <c:choose>
                                            <c:when test="${not user.isBlocked}">
                                                <button type="submit" name="command" value="block">
                                                    <fmt:message key="button.block"/>
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" name="command" value="unblock">
                                                    <fmt:message key="button.unblock"/>
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <p><fmt:message key="p.noUsers"/></p>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
