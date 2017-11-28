<%-- 
    Document   : addpolicy
    Created on : 11 25, 17, 3:00:34 AM
    Author     : Miguel
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="model.User"%>
<%@ page import="dao.Policy"%>
<%@ page import="dao.Penalty"%>
<%@ page import="dao.Document"%>
<%@ page import="java.sql.*"%>
<%  
    User user = (User)session.getAttribute("loginUser");
    String userID = user.getUserID();
    Penalty p = new Penalty();
    ResultSet allPens = p.sql_getAllPenalties();
    Document d = new Document();
    ResultSet allDocs = d.sql_getAllDocuments();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% System.out.println("Add Policy Page"); %>
        <title>Add Policy</title>
    </head>
    <body>
        <h4><a href="OfficerMain?action=policy"> Home </a></h4>
        <h4><a href="OfficerMain?action=viewAllPolicy"> View All Policies </a></h4>
        <h4><a href="OfficerMain?action=retirePolicy"> Retire a Policy </a></h4>     
        <form action="AddPolicyProcess" method="POST">
            <fieldset>
                <h2>Add Policy</h2>
                <p>Input Policy ID: <input type="text" name="policyID"/></p>
                <p>Input Policy Description: <input type="text" name="description"/></p>
                <p>Penalty: <select name ="penalty">
                    <option value="0">-- Select --</option> 
                        <%
                           while(allPens.next())
                               
                           {
                                   
                        %> 
                            <option value="<%= allPens.getString("penaltyID")%>"><%= allPens.getString("penaltydescription")%></option>
                        
                        <%   
                                
                        }
                            allPens.beforeFirst();
                        %>
                </select></p>
                <p>Document: <select name ="document">
                    <option value="0">-- Select --</option>  
                    <%
                           while(allDocs.next())
                               
                           {
                                   
                        %> 
                            <option value="<%= allDocs.getString("documentID")%>"><%= allDocs.getString("description")%></option>
                        
                        <%   
                                
                        }
                            allDocs.beforeFirst();
                        %>
                </select></p>
                Enabling Board Member: <%= userID %>
                <p><input type="submit" value="submit"/></p>
            </fieldset>
    </body>
</html>
