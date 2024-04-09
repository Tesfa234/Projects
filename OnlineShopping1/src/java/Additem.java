
import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/AddItemServlet")

public class Additem extends HttpServlet {

  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String itemname = request.getParameter("itemName");
        String itemdescription = request.getParameter("itemDescription");
        double price= Double.parseDouble(request.getParameter("startingBid"));
        String amount = request.getParameter("amount");
        String sellerid = request.getParameter("sellerid");
        String sellername = request.getParameter("sellername");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

            try {
                // Get a database connection
                 Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            PreparedStatement ps = con.prepareStatement("INSERT INTO ittems(itemname,itemdescription,price,amount,sellerid,sellername,phone,email) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(2, itemname);
            ps.setString(3, itemdescription);
            ps.setDouble(4, price);
            ps.setString(5,amount);
            ps.setString(6,sellerid);
            ps.setString(7,sellername);
            ps.setString(8,phone);
            ps.setString(9,email);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Item added successfully.");
                } else {
                    System.out.println("Failed to add item.");
                }
            
           
                   con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Manageitem.class.getName()).log(Level.SEVERE, null, ex);
            } 

        }
    }