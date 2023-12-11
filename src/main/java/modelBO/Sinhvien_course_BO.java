package modelBO;

import modelDAO.Sinhvien_courseDAO;

import java.sql.SQLException;
import java.util.List;

public class Sinhvien_course_BO {
    public List<Integer> getListSVbyCourseID(int courseID){
        try {
            return Sinhvien_courseDAO.getStudentIdByCourseId(courseID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
