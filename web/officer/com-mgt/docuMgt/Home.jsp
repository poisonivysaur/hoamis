<%-- 
    Document   : Home
    Created on : Oct 16, 2017, 1:41:14 AM
    Author     : Leebet Barraquias
--%>

<%@page import="model.dao.FolderDAO"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<%@ page import="model.User"%>
<%@ page import="model.dao.DocumentDAO"%>
<%@ page import="model.dao.UserDAO"%>
<%@ page import="model.Usergroup"%>
<% 
   
    ResultSet rst = (ResultSet)session.getAttribute("groups");
    ResultSet groupsAvailable = (ResultSet)session.getAttribute("groups");
    ResultSet rstt = (ResultSet)session.getAttribute("users");
    Usergroup q = new Usergroup();
    int curFolder = Integer.parseInt((String)session.getAttribute("curFolder"));
    User user = (User)session.getAttribute("UserObj");
    
    ResultSet files = (ResultSet)DocumentDAO.getFolderDocuments(user.getUserID(),curFolder);
    ResultSet folders = (ResultSet)FolderDAO.getChildFolders(curFolder,user.getUserID());
    
    groupsAvailable.beforeFirst();
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
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ourFiles/res/ba.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/ourFiles/res/gr.css">
    <style>
       .custom-menu {
    display: none;
    z-index: 1000;
    position: absolute;
    overflow: hidden;
    border: 1px solid #CCC;
    white-space: nowrap;
    font-family: sans-serif;
    background: #FFF;
    color: #333;
    text-align: left;
    border-radius: 5px;
}
.custom-menu div{
    
    list-style: none;
    padding-left: 0;
}
.custom-menu li {
    padding: 8px 12px;
    cursor: pointer;
    
    list-style: none;
    
}
.custom-menu li:hover {
    background-color: #DEF;
    
}
    </style>
    <div id="myModal" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
          <span id="span1" class="close">&times;</span>
          <center>
            <form action="AddFolder" method="POST">
                <input type="hidden" name="uname" value="<%= user.getUserID() %>">
                <h2 style="margin: 0px">Create a new Folder</h2>
                <br>
                
                <label>Name</label>
                <input type="text" class="group" style="display: inline-block;" placeholder="New folder name." name="foldername">
                <br><br>
                <label>Description</label>
                <input type="text" class="group" style="display: inline-block" placeholder="Folder description." name="folderdesc">
                <br><br>
                <button class="button-src" style="display: inline-block" type="submit">Create</button>
            </form>
          </center>
        </div>

      </div>
    <div id="modal2" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
          <span id="span2" class="close">&times;</span>
          <form method="post" action="ShareDocument">
              <input type="hidden" id="fileType" name="fileType" value="">
              <input type="hidden" id="fileId" name="fileId" value="">
                    <h2 style="margin: 5px">Share with other groups</h2>
                    <hr>
                    <center><input type="text" class="inpt" id="filenm" name="fileName" value="GAGO KA BA WAHAHAH" readonly></center>
                    
                    <br>
                    <label><b>Permissions: </b></label>
                    <br>
                    <input type="checkbox" name="permissions" value="read" required> Read<br>
                    <input type="checkbox" name="permissions" value="update" > Update<br>
                    <input type="checkbox" name="permissions" value="delete" > Delete<br>
                    <label><b>Groups: </b></label>
                    <br>
                    <select name="groups" style=" 
                                    border: 1px solid #AAA;
                                    color: #555;
                                    font-size: inherit;
                                    
                                    overflow: scroll;
                                    padding: 5px 10px;
                                    text-overflow: ellipsis;
                                    white-space: nowrap;
                                    width: 300px;">
                        <%
                           rst.beforeFirst();
                           while(groupsAvailable.next())
                           {     
                        %> 
                            <option value="<%= groupsAvailable.getString("userGroupID")%>"><%= groupsAvailable.getString("groupname")%></option>
                        <%   
                                
                        }
                            groupsAvailable.beforeFirst();
                        %>
                    </select>
                    <br>
                    <center><button class="button-src" type="submit">SHARE</button></center>
          </form>
        </div>
      </div>
    <body>
        <div class="template">    
            <div class="action-bar">
                <div class="col-3" style="display: inline-block">
                    HOAMIS DRIVE
                        <input type="text" class="search" style="margin-left: 20px" name="search" placeholder="Search For Groups!">
                </div>
                <div class="col-3" style="text-align: right; display: inline-block">
                    
                        <% if (session.getAttribute("uname")=="null") response.sendRedirect("index.jsp");
                           else out.println(user.getfName() +" "+user.getlName());%>
             
                               <a href="Login">Main Menu</a>

                </div>

            </div>
            
            <br><br>
           
        <div class="container" id="cont">        
            <main class="content">
                <div class="row"  style="padding:0px;margin:0px;background-color: #5a6c89; ">
                    <form action="AddDocument" method="post" enctype="multipart/form-data" style="display:inline-block">
                        <input type="text" style="display:none" name="action" value="upload">
                        <div id="result">
                           <% if (session.getAttribute("message")!=null){ %>
                               <script>
                                    window.onload = function() {

                                        var x = document.getElementById("snackbar");
                                        x.className = "show";
                                        setTimeout(function(){ 
                                        x.className = x.className.replace("show", ""); }, 3000);
                                    }
                                </script>
                           <% } %>
                        </div>

                        <div class="hide" style="display: inline-block; padding: 5px; margin-left:15px;">
                           <label style="cursor: pointer; display:inline-block">
                               <i class="fa fa-cloud-upload fa-lg"></i> UPLOAD FILE 
                                    <input style="display: none;" type="file" id="fileSelect2" onchange="showImage(this);" name="file"/>
                           </label>   
                        </div>
                        <br>
                        <button class="button-src" id="upBtn" name="submit" value="putangina" type="submit" style="display: none"> UPLOAD</button>
                    </form>
                        <button class="button-src" id="myBtn" name="submit" value="putangina" type="button" style="display: inline-block"> <i class="fa fa-folder-o fa-lg"></i>
                            NEW FOLDER</button>
                        <button class="button-src" id="btn2" name="submit" value="putangina" type="button" style="display: none"> <i class="fa fa-folder-o fa-lg"></i>
                            Share File</button>
                        <div class="dropdown">
                        <button class="dropbtn">YOUR DRIVE <i class="fa fa-hdd-o fa-2x"></i></button>
                        <div class="dropdown-content">
                            <a href="HomeDocu?fid=0"><i class="fa fa-hdd-o" style="font-size:18px"></i>  Your Drive</a>
                            <hr>
                            <center><label><i class="fa fa-users" style="font-size:18px"></i>  Shared Drives</a></label></center>
                            <% rst.beforeFirst(); while(rst.next()){%>
                                <a href="SharedHomeDocu?fid=0&gid=<%out.println(rst.getInt("userGroupID"));%>"><%out.println(rst.getString("groupname"));%></a>
                            <%}%>
                        </div>
                        </div>
                        
                            <label class="dropbtn" style="display: inline-block; color: white; float:right; margin-right: 5px"></label>
                 </div>
                    <div class="col-6" >
                        <div class="row" style="min-height: 50vh;">
                            
                            <% 
                                System.out.println("WAWAWAWAW");
                                if(files.isBeforeFirst()==false && folders.isBeforeFirst()==false){ %> 
                            <center style="color: #e8ecf2"><h1> No files in this storage</h1> <i class="fa fa-dropbox" style="font-size: 50px"></i></center>
                            <%}%>
                            
                            <%  files.beforeFirst();
                                folders.beforeFirst();
                                
                                int ctr1=0;while(folders.next()){%>
                             <div class="col-1" style="width:auto; text-align: center ">
                                 <form method="get" action="HomeDocu">
                                    <div id="item-0<%=ctr1%>" class="item" style="display:inline-block; padding: 5px">
                                        <img src="${pageContext.request.contextPath}/ourFiles/assets/file-ext/folder.png" alt="Avatar" style="width:100px;height: 80px"><br>
                                        <label><%out.print(folders.getString("folderName"));%></label>
                                        <input type="hidden" id="item-id-0<%=ctr1%>" value="<%out.print(folders.getString("folderName"));%>">
                                        <input type="hidden" id="item-id-0DOC<%=ctr1%>" value="<%out.print(folders.getInt("folderID"));%>">
                                    </div>
                                        <input type="hidden" name="fid" value="<%out.print(folders.getString("folderID"));%>"/>
                                    <button type="submit" id="btn-<%=ctr1%>" style="display:none"></button>
                                  </form>
                             </div>
                              <form action="DeleteFolder" method="POST">
                                  <input type="hidden" name="folderID" value="<%out.print(folders.getInt("folderID"));%>">
                                  <button type="submit" id="del-0<%=ctr1%>" style="display:none"></button>
                              </form>
                              <div class='custom-menu' id="custom-menu-0<%=ctr1%>">
                                  <li data-action = "first"><i class="fa fa-user-plus" style="font-size:24px"> </i> Share Folder</a><br>
                                  <li data-action = "second"><i class="fa fa-trash-o" style="font-size:24px"> </i>  Delete Folder</a><br>
                              </div>
                              
                            <% ctr1++;}
                                folders.beforeFirst();
                            %>
                                <% int ctr=0; while(files.next()){%>
                                
                             <div class="col-1" style="width:auto; text-align: center ">
                                     <div id="item-<%=ctr%>" class="item" style="display:inline-block; padding: 5px">
                                         <img src="${pageContext.request.contextPath}/ourFiles/assets/file-ext/<%out.print(DocumentDAO.getFileExtension(files.getString("description")));%>.png" alt="Avatar" style="width:100px;height: 80px"><br>
                                         <label><%out.print(files.getString("description"));%></label>
                                         <input type="hidden" id="item-id-<%=ctr%>" value="<%out.print(files.getString("description"));%>">
                                         <input type="hidden" id="item-id-DOC<%=ctr%>" value="<%out.print(files.getInt("documentID"));%>">
                                     </div>
                              </div>
                              <form action="DeleteDocument" method="POST">
                                  <input type="hidden" name="docid" value="<%out.print(files.getInt("documentID"));%>">
                                  <button type="submit" id="del-<%=ctr%>" style="display:none"></button>
                              </form>
                              <form action="DownloadDocument" method="POST">
                                  <input type="hidden" name="DL" value="<%out.print(files.getString("documentLocation"));%>">
                                  <button type="submit" id="dl-<%=ctr%>" style="display:none"></button>
                              </form>
                              <div class='custom-menu' id="custom-menu-<%=ctr%>">
                                <li data-action = "first"><i class="fa fa-user-plus" style="font-size:24px"> </i> Share</a><br>
                                <li data-action = "second"><i class="fa fa-trash-o" style="font-size:24px"> </i>  Delete File</a><br>
                                <li data-action = "third"><i class="fa fa-download" style="font-size:24px"> </i>  Download File</a><br>
                              </div> 
                                <%ctr++;}
                                 files.beforeFirst();
                                %>
                        </div>  
                    </div>
                </div>
            </main>
            <footer class="primary-footer">
                <div class="container">
                    <small>&copy; HOAMIS Community Groups Module by Mark.</small>
                </div>
            </footer>
                           
                           <div id="snackbar">Allah wakbar <% out.println(session.getAttribute("message")); session.removeAttribute("message"); %></div>

        </div>
    </body>
</html>

<script>

<%for(int i =0;i<ctr;i++){%>
    // Trigger action when the contexmenu is about to be shown
    $('#item-<%=i%>').bind("contextmenu", function (event) {
        // Avoid the real one
        event.preventDefault();

        // Show contextmenu
        $("#custom-menu-<%=i%>").finish().toggle(100).
                
        // In the right position (the mouse)
        css({
            top: event.pageY + "px",
            left: event.pageX + "px"
        });
        
        $('#item-<%=i%>').css("border-color", '#5a6c89');
        $('#item-<%=i%>').css("border-style", 'solid');
        $('#item-<%=i%>').css("border-radius", '3px');
        
        
    });
<%}%>
<%for(int i =0;i<ctr1;i++){%>
    // Trigger action when the contexmenu is about to be shown
    $('#item-0<%=i%>').bind("contextmenu", function (event) {
        // Avoid the real one
        event.preventDefault();

        // Show contextmenu
        $("#custom-menu-0<%=i%>").finish().toggle(100).
                
        // In the right position (the mouse)
        css({
            top: event.pageY + "px",
            left: event.pageX + "px"
        });
        
        $('#item-0<%=i%>').css("border-color", '#5a6c89');
        $('#item-0<%=i%>').css("border-style", 'solid');
        $('#item-0<%=i%>').css("border-radius", '3px');
        
        
    });
<%}%>
// If the menu element is clicked

<%for(int i =0;i<ctr1;i++){%>
$("#custom-menu-0<%=i%> li").click(function(){
    // This is the triggered action name
    switch($(this).attr("data-action")) {
        // A case for each action. Your actions here
        case "first": 
            $('#filenm').val($('#item-id-0<%=i%>').val());
            $('#fileId').val($('#item-id-0DOC<%=i%>').val());
            $('#fileType').val("folder");
            
            document.getElementById("btn2").click();
            break;
        case "second":
            
            if (confirm("Are you sure!") === true) {
                
            document.getElementById("del-0<%=i%>").click();
                
            } else {
                txt = "Eut";
            }
            break;
        
    }
  
    // Hide it AFTER the action was triggered
    $(".custom-menu").hide(100);
  });
<%}%>  

<%for(int i =0;i<ctr;i++){%>
$("#custom-menu-<%=i%> li").click(function(){
    
    // This is the triggered action name
    switch($(this).attr("data-action")) {
        
        // A case for each action. Your actions here
        case "first": 
            $('#filenm').val($('#item-id-<%=i%>').val());
            $('#fileId').val($('#item-id-DOC<%=i%>').val());
            $('#fileType').val("document");
            
            document.getElementById("btn2").click();
            break;
        case "second":
            if (confirm("Are you sure!") === true) {
                
                $('#del-<%=i%>').click();
            } else {
                txt = "Eut";
            }
            break;
        case "third":
            $('#dl-<%=i%>').click();
            break;
        
    }
  
    // Hide it AFTER the action was triggered
    $(".custom-menu").hide(100);
  });
<%}%>  
// If the document is clicked somewhere
$(document).bind("mousedown", function (e) {
    // If the clicked element is not the menu
    if (!$(e.target).parents(".custom-menu").length > 0) {
        // Hide it
        $(".custom-menu").hide(100);
        
        $('.item').css("border-color", '');
        $('.item').css("border-style", '');
        $('.item').css("border-radius", '');
    }
});


$(document).click(function(e){
    if($(e.target).closest('.item').length === 0){
        $('.item').css("border-color", '');
        $('.item').css("border-style", '');
        $('.item').css("border-radius", '');
    }
});
// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementById("span1");

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
// Get the modal
var modal2 = document.getElementById('modal2');

// Get the button that opens the modal
var btn2 = document.getElementById("btn2");

var span2 = document.getElementById("span2");

// When the user clicks the button, open the modal 
btn2.onclick = function() {
    modal2.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span2.onclick = function() {
    modal2.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}
<%for(int i =0;i<ctr1;i++){%>
    $('#item-0<%=i%>').dblclick(function() {
        
        document.getElementById('btn-<%=i%>').click();
        $('#btn-<%=i%>').click();
        
    });
<%}%>

<%for(int i =0;i<ctr1;i++){%>
    $('#item-0<%=i%>').click(function() {
        
        $('.item').css("border-color", '');
        $('.item').css("border-style", '');
        $('.item').css("border-radius", '');

        $('#item-0<%=i%>').css("border-color", '#5a6c89');
        $('#item-0<%=i%>').css("border-style", 'solid');
        $('#item-0<%=i%>').css("border-radius", '3px');
        
        
    });
<%}%>



<%for(int i =0;i<ctr;i++){%>
    $('#item-<%=i%>').click(function() {
        
        $('.item').css("border-color", '');
        $('.item').css("border-style", '');
        $('.item').css("border-radius", '');

        $('#item-<%=i%>').css("border-color", '#5a6c89');
        $('#item-<%=i%>').css("border-style", 'solid');
        $('#item-<%=i%>').css("border-radius", '3px');
        
        
    });
<%}%>

 function showImage(input) {
        document.getElementById("upBtn").click();
    }

</script>
<%
%>