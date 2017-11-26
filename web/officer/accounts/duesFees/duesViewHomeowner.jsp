<%-- 
    Document   : duesSuccessOffice
    Created on : Nov 24, 2017, 6:37:27 PM
    Author     : justine
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="model.dao.MonthlyDuesDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Current Monthly Dues | Homeowner</title>
    </head>
    <body>
        <div class="navbar">
            <h2>MENU</h2>
            <a href="duesHomeHomeowner.jsp"><button type="button">Home</button></a>
            <a href="duesViewHomeowner.jsp"><button type="button">View Current Monthly Dues</button></a>
        </div>
        <h1>DUES</h1>
        <%
            MonthlyDuesDAO mdd = new MonthlyDuesDAO();
            double currentMonthDues = mdd.getCurrentHomeownerMonthlyDues();
            // I'm sorry idk how to get userID based on user that's logged in :(
            double unpaidFees = mdd.getUnpaidFees("userID");
        
            if (currentMonthDues == 0){
                out.print("<p>No registered monthly dues for this month. Contact an officer for more details.</p>");
            }
            else{
                out.print("<p><b>Monthly Dues: " + currentMonthDues + "</b></p>");
                out.print("<p><b>Unpaid Fees: " + unpaidFees + "</b></p>");
                out.print("<p><b>Total Dues for this Month : " + (currentMonthDues + unpaidFees) + "</b></p>"); %>
            }
    </body>
</html>
