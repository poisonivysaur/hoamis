<%-- 
    Document   : userhome
    Created on : 11 7, 17, 10:25:33 PM
    Author     : Miguel
--%>
<%@page import="model.dao.Document"%>
<%@page import="model.dao.Penalty"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="model.User"%>
<%@ page import="dao.Policy"%>
<%  
    ResultSet rs = Policy.sql_getAllRetiredPolicies();
    User user = (User)session.getAttribute("loginUser");
    String userID = user.getUserID();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Retired Policies</title>
    </head>
    
    <body>
        <h1>Retired Policies</h1>
        <h3>Welcome: <%= userID %></h3>
        <h4><a href="OfficerMain?action=policy"> Home </a></h4>
        <h4><a href="OfficerMain?action=addPolicy"> Add Policy </a></h4>     
        <h4><a href="OfficerMain?action=retirePolicy"> Retire a Policy </a></h4>     
        <% 
        while (rs.next()) { %>
        <div><p>
        <%out.print("Policy Number: " + rs.getString(1) + " | Description: " + rs.getString(2) + " | Enactment Date: " 
        + rs.getString(5) + " | Stop Implement Date: " + rs.getString(6) + " | Enabling Board: " + rs.getString(7) + "<br />");
        }
        %>
        </p></div>
        <h4><a href="OfficerMain?action=viewAllPolicy"> View All Active Policies </a></h4>
    </body>
</html>
