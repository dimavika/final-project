<%--
  Created by IntelliJ IDEA.
  User: dimavi_ka
  Date: 2019-12-18
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title><fmt:message key="title.login"/></title>
    <link rel="stylesheet" href="stylescss/form.css">
    <link rel="stylesheet" href="stylescss/main1.css">
</head>

<body>
<header>
    <h1>Spotify</h1>
</header>

<div class="main">

    <div class="container">

        <div class="authorize">
            <form method="get" action="Controller" class="authorize-form">
                <div class="form-group">
                    <label for="login_input"><fmt:message key="label.login"/></label>
                    <input name="login" value="" id="login_input" type="text" required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="label.password"/></label>
                    <input name="password" value="" id="password" type="password" required>
                </div>
                <%--    <p class="clearfix">--%>
                <%--      <input type="checkbox" name="remember" id="remember">--%>
                <%--      <label for="remember">Remember me</label>--%>
                <%--    </p>--%>

                <%--    <label>--%>
                <%--      <input name="command" type="text">--%>
                <%--    </label>--%>
                <input type="submit" name="command" value="login">

                <%--    <input type="submit" name="command" value="registration">--%>
            </form>

            <div class="error-message">
                <c:if test="${not empty requestScope.errorMessage}">
                    <c:out value="${requestScope.errorMessage}"/>
                </c:if>
            </div>

        </div>


    </div>
</div>
</body>
</html>
