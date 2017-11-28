<%-- 
    Document   : Dashboard
    Created on : 11 23, 17, 10:18:54 PM
    Author     : Austin Pimentel
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="model.dao.DatabaseUtils"%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ViewSecurityViolations</title>
</head>
<body>

<div class="container">
	<h2 align="center"><font><strong>View Security Violations</strong></font></h2>
	<hr />
	
	
	<%
	Connection connection = DatabaseUtils.retrieveConnection();
	Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        Statement statement4 = null;
        Statement statement5 = null;
        Statement statement6 = null;
        
	ResultSet resultSet = null;
        ResultSet resultSet2 = null;
        ResultSet resultSet3 = null;
        ResultSet resultSet4 = null;
        ResultSet resultSet5 = null;
        ResultSet resultSet6 = null;
        
        String securityReportID = null;
	%>
	
	
	<table class="table" align="center" cellpadding="5" cellspacing="5" border="1" style="margin-top: 10px;">
	<thead>
        <th>Report Date</th>
        <th>Complaint</th>
        <th>Amount</th>
        <th>Complainant</th>
        <th>Accused</th>
	</thead>
	<%
            String userID = "1";//this is the id of the securitypersonnel
	try{ 

	statement=connection.createStatement();
	String sql ="SELECT securityReportID, reportDate, complaint, t.totalamount FROM mydb.security_violations sv JOIN mydb.trxreferences t on sv.trxID = t.trxID WHERE securityID ="+userID+";";

	resultSet = statement.executeQuery(sql);
	while(resultSet.next()){
           
           securityReportID = resultSet.getString("securityReportID");
	%>  
	<tr>
	<td><%=resultSet.getDate("reportDate") %></td>
        <td><%=resultSet.getString("complaint") %></td>
        <td><%=resultSet.getString("totalamount") %></td>
	
	
	<% //the codes below gets the accused and complaining from tables using the securityReportid from the first sql statement
            statement2=connection.createStatement();
            String sql2 ="SELECT fname, lname FROM mydb.user2user uu JOIN users u on u.userID = uu.accused_userID WHERE securityReportID ="+securityReportID;
            //this code gets the accused details from user2user table
            resultSet2 = statement2.executeQuery(sql2);
            while(resultSet2.next()){
                out.print("<td>"+resultSet2.getString("fname"));
                out.print(" "+resultSet2.getString("lname")+"</td>");
            }
            statement3=connection.createStatement();
            String sql3 ="SELECT fname, lname FROM mydb.user2user uu JOIN users u on u.userID = uu.complainant_userID WHERE securityReportID ="+securityReportID;
            //this code gets the complaintant details from the user2user table
            resultSet3 = statement3.executeQuery(sql3);
            while(resultSet3.next()){
                out.print("<td>"+resultSet3.getString("fname"));
                out.print(" "+resultSet3.getString("lname")+"</td></tr>");
            }
            statement4=connection.createStatement();
            String sql4 ="SELECT fname, lname, otherparty FROM mydb.user2anyone uu JOIN users u on u.userID = uu.userID WHERE securityReportID ="+securityReportID;
            //this code gets the other party and the user details from user2anyone table 
            resultSet4 = statement4.executeQuery(sql4);
            while(resultSet4.next()){
                out.print("<td>"+resultSet4.getString("otherparty")+"</td>");
                out.print("<td>"+resultSet4.getString("fname"));
                out.print(" "+resultSet4.getString("lname")+"</td></tr>");
                
            }
            statement5=connection.createStatement();
            String sql5 ="SELECT fname, lname, platenum FROM mydb.vehicle2user vu JOIN users u on u.userID = vu.userID WHERE securityReportID ="+securityReportID;
            //this code gets the user and the vehichle details from the vehicle 2user table
            resultSet5 = statement5.executeQuery(sql5);
            while(resultSet5.next()){
                out.print("<td>"+resultSet5.getString("fname"));
                out.print(" "+resultSet5.getString("lname")+"</td>");
                out.print("<td>"+resultSet5.getString("platenum")+"</td></tr>");
            }
            
            statement6=connection.createStatement();
            String sql6 ="SELECT complainantplatenum, accusedplatenum FROM mydb.vehicle2vehicle vv where securityReportID ="+securityReportID;
            //this code gets the acccused and complaintant platenumber details from the vehicle2cehicle table
            resultSet6 = statement6.executeQuery(sql6);
            while(resultSet6.next()){
                out.print("<td>"+resultSet6.getString("complainantplatenum")+"</td>");
                out.print("<td>"+resultSet6.getString("accusedplatenum")+"</td></tr>");
            }
	}
	
	} catch (Exception e) {
	e.printStackTrace();
	}
	%>
	</table>
</div>

</body>
</html>
