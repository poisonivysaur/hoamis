<%-- 
    Document   : Home
    Created on : Oct 16, 2017, 1:41:14 AM
    Author     : Leebet Barraquias
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="model.dao.User"%>
<%@ page import="model.Usergroup"%>
<% 
   
    ResultSet rst = (ResultSet)session.getAttribute("groups");
    ResultSet groupsAvailable = (ResultSet)session.getAttribute("groups");
    ResultSet rstt = (ResultSet)session.getAttribute("users");
    Usergroup q = new Usergroup();
    User user = (User)session.getAttribute("UserObj");
%>

<!DOCTYPE html>
<html>
    <script>
    function checkLoginState() {
        FB.getLoginStatus(function(response) {
          statusChangeCallback(response);
        });
      }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <link href="${pageContext.request.contextPath}/grid.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/base.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="commons/res/base.css">
    <link rel="stylesheet" href="commons/res/grid.css">
    
    <body>
        <div class="template">    
            <div class="action-bar">
                <div class="col-3" style="display: inline-block">
                    HOAMIS DRIVE
                        <input type="text" class="search" style="margin-left: 20px" name="search" placeholder="Search For Groups!">
                </div>
                <div class="col-3" style="text-align: right; display: inline-block">
                    
                        <% if (session.getAttribute("uname")=="null") response.sendRedirect("index.jsp");
                           else out.println(user.getfName() +" "+user.getlName());%> <img src="commons/assets/fuckboi.png" class="img-circle"  width="5%" height="5%">
             
                <form id="logout" action="" method="post" style="display: inline-block">
                 <a href="javascript:{}" onclick="document.getElementById('logout').submit();">
                                                Logout</a>
                
                </form>  
                 
                </div>

            </div>
            
            <br><br>
            <main class="content">
                <div class="container">
                    <div class="col-6">
                        <div class="row">
                            <center>
                                <h1 style="display: inline-block">Your Drive</h1>
                                <button class="button-src" style="display: inline-block" id="myBtn">Upload File 
                                    <img src="commons/assets/groupa.png" alt="Avatar" width="15%" height="15%">
                                </button>
                            </center>
                        </div>      
                           <%
                           while(rst.next())
                           {	
                           %> 
                           
                            <div class="col-1">
                                <div class="card">
                                    <img src="commons/assets/img_avatar.png" alt="Avatar" width="100%">
                                    <div class="container-card">
                                        <form method="GET" action="GroupDet" id="<% out.print(rst.getString("groupname"));%>">
                                            <a href="javascript:{}" onclick="document.getElementById('<% out.print(rst.getString("groupname"));%>').submit();">
                                                <h4><b><% out.print(rst.getString("groupname"));%></b></h4>
                                            </a>
                                            <hr style="color: gray">
                                            <p><b><% out.println(q.getNumOfMembers(rst.getInt("userGroupID")));%></b> members</p> 
                                            <input type="hidden" name="groupname" value="<%= rst.getString("groupname")%>">
                                            
                                        </form>
                                    </div>
                                </div>
                            </div>
                            </tr>
                           <%	
                            }
                            rst.beforeFirst();
                           %>
                        
                    </div>
                </div>
            </main>
            <footer class="primary-footer">
                <div class="container">
                    <small>&copy; HOAMIS Community Groups Module by Mark.</small>
                </div>
            </footer>
        </div>
    </body>
</html>
<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
</script>s