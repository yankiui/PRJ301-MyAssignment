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
        <c:if test="${sessionScope.auth ne null}" var = "a">
            Hello ${sessionScope.auth.displayname}!
        </c:if>
        <c:if test="${sessionScope.auth eq null}">
            You are not yet logged in!
        </c:if>
        <c:if test="${sessionScope.auth.rid eq 1}">
            <br/>
            You are Division leader <br/>
            You can do these function: <br/>
            <a href="${pageContext.request.contextPath}/view/application/create.jsp">Watch</a>
        </c:if>
        <c:if test="${sessionScope.auth.rid eq 2}">
            <br/>
            You are Group leader <br/>
            You can do these function: <br/>
            <a href="index.html">Watch</a>
        </c:if>
        <c:if test="${sessionScope.auth.rid eq 3}">
            <br/>
            You are Division leader <br/>
            You can do these function: <br/>
            <a href="index.html">Watch</a>
        </c:if>

    </body>
</html>
