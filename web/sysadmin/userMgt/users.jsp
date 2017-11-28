<%-- 
    Document   : users
    Created on : 11 26, 17, 1:17:31 PM
    Author     : Yuta
--%>

<%@page import="model.dao.DirectoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<User> allUsers = DirectoryDAO.getAllUsers();
String msg = (String)session.getAttribute("msg2");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>System Admin User Management</title>
        <%@include file="../base-sysadmin.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>User Management</h1>
            <% if(msg != null){ %>
            <p><%= msg%></p>
            <% session.removeAttribute("msg2");} %>
            <input type="text" id="myInput" onkeyup="filter()" placeholder="Search for homeowner" size="50"><br /><br />
            <table class="w3-table w3-striped w3-white w3-hoverable" id="userTable">
                <tr>
                    <th>Picture</th>
                    <th>User ID</th>
                    <th>Name</th>
                    <th>User Type</th>
                    <th>Action</th>
                </tr>
                <% for(User u : allUsers){ %>
                <tr>
                    <td><img src="<%= u.getPhoto().getDocumentLocation()%>" width="60" height="60"></td>
                    <td><%= u.getUserID() %></td>
                    <td><%= u.getfName() + " " + u.getlName() %></td>
                    <td><%= u.getUserTypeString() %></td>
                    <td><button class="w3-button w3-teal w3-round" onclick='insertInput("<%= u.getUserID() %>")'>Remove User</button></td>
                </tr>
                <% } %>
            </table>
            <form action="DeactivateAccount" method="post" id="f">
                <input type="hidden" name="userid" value="" id="u">
            </form>
        </div>
    </body>
    <script>
        function insertInput(userid){
            $('#u').val(userid);
            $('#f').submit();
        }
        
        function filter() {
            // Declare variables 
            var input, filter, table, tr, td, i;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("userTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                } 
            }
        }
    </script>
</html>
