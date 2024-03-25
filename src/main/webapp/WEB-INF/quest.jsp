<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>

<div class="container-xl">
    <h1 class="name_quest">Квест НЛО:</h1>
    <div class="list-group">
        <c:if test="${not empty currentQuestion}">
            <div class="quest-item">
                <h2>${currentQuestion.description}</h2>
                <c:forEach var="answer" items="${answerRepository.all}">
                    <form action="/quest" method="get">
                        <c:if test="${answer.questionId == currentQuestion.id}">
                            <input type="hidden" name="answerId" value="${answer.id}">
                            <button type="submit" class="list-group-item list-group-item-action">
                                    ${answer.text}
                            </button>
                        </c:if>
                    </form>
                </c:forEach>
            </div>
        </c:if>

        <c:if test="${empty currentQuestion}">
            <p>Квест завершен!</p>
        </c:if>
    </div>
</div>

<%@include file="parts/footer.jsp" %>