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
    <title><fmt:message key="title.addAudio"/></title>
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

        <jsp:useBean id="artists" scope="request" type="java.util.List"/>
        <form action="Controller" method="post">
            <p>
                <label>
                    <select name="artist">
                        <c:forEach var="artist" items="${artists}">
                            <option value="${artist.id}">${artist.name}</option>
                        </c:forEach>
                    </select>
                </label>
            </p>
            <p>
                <label for="title"><fmt:message key="label.title"/></label>
                <input name="title" value="" id="title" type="text" required>
            </p>
            <p>
                <label for="price"><fmt:message key="label.price"/></label>
                <input type="number" name="price" id="price"
                       pattern="[0-9]+([\.,][0-9]+)?" step="0.01"
                       title="This should be a number with up to 2 decimal places." required>
            </p>
            <p>
                <label>
                    <select name="genre">
                        <option value="rap"><fmt:message key="option.rap"/></option>
                        <option value="rock"><fmt:message key="option.rock"/></option>
                        <option value="country"><fmt:message key="option.country"/></option>
                        <option value="classic"><fmt:message key="option.classic"/></option>
                    </select>
                </label>
            </p>
            <p>
                <button type="submit" value="addAudio" name="command"><fmt:message key="button.addAudio"/></button>
            </p>
        </form>

    </div>

</div>
</body>
</html>
