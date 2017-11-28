<%-- 
    Document   : updateReports
    Created on : 11 26, 17, 11:44:00 PM
    Author     : Jennifer Ibay
--%>

<%@page import="model.dao.DatabaseUtils"%>
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
            Connection conn = DatabaseUtils.retrieveConnection();
            
            String sql = "SELECT securityReportID FROM SECURITY_VIOLATIONS";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            

        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update a report's Resolution</title>
    </head>
    <body>
     <form action=updateResolutionServlet method="POST" align="center" id="statusserv">
         
         <h1>Update a Report's Resolution</h1><br>
         <table align="center"><tr>
                    <td>Security Report's ID no: </td>
                    <td><select name = "securityReportID">
                        <option value=""> --Choose Report's ID #-- </option>
                        <%
                        while(rs.next()){
                        int id8 = rs.getInt("securityReportID");
                        String d9 = Integer.toString(id8);
                        %>
                        <option value="<%=id8 %>"><%=d9 %></option>
                        <%
                        }
                        %>
                    </select></td></tr>
                
                <tr>
                    <td>Resolution: </td>
                    <td><textarea name="resolution"></textarea></td>
                </tr></table><br>
                <a href="${pageContext.request.contextPath}/SecurityMain?action=security"><button type ="button"> Go Back</button></a> &nbsp; &nbsp; <input type="submit" value="Submit">
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
