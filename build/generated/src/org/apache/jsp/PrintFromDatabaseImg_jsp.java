package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Base64;
import java.sql.Blob;

public final class PrintFromDatabaseImg_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
  String username = (String) session.getAttribute("username");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Product Showcase</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"ProductDisplayStyles.css\">\n");
      out.write("    <script src=\"https://kit.fontawesome.com/e457018090.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("         <header>\n");
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
      out.write("                    \n");
      out.write("     <div class=\"products-container\">\n");
      out.write("    ");

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");
        String query = "SELECT * FROM products";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            String productId = rs.getString("product_id");
            String productName = rs.getString("name");
            

            // Convert Blob to Base64 directly in JSP
            Blob imageBlob = rs.getBlob("image");
            byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
            String base64Image = "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(imageBytes));
    
      out.write("\n");
      out.write("    \n");
      out.write("    <div class=\"product\">\n");
      out.write("            \n");
      out.write("            <img style='width:100%' src=\"");
      out.print( base64Image );
      out.write("\"/>\n");
      out.write("            <h2>");
      out.print( productName );
      out.write("</h2>\n");
      out.write("            <p>Bidding at: $");
      out.print( rs.getString("current_bid") );
      out.write("</p>\n");
      out.write("            <a href=\"ProductPage.jsp?productId=");
      out.print( productId );
      out.write("\">Enter Bid</a>\n");
      out.write("        </div>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("     </div>\n");
      out.write("    <script>\n");
      out.write("       let subMenu = document.getElementById(\"subMenu\");\n");
      out.write("\n");
      out.write("        function toggleMenu() {\n");
      out.write("        subMenu.classList.toggle(\"open-menu\");\n");
      out.write("    }\n");
      out.write("\n");
      out.write("   </script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
