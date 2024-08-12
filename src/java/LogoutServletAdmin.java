import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServletAdmin")
public class LogoutServletAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // do not create a new session if it doesn't exist

        if (session != null) {
            session.invalidate(); // invalidate the existing session
        }

        response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/Login.html"); // redirect to the login page after logout
    }
}
