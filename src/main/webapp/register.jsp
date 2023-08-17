<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Register</title>
    <meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="register.css">
    <link rel="preconnect" href="https://rsms.me/">
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <script src="Javascript/RegisterValidator.js"></script>
</head>


<body>
<form action="RegisterServlet" method="post" onsubmit="return check()">

    <div class="register">
        <div class="register_inside_div">

            <h1>Sign up</h1>
            <h6 class="registration-error"><c:out value="${ErrorMessage}"/></h6>

            <div class="input_container">
                <label for="username">Your Username</label>
                <input id="username" type="text" name="username" class="input_box">
            </div>

            <div class="input_container">
                <label for="email">Your Email</label>
                <input id="email" type="email" name="email" class="input_box">
            </div>

            <div class="input_container">
                <label for="password">Password</label>
                <input id="password" type="password" name="password" onkeyup="checkPassword()" class="input_box">
                <span id="msg"></span>
            </div>

            <div class="input_container">
                <label for="passwordRepeat">Repeat your password</label>
                <input id="passwordRepeat" type="password" name="passwordRepeat" onkeyup="checkPassword()"
                       class="input_box">
            </div>

            <button type="submit">Register</button>

            <p>Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>

</form>
</body>
</html>

