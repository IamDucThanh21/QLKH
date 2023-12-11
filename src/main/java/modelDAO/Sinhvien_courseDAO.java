package modelDAO;

import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sinhvien_courseDAO {
    public static List<Integer> getStudentIdByCourseId(int courseID) throws SQLException {
        Connection connection = DBUtil.getConnection();
        List<Integer> studentID = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM sinhvien_course WHERE course_id = ?;");
        ) {
            statement.setInt(1, courseID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    studentID.add(resultSet.getInt("sinhvien_id"));
                    // Các trường khác tương tự
                }
            }catch (SQLException e){
                System.err.println("Lỗi khi lấy sinh viên: " + e.getMessage());
            }

        }
        return studentID;
    }
}
