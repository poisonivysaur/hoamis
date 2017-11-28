<%-- 
    Document   : duesSuccessOffice
    Created on : Nov 24, 2017, 6:37:27 PM
    Author     : justine
--%>


<%@ page import="model.dao.MonthlyDuesDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home | Homeowner</title>
        
        <%@ include file="../../base-homeowner.jsp" %>
        <link rel="stylesheet" href="dues.css" type="text/css">
    </head>
    <body>
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">
        <div class="navbar">
            <h2>MENU</h2>
            <a href="HomeownerMain?action=dues"><button type="button">Home</button></a>
            <a href="HomeownerMain?action=duesView"><button type="button">View Current Monthly Dues</button></a>
        </div>
        <div class="container">
            <h1>Home</h1>
            <br>
            <%
                MonthlyDuesDAO mdd = new MonthlyDuesDAO();
                if(mdd.getCurrentMonthDues() != null){
                    out.print("<p><a href=\"duesViewHomeowner.jsp\">Notification: Monthly dues have been assigned! Click to view this month's monthly dues.</a></p>");
                }
                else{
                    out.print("<p>No notifications to display.</p>");
                }
            %>
        </div>
        </div>
    </body>
</html>
