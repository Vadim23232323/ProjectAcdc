<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div> <img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>
<div class="container-xl">
    <div class="row">
    <div class="col-12 text-center">
        <h1 class="name-page">КВЕСТ: НЛО</h1>
    </div>
    </div>

    <div class="list-group">
        <c:if test="${not empty currentQuestion}">
            <div class="quest-item">
                <h2 class="fon-quest">${currentQuestion.description}</h2>
                <c:forEach var="answer" items="${answerRepository.all}">
                    <form action="/question" method="get">
                        <c:if test="${answer.questionId == currentQuestion.id}">
                            <input type="hidden" name="answerId" value="${answer.id}">
                            <button type="submit" class="list-group-item list-group-item-action">
                                    ${answer.text}
                            </button>
                        </c:if>
                    </form>
                </c:forEach>
            </div>

            <div id="result" class="bottom-fixed">
                <c:if test="${currentQuestion.win == true || currentQuestion.wasted == true}">
                    <form action="/restart" method="post">
                        <button type="submit" class="btn-new">Начать заново</button>
                    </form>
                </c:if>
            </div>


        </c:if>
    </div>
</div>





<%@include file="parts/footer.jsp" %>