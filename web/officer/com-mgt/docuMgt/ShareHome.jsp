<%-- 
    Document   : Home
    Created on : Oct 16, 2017, 1:41:14 AM
    Author     : Leebet Barraquias
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.dao.FolderDAO"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ page import="model.User"%>
<%@ page import="model.dao.DocumentDAO"%>
<%@ page import="model.dao.UserDAO"%>
<%@ page import="model.Usergroup"%>

<%@ page import="model.dao.DocumentPermissionDAO"%>

<%@ page import="model.dao.FolderPermissionDAO"%>
<% 
    ResultSet folders;
    ResultSet files ;
    
    ResultSet rst = (ResultSet)session.getAttribute("groups");
    ResultSet groupsAvailable = (ResultSet)session.getAttribute("groups");
    ResultSet rstt = (ResultSet)session.getAttribute("users");
    
    rst.beforeFirst();
    
    int curFolder = Integer.parseInt((String)session.getAttribute("curFolder"));
    int groupID = Integer.parseInt(request.getParameter("gid"));
    User user = (User)session.getAttribute("UserObj");
    Usergroup group = new Usergroup(groupID);
    
    if(curFolder == 0 ){
         files = (ResultSet) DocumentDAO.getSharedDocuments(groupID, user.getUserID());
         folders = (ResultSet) FolderDAO.getSharedFolders(groupID, user.getUserID());
         
         files.beforeFirst();
         folders.beforeFirst();
    }else{
         folders = null;
         files = (ResultSet) DocumentDAO.getFolderDocuments(user.getUserID(), curFolder);
         
         files.beforeFirst();
    }  
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
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
    
     <body>
        <div class="template">    
            <div class="action-bar">
                <div class="col-3" style="display: inline-block">
                    HOAMIS GROUP DRIVES
                        <input type="text" class="search" style="margin-left: 20px" name="search" placeholder="Search For Groups!">
                </div>
                <div class="col-3" style="text-align: right; display: inline-block">
                    
                        <% if (session.getAttribute("uname")=="null") response.sendRedirect("index.jsp");
                           else out.println(user.getfName() +" "+user.getlName());%> 
                 <a href="OfficerMain">Main Menu</a>
                
                 
                </div>

            </div>
            
            <br><br>
           
        <div class="container" id="cont">        
            <main class="content">
                <div class="row"  style="padding:0px;margin:0px;background-color: #5a6c89; ">
                   
                             <label class="button-src" style="display: inline-block; color: white; ">
                                <% out.println(group.getGroupname());%>
                             </label>
                    
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
                       
                    </form>
                       
                       
                        <div class="dropdown">
                        <button class="dropbtn">YOUR DRIVE <i class="fa fa-hdd-o fa-2x"></i></button>
                        <div class="dropdown-content">
                            <a href="HomeDocu?fid=0"><i class="fa fa-hdd-o" style="font-size:18px"></i>  Your Drive</a>
                            <hr>
                            <center><label><i class="fa fa-users" style="font-size:18px"></i>  Shared Drives</a></label></center>
                             <%while(rst.next()){%>
                                <a href="SharedHomeDocu?fid=0&gid=<%out.println(rst.getInt("userGroupID"));%>"><%out.println(rst.getString("groupname"));%></a>
                            <%}%>
                          
                        </div>
                        </div>
                        
                            <label class="dropbtn" style="display: inline-block; color: white; float:right; margin-right: 5px"></label> 
                                

                 </div>
                    <div class="col-6" >
                        <div class="row" style="min-height: 50vh;">
                            
                            <% if(files.isBeforeFirst()==false && folders.isBeforeFirst()==false){ %> 
                            <center style="color: #e8ecf2"><h1> No files in this storage</h1> <i class="fa fa-dropbox" style="font-size: 50px"></i></center>
                            <%}%>
                            
                            <%  
                            int ctr1=0;
                            if(folders!=null){
                                while(folders.next()){%>
                             <div class="col-1" style="width:auto; text-align: center ">
                                 <form method="get" action="SharedHomeDocu">
                                    <div id="item-0<%=ctr1%>" class="item" style="display:inline-block; padding: 5px">
                                        <img src="${pageContext.request.contextPath}/ourFiles/assets/file-ext/folder.png" alt="Avatar" style="width:100px;height: 80px"><br>
                                        <label><%out.print(folders.getString("folderName"));%></label>
                                        
                                        <input type="hidden" id="item-id-0<%=ctr1%>">
                                    </div>
                                        <input type="hidden" name="fid" value="<%out.print(folders.getString("folderID"));%>"/>
                                        <input type="hidden" name="gid" value="<%out.print(groupID);%>"/>
                                    <button type="submit" id="btn-<%=ctr1%>" style="display:none"></button>
                                  </form>
                                  
                             </div>
                              <form action="DeleteFolder" method="POST">
                                  <input type="hidden" name="folderID" value="<%out.print(folders.getInt("folderID"));%>">
                                  <button type="submit" id="del-0<%=ctr1%>" style="display:none"></button>
                              </form>
                              <div class='custom-menu' id="custom-menu-0<%=ctr1%>">
                                  
                                  <%if (FolderPermissionDAO.hasDeletePermission(folders.getInt("folderID"),user.getUserID(), groupID)){%>
                                    <li data-action = "second"><i class="fa fa-trash-o" style="font-size:24px"> </i> Delete File</a><br>
                                  <%}%>
                                      
                              </div>
                              
                            <% ctr1++;}
                                    folders.beforeFirst();
                                }
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
                                  <%if (curFolder == 0){%>
                                    <%if (DocumentPermissionDAO.hasReadPermission(groupID, user.getUserID(),files.getInt("documentID"))){%>
                                       <li data-action = "third"><i class="fa fa-download" style="font-size:24px"> </i>  Download File</a><br>
                                    <%}%>
                                    <%if (DocumentPermissionDAO.hasDeletePermission(groupID, user.getUserID(),files.getInt("documentID"))){%>
                                      <li data-action = "second"><i class="fa fa-trash-o" style="font-size:24px"> </i>  Delete File</a><br>
                                  <%}}else{%>
                                    <%if (FolderPermissionDAO.hasReadPermission(curFolder, user.getUserID(),groupID)){%>
                                       <li data-action = "third"><i class="fa fa-download" style="font-size:24px"> </i>  Download File</a><br>
                                    <%}%>
                                    <%if (FolderPermissionDAO.hasDeletePermission(curFolder, user.getUserID(),groupID)){%>
                                      <li data-action = "second"><i class="fa fa-trash-o" style="font-size:24px"> </i>  Delete File</a><br>
                                    <%}%>
                                      
                                  <%}%>
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

</script>
<%
%>