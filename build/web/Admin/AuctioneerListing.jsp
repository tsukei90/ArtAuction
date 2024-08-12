<%@page import="java.sql.PreparedStatement"%>
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
    <link rel="stylesheet" type="text/css" href="AuctioneerListingStyles.css">
    <script src="https://kit.fontawesome.com/e457018090.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <header>
        <div class="hero">
            <nav>
                <h2><a href="http://localhost:8080/WebApplication_TEST/Admin/AuctioneerListing.jsp" style="color: #c5bc9f; text-decoration: none; font-family: Aboreto">Lautrec</a></h2>&nbsp;<small>AUCTIONEER</small>   
                
                <ul>
                    
                    <li><a href="http://localhost:8080/WebApplication_TEST/Login.html">Buyer</a></li>
                    <li><a href="http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp">Add Products</a></li>
                    <li><a href="http://localhost:8080/WebApplication_TEST/Admin/Feedback/feedback.html">Feedback</a></li>
                </ul>
            
                <i class="fa-solid fa-user" style="color: #c5bc9f; margin-left: 30px;" onclick="toggleMenu()" ></i> 

                <div class="sub-menu-wrap" id="subMenu">
                    <div class="sub-menu">
                        <div class="user-info">
                            <h3><%= username %></h3>
                        </div>
                        <hr>

                        <a href="http://localhost:8080/WebApplication_TEST/Admin/UserProfile.jsp" class="sub-menu-link">
                            <i class="fa-solid fa-user" style="color: #4e4d4a;"></i>
                            <p>Edit Profile</p>
                            <span>></span>
                        </a>  

                        <a href="Help" class="sub-menu-link">
                            <i class="fa-solid fa-circle-question" style="color: #4e4d4a;"></i>
                            <p>Help & Support</p>
                            <span>></span>
                        </a>    

                        <a href="http://localhost:8080/WebApplication_TEST/LogoutServletAdmin"class="sub-menu-link">
                            <i class="fa-solid fa-right-from-bracket" style="color: #4e4d4a;"></i>
                            <p>Logout</p>
                            <span>></span>
                        </a>  
                    </div>
                </div>
            </nav>
        </div>
    </header>
                        
                        <div class="HeadofList">
                            <h1>Auctioneers Listed Products</h1>
                        </div>

    <div class="table-container">                    
            <table>
                <thead>
                    <tr>
                        <th>Product Name</th>
                        <th>Image</th>
                        <th>Description</th> <!-- Combine the details in one column -->
                        <th> Current Status
                        <th>Action</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        try {
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "scott", "scott");

                            String query = "SELECT * FROM products WHERE sellers_name = ?";
                            PreparedStatement pstmt = conn.prepareStatement(query);
                            pstmt.setString(1, username);
                            ResultSet rs = pstmt.executeQuery();

                            while (rs.next()) {
                                String productId = rs.getString("product_id");
                                String productName = rs.getString("name");

                                // Convert Blob to Base64 directly in JSP
                                Blob imageBlob = rs.getBlob("image");
                                byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                                String base64Image = "data:image/jpeg;base64," + new String(Base64.getEncoder().encode(imageBytes));
                    %>

                                <tr>
                                    <td><%= productName %></td>
                                    <td><img src="<%= base64Image %>"/></td>
                                    <td>
                                        <p><%= rs.getString("description") %></p>

                                    </td>
                                    <td>
                                        <p><b>Current Bid:</b> $<%= rs.getInt("current_bid") %></p>
                                        <p><b>Highest Bidder:</b> <%= rs.getString("highes_bidders_name") %></p>

                                        <%
                                            String status = rs.getString("end").equals("0") ? "Bidding Open" : "Bidding Closed";
                                            String buttonLabel = rs.getString("end").equals("0") ? "End Bidding" : "Bidding Closed";
                                            String buttonStyleClass = rs.getString("end").equals("0") ? "bidding-open" : "bidding-closed";
                                        %>
                                        <p><b>Status:</b> <%= status %></p>
                                    </td>

                                    <td>
                                        <!--End the bidding -->
                                        <form action="http://localhost:8080/WebApplication_TEST/EndBiddingServlet" method="post">
                                            <input type="hidden" name="productId" value="<%= productId %>">
                                             <button type="submit" class="<%= buttonStyleClass %>" <%= rs.getString("end").equals("0") ? "" : "disabled" %>>
                                                <%= buttonLabel %>
                                            </button>

                                        </form>
                                    </td>
                                </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    %>
                </tbody>
            </table>
    </div>

    <script>
        let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
            subMenu.classList.toggle("open-menu");
        }
    </script>
</body>
</html>
