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
        <title>Trang chủ</title>
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/home.css">
        
    </head>
    <body>
        
        <a href="${pageContext.request.contextPath}/request/logout" class="logout-button">
            Đăng xuất
        </a>
        
        <jsp:include page="../common/greeting.jsp"></jsp:include>
        
        <nav class="function-menu">
            <h2>Chức năng</h2>
            
            <c:if test="${sessionScope.auth.roles[0].id >= 1}">
                <a href="${pageContext.request.contextPath}/request/list">
                    Xem danh sách đơn
                </a>
            </c:if>
            
            <c:if test="${sessionScope.auth.roles[0].id eq 2 or sessionScope.auth.roles[0].id eq 3}">
                <a href="${pageContext.request.contextPath}/request/create">
                    Tạo đơn mới
                </a>
            </c:if>

        </nav>
    </body>
</html>