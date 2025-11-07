<%-- 
    Document   : create
    Created on : Oct 11, 2025, 3:16:00 PM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* ----- Thân trang để căn giữa form ----- */
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    /* ----- Định dạng cho toàn bộ form ----- */
    form {
        width: 100%;
        max-width: 500px; /* Giới hạn chiều rộng tối đa của form */
    }

    /* ----- Khung chính của form (panel) ----- */
    .panel {
        background-color: white;
        padding: 25px;
        border-radius: 8px; /* Bo tròn các góc */
        box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Tạo hiệu ứng đổ bóng mềm */
        border: 1px solid #ddd;
    }

    .panel h2 {
        text-align: center;
        margin-top: 0;
        margin-bottom: 20px;
        color: #333;
    }

    /* ----- Định dạng chung cho các ô input và textarea ----- */
    .panel input[type="date"],
    .panel textarea {
        width: 100%;                /* ✅ Chìa khóa 1: Chiếm 100% chiều rộng của panel */
        padding: 10px;              /* Tạo khoảng đệm bên trong */
        margin-top: 5px;            /* Khoảng cách với text phía trên */
        margin-bottom: 15px;        /* Khoảng cách với phần tử bên dưới */
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 1rem;
        box-sizing: border-box;     /* ✅ Chìa khóa 2: Đảm bảo padding và border không làm tràn layout */
    }
    
    /* ----- Định dạng riêng cho ô textarea ----- */
    .panel textarea {
        height: 120px;
        resize: vertical; /* Chỉ cho phép người dùng kéo dãn theo chiều dọc */
    }

    /* ----- Định dạng cho nút Gửi ----- */
    #btnSend {
        width: 100%;
        padding: 12px;
        background-color: #007bff; /* Màu xanh dương hiện đại */
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer; /* Biến con trỏ thành hình bàn tay khi di chuột */
        transition: background-color 0.2s; /* Hiệu ứng chuyển màu mượt mà */
    }
    
    /* Hiệu ứng khi di chuột vào nút */
    #btnSend:hover {
        background-color: #0056b3;
    }
        </style>
    </head>
    <body>
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
    </body>
</html>
