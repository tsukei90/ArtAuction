import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215) // for max 16 MB images

public class ImgProductServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sellerName = request.getParameter("sellerName"); 
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        Connection conn = null;

        try {
            // Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");

            // Insert the product into the database
            String sql = "INSERT INTO products (product_id, name, description, image, sellers_name) VALUES (PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, new String[] { "product_id" });
            statement.setString(1, productName);
            statement.setString(2, productDescription);

            Part filePart = request.getPart("productImage"); // Retrieves <input type="file" name="productImage">
            InputStream fileContent = filePart.getInputStream();
            long fileSize = filePart.getSize();
            statement.setBinaryStream(3, fileContent, (int) fileSize);
            
            statement.setString(4, sellerName);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int productId = generatedKeys.getInt(1);
                    response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp?status=success&productId=" + productId);
                } else {
                    response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp?status=failed");
                }
            } else {
                response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp?status=failed");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ImgProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
