<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%  String username = (String) session.getAttribute("username");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Showcase</title>
    <link rel="stylesheet" type="text/css" href="ProductDisplayStyles.css">
    <script src="https://kit.fontawesome.com/e457018090.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
         <header>
        <div class="hero">
            <nav>
            <h2><a href="welcome.jsp" style="color: #c5bc9f; text-decoration: none; font-family: Aboreto">Lautrec</a></h2>
            <ul>
                
                <li><a href="http://localhost:8080/WebApplication_TEST/PrintFromDatabaseImg.jsp">Auctions</a></li>
                <li><a href="http://localhost:8080/WebApplication_TEST/AboutUs.html">About Us</a></li>
                <li><a href="http://localhost:8080/WebApplication_TEST/Feedback/feedback.html">Feedback</a></li>
                
            </ul>
            
      
            <i class="fa-solid fa-user" style="color: #c5bc9f; margin-left: 30px;" onclick="toggleMenu()" ></i> 

            <div class="sub-menu-wrap" id="subMenu">
                <div class="sub-menu">
                    <div class="user-info">
                        <h3><%= username %></h3>
                    </div>
                        <hr>

                    <a href="http://localhost:8080/WebApplication_TEST/UserProfile.jsp" class="sub-menu-link">
                        <i class="fa-solid fa-user" style="color: #4e4d4a;"></i>
                        <p>Edit Profile</p>
                        <span>></span>
                    </a>  

                    <a href="Help" class="sub-menu-link">
                        <i class="fa-solid fa-circle-question" style="color: #4e4d4a;"></i>
                        <p>Help & Support</p>
                        <span>></span>
                    </a>    

                     <a href="LogoutServlet"class="sub-menu-link">
                        <i class="fa-solid fa-right-from-bracket" style="color: #4e4d4a;"></i>
                        <p>Logout</p>
                        <span>></span>
                    </a>  

                </div>
            </div>
            </nav>
            
                            
                    
 
        </div>
    </header>
                    
     <div class="products-container">
    <%
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
    %>
    
    <div class="product">
            
            <img style='width:100%' src="<%= base64Image %>"/>
            <h2><%= productName %></h2>
            <p>Bidding at: $<%= rs.getString("current_bid") %></p>
            <a href="ProductPage.jsp?productId=<%= productId %>">Enter Bid</a>
        </div>
    <%
        }
    %>
     </div>
    <script>
       let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
        subMenu.classList.toggle("open-menu");
    }

   </script>
</body>
</html>
