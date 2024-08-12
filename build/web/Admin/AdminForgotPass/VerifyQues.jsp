<%@page import="oracle.jdbc.OracleResultSet"%>
<%@page import="oracle.jdbc.OraclePreparedStatement"%>
<%@page import="oracle.jdbc.OracleConnection"%>
<%@page import="java.sql.DriverManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="http://localhost:8080/WebApplication_TEST/Admin//RegistrationStyles.css">
        <link href="https://fonts.googleapis.com/css2?family=Aboreto&family=Martel:wght@200&display=swap" rel="stylesheet">
        <script>
            function funClose()
            {
                if(window.parent) if(confirm("Closing window......") === true)  window.parent.window.close();    
                else if(confirm("Closing window......") === true) window.close();   
              }
         </script>     
    </head>
    <body>
        <header>
        <div class="hero">
            <nav>
            <h2>L a u t r e c</h2>
            <h5>A U C T I O N E E R</h5> 
            </nav>
        </div>
           
    </header>
        
         <%!
            String vemail, vques, vans;
            OracleConnection oconn;
          OraclePreparedStatement ost;
          OracleResultSet ors = null;
            %>
        <%
            vemail = request.getParameter("pemail");
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            oconn = (OracleConnection)DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","scott","scott");
           ost =(OraclePreparedStatement) oconn.prepareStatement("SELECT * FROM auctioneers where email=?");
            ost.setString(1, vemail);
            ors = (OracleResultSet) ost.executeQuery();
            if(ors.next()) 
            {
                vques = ors.getString("SQUES"); //SQUES IS D COL NAME FOR SECURITY QUES IN UR DB
                vans = ors.getString("SANS");
            }
            else
            {
                 %>
                <script>
                    alert("Do not try any malaligned URL. \n You can only use  the link received in mail ");
                    window.close();
                </script>
                <%
            }
            ost.close();
            oconn.close();
            if(request.getParameter("bVerify")!=null)
            {
                if(request.getParameter("tbAns").equals(vans))
                {
                %>
                <script>
                    alert("Security Answer verified successfully!!!");
                    location.href="http://localhost:8080/WebApplication_TEST/Admin/AdminForgotPass/NewPassword.jsp?pemail=<%=vemail%>";
                </script>
                <%
                }
                else
                {
                       %> 
                       <h3 style="color:red">Wrong Answer. Please try again!!!</h3>                 
                        <%
                }
            }
            else
            {
                   %>
                   
                   
                    <%
            }   
            %> 
        
        <div class="wrapper">
        <form name="frmSecurity" method="POST" action="http://localhost:8080/WebApplication_TEST/Admin/AdminForgotPass/VerifyQues.jsp?pemail=<%=vemail%>">
       
          
                <h3>Security Verification Form</h3>
               
                <div class="question">
                    <h3> <%=vques%></h3>
                </div>    
                        <input type="text" size="30" name="tbAns" required placeholder="Answer"></td>
                   
                    
                        <div class="VerifyPage">
                            <button type="submit" name="bVerify">Verify</button>
                            <button type="reset" name="bReset">Reset</button><br>
                            
                           <!--<button type="button" name="bClose" onclick="funClose();">Close</button>--> 
                           
                        </div>
                        
                        
     
        </form>                
        </div>
           
    </body>
</html>