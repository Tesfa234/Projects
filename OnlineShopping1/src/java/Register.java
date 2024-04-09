import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/RegisterServlet")

public class Register extends HttpServlet {
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email=request.getParameter("email");
            String phone=request.getParameter("phone");
            String role = request.getParameter("role");
        

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password,email,phone,role) VALUES(?,?,?,?,?)");
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setString(4,email);
            ps.setString(5, phone);
            ps.setString(6, role);

            int i = ps.executeUpdate();
            if(i > 0) {
                response.sendRedirect("login.jsp");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Registration failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}