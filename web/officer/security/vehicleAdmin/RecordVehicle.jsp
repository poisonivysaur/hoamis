<%-- 
    Document   : RecordVehicle
    Created on : Nov 24, 2017, 11:40:25 AM
    Author     : Patrisha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vehicle Registration</title>
    </head>
    <style>
        input[type="text"], [type="date"]{
            width: 30%;
            padding: 12px 20px;
            border-radius: 4px;
            box-sizing: border-box;
        }
        select {
            width: 100%;
            padding: 16px 20px;
            border: none;
            border-radius: 4px;
            background-color: #f1f1f1;
        }
        input[type=submit]{
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        } 
        </style>
    <body>
         <a href ="Dashboard.jsp"> << Go back home </a>
        <h2> Record a Vehicle </h2>
        <form action ="RecordVehicleServlet" method="POST">
            <br>Vehicle Plate Number: 
            <br><input type="text" name="platenum" required> 
            <br>
            <br>Model: 
            <br><input type="text" name="model" required>
            <br>
            <br>Make: 
            <br><input type="text" name="make" required>
            <br>
            <br>Year: 
            <br><input type="text" name="year" required>
            <br>
            <br>Banned: 
            <br><input type="radio" name="banned" value=1> Yes 
            <br><input type="radio" name="banned" value=0> No
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
