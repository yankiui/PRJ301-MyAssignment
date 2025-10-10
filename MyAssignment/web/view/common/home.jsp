<%-- 
    Document   : home
    Created on : Oct 10, 2025, 4:09:31 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.auth ne null}">
            Hello ${sessionScope.auth.displayname}!
        </c:if>
        <c:if test="${sessionScope.auth eq null}">
            You are not yet logged in!
        </c:if>    
    </body>
</html>
