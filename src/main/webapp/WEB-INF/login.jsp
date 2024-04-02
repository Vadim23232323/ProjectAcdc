<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div><img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>

<!-- Form authorization -->
<div class="container mt-3">
    <div class="row justify-content-center">
        <div class="col-9 col-md-9 col-lg-7 col-xl-5">
            <div class="authorization-form">

                <form class="form-horizontal" action="login" method="post">
                    <fieldset>
                        <legend class="name-page">Вход в учетную запись</legend>

                        <!-- Error message -->
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">${errorMessage}</div>
                        </c:if>

                        <!-- Login input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userLogin">Учетная запись</label>
                            <div class="col-md-12">
                                <input id="userLogin" name="login" type="text" placeholder="введите логин"
                                       class="form-control input-md"
                                       value="">

                            </div>
                        </div>

                        <!-- Password input-->
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="userPassword">Пароль</label>
                            <div class="col-md-12">
                                <input id="userPassword" name="password" type="password" placeholder="введите пароль"
                                       class="form-control input-md" required=""
                                       value="">

                            </div>
                        </div>

                        <!-- Button -->
                        <div class=" form-group">
                            <label class="col-md-4 control-label" for="submit"></label>
                            <div class="col-md-12">
                                <button id="submit" name="loginButton"
                                        class="btn btn-success">Войти
                                </button>
                            </div>
                        </div>

                    </fieldset>
                </form>


            </div>
        </div>
    </div>
</div>


<%@include file="parts/footer.jsp" %>