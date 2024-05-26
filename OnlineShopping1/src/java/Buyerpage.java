import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ViewItemServlet")
public class Buyerpage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Cart</title><style>\n"
                +"body{"
               
                + "form{"
                + "height:100%;"
                + "width:100%;"
                 + "display:flex;"
                + "justify-coontent:space-around;}"
                + "h2{}"
                +".selected {"
                + "height:60%;"
                + "width:20%;"
                + "border:1px solid #ccc;"
                + "border-radius:20px;"
                + "box-shadow:0 0 10px rgba(0,0,0,1.0);"
                + "margin-bottom:20px;"
                + "margin-left:20px;"
                + "text-align:center;"
                + "margin-top:20px;}"+
                
                

                "button{"
                + "height:7%;"
                + "width:40%;"
                + "border:none;"
                + "border-radius:15px;"
                + "background-color:#007bff;}"
               
                + "button:hover {"
               + "height:9%;"
                + "width:45%;"
                + "border:none;"
                + "border-radius:15px;"
                + "background-color:#0056b3;}"
  
                +"</style>"+
                "</head>"+
                "<body>");
         out.println("<form action=\"cartServlet\" method=\"post\">");
        

        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

            // Execute SQL query to retrieve files
            stmt = con.createStatement();
            String sql = "SELECT itemid,itemname,itemdescription,price,amount,sellername,email FROM ittems";
            rs = stmt.executeQuery(sql);

            // Iterate through the result set and generate HTML table rows with checkboxes for selecting items
            while (rs.next()) {
                    
                        out.print("<div class=\"selected\">");
                        out.println("  <h3>Item Details:</h3>");
                        out.println("  <p>ITEM ID: " + rs.getString("itemid") + "</p>");
                        out.println("  <p>ITEM NAME: " + rs.getString("itemname") + "</p>");
                        out.println("  <p>DESCRIPTION: " + rs.getString("itemdescription") + "</p>");
                        out.println("  <p>PRICE: " + rs.getDouble("price") + "</p>");
                        out.println("  <p>AMOUNT: " + rs.getString("amount") +"  avaliable"+ "</p>");
                        out.println("  <p>SELLER NAME: " + rs.getString("sellername") + "</p>");
                        out.println("  <p>EMAIL: " + rs.getString("email") + "</p>");
                        out.println("<button>Add to Cart</button>");

                        out.println("</div>");

                        // Add more details as needed
                    }
      
           // Submit button to add selected items to cart
            out.println("</form>");
            
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("<p>Error retrieving files</p>");
        }

        out.println("</body></html>");
    }
}
