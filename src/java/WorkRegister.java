import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;

public class WorkRegister extends HttpServlet 
{
    String vEmail, vName,vPass,vUser;
    
    // STEP 1: DECLARING ORACLE OBJECTS
    OracleConnection oconn;
    OraclePreparedStatement ops;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {       
            vEmail = request.getParameter("tbemail");
            vName = request.getParameter("tbpass");
            vPass=request.getParameter("tbuser");
            vUser=request.getParameter("tbname");
            try 
            {
                // STEP 2: REGISTERING THE ORACLE DRIVER WITH THIS SERVLEt  
                DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                
                // STEP 3: INSTANTIATING THE ORACLE CONNECTION OBJECT
                oconn = (OracleConnection) DriverManager.getConnection
        ("jdbc:oracle:thin:@localhost:1521:ORCL","scott","scott");
                
                // STEP 4: INSTANTIATING THE ORACLE PREPARED STATEMENT OBJECT
                ops = (OraclePreparedStatement) 
                        oconn.prepareCall
        ("INSERT INTO users(EMAIL, USERNAME, NAME, PASSWORD) values(?,?,?,?)");
      
                //STEP 6: FILLING UP THE BLANK QUERY PARAMETERS (?)
                ops.setString(1, vEmail);
                ops.setString(3, vUser);
                ops.setString(4, vName);
                ops.setString(2, vPass);
                
                // STEP 7: EXECUTING THE QUERY
                int x = ops.executeUpdate();
                
                if (x > 0) {
                     // Record inserted successfully, redirect to Login.html
                    RequestDispatcher rd = request.getRequestDispatcher("Login.html");
                    rd.forward(request, response);
                } 
                else {
                       out.println("<h2 style='color:brown'> Record could not be added...");
                }

               
                //STEP 8: CLOSING THE ORACLE OBJECTS
                ops.close();
                oconn.close();
                
            } 
            
                //STEP 9: FORMATTING THE CATCH CLAUSE
            catch (SQLException ex) {
    Logger.getLogger(WorkRegister.class.getName()).log(Level.SEVERE, null, ex);
    if (ex instanceof SQLIntegrityConstraintViolationException) {
        // Unique constraint violation (username already in use)
        response.sendRedirect("Register.html?message=This%20username%20is%20already%20in%20use.%20Please%20choose%20a%20different%20one.");
        return;  // Add this line to exit the method and prevent further processing
    } else {
        // Other SQL exception
        out.println("<h2 style='color:red'> Error is : " + ex.toString() + "</h2>");
    }
}
            
            out.println("</head>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}