<%-- 
    Document   : greeting
    Created on : Nov 8, 2025, 5:46:39 PM
    Author     : duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="greeting-container">
    <c:if test="${sessionScope.auth ne null}">
        Session of: <strong>${sessionScope.auth.displayname}</strong>
        <br/>
        Employee: ${sessionScope.auth.employee.id} - ${sessionScope.auth.employee.ename}
    </c:if>
    <c:if test="${sessionScope.auth eq null}">
        You are not logged in yet!
    </c:if>
</div>