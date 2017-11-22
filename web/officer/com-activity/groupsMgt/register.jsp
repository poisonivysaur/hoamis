<%-- 
    Document   : register
    Created on : Oct 15, 2017, 3:56:30 PM
    Author     : Leebet-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register User</title>
    </head>
    <body>
        <form action="SignupProcess" method="POST">
            <fieldset>
                <legend>New Account</legend>
                Username: <input type="text" name="username"/><br>
                Password:<input type="text" name="password"/><br>
                Re-type Password: <input type="text"/><br>
                Email: <input type="email" name="email"/></br>
                <select name="usertype">
                    <option value="1">Officer</option>
                    <option value="2">Homeowner</option>
                </select>
                <input type="submit" value="submit"/>
            </fieldset>    
            </form>
    </body>
</html>
