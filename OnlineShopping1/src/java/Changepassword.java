

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/updateUser")

public class Changepassword extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String username = request.getParameter("username");
        String password = request.getParameter("password");
       
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");
                String sql = "UPDATE users SET password = ? WHERE username= ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, password);
                ps.setString(2, username);
                
                // Execute the SQL statement
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println(" updated password successfully.");
                } else {
                    System.out.println("Failed to update password.");
                }
                        con.close();
                    }
                 catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(Changepassword.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Redirect back to admin page
        response.sendRedirect("admin.html");
    }
}
