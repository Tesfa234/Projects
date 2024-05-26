 import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/LoginServlet")

public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2", "root", "543852@Liduja");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
 
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String role = rs.getString("role");
                HttpSession session = request.getSession();
                session.setAttribute("role", role);
                if ("seller".equals(role)) {
                    response.sendRedirect("Sellerpage.html");
                } else if ("buyer".equals(role)) {
                    response.sendRedirect("Buyerpage.html");
                } 
                else if ("Admin".equals(role)) {
                    response.sendRedirect("Adminpage.html");
                }
                else {
                    response.sendRedirect("Loginpage.html");
                }
            } 
            else {
                PrintWriter out = response.getWriter();
              

                  
                out.println("<script type=\"text/javascript\">");
                out.println("window.alert(\"Login failed\");");
                out.println("</script>");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}