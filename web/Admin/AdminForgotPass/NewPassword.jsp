<%@page import="java.sql.DriverManager"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Reset Page</title>
        <link rel="stylesheet" href="http://localhost:8080/WebApplication_TEST/Admin//RegistrationStyles.css">
        <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&display=swap" rel="stylesheet">
    </head>
    <body>
        <header>
                        <div class="hero">
                            <nav>
                            <h2>Lautrec</h2>
                            </nav>
                        </div>
                    </header>
         <%!
            String vemail, vpass;
            OracleConnection oconn;
            OraclePreparedStatement ost;
            HttpSession sess;
          
            %>
            <%
            if(request.getParameter("bConfirm")!=null)
            {
                if(request.getParameter("tpass").equals(request.getParameter("cpass")))
                {
                   sess = request.getSession(false);
                   vpass = request.getParameter("tpass");
                   vemail = sess.getAttribute("sessemail").toString();
                   DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
                   oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","scott");
                   ost =(OraclePreparedStatement) oconn.prepareStatement("update auctioneers set  PASSWORD=? where EMAIL=?");
                   ost.setString(1, vpass);
                   ost.setString(2, vemail);
                   int x = ost.executeUpdate();
                   ost.close();
                   oconn.close();
                   sess.invalidate();
                     %>
                    <script>
                    alert("Password reset successfully!!! You can now login using your new password");
                    alert("Redirecting for logging==>>");
                    location.href="http://localhost:8080/WebApplication_TEST/Admin/Login.html";
                </script>
                <%  
                }
                else
                {
                       %> 
                       <h3 style="color:red">Password do not match!!!. Please try again!!!</h3>                 
                        <%
                }
            }
            else
            {
                   vemail = request.getParameter("pemail");
                   sess = request.getSession(true);
                   sess.setAttribute("sessemail", vemail);
                   %>
                  
                    <%
                   
            }   
            %>
                    
            <form>
        <div class="wrapper">
           
                <h3>Reset your Password</h3>
                    
                        
                       <input type="password" name="tpass" id="tpass" required placeholder="New Password">
                   
                        <input type="password" name="cpass" id="cpass" required placeholder="Confirm Password">
                   
                    <div class="VerifyPage">
                        <button type="submit" name="bConfirm">Confirm</button>

                        <button type="reset" name="bClear">Clear</button>
                    </div>
        </div>
            </form>
    </body>
</html>