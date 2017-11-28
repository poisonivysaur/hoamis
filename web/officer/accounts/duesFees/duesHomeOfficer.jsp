<%-- 
    Document   : duesSuccessOffice
    Created on : Nov 24, 2017, 6:37:27 PM
    Author     : justine
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="model.dao.MonthlyDuesDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Officer</title>
        <link rel="stylesheet" href="dues.css" type="text/css">
    </head>
    <body>
        <div class="navbar">
            <h2>MENU</h2>
            <a href="OfficerMain?action=dues"><button type="button">Home</button></a>
            <a href="OfficerMain?action=duesForm"><button type="button">Set Monthly Dues</button></a>
            <a href="OfficerMain?action=duesView"><button type="button">View All Monthly Dues</button></a>
        </div>
        <div class="container">
            <h1>Home</h1>
            <br>
            <%
                MonthlyDuesDAO mdd = new MonthlyDuesDAO();
                if(mdd.getCurrentMonthDues() == null){
                    out.print("<p><a href=\"OfficerMain?action=duesForm\">Notification: No monthly dues assigned for the month!</a></p>");
                }
                else{
                    out.print("<p>No notifications to display.</p>");
                }
            %>
            <a href="OfficerMain"><button type="button">Back to Dashboard</button></a>
        </div>
        
    </body>
</html>
