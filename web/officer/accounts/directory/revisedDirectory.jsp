<%-- 
    Document   : revisedDirectory
    Created on : 11 30, 17, 11:57:09 PM
    Author     : Yuta
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<User> homeowners;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Directory</title>
        <%@include file="../../base-officer.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Directory</h1>
            <table class="w3-table w3-stripped w3-white w3-hoverable">
                <tr>
                    <th>Picture</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>See Further Information</th>
                </tr>
            </table>
        </div>
    </body>
</html>
