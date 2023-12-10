package controller;

import modelBEAN.Course;
import modelBEAN.GiangVien;
import modelBEAN.SinhVien;
import modelDAO.CourseDAO;
import modelDAO.GiangVienDAO;
import modelDAO.SinhVienDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/sinhVienController")

public class SinhVienController extends HttpServlet {
    private String email;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin sinh viên từ session (đã đăng nhập)
        HttpSession session = request.getSession();
        this.email = (String) session.getAttribute("email");
        String action = "";


            List<Course> myCourses = getMycourse();
            request.setAttribute("myCourses", myCourses);
            System.out.println("Dữ liệu đã được đặt vào request: " + myCourses);
            RequestDispatcher dispatcher = request.getRequestDispatcher("homeSV.jsp");
            dispatcher.forward(request, response);

        action = request.getParameter("courseID");
        System.out.println(action);
    }
    public List<Course> getMycourse(){
        SinhVienDAO sinhVienDAO;
        try {
            sinhVienDAO = new SinhVienDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CourseDAO courseDAO = null;
        try {
            courseDAO = new CourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int IDSV = 0;
        SinhVien sinhVien = new SinhVien();
        try {
            IDSV = sinhVienDAO.getIDSVByEmail(this.email);
            System.out.println("IDGV: "+ IDSV);
            sinhVien = sinhVienDAO.getSinhVienByID(IDSV);
            System.out.println("IDGV: "+ sinhVien.getIDSV() + " Ten sinh vien: " + sinhVien.getName() + " Email: " + sinhVien.getEmail() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Integer> coursesID = null;
        try {
            coursesID = sinhVienDAO.getCoursesByStudentId(IDSV);

            System.out.println("Lay danh sach course thanh cong");

            for (int course : coursesID) {
                System.out.println("ID khóa học: " + course);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Course> myCourses = new ArrayList<>();
        try {
            List<Course> courses = courseDAO.getAllCourses();
            for(int courseId : coursesID){
                myCourses.add(courses.get(courseId - 1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  myCourses;
    }
    public void responseCourse(){

    }
}
