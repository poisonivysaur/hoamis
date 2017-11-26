<%-- 
    Document   : duesContOfficer
    Created on : 11 6, 17, 2:54:38 AM
    Author     : Anne Charlene Cipres
--%>

<%@ page import="java.text.NumberFormat"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="model.Ref_MonthlyDues"%>
<%@ page import="model.dao.MonthlyDuesDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="dues.css" type="text/css">
        <title>Set Monthly Dues | Officer</title>
    </head>
    <body>
        <div class="navbar">
            <h2>MENU</h2>
            <a href="OfficerMain?action=dues"><button type="button">Home</button></a>
            <a href="OfficerMain?action=duesForm"><button type="button">Set Monthly Dues</button></a>
            <a href="OfficerMain?action=duesView"><button type="button">View All Monthly Dues</button></a>
        </div>
        <%
            Ref_MonthlyDues rmd = (Ref_MonthlyDues) request.getAttribute("rmdObj");
            MonthlyDuesDAO mdd = new MonthlyDuesDAO();
            int startMonth = rmd.getStartMonth();
            int startYear = rmd.getStartYear();
            int endMonth = rmd.getEndMonth();
            int endYear = rmd.getEndYear();
            double amountApproved = rmd.getAmountApproved();
            
            String start = rmd.getStartMonthWord();
            String end = rmd.getEndMonthWord();
            
            int numOfMonths = rmd.getNumberOfMonths();
            
            double monthDues = amountApproved / numOfMonths;
            NumberFormat formatter = new DecimalFormat("#0.00");
        %>
        <div class="container">
            
            <h2>Confirm Monthly Dues</h2>
            <form action="duesOfficerServlet" method="POST">
                <p><b>Start Month: </b> <% out.print(start + " " + startYear); %></p>
                <input type="hidden" value="<% out.print(startMonth); %>" name="startMonth">
                <input type="hidden" value="<% out.print(startYear); %>" name="startYear">
                <p><b>End Month: </b> <% out.print(end + " " + endYear); %></p>
                <input type="hidden" value="<% out.print(endMonth); %>" name="endMonth">
                <input type="hidden" value="<% out.print(endYear); %>" name="endYear">
                <p><b>Total Amount Approved: </b> <% out.print(amountApproved); %></p>
                <input type="hidden" value="<% out.print(amountApproved); %>" name="amount">
                <p><b>Monthly Dues for all Homeowners: <% out.print(formatter.format(monthDues)); %> </b></p>
                <p><b>Monthly Dues allotted per Homeowner: <% out.print(formatter.format(monthDues / mdd.getNumberOfActiveHomeowner())); %> </b></p>
                <input type="hidden" value="<% out.print(formatter.format(monthDues)); %>" name="monthDues">
                <button type="submit" name="submit2">SUBMIT</button>
            </form>
        </div>
    </body>
</html>
