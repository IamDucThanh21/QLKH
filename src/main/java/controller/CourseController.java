package controller;

import modelBEAN.Course;
import modelBEAN.Video;
import modelDAO.CourseDAO;
import modelDAO.GiangVienDAO;
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
            // Hiển thị danh sách các video
            request.setAttribute("videos", videos);
            request.getServletContext().getRequestDispatcher("/viewcourse.jsp").forward(request, response);
        } else if (session.getAttribute("role").equals("sinhvien")) {
            CourseDAO courseDAO = null;
            try {
                courseDAO = new CourseDAO();
                Course course = courseDAO.getCoursesById(courseId);
                request.setAttribute("nameCourse", course.getCourse_name());
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
