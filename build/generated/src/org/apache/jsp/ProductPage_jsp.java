package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.io.ByteArrayInputStream;
import util.ImageUtil;

public final class ProductPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 String status = request.getParameter("status"); 
      out.write('\n');
 String message = (String) request.getAttribute("message"); 
      out.write('\n');
  String username = (String) session.getAttribute("username");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <title>Product Details</title>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"Product.css\">\n");
      out.write("    <script src=\"https://kit.fontawesome.com/e457018090.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("     <header>\n");
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
      out.write("    ");

        String productId = request.getParameter("productId");
        if (productId != null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");
                String query = "SELECT * FROM products WHERE product_id = " + productId;
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) {
                    int currentBid = rs.getInt("current_bid");
                    int auctionStatus = rs.getInt("end");
    
      out.write("\n");
      out.write("\n");
      out.write("                    <!-- Common HTML code for image, name, and details -->\n");
      out.write("                    <div class=\"tex\"><h1>");
      out.print( rs.getString("name") );
      out.write("</h1></div>\n");
      out.write("                    <img src=\"");
      out.print( ImageUtil.getProductImageSrc(rs) );
      out.write("\"/>\n");
      out.write("                    <p>");
      out.print( rs.getString("description") );
      out.write("</p>\n");
      out.write("\n");
      out.write("                    <!-- Bidding Section -->\n");
      out.write("                    ");
 if (auctionStatus == 0) { 
      out.write("\n");
      out.write("                    <p>Current Bid: $");
      out.print( currentBid );
      out.write("</p>\n");
      out.write("\n");
      out.write("                    <form action=\"http://localhost:8080/WebApplication_TEST/bidSys\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"productId\" value=\"");
      out.print( productId );
      out.write("\">\n");
      out.write("                        <input type=\"hidden\" name=\"bidderName\" value=\"");
      out.print( username );
      out.write("\">\n");
      out.write("                        \n");
      out.write("                        <label for=\"bidAmount\">Enter Your Bid:</label>\n");
      out.write("                        <input type=\"number\" name=\"bidAmount\" required>\n");
      out.write("                        \n");
      out.write("                            <button type=\"submit\">Place Bid</button>\n");
      out.write("                        ");
 } else { 
      out.write("\n");
      out.write("                            <p>Auctioned at: $");
      out.print( currentBid );
      out.write("</p>\n");
      out.write("                            <button type=\"button\" style=\"background-color: rgb(36, 39, 42); color: white;\">Bidding Closed</button>\n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <br>\n");
      out.write("\n");
      out.write("                    ");
 if ("success".equals(status)) { 
      out.write("\n");
      out.write("                        <div class=\"success\">");
      out.print( message );
      out.write("</div>\n");
      out.write("                    ");
 } else if ("fail".equals(status)) { 
      out.write("\n");
      out.write("                        <div class=\"fail\">");
      out.print( message );
      out.write("</div>\n");
      out.write("                    ");
 } 
      out.write("\n");
      out.write("                    <br>\n");
      out.write("\n");
      out.write("    ");

                } else {
    
      out.write("\n");
      out.write("                    <p>No product found with ID ");
      out.print( productId );
      out.write("</p>\n");
      out.write("    ");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
    
      out.write("\n");
      out.write("        <p>Product ID is missing in the request</p>\n");
      out.write("    ");

        }
    
      out.write("\n");
      out.write("    <script>\n");
      out.write("       let subMenu = document.getElementById(\"subMenu\");\n");
      out.write("\n");
      out.write("        function toggleMenu() {\n");
      out.write("            subMenu.classList.toggle(\"open-menu\");\n");
      out.write("        }\n");
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
