<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.io.ByteArrayInputStream"%>
<%@ page import="util.ImageUtil"%>
<% String status = request.getParameter("status"); %>
<% String message = (String) request.getAttribute("message"); %>
<%  String username = (String) session.getAttribute("username");%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Product Details</title>
    <link rel="stylesheet" type="text/css" href="Product.css">
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

    <%
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
    %>

                    <!-- Common HTML code for image, name, and details -->
                    <div class="tex"><h1><%= rs.getString("name") %></h1></div>
                    <img src="<%= ImageUtil.getProductImageSrc(rs) %>"/>
                    <p style="text-align: center; padding-top: 10px"><%= rs.getString("description") %></p>

                    <div class="CB">
                    <!-- Bidding Section -->
                    <% if (auctionStatus == 0) { %>
                    <p>Current Bid: $<%= currentBid %></p>
                    
                    <form action="http://localhost:8080/WebApplication_TEST/bidSys" method="post">
                        <input type="hidden" name="productId" value="<%= productId %>">
                        <input type="hidden" name="bidderName" value="<%= username %>">
                        
                    <% if ("success".equals(status)) { %>
                        <div class="success"><%= message %></div>
                    <% } else if ("fail".equals(status)) { %>
                        <div class="fail"><%= message %></div>
                    <% } %>    
                        
                      
                        <div class="CB-2">   
                        <input type="number" placeholder="Enter Your Bid" name="bidAmount" required>
                        </div>
                        <br>
                        
                            <button type="submit">BID</button>
                        <% } else { %>
                            <p>Auctioned at: $<%= currentBid %></p>
                            <div class="ends">
                            <button type="button"   >Bidding Closed</button>
                            </div>
                        <% } %>
                    </form>
                    </div>
                    <br>

                    
                    <br>

    <%
                } else {
    %>
                    <p align="center">No product found with ID <%= productId %></p>
    <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
    %>
        <p>Product ID is missing in the request</p>
    <%
        }
    %>
    <script>
       let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
            subMenu.classList.toggle("open-menu");
        }

   </script>
</body>
</html>
