<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div><img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>

<div class="container-xl">
    <div class="row">
        <div class="col-12 text-center">
            <c:forEach var="quest" items="${questRepository.all}">
                <c:if test="${quest.id == currentQuestion.questId}">
                    <h1 class="name-page">КВЕСТ: ${quest.name}</h1>
                </c:if>
            </c:forEach>
        </div>
    </div>

    <!-- Error message -->
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

<%--    <div class="list-group">--%>
<%--        <c:if test="${not empty currentQuestion}">--%>
<%--            <div class="quest-item">--%>
<%--                <h2 class="fon-quest">${currentQuestion.description}</h2>--%>
<%--                <c:forEach var="answer" items="${answerRepository.all}">--%>
<%--                    <form action="/question" method="get">--%>
<%--                        <c:if test="${answer.questionId == currentQuestion.id}">--%>
<%--                            <input type="hidden" name="answerId" value="${answer.id}">--%>
<%--                            <button type="submit" class="list-group-item list-group-item-action">--%>
<%--                                    ${answer.text}--%>
<%--                            </button>--%>
<%--                        </c:if>--%>
<%--                    </form>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </c:if>--%>

    <div class="list-group">
        <c:if test="${not empty currentQuestion}">
            <div class="quest-item">
                <h2 class="fon-quest">${currentQuestion.description}</h2>
                <c:forEach var="answer" items="${filteredAnswers}">
                    <form action="/question" method="get">
                        <input type="hidden" name="answerId" value="${answer.id}">
                        <button type="submit" class="list-group-item list-group-item-action">
                                ${answer.text}
                        </button>
                    </form>
                </c:forEach>
            </div>
        </c:if>

        <div id="result" class="bottom-fixed">
            <div class="col-12 justify-content-center text-center">
                <div class="row row-cols-1 row-cols-md-2 row-cols-lg-2 row-cols-xl-2">
                        <c:if test="${showResults}">
                        <div class="restart">
                            <a href="/question?questId=${currentQuestion.questId}" class="btn-new">Начать заново</a>
                        </div>
                        <div class="restart">
                            <a href="${pageContext.request.contextPath}/list-quest" class="btn-new">Перейти к
                                квестам</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="parts/footer.jsp" %>