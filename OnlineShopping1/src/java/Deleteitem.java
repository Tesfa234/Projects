
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/DeleteItemServlet")

public class Deleteitem extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemname=request.getParameter("itemname");
        String itemId = request.getParameter("itemId");

            try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

                String sql = "DELETE FROM items WHERE itemId = ? OR itemname =?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, itemId);
                ps.setString(2, itemname);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item deleted successfully.");
                } else {
                    System.out.println("Failed to delete item.");
                }
                                    if (con != null) {
                        con.close();
                    }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(Deleteitem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("admin.html");
    }
    }