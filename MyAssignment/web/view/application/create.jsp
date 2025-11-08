<%-- 
    Document   : create
    Created on : Oct 11, 2025, 3:16:00 PM
    Author     : duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/create.css">
    </head>
    <body>
        <div class="form-container">
            <jsp:include page="../common/greeting.jsp"></jsp:include>
            <form action="${pageContext.request.contextPath}/request/create" method="post">
                <div id="panel" class="panel">
                    <h2>Đơn xin nghỉ phép</h2> <br/><!-- comment -->
                    User: ${sessionScope.auth.displayname} , 
                    Role: ${sessionScope.auth.roles[0].role} ,
                    Dep: phòng ${sessionScope.auth.employee.dept.dname} <br/><!-- comment -->
                    Từ ngày: <input type="date" name="datStart" id="datStart" required/><br/><!-- comment -->
                    Tới ngày: <input type="date" name="datEnd" id="datEnd" required/><br/>
                    Lý do: <br/>
                    <textarea name="reason" id ="reason" required></textarea><br/>
                </div>
                <input type="submit" id="btnSend" value="Gửi"/>
            </form>
        </div>           
    </body>
</html>
