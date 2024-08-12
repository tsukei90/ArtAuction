<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
    String username = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome Page</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/WebApplication_TEST/welcomestyles.css">
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
                    
          <div class="slideshow-container">

  <!-- Full-width images with number and caption text -->
  <div class="mySlides fade">
    <div class="numbertext">1 / 3</div>
    <img src="images/H3.png" >
    <div class="text"></div>
  </div>

  <div class="mySlides fade">
    <div class="numbertext">2 / 3</div>
    <img src="images/TOMA VAGNER.jpg">
    <div class="text"></div>
  </div>
  <div class="mySlides fade">
    <div class="numbertext">2 / 3</div>
    <img src="images/Madrastra.jpg">
    <div class="text"></div>
  </div>

  <!-- Next and previous buttons -->
  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  <a class="next" onclick="plusSlides(1)">&#10095;</a>
  
</div>
<br>

<!-- The dots/circles -->
<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span>
  <span class="dot" onclick="currentSlide(2)"></span>
  
</div>

<br>
<br>

<div class="HeadofList">
    <h1>Live Auction </h1>
</div>

<hr style="width: 30%; margin: 0 auto;">

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
            
            int endValue = rs.getInt("end");

        // Only Shows the Auctions which are live 
        if (endValue == 1) {
            continue; // Skip to the next iteration of the loop
        }

            
            
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
        
        
            let slideIndex = 1;
        showSlides(slideIndex);

        // Next/previous controls
        function plusSlides(n) {
          showSlides(slideIndex += n);
        }

        // Thumbnail image controls
        function currentSlide(n) {
          showSlides(slideIndex = n);
        }

        function showSlides(n) {
          let i;
          let slides = document.getElementsByClassName("mySlides");
          let dots = document.getElementsByClassName("dot");
          if (n > slides.length) {slideIndex = 1;}
          if (n < 1) {slideIndex = slides.length;}
          for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
          }
          for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
          }
          slides[slideIndex-1].style.display = "block";
          dots[slideIndex-1].className += " active";
        }

   </script>
</body>
</html>
