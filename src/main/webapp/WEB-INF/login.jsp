<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="parts/header.jsp" %>
<div><img src="${pageContext.request.contextPath}/image/quest_fon.png" class="fon" alt="..."></div>

<div class="authorization-form">

</div>



<div class="container">
    <form class="form-horizontal" action="login" method="post">
        <fieldset>
            <!-- Form Name -->
            <legend>Login form</legend>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userLogin">Login</label>
                <div class="col-md-4">
                    <input id="userLogin" name="login" type="text" placeholder="set login" class="form-control input-md"
                           required=""
                           value="Carl">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="userPassword">Password</label>
                <div class="col-md-4">
                    <input id="userPassword" name="password" type="password" placeholder="pass req"
                           class="form-control input-md" required=""
                           value="admin">

                </div>
            </div>

            <!-- Button -->
            <div class=" form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="loginButton"
                            class="btn btn-success">Войти
                    </button>
                </div>
            </div>

        </fieldset>
    </form>
</div>

<%@include file="parts/footer.jsp" %>