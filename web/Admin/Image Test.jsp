<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<%  String username = (String) session.getAttribute("username");
    if (Objects.isNull(username) || username.trim().isEmpty()) {
        // Redirect to login page if the username is null or empty
        response.sendRedirect("http://localhost:8080/WebApplication_TEST/Admin/LoginAddProduct.html");
    }
%>

<html>
<head>
    <title>Add Product</title>
    <link rel="stylesheet" href="ImageTest.css">
    <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/e457018090.js" crossorigin="anonymous"></script>
    
    <style>
        /* Additional styles for product details */
        .product-details {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .product-details img {
            width: 200px;
            height: 300px;
            margin-top: 10px;
        }
    </style>

    <script>
        function checkProductName() {
            // Additional validation for product name
            // You can customize this based on your requirements
            var productName = document.getElementById("productName").value;
            if (productName.trim() === "") {
                document.getElementById("productNameErr").innerHTML = "Enter a product name";
                return false;
            } else {
                document.getElementById("productNameErr").innerHTML = "";
                return true;
            }
        }

        function validate() {
            // Combine existing validation with new product name validation
            var a = checkImg();
            var b = checkProductName();
            return a && b;
        }
    </script>
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
    <form onsubmit="return validate()" method="POST" action="http://localhost:8080/WebApplication_TEST/ImgProductServlet" enctype="multipart/form-data">
        <div class="container" align="center">
            <h1>Add Product To Showcase</h1>
            
            <input type="hidden" name="sellerName" value="<%= username %>">
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" onblur="checkProductName()"><br>
            <span id="productNameErr" style="color:red"></span>

            <label for="productDescription">Product Description:</label>
            <textarea id="productDescription" name="productDescription"></textarea><br>

            <label for="productImage">Product Image:</label>
            <input type="file" id="productImage" name="productImage" onchange="imgLoad(event)"><br>
            
  
            <br>
            <span id="statusMsg" class="<%= request.getParameter("status") != null ? (request.getParameter("status").equals("success") ? "success-msg" : "error-msg") : "" %>">
                <%= request.getParameter("status") != null ? (request.getParameter("status").equals("success") ? "Added Successfully" : "Failed To Add") : "" %>
            </span>
            <br>
            <!-- Existing code remains unchanged -->

            <%
                String productIdParam = request.getParameter("productId");
                if (productIdParam != null) {
                    int productId = Integer.parseInt(productIdParam);
            %>
                <span class="success-msg">Added Successfully. Product ID: <%= productId %></span>
            <%
                } else {
            %>
                <span class="error-msg"></span>
            <%
                }
            %>

            <button type="submit" name="submit" id="done">Add Product</button>
        </div>
    </form>
<br>
    <div class="homediv" align="center">
        <!-- Your existing code for viewing submitted images -->
        <form action="AuctioneerListing.jsp">
            <input type="submit" value="Manage your Already Added Products">
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