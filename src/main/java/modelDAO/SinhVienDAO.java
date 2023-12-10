package modelDAO;

import modelBEAN.Course;
import modelBEAN.GiangVien;
import modelBEAN.SinhVien;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SinhVienDAO {

    Connection connection = DBUtil.getConnection();

    public SinhVienDAO() throws SQLException {
    }

    public int getIDSVByEmail(String email) throws SQLException {
        System.out.println(email);
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM sinhvien WHERE email = ?"))
        {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return resultSet.getInt("sinhvien_id");
                } else {
                    // Sinh viên không tồn tại
                    return -1;
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy IDSV: " + e.getMessage());
            throw e;
        }
    }

    public SinhVien getSVByIDSV(int IDSV) throws  SQLException{
        SinhVien sinhvien = null;

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM sinhvien WHERE sinhvien_id = ?"))
        {
            statement.setInt(1, IDSV);

            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    sinhvien = new SinhVien();
                    sinhvien.setIDSV(resultSet.getInt("sinhvien_id"));
                    sinhvien.setName(resultSet.getString("ho_ten"));
                    sinhvien.setEmail(resultSet.getString("email"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy IDSV: " + e.getMessage());
            throw e;
        }
        return sinhvien;
    }
    public List<Integer> getCoursesByStudentId(int IDSV) throws SQLException {
        List<Integer> coursesID = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM sinhvien_course WHERE sinhvien_id = ?");
        ) {
            statement.setInt(1, IDSV);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    coursesID.add(resultSet.getInt("course_id"));
                    // Các trường khác tương tự
                }
            }catch (SQLException e){
                System.out.println("IDSV: " + IDSV);
                System.err.println("Lỗi khi lấy course: " + e.getMessage());
            }

        }
        return coursesID;
    }
    public List<SinhVien> getAllSinhVienByCourseID(int courseID) throws SQLException{
        List<SinhVien> sinhViens = new ArrayList<>();

        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM sinhvien_course WHERE course_id = ?")
        ) {
            statement.setInt(1, courseID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SinhVien sinhVien = new SinhVien();
                    sinhVien.setIDSV(resultSet.getInt("sinhvien_id"));

                    sinhVien = getSVByIDSV(sinhVien.getIDSV());

                    sinhViens.add(sinhVien);
                }
            }
        }
        return sinhViens;
    }
}
