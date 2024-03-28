<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div><img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>

<div class="container-xl">
    <div class="row">
        <div class="col-12 text-center">
            <h1 class="name-page">Список квестов:</h1>
        </div>
    </div>
    <div class="col-12 justify-content-center text-center">
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 row-cols-xl-3">
        <c:forEach items="${quest}" var="quest">
            <div class="col mb-3">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/image?questId=${quest.id}" class="card-img-top" alt="${quest.name}">
                    <div class="card-body">
                        <h5 class="card-title">${quest.name}</h5>
                        <a href="/question?questId=${quest.id}" class="btn btn-primary">Перейти к квесту</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</div>

<%@include file="parts/footer.jsp" %>