<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-01-17
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
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
                    <a href="<c:url value="/controller?command=main"/>">Home</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=showUsers"/>">Users</a>
                </li>
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
        <c:choose>
            <c:when test="${not empty requestScope.users}">
                <div class="table">
                    <table>
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Login</th>
                            <th scope="col">Role</th>
                            <th scope="col">Blocked</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${requestScope.users}">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                                <td>${user.role}</td>
                                <td>${user.isBlocked}</td>
                                <td>
                                    <form method="post" action="Controller">
                                        <label>
                                            <input type="hidden" name="userId" value="${user.id}">
                                        </label>
                                        <c:choose>
                                            <c:when test="${not user.isBlocked}">
                                                <button type="submit" name="command" value="block">Block</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="submit" name="command" value="unblock">Unblock</button>
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
                There are no users.
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
