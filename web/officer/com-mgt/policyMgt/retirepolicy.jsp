<%-- 
    Document   : addpolicy
    Created on : 11 25, 17, 3:00:34 AM
    Author     : Miguel
--%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="dao.User"%>
<%@ page import="dao.Policy"%>
<%@ page import="dao.Penalty"%>
<%@ page import="dao.Document"%>
<%@ page import="java.sql.*"%>
<%  
    User user = (User)session.getAttribute("UserObj");
    String userID = (String)session.getAttribute("userID");
    Policy po = new Policy();
    ResultSet allPolicies = po.sql_getAllPolicies();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% System.out.println("Retire Policy Page"); %>
        <title>Add Policy</title>
    </head>
    <body>
        <h4><a href="userhome.jsp"> Home </a></h4>
        <h4><a href="viewpolicies.jsp"> View All Policies </a></h4>
        <h4><a href="addpolicy.jsp"> Add Policy </a></h4>
        <form action="RetirePolicyProcess" method="POST">
            <fieldset>
                <h2>Retire Policy</h2>
                <p>Policy: <select name ="policy">
                    <option value="0">-- Select --</option> 
                        <%
                           while(allPolicies.next())
                               
                           {
                                   
                        %> 
                            <option value="<%= allPolicies.getString("policyID")%>"><%= allPolicies.getString("policydesc")%></option>
                        
                        <%   
                                
                        }
                            allPolicies.beforeFirst();
                        %>
                </select></p>
                <p><input type="submit" value="submit"/></p>                
            </fieldset>
        </form>
    </body>
</html>
