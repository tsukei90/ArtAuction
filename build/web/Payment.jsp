<%-- 
    Document   : Payment
    Created on : 14 Nov, 2023, 1:21:17 AM
    Author     : AZAM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Payment Page</title>
    <link rel="stylesheet" type="text/css" href="PaymentStyles.css">
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
    
    
    
    <div class="container">
        <h1>Payment Details</h1>
        <form action="PaymentSucc.html" method="post">
            <label for="cardNumber">Card Number:</label>
            <input type="number" id="cardNumber" name="cardNumber" required>

            <label for="expiryDate">Expiry Date:</label>
            <input type="number" id="expiryDate" name="expiryDate" placeholder="MM/YYYY" required>

            <label for="cvv">CVV:</label>
            <input type="number" id="cvv" name="cvv" required>

            <label for="holderName">Cardholder Name:</label>
            <input type="text" id="holderName" name="holderName" required>

            <div class="but">
            <button type="submit">Make Payment</button>
            </div>
        </form>
    </div>
                                                                                                                    
    <script>
       let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
            subMenu.classList.toggle("open-menu");
        }
   </script>                
</body>
</html>