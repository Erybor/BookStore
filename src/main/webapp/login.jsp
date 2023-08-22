<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>

<html>
<head>
    <title>Log in</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/register.css">
    <link rel="preconnect" href="https://rsms.me/">
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">

</head>

<body>
<form action="LoginServlet" method="post">

    <div class="register">
        <div class="register_inside_div">

            <h1>Log in</h1>

            <h6 class="login-error" style="color: darkred; font-size: 14px"><c:out value="${ErrorMessage}"/></h6>

            <h6 class="login-error" style="color: darkred; font-size: 14px"><c:out value="${sessionScope.authenticationError}"/></h6>
            <c:set var="authenticationError" value="" scope="session"/>

            <div class="input_container">
                <label for="username">Your Username</label>
                <input id="username" type="text" name="username" class="input_box">
            </div>

            <div class="input_container">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" onkeyup="checkPassword()" class="input_box">
                <span id="msg"></span>
            </div>

            <button type="submit">Log in</button>

            <p>Don't have an account? <a href="${pageContext.request.contextPath}/Register">Register here</a></p>
        </div>
    </div>

</form>
</body>
</html>

