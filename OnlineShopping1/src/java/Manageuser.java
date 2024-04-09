import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UserManagementServlet")
public class Manageuser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Files</title>");
        out.println("<style>");
        out.println("table { width: 100%; border-collapse: collapse; text-align:center; }");
        out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }");
        out.println("button { padding: 8px 15px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }");
        out.println("button:hover { background-color: #0056b3; }");
        out.println("</style>");
        out.println("</head><body>");

        out.println("<h2>All Users:</h2>");
        out.println("<table border=\"1\"><tr><th>User name</th><th>Password</th><th>Role</th></tr>");

        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            // Execute SQL query to retrieve files
            stmt = con.createStatement();
            String sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);

            // Iterate through the result set and generate HTML table rows
            while (rs.next()) {
                String username= rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                out.println("<tr><td>" + username + "</td><td>" + password + "</td><td>" + role+ "</td></tr>");
            }
            out.println("</table><br><br>");
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error retrieving files</p>");
        }

        out.println("<a href=\"addUser.html\"><button> Add User</button></a><br><br>");
        out.println("<a href=\"updateUser.html\"><button>update password</button></a><br><br>");
        out.println("<a href=\"deleteUser.html\"><button>Delete User</button></a><br><br>");

        out.println("</body></html>");
    }
}
