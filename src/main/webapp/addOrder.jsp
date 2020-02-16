<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2020-02-08
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.addOrder"/></title>
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
        <form action="Controller" method="post">
            <p>
                <label>
                    <input name="audioId" value="${requestScope.audioId}" type="hidden">
                </label>
            </p>
            <p>

                <label>
                    <input name="price" value="${requestScope.price}" type="hidden">
                </label>
            </p>

            <p>
                <label for="cardNum"><fmt:message key="label.cardNum"/></label>
                    <input name="cardNum" value="" type="text" pattern="[0-9]{16}" title="16 nums" id="cardNum" required>
            </p>

            <p>
                <label for="year"><fmt:message key="label.year"/></label>
                <input name="year" value="" type="text" pattern="[0-9]{4}" title="4 nums" id="year" required>
            </p>

            <p>
                <label for="thirdNum"><fmt:message key="label.thirdNum"/></label>
                <input name="thirdNum" value="" type="text" pattern="[0-9]{3}" title="3 nums" id="thirdNum" required>
            </p>

            <p>
                <button type="submit" value="addOrder" name="command"><fmt:message key="button.pay"/></button>
            </p>
        </form>
    </div>
</div>
</body>
</html>
