import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/manage-item")
public class Manageitem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String itemId = request.getParameter("itemId");
        String itemName = request.getParameter("itemName");
        String itemDescription = request.getParameter("itemDescription");
        double startingBid = Double.parseDouble(request.getParameter("startingBid"));

        if (action.equals("ADD ITEM")) {

            try {
                // Get a database connection
                 Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            PreparedStatement ps = con.prepareStatement("INSERT INTO items(itemId,itemname,itemdescription,startingbid) VALUES(?, ?,?,?)");
            ps.setString(1, itemId);
            ps.setString(2, itemName);
            ps.setString(3, itemDescription);
            ps.setDouble(4, startingBid);

                // Execute the SQL statement
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item added successfully.");
                } else {
                    System.out.println("Failed to add item.");
                }
                // Close the database connection
                if (con != null) {
                    con.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Manageitem.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }
}