<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="<c:url value="/static/jquery-3.6.0.min.js"/>"></script>
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/13d829f7a0.js"></script>
    <title>Quest</title>
</head>
<body>
<header class="bg-light d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <a href="${pageContext.request.contextPath}/start"
       class="d-flex align-items-left col-md-2 mb-2 mb-md-0 text-dark text-decoration-none">
        <i class="fa fa-puzzle-piece" aria-hidden="true"></i>
        <p class="logo">КВЕСТ</p>
    </a>

    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <%-- guest --%>
        <li><a href="${pageContext.request.contextPath}/start" class="nav-link px-2">Home</a></li>
        <%-- user --%>
        <li><a href="${pageContext.request.contextPath}/quest" class="nav-link px-2">Квест</a></li>
        <%-- admin--%>
        <li><a href="list-user" class="nav-link px-2">Меню 2</a></li>
        <li><a href="create-quest" class="nav-link px-2">Меню 3</a></li>
    </ul>

    <ul class="nav col-md-3 text-end">
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <li><a href="profile" class="nav-m">Profile</a></li>
                <li><a href="logout" class="nav-m">Logout</a></li>
            </c:when>
            <c:otherwise>

                <li> <a href="login" class="sign-nav">Login </a></li>
                <li><a href="signup" class="sign-nav"> Sign-up</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</header>




