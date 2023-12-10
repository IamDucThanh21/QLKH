package controller;

import util.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Thực hiện xác thực người dùng (có thể sử dụng Service hoặc DAO)
        // Nếu xác thực thành công, chuyển hướng đến homepage tương ứng
        if (authenticateUser(email, password, role)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("role", role);
            System.out.println("Đăng nhập thành công");
            String redirectURL = getRedirectURL(role);
            response.sendRedirect(redirectURL);
        } else {
            // Xác thực không thành công, có thể hiển thị thông báo lỗi hoặc chuyển hướng đến trang đăng nhập lại
            System.out.println("Dang nhap khong thanh cong");
            response.sendRedirect("login.jsp");
        }
    }


    // Trong phương thức authenticateUser của servlet
    private boolean authenticateUser(String email, String password, String role) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        System.out.println("email: "+ email + " password: " + password + " role: " + role);

        try {
            connection = DBUtil.getConnection();

            String query = "";
            String tableName = "";

            switch (role) {
                case "sinhvien":
                    query = "SELECT * FROM sinhvien WHERE email=? AND password=?";
                    tableName = "sinhvien";
                    break;

                case "giangvien":
                    query = "SELECT * FROM giangvien WHERE email=? AND password=?";
                    tableName = "giangvien";
                    break;

                case "admin":
                    query = "SELECT * FROM admin WHERE email=? AND password=?";
                    tableName = "admin";
                    break;

                default:
                    return false; // Trả về false nếu role không hợp lệ
            }

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            // Kiểm tra xem người dùng có tồn tại trong bảng tương ứng không
            if (resultSet.next()) {
                // Người dùng tồn tại, bạn có thể lấy thông tin khác nếu cần
                // Ví dụ: String name = resultSet.getString("name");

////                 Lưu thông tin người dùng vào session
//                HttpSession session = request.getSession();
//                session.setAttribute("email", email);
////                session.setAttribute("role", role);

                return true;
            } else {
                // Người dùng không tồn tại hoặc thông tin đăng nhập không chính xác
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }  finally {
            DBUtil.closeConnection(connection);
            // Đóng các tài nguyên khác nếu cần
        }
    }


    private String getRedirectURL(String role) {
        if (!Objects.equals(role, "admin")) {
            if (Objects.equals(role, "giangvien")){
                return "giangvienController";
            }
            else {

                return  "sinhVienController";
            }
        } else {
            return "homeAD.jsp";
        }
    }
}
