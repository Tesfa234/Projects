import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CartServlet")
public class Cart extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve selected item IDs from the request
        String[] selectedItems = request.getParameterValues("selectedItems");

        out.println("<html><head><title>Cart</title><style>\n"
                +"body{"
                + "display:flex;}"
                + "h2{"
                + "position:absolute;"
                + "left:40%;}"
                +".selected {"+
                "position:relative;\n"+
                "top:60px;\n"+
                "left:0px;\n"+
                "text-align:center;"+  
"                width: 150px;\n" +
                "height:210px;\n"+
                "margin-right:20px;"+
                "display:flex:\n"+              
"                padding: 20px;\n" +
"                background: #fff;\n" +
"                border-radius: 5px;\n" +
"                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);"
                + "text_align:center}" +
                "button{ "
                + "position:absolute;"
                + "top:60%;"
                + "background-color: #4CAF50;"
                + "color: white;"
                + "padding: 10px 20px;"
                + "border: none;"
                + "border-radius: 5px;"
                + "cursor: pointer;"
                + "}"
                + "button:hover {"
                + "background-color: #45a049;}"
                +
                "</style>"+
                "</head>"+
                "<body>");
        out.println("<h2>Items added to Cart:</h2>");

        if (selectedItems != null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");

                for (String itemId : selectedItems) {
                    String sql = "SELECT * FROM ittems WHERE itemid = ?";
                    PreparedStatement pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, itemId);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        out.print("<div class=\"selected\">");
                        out.println("  <h3>Item Details:</h3>");
                        out.println("  <p>Item ID: " + rs.getString("itemid") + "</p>");
                        out.println("  <p>Item Name: " + rs.getString("itemname") + "</p>");
                        out.println("  <p>Description: " + rs.getString("itemdescription") + "</p>");
                        out.println("  <p>Price: " + rs.getDouble("price") + "</p>");
                        out.println("</div>");

                        // Add more details as needed
                    }
                }
                        out.println("<a href=\"Transaction.html\"><button >BUY</button></a>");


                con.close();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                out.println("An error occurred while retrieving item details.");
            }
        } else {
            out.println("<p>No items selected.</p>");
        }
        
        out.println("</body></html>");
    }
}
