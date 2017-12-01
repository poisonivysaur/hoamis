<%-- 
    Document   : home
    Created on : 12 1, 17, 4:20:31 PM
    Author     : Yuta
--%>

<%@page import="model.Pet"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.dao.DirectoryDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Homeowner"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
User loginUser = (User)session.getAttribute("loginUser");
Homeowner selectedHomeowner = (Homeowner) request.getAttribute("selectedUser");
ArrayList<User> homemembers = DirectoryDAO.getHomeMember(selectedHomeowner);
ArrayList<User> kasambahays = DirectoryDAO.getKasambahay(selectedHomeowner);
ArrayList<Pet> pets = DirectoryDAO.getPetsByUserId(selectedHomeowner.getUserID());
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String msg = (String)session.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homeowner Directory</title>
        <%@include file="../../base-homeowner.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <br />
            <% if(loginUser.getUserID().equals(selectedHomeowner.getUserID())){ %>
            <% if(msg != null){ out.println("<p>" + msg + "</p>"); session.removeAttribute("msg");} %>
            <h1>Hello <%= loginUser.getfName() + ", " + loginUser.getlName() %>! Manage Your Directory here</h1>
            <img src="<%=selectedHomeowner.getPhoto().getDocumentLocation()%>" width="100" height="100">
            <h2>Home Owner: <%= loginUser.getfName() + ", " + loginUser.getlName()%></h2>
            <a href="HomeownerMain?action=petManagement" class="w3-button w3-teal w3-round">Manage Pets</a><span>&nbsp;&nbsp;&nbsp;</span><a href="HomeownerMain?action=serviceManagement" class="w3-button w3-teal w3-round">Manage Services</a>
            <% }else{ %>
            <img src="<%=selectedHomeowner.getPhoto().getDocumentLocation()%>" width="100" height="100">
            <h2>Home Owner: <%= selectedHomeowner.getfName() + ", " + selectedHomeowner.getlName() %></h2>
            <% } %>
            <br /><br />    
            <h2>Home Members</h2>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Home Member</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Occupation</th>
                    <th>Move In</th>
                </tr>
                <% for(User u : homemembers){ %>
                <tr>
                    <td><img src="<%= u.getPhoto().getDocumentLocation()%>" width="40" height="40"></td>
                    <td><%= u.getfName()%></td>
                    <td><%= u.getlName()%></td>
                    <td><%= u.getOccupation().getOccupation() %></td>
                    <td><%= sdf.format(u.getMovingIn()) %></td>
                </tr>
                <% } %>
            </table>
            <h2>Kasambahays</h2>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Kasambahay</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Occupation</th>
                    <th>Move In</th>
                </tr>
                <% for(User u : kasambahays){ %>
                <tr>
                    <td><img src="<%= u.getPhoto().getDocumentLocation()%>" width="40" height="40"></td>
                    <td><%= u.getfName()%></td>
                    <td><%= u.getlName()%></td>
                    <td><%= u.getOccupation().getOccupation() %></td>
                    <td><%= sdf.format(u.getMovingIn()) %></td>
                </tr>
                <% } %>
            </table>
            <h2>Pets</h2>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Pet</th>
                    <th>Animal Type</th>
                    <th>Pet Name</th>
                    <th>Vaccinated</th>
                </tr>
                <% for(Pet p : pets){ %>
                <tr>
                    <td><img src="<%= p.getPhoto().getDocumentLocation()%>" width="40" height="40"></td>
                    <td><%= p.getAnimalTypeString()%></td>
                    <td><%= p.getPetName()%></td>
                    <td><% if(p.isVaccinated()){out.print("Yes");}else{out.print("No");} %></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>
