import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/bidSys")
public class bidSys extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bidderName = request.getParameter("bidderName");
        int newBid = Integer.parseInt(request.getParameter("bidAmount"));
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");

            // Fetch current bid details
            String query = "SELECT * FROM products WHERE product_id = " + productId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int currentBid = resultSet.getInt("current_bid");

                // Check if the new bid is higher
                if (newBid > currentBid) {
                    // Update the database with the new bid and bidder's name
                   String updateQuery = "UPDATE products SET current_bid = " + newBid + ", HIGHES_BIDDERS_NAME = '" + bidderName + "' WHERE product_id = " + productId;
                    statement.executeUpdate(updateQuery);

                        String successMessage = "Bid successfully placed!";
                    request.setAttribute("message", successMessage);

                    connection.close();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ProductPage.jsp?status=success&productId=" + productId);
                    dispatcher.forward(request, response);
                } else {
                    // Handle bid not higher
                     String failMessage = "Your bid must be higher than the current bid of $" + currentBid;
                    request.setAttribute("message", failMessage);

                    connection.close();

                    // Forward to ProductPage.jsp
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ProductPage.jsp?status=fail&productId=" + productId);
                    dispatcher.forward(request, response);
                }
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}