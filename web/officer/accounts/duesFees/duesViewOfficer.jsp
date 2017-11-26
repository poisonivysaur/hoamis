<%-- 
    Document   : duesViewOfficer
    Created on : Nov 25, 2017, 9:05:37 PM
    Author     : justine
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="model.dao.MonthlyDuesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Ref_MonthlyDues" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Monthly Dues | Officer</title>
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
            <div class="row">
                <h1>View Assigned Monthly Dues</h1>
                <form action="duesViewServlet" method="POST">
                    <select name="monthlys">
                        <% 
                            int startMonth = 0, startYear = 0, endMonth = 0, endYear = 0, numOfMonths = 0;
                            double amountApproved = 0, monthDues = 0;
                            String start = "", end = "";
                            NumberFormat formatter = null;
                            Ref_MonthlyDues rmd = (Ref_MonthlyDues) request.getAttribute("rmdObj");
                            if(rmd != null){
                                startMonth = rmd.getStartMonth();
                                startYear = rmd.getStartYear();
                                endMonth = rmd.getEndMonth();
                                endYear = rmd.getEndYear();
                                amountApproved = rmd.getAmountApproved();

                                start = rmd.getStartMonthWord();
                                end = rmd.getEndMonthWord();

                                numOfMonths = rmd.getNumberOfMonths();

                                monthDues = amountApproved / numOfMonths;
                                formatter = new DecimalFormat("#0.00");
                            }
                            
                            MonthlyDuesDAO mdd = new MonthlyDuesDAO();
                            ArrayList<Ref_MonthlyDues> array = mdd.getAllRangedMonthlyDues();
                            for(int ref = 0; ref < array.size(); ref++){
                                Ref_MonthlyDues md = array.get(ref);
                                out.print("<option value=" + md.getMduesID() + ">" + md.getStartMonthWord() + " " + md.getStartYear() + "-" + md.getEndMonthWord() + " " + md.getEndYear());
                            }
                        %>
                    </select>
                    <button type="submit" value="submit" name="submit">SUBMIT</button>
                </form>
            </div>
            <div class="row">
                <p><b>Start Month: </b>
                <%
                    if(null != rmd) out.print(start + " " + startYear);
                %>
                </p>
                
                <p><b>End Month: </b> <% if(null != rmd) out.print(end + " " + endYear); %></p>
                
                <p><b>Total Amount Approved: </b> <% if(null != rmd) out.print(amountApproved); %></p>
                
                <p><b>Monthly Dues for all Homeowners: <% if(null != rmd) out.print(formatter.format(monthDues)); %> </b></p>
                <p><b>Monthly Dues allotted per Homeowner: <% if(null != rmd) out.print(formatter.format(monthDues / mdd.getNumberOfActiveHomeowner())); %> </b></p>
            </div>
           
        </div>
    </body>
</html>
