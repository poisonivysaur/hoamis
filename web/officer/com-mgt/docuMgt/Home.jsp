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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <link rel="stylesheet" href="commons/res/ba.css">
    <link rel="stylesheet" href="commons/res/gr.css">
    <style>
        .hide input[type=file]{
            
            display: block;
        }
        .hide {
            display: inline-block ;
            padding: 2px 20px;
            border: none;
            color: white;
            border-radius: 5px;
            text-align: center;
            text-decoration: #4CAF50;
            font-size: 20px;
            cursor: pointer;
            background-color: #5a6c89;    
        }
        .hide input[type=file]:active + label{
            display: block;
            background-image: none;
            background-color: #5a6c89;
            color: white;
        }
    </style>
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
             
                <form id="logout" action="LogoutDocu" method="post" style="display: inline-block">
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
                                <form action="upload" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="action" value="upload"/>
                                    <div id="result">
                                        <h3>${requestScope["message"]}</h3>
                                    </div>
                                    <h1 style="display: inline-block">Your Drive</h1>
                                
                                    <div class="hide"style="display: inline-block">
                                       <label style="cursor: pointer; display:inline-block">
                                            <b>UPLOAD</b>
                                                <input style="display: none;" type="file" id="fileSelect2" onchange="showImage(this);" name="file"/>
                                                
                                       </label>   
                                    </div>
                                    <input class="button-src" type="submit" style="border-radius: 70%;padding:5px" value="✓" />
                                </form>
                                    <br>
                                    <h3 id="fileName"></h3>
                                    <img id="fileImage" src="" alt="" style="max-width: 300px; max-height: 300px" min-width="0px" min-height="0px" />
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
/*
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
*/
 function showImage(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            var files = input.files[0];
            var fileType = files["type"];
            var ValidImageTypes = ["image/gif", "image/jpeg", "image/png"];
            if ($.inArray(fileType, ValidImageTypes) < 0) {
                 $('#fileImage')
                        .attr('src', 'commons/assets/default.png').style="display: inline;"
                 ;
                 $('#fileName')
                        .html(files["name"]);
                 ;
            }
            else
            {
                reader.onload = function (e) {
                    $('#fileImage')
                        .attr('src', e.target.result).style="display: inline;"
                    ;
                    $('#fileName')
                        .html(files["name"]);
                    ;
                };
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

</script>s