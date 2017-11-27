<%-- 
    Document   : directory
    Created on : 11 24, 17, 2:47:14 PM
    Author     : Yuta
--%>

<%@page import="model.dao.DirectoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<User> allUsers = DirectoryDAO.getAllUsers();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Officer Directory</title>
        <%@include file="../../base-homeowner.jsp" %>
    </head>
    <body>
        <!-- !PAGE CONTENT! -->
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Officer Directory</h1>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Picture</th>
                    <th>Name</th>
                    <th>User Type</th>
                    <th>See Further Information</th>
                </tr>
                <% for(User u : allUsers){ %>
                <tr>
                    <td><img src="<%= u.getPhoto().getDocumentLocation()%>" width="80" height="80"></td>
                    <td><%= u.getfName() + ", " + u.getlName()%></td>
                    <td><%= u.getUserTypeString()%></td>
                    <td><a href="Directory?userid=<%= u.getUserID()%>"><button class="w3-button w3-teal w3-round">Go</button></a></td>
                </tr>
                <% } %>
            </table><!--/.w3table-->
        </div><!--/.w3-main-->
    </body>
</html>
