package modelDAO;

import modelBEAN.Course;
import util.DBUtil;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    Connection connection = DBUtil.getConnection();

    public CourseDAO() throws SQLException {
    }


    public List<Course> getAllCourses() throws SQLException {
        // Lấy danh sách các khóa học từ cơ sở dữ liệu
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM course");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Course course = new Course();
                course.setCourse_id(resultSet.getInt("course_id"));
                course.setCourse_name(resultSet.getString("course_name"));
                // Các trường khác tương tự

                courses.add(course);
            }
        }
        return courses;
    }
    public List<Course> getCoursesByTeacherId(int IDGV) throws SQLException {
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM course WHERE giangvien_id = ?");
        ) {
            statement.setInt(1, IDGV);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Course course = new Course();
                    course.setCourse_id(resultSet.getInt("course_id"));
                    course.setCourse_name(resultSet.getString("course_name"));
                    // Các trường khác tương tự

                    courses.add(course);
                }
            }
        }

        return courses;
    }

    public void addCourse(Course course) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO course (course_name, giangvien_id) VALUES (?, ?)")) {
            statement.setString(1, course.getCourse_name());
            statement.setInt(2, course.getIDGV());

            statement.executeUpdate();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void closeConnection() {
        DBUtil.closeConnection(connection);
    }

    // Các phương thức khác tương tự
}
