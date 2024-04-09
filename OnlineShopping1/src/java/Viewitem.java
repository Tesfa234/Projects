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


public class Viewitem extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Files</title>");
out.println("<style>");
out.println("table { width: 100%; border-collapse: collapse; }");
out.println("th, td { padding: 8px; text-align: left; border-bottom: 1px solid #ddd; }");
out.println("th { background-color: #f2f2f2; }");
out.println("</style>");
out.println("</head><body>");

        out.println("<h2>ITEMS:</h2>");
        out.println("<table border=\"1\"><tr><th>Item Id</th><th>Item name</th><th>Description</th><th>Price</th><th>Amount</th><th>Seller ID</th><th>Seller Name</th><th>Seller Phone</th><th>Seller Email</th></tr>");

        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jobportal2","root", "543852@Liduja");


            // Execute SQL query to retrieve files
            stmt = con.createStatement();
            String sql = "SELECT * FROM ittems";
            rs = stmt.executeQuery(sql);

            // Iterate through the result set and generate HTML table rows
            while (rs.next()) {
                String itemid= rs.getString("itemid");
                String itemname = rs.getString("itemname");
                String itemdescription = rs.getString("itemdescription");
                double itemprice = Double.parseDouble(rs.getString("price"));
                String amount=rs.getString("amount");
                String sellerid = rs.getString("sellerid");
                String sellername = rs.getString("sellername");
                String sellerphone = rs.getString("phone");
                String selleremail = rs.getString("email");
                

                out.println("<tr><td>" + itemid + "</td><td>" + itemname + "</td><td>"  + itemdescription+ "</td><td>"  + itemprice+ "</td><td>"  + amount + "</td><td>"  + sellerid+ "</td><td>"  + sellername+ "</td><td>"  + sellerphone + "</td><td>"  + selleremail + "</td></tr>");
            }
            out.println("</table><br><br>");
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

