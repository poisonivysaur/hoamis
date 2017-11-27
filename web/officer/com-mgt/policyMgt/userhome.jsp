<%-- 
    Document   : userhome
    Created on : 11 7, 17, 10:25:33 PM
    Author     : Miguel
--%>
<%@page import="dao.Document"%>
<%@page import="dao.Penalty"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="dao.User"%>
<%@ page import="dao.Policy"%>
<%  
    ResultSet rs = Policy.sql_getAllPolicies();
    User user = (User)session.getAttribute("UserObj");
    String userID = (String)session.getAttribute("userID");
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
        <h1>User Dashboard</h1>
        <h3>Welcome: <%= userID %></h3>
        <h4><a href="viewpolicies.jsp"> View All Policies </a></h4>
        <h4><a href="addpolicy.jsp"> Add a Policy </a></h4>        
        <h4><a href="retirepolicy.jsp"> Retire a Policy </a></h4>     
    </body>
</html>
