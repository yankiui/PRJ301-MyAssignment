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
        <title>Application</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list.css">
    </head>
    <body>
        <jsp:include page="../common/greeting.jsp"></jsp:include>
        <table border="1px">
            <tr>
                <th>request id</th>
                <th>created by</th>
                <th>reason</th>
                <th>from</th>
                <th>to</th>
                <th>status</th>
                    <c:if test="${sessionScope.auth.roles[0].id < 3}">
                    <th>processed by</th>
                    </c:if>
            </tr>

            <c:forEach items="${requestScope.rfls}" var="r">
                <tr>
                    <td>${r.id}</td>
                    <td>${r.created_by.ename}</td>
                    <td>${r.reason}</td>
                    <td>${r.from}</td>
                    <td>${r.to}</td>

                    <td> <%-- Cột "Status" --%>
                        <c:if test="${sessionScope.auth.roles[0].id eq 3}">
                            ${r.status eq 0?"(processing)":
                              r.status eq 1?"approved":"rejected"
                            }
                        </c:if>
                        <c:if test="${sessionScope.auth.roles[0].id < 3}">
                            ${r.status eq 0?"processing":
                              r.status eq 1?"approved":"rejected"
                            }
                        </c:if>
                    </td>


                    <%-- Đây là cột "Processed by" (Hành động) --%>
                    <c:if test="${sessionScope.auth.roles[0].id < 3}">
                        <td>
                            <%-- A: Manager xem đơn của cấp dưới --%>
                            <c:if test="${sessionScope.auth.employee.id ne r.created_by.id}">
                                <c:if test="${r.processed_by eq null}">
                                    <a href="review?id=${r.id}&status=1">Approve</a>
                                    <a href="review?id=${r.id}&status=2">Reject</a>
                                </c:if>
                                <c:if test="${r.processed_by ne null}">
                                    ${r.processed_by.ename}, You can change to
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
                    </c:if> 

                </tr>
            </c:forEach>
        </table>
    </body>
</html>