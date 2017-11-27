
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="assets/uts.png">
        <title>Welcome!</title>
    </head>
    <body>
        <h1>Hello User!</h1>
            <form action="LoginProcess" method="POST">
            <fieldset>
                <legend>Policy Management</legend>
                User ID: <input type="text" name="username"/><br>
                Password: <input type="password" name="password"/><br>
                <input type="submit" value="submit"/>
            </fieldset>    
            </form>
        <b style="color: red;"><% if (request.getAttribute("username") == "bad"){ out.println("Incorrect Password!" + " username " + request.getAttribute("username") + " password: " +request.getAttribute("password"));}%></b>
        <a href="register.jsp"> No account? sign up here!</a>
    </body>
</html>
