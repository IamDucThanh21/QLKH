<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelBEAN.Video" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách các video</title>
</head>
<body>
    <h1>Danh sách các video</h1>

    <%
        // Lấy danh sách các video từ request
        List<Video> videos = (List<Video>) request.getAttribute("videos");
    %>

    <% if (videos != null && videos.size() > 0) { %>
        <table class="table table-bordered mt-3">
            <thead>
                <tr>
                    <th>ID video</th>
                    <th>Tên video</th>
                    <th>Đường dẫn</th>
                </tr>
            </thead>
            <tbody>
                <% for (Video video : videos) { %>
                    <tr>
                        <td><%= video.getIDVD() %></td>
                        <td><%= video.getVideo_title() %></td>
                        <td><%= video.getFile_path() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } else { %>
        <p>Không có video nào cho khóa học này.</p>
    <% } %>
</body>
</html>
