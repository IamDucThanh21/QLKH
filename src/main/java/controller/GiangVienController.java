package controller;

import modelBEAN.Course;
import modelBEAN.GiangVien;
import modelDAO.CourseDAO;
import modelDAO.GiangVienDAO;
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

@WebServlet("/giangvienController")

public class GiangVienController  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin giảng viên từ session (đã đăng nhập)
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        System.out.println("email: "+ email);

        GiangVienDAO giangVienDAO;
        try {
            giangVienDAO = new GiangVienDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        CourseDAO courseDAO = null;
        try {
            courseDAO = new CourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int IDGV = 0;
        GiangVien giangVien = new GiangVien();
        try {
            IDGV = giangVienDAO.getIDGVByEmail(email);
            System.out.println("IDGV: "+ IDGV);
            giangVien = giangVienDAO.getGiangVienByID(IDGV);
            System.out.println("IDGV: "+ giangVien.getIDGV() + " Ten Giang vien: " + giangVien.getName() + " Email: " + giangVien.getEmail() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        List<Course> courses = null;
        try {
            courses = giangVienDAO.getCoursesByTeacherId(IDGV);

            System.out.println("Lay danh sach course thanh cong");

            for (Course course : courses) {
                System.out.println("ID khóa học: " + course.getCourse_id());
                System.out.println("Tên khóa học: " + course.getCourse_name());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("courses", courses);
        System.out.println("Dữ liệu đã được đặt vào request: " + courses);
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeGV.jsp");
        dispatcher.forward(request, response);

//        request.getServletContext().getRequestDispatcher("/homeGV.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("email");
        int IDGV = 0;
        GiangVien giangVien = new GiangVien();
        GiangVienDAO giangVienDAO = null;
        try {
            giangVienDAO = new GiangVienDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            IDGV = giangVienDAO.getIDGVByEmail(email);
            System.out.println("IDGV: " + IDGV);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Lấy thông tin khóa học từ form
        String courseName = request.getParameter("courseName");
        System.out.println("courseName: " + courseName);

        // ... Lấy thông tin khác nếu cần thiết

        // Gọi phương thức thêm khóa học
        CourseDAO courseDAO;
        try {
            courseDAO = new CourseDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Course course = new Course();
        course.setIDGV(IDGV);
        course.setCourse_name(courseName);
        try {
            courseDAO.addCourse(course);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        List<Course> courses = null;
        try {
            courses = giangVienDAO.getCoursesByTeacherId(IDGV);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("courses", courses);
        // Chuyển hướng hoặc forward đến trang homeGV.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("homeGV.jsp");
        dispatcher.forward(request, response);
    }
}
