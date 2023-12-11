package controller;

import modelBEAN.Course;
import modelBEAN.SinhVien;
import modelBEAN.Video;
import modelBO.SinhVienBO;
import modelBO.Sinhvien_course_BO;
import modelDAO.CourseDAO;
import modelDAO.GiangVienDAO;
import modelDAO.SinhVienDAO;
import modelDAO.VideoDAO;
import util.DBUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/courseController")
public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin giảng viên từ session (đã đăng nhập)
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        HttpSession session = request.getSession();
        List<Video> videos = getVideoByIDCourse(courseId);
        if(session.getAttribute("role").equals("giangvien")){
            request.setAttribute("videos", videos);
            request.getServletContext().getRequestDispatcher("/viewcourse.jsp").forward(request, response);
        } else if (session.getAttribute("role").equals("sinhvien")) {
            CourseDAO courseDAO = null;
            try {
                // Lấy khoá học theo id
                courseDAO = new CourseDAO();
                Course course = courseDAO.getCoursesById(courseId);

                Sinhvien_course_BO sinhvienCourseBo = new Sinhvien_course_BO();
                SinhVienBO sinhVienBO = new SinhVienBO();
                List<Integer> studentIDs = sinhvienCourseBo.getListSVbyCourseID(courseId);
                List<SinhVien> sinhViens = new ArrayList<>();
                for(int id : studentIDs){
                    System.out.println(id);
                    sinhViens.add(sinhVienBO.getSinhVienById(id));
                }

                request.setAttribute("sinhViens", sinhViens);
                request.setAttribute("course", course);
                request.setAttribute("videos", videos);
                request.getServletContext().getRequestDispatcher("/detailCourse.jsp").forward(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public List<Video> getVideoByIDCourse(int courseID){
        // Lấy danh sách các video cho khóa học
        VideoDAO videoDAO = null;
        try {
            videoDAO = new VideoDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Video> videos = null;
        try {
            videos = videoDAO.getAllVideoByCourseID(courseID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  videos;
    }
}
