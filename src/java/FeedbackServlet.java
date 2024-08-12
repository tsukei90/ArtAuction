/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String improvementComments = request.getParameter("improvementComments");


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
        String dbUsername = "scott";
        String dbPassword = "scott";

      
        String sql = "INSERT INTO feedback_table (username, improvement_comments) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, improvementComments); 
       
            pstmt.executeUpdate();
     
            response.sendRedirect("thankyou.html");
        } catch (SQLException e) {
           
            e.printStackTrace(); 
            response.sendRedirect("error.html"); 
        }
    }
}
