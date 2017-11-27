<%-- 
    Document   : updateReports
    Created on : 11 26, 17, 11:44:00 PM
    Author     : Jennifer
--%>

<%@page import="dao.Database"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
        try{
         
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = Database.getDBConnection();
            
            String sql = "SELECT securityReportID FROM SECURITY_VIOLATIONS";
            String sql7 = "SELECT statusID, status FROM REF_VIOLATIONSTATUS";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps2 = conn.prepareStatement(sql7);
            
            ResultSet rs = ps.executeQuery(); 
            ResultSet rs2 = ps2.executeQuery();
            

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a report's status</title>
    </head>
    <body>
     <form action='updateStatusServlet' method="POST" align="center" id="statusserv">
         <h1>Update a Report's status</h1><br>
         <table align="center"><tr>
                    <td>Security Report's ID no: </td>
                    <td><select name = "securityReportID">
                        <option value=""> --Choose Report's ID #-- </option>
                        <%
                        while(rs.next()){
                        String id8 = rs.getString("securityReportID");
                        %>
                        <option value="<%=id8 %>"><%=id8 %></option>
                        <%
                        }
                        %>
                    </select></td></tr>
                
                <tr>
                    <td>New Status ID: </td>
                    <td><select name = "statusID">
                        <option value=""> --Choose New Status ID-- </option>
                        <%
                        while(rs2.next()){
                        String id7 = rs2.getString("statusID");
                        String id8 = rs2.getString("status");
                        %>
                        <option value="<%=id7 %>"><%=id8 %></option>
                        <%
                        }
                        %>
                    </select></td>
                </tr></table><br>
                <a href="Dashboard.jsp"><button type ="button"> Go Back</button></a> &nbsp; &nbsp; <input type="submit" value="Submit">
     </form>  
        <%
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
     </body>
</html>
