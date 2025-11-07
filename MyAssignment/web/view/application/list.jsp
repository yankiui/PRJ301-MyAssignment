<%-- 
    Document   : list
    Created on : Nov 8, 2025, 5:36:31 AM
    Author     : duong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách đơn</title>
    </head>
    <body>
        
        <table border="1px">
            <tr>
                <td>request id</td>
                <td>created by</td>
                <td>reason</td>
                <td>from</td>
                <td>to</td>
                
                <%-- 1. Cột "status" - LUÔN LUÔN HIỂN THỊ --%>
                <td>status</td>
                
                <%-- 2. Chỉ hiển thị tiêu đề "processed by" nếu là Manager/Leader --%>
                <c:if test="${sessionScope.auth.roles[0].id < 3}">
                    <td>processed by</td>
                </c:if>
            </tr>
            
            <c:forEach items="${requestScope.rfls}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.created_by.ename}</td>
                    <td>${r.reason}</td>
                    <td>${r.from}</td>
                    <td>${r.to}</td>

                    <%-- 1. Cột "status" - LUÔN LUÔN HIỂN THỊ --%>
                    <td>
                        <%-- 
                          Nếu người xem là Employee (role 3), chỉ hiển thị trạng thái
                          mà không hiển thị "processing" 
                        --%>
                        <c:if test="${sessionScope.auth.roles[0].id eq 3}">
                            ${r.status eq 0?"(chờ xử lý)":
                              r.status eq 1?"approved":"rejected"
                            }
                        </c:if>
                        
                        <%-- Nếu là Manager (role < 3), hiển thị đầy đủ --%>
                        <c:if test="${sessionScope.auth.roles[0].id < 3}">
                            ${r.status eq 0?"processing":
                              r.status eq 1?"approved":"rejected"
                            }
                        </c:if>
                    </td>
                    
                    <%-- 2. Chỉ hiển thị cột "processed by" nếu là Manager/Leader --%>
                    <c:if test="${sessionScope.auth.roles[0].id < 3}">
                        <td>
                            <%-- A: Manager xem đơn của cấp dưới --%>
                            <c:if test="${sessionScope.auth.employee.id ne r.created_by.id}">
                                <c:if test="${r.processed_by eq null}">
                                    <a href="review?id=${r.id}&status=1">Approve</a>
                                    <a href="review?id=${r.id}&status=2">Reject</a>
                                </c:if>
                                <c:if test="${r.processed_by ne null}">
                                    ${r.processed_by.ename}, bạn có thể đổi thành
                                    <c:if test="${r.status eq 1}">
                                        <a href="review?id=${r.id}&status=2">Rejected</a>
                                    </c:if>
                                    <c:if test="${r.status eq 2}">
                                        <a href="review?id=${r.id}&status=1">Approved</a>
                                    </c:if>
                                </c:if>
                            </c:if>

                            <%-- B: Manager xem đơn của chính mình --%>
                            <c:if test="${sessionScope.auth.employee.id eq r.created_by.id}">
                                <c:if test="${r.processed_by ne null}">
                                    ${r.processed_by.ename}
                                </c:if>
                                <c:if test="${r.processed_by eq null}">
                                    processing
                                </c:if>
                            </c:if>
                        </td>
                    </c:if> <%-- Kết thúc khối ẩn "processed by" --%>
                    
                </tr>
            </c:forEach>
        </table>
    </body>
</html>