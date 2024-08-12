import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.servlet.http.HttpSession;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OraclePreparedStatement;
/**
 *
 * @author AZAM
 */
public class Weee extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            PrintWriter out=response.getWriter();
            response.setContentType("text/html");
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con= (OracleConnection) DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:1521:ORCL","scott","scott");
            String e=request.getParameter("tbemail");
            String p=request.getParameter("tbpass");
            
            PreparedStatement ps=con.prepareStatement("SELECT EMAIL, USERNAME FROM auctioneers WHERE EMAIL=? AND PASSWORD=?");
            ps.setString(1,e);
            ps.setString(2, p);
            ResultSet rs=ps.executeQuery();
            
            if(rs.next()){
                 HttpSession session = request.getSession();
                session.setAttribute("tbemail", rs.getString("email"));
                session.setAttribute("username", rs.getString("username"));


                 response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp");
            }
            else{
                out.println("<div align = center><font color= red>LOGIN FAILED<br></font></div>");
                RequestDispatcher rd = request.getRequestDispatcher("http://localhost:8080/WebApplication_TEST/Admin/LoginAddProduct.html");
                    rd.include(request, response);
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(WorkLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
}