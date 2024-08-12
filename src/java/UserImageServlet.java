/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;


/**
 *
 * @author AZAM
 */
@MultipartConfig(maxFileSize = 16177215) // for max 16 MB images

public class UserImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // Assuming you have a parameter for the username

        Connection conn = null;

        try {
            // Register JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");

            // Insert the image into the users table
             if (conn != null) {
        // Insert the image into the users table
        String sql = "UPDATE users SET users_image = ? WHERE username = ?";
        PreparedStatement statement = conn.prepareStatement(sql);

        Part filePart = request.getPart("image"); 
        
        // Check if the filePart is not null
        if (filePart != null) {
            InputStream fileContent = filePart.getInputStream();
            long fileSize = filePart.getSize();
            statement.setBinaryStream(1, fileContent, (int) fileSize);

            statement.setString(2, username);

            int rowsUpdated = statement.executeUpdate();

            // Check if the rowsUpdated is greater than 0
            if (rowsUpdated > 0) {
                response.sendRedirect("http://localhost:8080/WebApplication_TEST/UserProfile.jsp?status=success");
            } else {
                response.sendRedirect("http://localhost:8080/WebApplication_TEST/UserProfile.jsp?status=failed");
            }
        } else {
            response.sendRedirect("http://localhost:8080/WebApplication_TEST/UserProfile.jsp?status=fileNull");
        }
    } else {
        // Handle the case where the connection is null
        response.sendRedirect("http://localhost:8080/WebApplication_TEST/UserProfile.jsp?status=connectionNull");
    }
} catch (SQLException | ClassNotFoundException ex) {
    Logger.getLogger(UserImageServlet.class.getName()).log(Level.SEVERE, null, ex);
} finally {
    // Ensure that the connection is closed in the case of an exception or successful execution
    try {
        if (conn != null) {
            conn.close();
        }
    } catch (SQLException ex) {
        Logger.getLogger(UserImageServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }
}