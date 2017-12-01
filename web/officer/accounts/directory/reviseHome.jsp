<%-- 
    Document   : reviseHome
    Created on : 12 1, 17, 9:46:49 AM
    Author     : Yuta
--%>

<%@page import="model.Homeowner"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
Homeowner selectedUser = (Homeowner) request.getAttribute("selectedUser");
String pattern = "yyyy-MM-dd";
SimpleDateFormat sdf = new SimpleDateFormat(pattern);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Officer Directory</title>
        <%@include file="../../base-officer.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1><%= selectedUser.getfName() + ", " + selectedUser.getlName()%> Household</h1>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Homeowner</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Occupation</th>
                    <th>Move In Date</th>
                </tr>
                <tr>
                    <td><img src="<%= selectedUser.getPhoto().getDocumentLocation()%>" width="80" height="80"></td>
                    <td><%= selectedUser.getfName()%></td>
                    <td><%= selectedUser.getlName()%></td>
                    <td><%= selectedUser.getOccupation().getOccupation()%></td>
                    <td><%= sdf.format(selectedUser.getMovingIn()) %></td>
                </tr>
            </table>
            <p></p>
        </div>
    </body>
</html>
