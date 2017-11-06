<%-- 
    Document   : error403
    Created on : 11 6, 17, 3:01:36 PM
    Author     : Yuta
--%>
<%@page import="model.User"%>
<%
User loginUser = (User) session.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 403 - Forbidden</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p><strong>YOU TRIED TO ACCESS A PAGE THAT IS FORBIDDEN FOR YOUR KIND <%= loginUser.getlName() + ", " + loginUser.getfName() %></strong></p>
    </body>
</html>
