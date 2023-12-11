<%@ page import="modelBEAN.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="modelBEAN.Video" %>
<%@ page import="modelBEAN.SinhVien" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12/10/2023
  Time: 7:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi tiết khoá học</title>
    <style>
        h1 {
            text-align: center;
        }
        .menu {
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
        }

        .menu a {
            text-decoration: none;
            color: #000;
            padding: 10px 0;
            font-size: larger;
            align-self: center;
        }

        .menu a:hover {
            color: #ff0000;
        }

        .menu p {
            font-size: larger;
            align-self: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%Course course = (Course) request.getAttribute("course");
    List<Video> videos = (List<Video>) request.getAttribute("videos");%>
    <h1>Khoá học <%=course.getCourse_name()%></h1>
    <div class="menu">
        <% if (videos != null && videos.size() > 0) {
            int i = 1;
            for (Video video : videos) {
        %>
            <a href="">Bài <%=i++%>. <%=video.getVideo_title()%><a>
        <% }
        }
        else { %>
            <p>Không có video nao de hien thi.</p>
        <% } %>
    </div>
    <h2>Danh sách sinh viên</h2>
    <br>
    <%
        List<SinhVien> sinhViens = (List<SinhVien>) request.getAttribute("sinhViens");
        for(SinhVien sinhVien : sinhViens){
            %>
                <p><%=sinhVien.getName()%></p>
                <br>
            <%
        }
    %>

</body>
</html>
