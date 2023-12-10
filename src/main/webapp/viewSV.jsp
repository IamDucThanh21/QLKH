<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelBEAN.SinhVien" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sinh vieen</title>
</head>
<body>
    <h1>Danh sách sinh vien</h1>

    <%
        // Lấy danh sách các sinhvien từ request
        List<SinhVien> sinhviens = (List<SinhVien>) request.getAttribute("sinhviens");
    %>

    <% if (sinhviens != null && sinhviens.size() > 0) { %>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID video</th>
                    <th>Tên video</th>
                    <th>Đường dẫn</th>
                </tr>
            </thead>
            <tbody>
                <% for (SinhVien sinhvien : sinhviens) { %>
                    <tr>
                        <td><%= sinhvien.getIDSV() %></td>
                        <td><%= sinhvien.getName() %></td>
                        <td><%= sinhvien.getEmail() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Không có sinh vien  nào tham gia khóa học này.</p>
    <% } %>
</body>
</html>
