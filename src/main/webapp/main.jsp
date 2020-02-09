<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"  var="rb"/>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="stylescss/main1.css">
</head>
<body>
<header>
    <h1><fmt:message key="title.main" bundle="${rb}"/></h1>
    <form style="float: right" method="post" action="Controller">
        <button type="submit" name="command" value="logout">Log out</button>
    </form>
</header>
<div class="main">
    <div class="navbar-menu">
        <nav>
            <ul>
                <li>
                    <a href="<c:url value="/controller?command=main"/>">Home</a>
                </li>
                <c:if test="${sessionScope.user.role eq 'ADMIN'}">
                    <li>
                        <a href="<c:url value="/controller?command=showUsers"/>">Users</a>
                    </li>
                </c:if>
                <li>
                    <a href="<c:url value="/controller?command=audios"/>">Audios</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=sendAlbums"/>">Albums</a>
                </li>
                <li>
                    <a href="<c:url value="/controller?command=sendOrders"/>">Orders</a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="container">
        <p>Hello, ${sessionScope.user.login}</p>
    </div>
</div>
</body>
</html>