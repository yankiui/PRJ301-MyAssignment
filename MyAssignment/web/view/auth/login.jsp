<%-- 
    Document   : login
    Created on : Nov 8, 2025, 5:57:08 PM
    Author     : duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    </head>
    <body>
        <div class="login-container">
            <h2>Đăng nhập</h2>
            <c:if test="${not empty requestScope.errorMessage}">
                <div class="error-message">
                    ${requestScope.errorMessage}
                </div>
            </c:if>
            
            <form action="login" method="POST">
                <label for="txtUsername">Username</label>
                <input type="text" name="username" id="txtUsername" required/> 
                
                <label for="txtPassword">Password</label>
                <input type="password" name="password" id="txtPassword" required/> 
                
                <input type="submit" id="btnLogin" value="Login"/>
            </form>
        </div>
    </body>
</html>
