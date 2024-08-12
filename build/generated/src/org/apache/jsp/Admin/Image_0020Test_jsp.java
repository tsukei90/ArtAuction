package org.apache.jsp.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Objects;

public final class Image_0020Test_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
  String username = (String) session.getAttribute("username");
    if (Objects.isNull(username) || username.trim().isEmpty()) {
        // Redirect to login page if the username is null or empty
        response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/LoginAddProduct.html");
    }

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <title>Add Product</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"ImageTest.css\">\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap\" rel=\"stylesheet\">\n");
      out.write("    <script src=\"https://kit.fontawesome.com/e457018090.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("    \n");
      out.write("    <style>\n");
      out.write("        /* Additional styles for product details */\n");
      out.write("        .product-details {\n");
      out.write("            margin-top: 20px;\n");
      out.write("            padding: 10px;\n");
      out.write("            border: 1px solid #ccc;\n");
      out.write("            border-radius: 5px;\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        .product-details img {\n");
      out.write("            width: 200px;\n");
      out.write("            height: 300px;\n");
      out.write("            margin-top: 10px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("        function checkProductName() {\n");
      out.write("            // Additional validation for product name\n");
      out.write("            // You can customize this based on your requirements\n");
      out.write("            var productName = document.getElementById(\"productName\").value;\n");
      out.write("            if (productName.trim() === \"\") {\n");
      out.write("                document.getElementById(\"productNameErr\").innerHTML = \"Enter a product name\";\n");
      out.write("                return false;\n");
      out.write("            } else {\n");
      out.write("                document.getElementById(\"productNameErr\").innerHTML = \"\";\n");
      out.write("                return true;\n");
      out.write("            }\n");
      out.write("        }\n");
      out.write("\n");
      out.write("        function validate() {\n");
      out.write("            // Combine existing validation with new product name validation\n");
      out.write("            var a = checkImg();\n");
      out.write("            var b = checkProductName();\n");
      out.write("            return a && b;\n");
      out.write("        }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("   <header>\n");
      out.write("        <div class=\"hero\">\n");
      out.write("            <nav>\n");
      out.write("                <h2><a href=\"http://localhost:8080/WebApplication_TEST/Admin/AuctioneerListing.jsp\" style=\"color: #c5bc9f; text-decoration: none; font-family: Aboreto\">Lautrec</a></h2>&nbsp;<small>AUCTIONEER</small>   \n");
      out.write("                \n");
      out.write("                <ul>\n");
      out.write("                    \n");
      out.write("                    <li><a href=\"http://localhost:8080/WebApplication_TEST/Login.html\">Buyer</a></li>\n");
      out.write("                    <li><a href=\"http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp\">Add Products</a></li>\n");
      out.write("                    <li><a href=\"http://localhost:8080/WebApplication_TEST/Admin/Feedback/feedback.html\">Feedback</a></li>\n");
      out.write("                </ul>\n");
      out.write("            \n");
      out.write("                <i class=\"fa-solid fa-user\" style=\"color: #c5bc9f; margin-left: 30px;\" onclick=\"toggleMenu()\" ></i> \n");
      out.write("\n");
      out.write("                <div class=\"sub-menu-wrap\" id=\"subMenu\">\n");
      out.write("                    <div class=\"sub-menu\">\n");
      out.write("                        <div class=\"user-info\">\n");
      out.write("                            <h3>");
      out.print( username );
      out.write("</h3>\n");
      out.write("                        </div>\n");
      out.write("                        <hr>\n");
      out.write("\n");
      out.write("                        <a href=\"http://localhost:8080/WebApplication_TEST/Admin/UserProfile.jsp\" class=\"sub-menu-link\">\n");
      out.write("                            <i class=\"fa-solid fa-user\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                            <p>Edit Profile</p>\n");
      out.write("                            <span>></span>\n");
      out.write("                        </a>  \n");
      out.write("\n");
      out.write("                        <a href=\"Help\" class=\"sub-menu-link\">\n");
      out.write("                            <i class=\"fa-solid fa-circle-question\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                            <p>Help & Support</p>\n");
      out.write("                            <span>></span>\n");
      out.write("                        </a>    \n");
      out.write("\n");
      out.write("                        <a href=\"http://localhost:8080/WebApplication_TEST/LogoutServletAdmin\"class=\"sub-menu-link\">\n");
      out.write("                            <i class=\"fa-solid fa-right-from-bracket\" style=\"color: #4e4d4a;\"></i>\n");
      out.write("                            <p>Logout</p>\n");
      out.write("                            <span>></span>\n");
      out.write("                        </a>  \n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </nav>\n");
      out.write("        </div>\n");
      out.write("    </header>\n");
      out.write("    <form onsubmit=\"return validate()\" method=\"POST\" action=\"http://localhost:8080/WebApplication_TEST/ImgProductServlet\" enctype=\"multipart/form-data\">\n");
      out.write("        <div class=\"container\" align=\"center\">\n");
      out.write("            <h1>Add Product To Showcase</h1>\n");
      out.write("            \n");
      out.write("            <input type=\"hidden\" name=\"sellerName\" value=\"");
      out.print( username );
      out.write("\">\n");
      out.write("            <label for=\"productName\">Product Name:</label>\n");
      out.write("            <input type=\"text\" id=\"productName\" name=\"productName\" onblur=\"checkProductName()\"><br>\n");
      out.write("            <span id=\"productNameErr\" style=\"color:red\"></span>\n");
      out.write("\n");
      out.write("            <label for=\"productDescription\">Product Description:</label>\n");
      out.write("            <textarea id=\"productDescription\" name=\"productDescription\"></textarea><br>\n");
      out.write("\n");
      out.write("            <label for=\"productImage\">Product Image:</label>\n");
      out.write("            <input type=\"file\" id=\"productImage\" name=\"productImage\" onchange=\"imgLoad(event)\"><br>\n");
      out.write("            \n");
      out.write("  \n");
      out.write("            <br>\n");
      out.write("            <span id=\"statusMsg\" class=\"");
      out.print( request.getParameter("status") != null ? (request.getParameter("status").equals("success") ? "success-msg" : "error-msg") : "" );
      out.write("\">\n");
      out.write("                ");
      out.print( request.getParameter("status") != null ? (request.getParameter("status").equals("success") ? "Added Successfully" : "Failed To Add") : "" );
      out.write("\n");
      out.write("            </span>\n");
      out.write("            <br>\n");
      out.write("            <!-- Existing code remains unchanged -->\n");
      out.write("\n");
      out.write("            ");

                String productIdParam = request.getParameter("productId");
                if (productIdParam != null) {
                    int productId = Integer.parseInt(productIdParam);
            
      out.write("\n");
      out.write("                <span class=\"success-msg\">Added Successfully. Product ID: ");
      out.print( productId );
      out.write("</span>\n");
      out.write("            ");

                } else {
            
      out.write("\n");
      out.write("                <span class=\"error-msg\"></span>\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("\n");
      out.write("            <button type=\"submit\" name=\"submit\" id=\"done\">Add Product</button>\n");
      out.write("        </div>\n");
      out.write("    </form>\n");
      out.write("<br>\n");
      out.write("    <div class=\"homediv\" align=\"center\">\n");
      out.write("        <!-- Your existing code for viewing submitted images -->\n");
      out.write("        <form action=\"PrintFromDatabaseImg.jsp\">\n");
      out.write("            <input type=\"submit\" value=\"See Showcased Products\">\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("       let subMenu = document.getElementById(\"subMenu\");\n");
      out.write("\n");
      out.write("        function toggleMenu() {\n");
      out.write("        subMenu.classList.toggle(\"open-menu\");\n");
      out.write("    }\n");
      out.write("\n");
      out.write("   </script>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>");
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
