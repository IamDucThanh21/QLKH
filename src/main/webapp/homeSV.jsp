<!-- student.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Homepage</title>
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
    <h1>Các khoá học của tôi</h1>
    <div class="menu">
        <c:forEach var="courseID" items="${coursesID}">
            <p>ID khóa học: ${courseID}</p>
        </c:forEach>
    </div>
</body>
</html>
