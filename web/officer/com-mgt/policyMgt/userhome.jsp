<%-- 
    Document   : userhome
    Created on : 11 7, 17, 10:25:33 PM
    Author     : Miguel
--%>
<%@page import="dao.Policy"%>
<%@page import="dao.Document"%>
<%@page import="dao.Penalty"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="model.User"%>
<%  
    ResultSet rs = Policy.sql_getAllPolicies();
    User user = (User)session.getAttribute("loginUser");
    String userID = user.getUserID();
    Document d = new Document();
    int pdocumentID = d.sql_getDocumentID("dummydoc");
    Penalty p = new Penalty();
    int ppenaltyID = p.sql_getPenaltyID("A");   
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    
    <body>
        <h1>Policy Dashboard</h1>
        <h3>Welcome: <%= userID %></h3>
        <h4><a href="OfficerMain"> Back to Main Menu </a></h4>
        <h4><a href="OfficerMain?action=viewAllPolicy"> View All Policies </a></h4>
        <h4><a href="OfficerMain?action=addPolicy"> Add a Policy </a></h4>        
        <h4><a href="OfficerMain?action=retirePolicy"> Retire a Policy </a></h4>     
    </body>
</html>
