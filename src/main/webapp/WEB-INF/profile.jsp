<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div><img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>

<!-- Form profile -->
<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col-9 col-md-9 col-lg-7 col-xl-5">
            <div class="authorization-form">
                <form class="form-horizontal" action="profile" method="post"> <%-- Change action to "signup" --%>
                    <fieldset>
                        <legend class="name-page">Профиль: ${sessionScope.user.login}</legend>

                        <!-- Error message -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">${errorMessage}</div>
                        </c:if>

                        <!-- Login input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userLogin">Логин</label>
                            <div class="col-md-12">
                                <input id="userLogin" name="userLogin" type="text"
                                       class="form-control input-md"
                                       value="${sessionScope.user.login}" disabled>
                            </div>
                        </div>

                        <!-- Surname input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userSurname">Фамилия</label>
                            <div class="col-md-12">
                                <input id="userSurname" name="userSurname" type="text"
                                       class="form-control input-md"
                                       value="${sessionScope.user.surname}">
                            </div>
                        </div>

                        <!-- Name input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userName">Имя</label>
                            <div class="col-md-12">
                                <input id="userName" name="userName" type="text"
                                       class="form-control input-md"
                                       value="${sessionScope.user.name}">
                            </div>
                        </div>

                        <!-- Password input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userPassword">Пароль</label>
                            <div class="col-md-12">
                                <input id="userPassword" name="userPassword" type="password"
                                       placeholder="введите новый пароль"
                                       class="form-control input-md">
                            </div>
                        </div>

                        <!-- Button (Double) -->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="submit"></label>
                            <div class="col-md-12">
                                <button id="submit" name="submit" class="btn btn-success">Обновить данные</button>
                                <button id="close" name="close" class="btn btn-danger">Отмена</button>
                            </div>
                        </div>

                    </fieldset>
                </form>


            </div>
        </div>
    </div>
</div>


<%@include file="parts/footer.jsp" %>




