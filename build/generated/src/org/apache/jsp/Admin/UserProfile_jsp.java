package org.apache.jsp.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.DriverManager;
import java.util.Base64;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public final class UserProfile_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

   
    String username = (String) session.getAttribute("username");

    // Database connection details
    String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String user = "scott";
    String password = "scott";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
     

    try {
           // Establish the database connection
           Class.forName("oracle.jdbc.driver.OracleDriver");
           connection = DriverManager.getConnection(url, user, password);

           // Execute the SQL query to fetch email and name for the logged-in user
           String query = "SELECT email, name FROM users WHERE username = '" + username + "'";
           statement = connection.createStatement();
           resultSet = statement.executeQuery(query);

           // Check if the result set has any rows
           if (resultSet.next()) {
               String email = resultSet.getString("email");
               String name = resultSet.getString("name");

               // Set the retrieved values to the session attributes
               session.setAttribute("email", email);
               session.setAttribute("name", name);
           }
       } catch (Exception e) {
           // Handle exceptions (log them, display an error message, etc.)
           e.printStackTrace();
       } finally {
           // Close the database resources in the reverse order of their creation
           if (resultSet != null) resultSet.close();
           if (statement != null) statement.close();
           if (connection != null) connection.close();
       }
   
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>User Profile</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"UserProfileStyle.css\">\n");
      out.write("    <script src=\"https://kit.fontawesome.com/e457018090.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <script>\n");
      out.write("        function imgLoad(event) {\n");
      out.write("            var image = document.getElementById('imgout');\n");
      out.write("            fname = event.target.files[0].name;\n");
      out.write("            ext = fname.replace(/^.*\\./, '');\n");
      out.write("            if (ext === 'png' || ext === 'jpg' || ext === 'jpeg') {\n");
      out.write("                image.src = URL.createObjectURL(event.target.files[0]);\n");
      out.write("                document.getElementById(\"imgerr\").innerHTML = \"\";\n");
      out.write("                return true;\n");
      out.write("            } else {\n");
      out.write("                document.getElementById(\"imgerr\").innerHTML = \"Only png, jpg, jpeg types allowed\";\n");
      out.write("                image.src = \"null/\";\n");
      out.write("                return false;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <header>\n");
      out.write("        <div class=\"hero\">\n");
      out.write("            <nav>\n");
      out.write("            <h2><a href=\"welcome.jsp\" style=\"color: #c5bc9f; text-decoration: none; font-family: Aboreto\">Lautrec</a></h2>\n");
      out.write("            <ul>\n");
      out.write("                \n");
      out.write("                <li><a href=\"http://localhost:8080/WebApplication_TEST/PrintFromDatabaseImg.jsp\">Auctions</a></li>\n");
      out.write("                <li><a href=\"http://localhost:8080/WebApplication_TEST/AboutUs.html\">About Us</a></li>\n");
      out.write("                <li><a href=\"http://localhost:8080/WebApplication_TEST/Feedback/feedback.html\">Feedback</a></li>\n");
      out.write("                \n");
      out.write("            </ul>\n");
      out.write("            \n");
      out.write("      \n");
      out.write("            <i class=\"fa-solid fa-user\" style=\"color: #c5bc9f; margin-left: 30px;\" onclick=\"toggleMenu()\" ></i> \n");
      out.write("\n");
      out.write("            <div class=\"sub-menu-wrap\" id=\"subMenu\">\n");
      out.write("                <div class=\"sub-menu\">\n");
      out.write("                    <div class=\"user-info\">\n");
      out.write("                        <h3>");
      out.print( username );
      out.write("</h3>\n");
      out.write("                    </div>\n");
      out.write("                        <hr>\n");
      out.write("\n");
      out.write("                    <a href=\"http://localhost:8080/WebApplication_TEST/UserProfile.jsp\" class=\"sub-menu-link\">\n");
      out.write("                        <i class=\"fa-solid fa-user\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                        <p>Edit Profile</p>\n");
      out.write("                        <span>></span>\n");
      out.write("                    </a>  \n");
      out.write("\n");
      out.write("                    <a href=\"Help\" class=\"sub-menu-link\">\n");
      out.write("                        <i class=\"fa-solid fa-circle-question\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                        <p>Help & Support</p>\n");
      out.write("                        <span>></span>\n");
      out.write("                    </a>    \n");
      out.write("\n");
      out.write("                     <a href=\"LogoutServlet\"class=\"sub-menu-link\">\n");
      out.write("                        <i class=\"fa-solid fa-right-from-bracket\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                        <p>Logout</p>\n");
      out.write("                        <span>></span>\n");
      out.write("                    </a>  \n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            </nav>\n");
      out.write("            \n");
      out.write("                            \n");
      out.write("                    \n");
      out.write(" \n");
      out.write("        </div>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <div class=\"container\">\n");
      out.write("        <h1>User Profile</h1>\n");
      out.write("        <hr style=\"width: 5%; margin: 0 500px;\">\n");
      out.write("        <div class=\"usea\">\n");
      out.write("        ");

            if (username != null) {
        
      out.write("\n");
      out.write("            <div class=\"usea-m\">\n");
      out.write("                <p><b>Name: </b>");
      out.print( session.getAttribute("name") );
      out.write("</p>\n");
      out.write("                <p><b>Email: </b>");
      out.print( session.getAttribute("email") );
      out.write("</p>\n");
      out.write("                <p><b>Username: </b>");
      out.print( username );
      out.write("</p>\n");
      out.write("            </div>\n");
      out.write("        ");

            } else {
                response.sendRedirect("http://localhost:8080/WebApplication_TEST/Login.html");
            }
        
      out.write("\n");
      out.write("\n");
      out.write("        <form onsubmit=\"return validate()\" method=\"POST\" action=\"http://localhost:8080/WebApplication_TEST/UserImageServlet\" enctype=\"multipart/form-data\">\n");
      out.write("            <label for=\"Image\">Image</label>\n");
      out.write("            <input type=\"file\" id=\"image\" name=\"image\" onchange=\"imgLoad(event)\">\n");
      out.write("            <span id=\"imgerr\" style=\"color:red\"></span>\n");
      out.write("            <br>\n");
      out.write("            <button type=\"submit\" name=\"submit\" id=\"done\">Add Image</button>\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("    </div>\n");
      out.write("        <script>\n");
      out.write("       let subMenu = document.getElementById(\"subMenu\");\n");
      out.write("\n");
      out.write("        function toggleMenu() {\n");
      out.write("            subMenu.classList.toggle(\"open-menu\");\n");
      out.write("        }\n");
      out.write("\n");
      out.write("   </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
