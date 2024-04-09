
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addUser")


public class Adduser extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
        

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            PreparedStatement ps = con.prepareStatement("INSERT INTO users(username, password,role) VALUES(?, ?,?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

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