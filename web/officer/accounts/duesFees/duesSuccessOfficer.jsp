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
        <title>Set Monthly Dues | Officer</title>
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
            <h1>Successfully recorded monthly dues in database!</h1>
            <br>
            Leave this screen with the menu
        </div>
        
    </body>
</html>
