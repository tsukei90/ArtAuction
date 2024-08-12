<%-- 
    Document   : AdminHome
    Created on : 21 Nov, 2023, 2:52:32 AM
    Author     : AZAM
--%>
<%  String username = (String) session.getAttribute("username");%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin HomePage</title>
        <link rel="stylesheet" href="AdminHome.css">
    <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/e457018090.js" crossorigin="anonymous"></script>
    </head>
    <body>
          <header>
        <div class="hero">
            <nav>
            <h2>Lautrec</h2>&nbsp;<small>AUCTIONEER</small>   
                <ul>
                <li><a href="http://localhost:8080/WebApplication_TEST/Admin/AdminHome.jsp">Home</a></li>
                <li><a href="http://localhost:8080/WebApplication_TEST/PrintFromDatabaseImg.jsp">Buyer</a></li>
                <li><a href="http://localhost:8080/WebApplication_TEST/Admin/Image%20Test.jsp">Add Products</a></li>
            </ul>
            
      
            <i class="fa-solid fa-user" style="color: #c5bc9f; margin-left: 30px;" onclick="toggleMenu()" ></i> 

            <div class="sub-menu-wrap" id="subMenu">
                <div class="sub-menu">
                    <div class="user-info">
                        <h3><%= username %></h3>
                    </div>
                        <hr>

                    <a href="#" class="sub-menu-link">
                        <i class="fa-solid fa-user" style="color: #4e4d4a;"></i>
                        <p>Edit Profile</p>
                        <span>></span>
                    </a>  

                    <a href="TEST.jsp" class="sub-menu-link">
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
                    
                    <a href="AuctioneerListing.jsp"> Your Products </a>             
                    
    <script>
       let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
        subMenu.classList.toggle("open-menu");
    }

   </script>
                
    </body>
</html>
