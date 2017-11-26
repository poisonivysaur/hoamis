<%-- 
    Document   : home
    Created on : 11 25, 17, 11:16:46 AM
    Author     : Yuta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
User selectedUser = (User) request.getAttribute("selectedUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Officer Directory</title>
        <%@include file="../../base-officer.jsp" %>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <img src="<%=selectedUser.getPhoto().getDocumentLocation()%>" width="200" height="200">
            <h2><%= selectedUser.getfName() + ", " + selectedUser.getlName()%></h2>
            <h2>Address: Walang Address</h2>
            <% if(selectedUser.getUserTypeString().equals(User.USERTYPE6)){ %>
            <h2>Occupation: Kasambahay</h2>
            <% } else{ %>
            <h2>Occupation: <%= selectedUser.getOccupation().getOccupation() %></h2>
            <% } %>
            <h2>User Type: <%= selectedUser.getUserTypeString() %></h2>
            <% if(selectedUser.getUserTypeString() == User.USERTYPE1){ %>
            <a href="/hoamis/OfficerMain?action=billingViewDetails&userid=<%= selectedUser.getUserID() %>&fname=<%= selectedUser.getfName() %>&lname=<%= selectedUser.getlName() %>&mname=<%= selectedUser.getmName() %>"><button class="w3-button w3-teal w3-round">Check Billing</button></a>
            <% } %>
            <!--
            <h3>Home Owner</h3>
            <h3>Kasambahay</h3>
            <h3>Home Member</h3>
            <h3>Pets</h3>
            -->
        </div>
    </body>
</html>
