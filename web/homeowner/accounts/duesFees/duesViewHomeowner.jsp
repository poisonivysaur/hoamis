<%-- 
    Document   : duesSuccessOffice
    Created on : Nov 24, 2017, 6:37:27 PM
    Author     : justine
--%>

<%@page import="model.User"%>

<%@ page import="model.dao.MonthlyDuesDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Current Monthly Dues | Homeowner</title>
        <%@ include file="../../base-officer.jsp" %>
    </head>
    <body>
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">
        <div class="navbar">
            <h2>MENU</h2>
            <a href="OfficerMain?action=dues"><button type="button">Home</button></a>
            <a href="OfficerMain?action=duesViewHO"><button type="button">View Current Monthly Dues</button></a>
        </div>
        <h1>DUES</h1>
        <%
            MonthlyDuesDAO mdd = new MonthlyDuesDAO();
            double currentMonthDues = mdd.getCurrentHomeownerMonthlyDues();
            User loginUser = (User) session.getAttribute("loginUser");
            double unpaidFees = 100;//mdd.getUnpaidFees(loginUser.getUserID());
        
            if (currentMonthDues == 0){
                out.print("<p>No registered monthly dues for this month. Contact an officer for more details.</p>");
            }
            else{
                out.print("<p><b>Monthly Dues: " + currentMonthDues + "</b></p>");
                out.print("<p><b>Unpaid Fees: " + unpaidFees + "</b></p>");
                out.print("<p><b>Total Dues for this Month : " + (currentMonthDues + unpaidFees) + "</b></p>"); 
            }%>
        </div>
    </body>
</html>
