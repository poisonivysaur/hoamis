<%-- 
    Document   : Home
    Created on : Oct 16, 2017, 1:41:14 AM
    Author     : Leebet-PC
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

    <link rel="stylesheet" href="base.css">
    <link rel="stylesheet" href="grid.css">
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
          <span class="close">&times;</span>
          <h1>Add Group</h1>
          <hr>
          <form action="AddGroup" method="POST">
              <input type="hidden" name="uname" value="<%= session.getAttribute("uname") %>">
                    Group Name:<br>
                    <input type="text" class="group" name="groupname"><br>
                    Privacy Setting:
                    <select name="settings" id="soflow">
                        <option value="1">Public</option>
                        <option value="2">Closed</option>
                    </select><br>
                    Add Members:<br>
                    <select name="members" style=" 
                                    border: 1px solid #AAA;
                                    color: #555;
                                    font-size: inherit;
                                    margin: 20px;
                                    overflow: scroll;
                                    padding: 5px 10px;
                                    text-overflow: ellipsis;
                                    white-space: nowrap;
                                    width: 300px;" multiple>
                        <%
                           while(rstt.next())
                               
                           {
                               
                               if(rstt.getString("userID").toString() != user.getUserID().toString()){
                                   
                        %> 
                            <option value="<%= rstt.getString("userID")%>"><%= rstt.getString("userID")%></option>
                        
                        <%   
                                }
                        }
                            rstt.beforeFirst();
                        %>
                    </select>
                    <br>
                    Add Groups:<br>
                    <select name="groups" style=" 
                                    border: 1px solid #AAA;
                                    color: #555;
                                    font-size: inherit;
                                    margin: 20px;
                                    overflow: scroll;
                                    padding: 5px 10px;
                                    text-overflow: ellipsis;
                                    white-space: nowrap;
                                    width: 300px;" multiple>
                        <%
                           while(groupsAvailable.next())
                           {     
                        %> 
                            <option value="<%= groupsAvailable.getString("groupname")%>"><%= groupsAvailable.getString("groupname")%></option>
                        <%   
                                
                        }
                            groupsAvailable.beforeFirst();
                        %>
                    </select>
                    
                    <br>
                    <button class="button-src" type="submit">Create</button>
                </form>
        </div>

      </div>
    <body>
        <div class="template">    
            <div class="action-bar">
                <div class="col-3" style="display: inline-block">
                    Hoamis Community Groups
                        <input type="text" class="search" style="margin-left: 20px" name="search" placeholder="Search For Groups!">
                </div>
                <div class="col-3" style="text-align: right; display: inline-block">
                    
                        <% if (session.getAttribute("uname")=="null") response.sendRedirect("index.jsp");
                           else out.println(session.getAttribute("uname"));%> <img src="assets/fuckboi.png" class="img-circle"  width="5%" height="5%">
             
                <form id="logout" action="LogoutServ" method="post" style="display: inline-block">
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
                                <h1 style="display: inline-block">Your Groups</h1>
                                <button class="button-src" style="display: inline-block" id="myBtn">Create a Group 
                                    <img src="assets/groupa.png" alt="Avatar" width="15%" height="15%">
                                </button>
                            </center>
                        </div>      
                           <%
                           while(rst.next())
                           {	
                           %> 
                           
                            <div class="col-1">
                                <div class="card">
                                    <img src="assets/img_avatar.png" alt="Avatar" width="100%">
                                    <div class="container-card">
                                        <form method="GET" action="GroupDet" id="<% out.print(rst.getString("groupname"));%>">
                                            <a href="javascript:{}" onclick="document.getElementById('<% out.print(rst.getString("groupname"));%>').submit();">
                                                <h4><b><% out.print(rst.getString("groupname"));%></b></h4>
                                            </a>
                                            <hr style="color: gray">
                                            <p><b><% out.println(q.getNumOfMembers(rst.getInt("group_id")));%></b> members</p> 
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