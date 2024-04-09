import java.sql.DriverManager;
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
@WebServlet("/UpdateItemServlet")


public class Updateitem extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemId = request.getParameter("itemId");
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        double startingBid = Double.parseDouble(request.getParameter("startingBid"));
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");
                String sql = "UPDATE items SET itemname = ?, itemdescription = ?, startingbid = ? WHERE itemId = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, itemName);
                ps.setString(2, itemDescription);
                ps.setDouble(3, startingBid);
                ps.setString(4, itemId);

                // Execute the SQL statement
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item updated successfully.");
                } else {
                    System.out.println("Failed to update item.");
                }
                        con.close();
                    }
                 catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
            Logger.getLogger(Updateitem.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Redirect back to admin page
        response.sendRedirect("admin.html");
    }
}
