import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EndBiddingServlet")
public class EndBiddingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String productId = request.getParameter("productId");

        BidderDetails bidderDetails = getHighestBidderDetails(productId);

        updateEndColumn(productId);

        sendEmailToBidder(bidderDetails);

        
        response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/AuctioneerListing.jsp");
    }

    private BidderDetails getHighestBidderDetails(String productId) {
        BidderDetails bidderDetails = null;
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String dbUser = "scott";
        String dbPassword = "scott";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Gettiong highest bidders details
            String selectQuery = "SELECT highes_bidders_name, email FROM products INNER JOIN users ON products.highes_bidders_name = users.username WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, productId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String highestBidderName = resultSet.getString("highes_bidders_name");
                    String bidderEmail = resultSet.getString("email");
                    bidderDetails = new BidderDetails(highestBidderName, bidderEmail);
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return bidderDetails;
    }

    private void updateEndColumn(String productId) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String dbUser = "scott";
        String dbPassword = "scott";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            
            String updateQuery = "UPDATE products SET end = 1 WHERE product_id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setString(1, productId);
                preparedStatement.executeUpdate();
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void sendEmailToBidder(BidderDetails bidderDetails) {
        if (bidderDetails != null) {
            String toEmail = bidderDetails.getEmail();
            String subject = "Auction Bidding Ended";
            String body = "Dear " + bidderDetails.getUsername() + ",\n\nThe bidding for the product has ended. Click the following link for more details:\n\nhttp://localhost:8080/WebApplication_TEST/Payment.jsp";

            
            sendEmail(toEmail, subject, body);
        }
    }

    private void sendEmail(String toEmail, String subject, String body) {
        final String username = "lautreccorpo@gmail.com";
        final String password = "fefgiucpvkixkxwz";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private class BidderDetails {
        private String username;
        private String email;

        public BidderDetails(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }
}
