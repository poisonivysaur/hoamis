<%-- 
    Document   : groups
    Created on : Oct 23, 2017, 8:04:23 PM
    Author     : Leebet-PC
--%>
<%@ page import="DAO.Group"%>

<%@ page import="DAO.User"%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    ResultSet rst = (ResultSet)session.getAttribute("users");
    
    Group g = (Group)request.getAttribute("groupDet");
    
    User user = (User)session.getAttribute("UserObj");
    User u = user;
    
    ResultSet rsGroupMembers = g.getMembers(g.getGroup_id());
    rsGroupMembers.beforeFirst();
    
    ResultSet rstt = (ResultSet)session.getAttribute("userMem");
    
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><% out.println(request.getParameter("groupname")); %></h1>
    </body>
</html>



    <link rel="stylesheet" href="${pageContext.request.contextPath}/ourFiles/res/base.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ourFiles/res/grid.css">
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
          <span class="close">&times;</span>
          <h1>Add Member!</h1>
          <hr>
          <form action="AddMember" method="POST">
              <input type="hidden" name="groupname" value="<%= g.getGroupname() %>">
                    <br>
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
                        %> 
                            <option value="<%= rstt.getString("username")%>"><%= rstt.getString("username")%></option>
                        
                        <%   
                                
                        }
                            rstt.beforeFirst();
                        %>
                    </select><br>
                    <button class="button-src" type="submit">Create</button>
                </form>
        </div>

      </div>
    <body>
        <div class="template">    
            <div class="action-bar">
                <div class="col-3" style="display: inline-block">
                    <a href="Home.jsp">Hoamis Community Groups</a>
                        <input type="text" class="search" style="margin-left: 20px" name="search" placeholder="Search For Groups!">
                </div>
                <div class="col-3" style="text-align: right; display: inline-block">
                    
                        <% if (session.getAttribute("uname")==null){response.sendRedirect("http://localhost:8080/PlsRun/index.jsp");}
              else out.println(session.getAttribute("uname"));%> 
                 <a href="OfficerMain">Main Menu</a>
                </div>

            </div>
            
            <br><br>
            <main class="content">
                <div class="container">
                    <div class="col-6">
                        <div class="row">
                            <center>
                                <h1 style="display: inline-block">
                                    <%
                                        if(g.getPrivacy_set() == 1)
                                            out.print("Public");
                                        else
                                            out.print("Private");
                                        
                                    %> Group</h1>
                                
                                 <%
                                    if(!(g.isAMember(u.getUser_id(), g.getGroup_id())))
                                    {	out.println(g.isAMember(u.getUser_id(), g.getGroup_id()));
                                 %> 
                                <form method="post" action="JoinGroup">
                                    <button class="button-src" style="display: inline-block">Group Jhioin 
                                        <img src="assets/groupa.png" alt="Avatar" width="15%" height="15%">
                                        <input type="hidden" value="<%= g.getGroup_id() %>" name="gid">
                              
                                        <input type="hidden" value="<%= u.getUserID(user.getUsername()) %>" name="uid">
                                    </button>
                                </form>
                                    
                                <% }
                                else{
                                %>
                                    <button class="button-src" style="display: inline-block" id="myBtn">Invite Member</button>
                                <%}%>
                            </center>
                        </div>      
                           <%
                           while(rsGroupMembers.next())
                           {	
                           %> 
                           
                            <div class="col-1">
                                <div class="card">
                                    <img src="assets/img_avatar.png" alt="Avatar" width="100%">
                                    <div class="container-card">
                                        
                                        <b><% out.print(rsGroupMembers.getString("user_id"));%></b><br>
                                        <b><% out.print(rsGroupMembers.getString("username"));%></b>
                                    </div>
                                </div>
                            </div>
                            </tr>
                           <%	
                            }
                            rsGroupMembers.beforeFirst();
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
</script>