<%-- 
    Document   : UserProfile
    Created on : 22 Oct, 2023, 6:07:29 PM
    Author     : AZAM
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.util.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
   
    String username = (String) session.getAttribute("username");

    // Database connection details
    String url = "jdbc:oracle:thin:@localhost:1521:ORCL";
    String user = "scott";
    String password = "scott";

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
     

    try {
           // Establish the database connection
           Class.forName("oracle.jdbc.driver.OracleDriver");
           connection = DriverManager.getConnection(url, user, password);

           // Execute the SQL query to fetch email and name for the logged-in user
           String query = "SELECT email, name FROM auctioneers WHERE username = '" + username + "'";
           statement = connection.createStatement();
           resultSet = statement.executeQuery(query);

           // Check if the result set has any rows
           if (resultSet.next()) {
               String email = resultSet.getString("email");
               String name = resultSet.getString("name");

               // Set the retrieved values to the session attributes
               session.setAttribute("email", email);
               session.setAttribute("name", name);
           }
       } catch (Exception e) {
           // Handle exceptions (log them, display an error message, etc.)
           e.printStackTrace();
       } finally {
           // Close the database resources in the reverse order of their creation
           if (resultSet != null) resultSet.close();
           if (statement != null) statement.close();
           if (connection != null) connection.close();
       }
   %>


<html>
<head>
    <title>Auctioneer Profile</title>
    <link rel="stylesheet" type="text/css" href="UserProfileStyle.css">
    <script src="https://kit.fontawesome.com/e457018090.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&family=Source+Sans+3:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <script>
        function imgLoad(event) {
            var image = document.getElementById('imgout');
            fname = event.target.files[0].name;
            ext = fname.replace(/^.*\./, '');
            if (ext === 'png' || ext === 'jpg' || ext === 'jpeg') {
                image.src = URL.createObjectURL(event.target.files[0]);
                document.getElementById("imgerr").innerHTML = "";
                return true;
            } else {
                document.getElementById("imgerr").innerHTML = "Only png, jpg, jpeg types allowed";
                image.src = "null/";
                return false;
            }
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

    <div class="container">
        <h1>User Profile</h1>
        <hr style="width: 5%; margin: 0 500px;">
        <div class="usea">
        <%
            if (username != null) {
        %>
            <div class="usea-m">
                <p><b>Name: </b><%= session.getAttribute("name") %></p>
                <p><b>Email: </b><%= session.getAttribute("email") %></p>
                <p><b>Username: </b><%= username %></p>
            </div>
        <%
            } else {
                response.sendRedirect("http://localhost:8080/WebApplication_TEST/Login.html");
            }
        %>

        <form onsubmit="return validate()" method="POST" action="http://localhost:8080/WebApplication_TEST/UserImageServlet" enctype="multipart/form-data">
            <label for="Image">Image</label>
            <input type="file" id="image" name="image" onchange="imgLoad(event)">
            <span id="imgerr" style="color:red"></span>
            <br>
            <button type="submit" name="submit" id="done">Add Image</button>
        </form>
    </div>
    </div>
        <script>
       let subMenu = document.getElementById("subMenu");

        function toggleMenu() {
            subMenu.classList.toggle("open-menu");
        }

   </script>
</body>
</html>



