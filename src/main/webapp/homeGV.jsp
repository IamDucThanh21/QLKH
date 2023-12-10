<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="modelBEAN.Course" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Teacher Home</title>
    <!-- Add Bootstrap CSS link -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Danh sách các khoá học</h1>

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addCourseModal">
                    Thêm Khoá học
        </button>
        <%
            // Lấy danh sách các khóa học từ request
            List<Course> courses = (List<Course>) request.getAttribute("courses");
        %>

        <% if (courses != null && courses.size() > 0) { %>
            <table class="table table-bordered mt-3">
                <thead>
                    <tr>
                        <th>ID khóa học</th>
                        <th>Tên khóa học</th>
                        <th>Danh sách sinh viên</th>

                    </tr>
                </thead>
                <tbody>
                    <% for (Course course : courses) { %>
                        <tr>
                            <td><%= course.getCourse_id() %></td>
                            <td><a href="courseController?courseId=<%= course.getCourse_id() %>"><%= course.getCourse_name() %></a></td>
                            <td><a href="sinhvienController?courseId=<%= course.getCourse_id() %>">Xem danh sách</td>

                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>Không có khoa hoc nao de hien thi.</p>
        <% } %>
    </div>

     <%-- Add Course Modal --%>
            <div class="modal fade" id="addCourseModal" tabindex="-1" role="dialog" aria-labelledby="addCourseModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addCourseModalLabel">Thêm Khoá học</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <!-- Add Course Form -->
                            <form action="giangvienController" method="post">
                                <div class="form-group">
                                    <label for="courseName">Tên khóa học:</label>
                                    <input type="text" class="form-control" id="courseName" name="courseName" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Thêm</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    <!-- Add Bootstrap JS and Popper.js scripts (optional) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
