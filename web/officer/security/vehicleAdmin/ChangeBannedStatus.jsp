<%-- 
    Document   : ChangeBannedStatus
    Created on : Nov 24, 2017, 1:07:52 PM
    Author     : Patrisha
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unban a Vehicle</title>
    </head>
    <style>
        input[type="text"], [type="date"]{
            width: 30%;
            padding: 12px 20px;
            border-radius: 4px;
            box-sizing: border-box;
        }
        select {
            width: 30%;
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
        <%! String driverName = "com.mysql.jdbc.Driver";%>
        <%!String url = "jdbc:mysql://localhost:3306/hoamis";%>
        <%!String user = "root";%>
        <%!String psw = "password";%>
         <a href ="Dashboard.jsp"> << Go back home </a>
        <h2> Unban a Vehicle </h2>
        <form action="ChangeBannedStatusServlet" method="POST">
            <%
            Connection con = null;
            PreparedStatement ps = null;
            try
            {
            Class.forName(driverName);
            con = DriverManager.getConnection(url,user,psw);
            String sql = "SELECT * FROM vehicles where banned = 1";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            %>
         <select name = "vehicles">
            <option value=""> --Select a Banned Vehicle-- </option>
            <%
            while(rs.next()){
            String platenum = rs.getString("platenum"); 
            %>
            <option value="<%=platenum %>"><%=platenum %></option>
            <%
            }
            %>
        </select>
            <input type="submit" value="Submit">
            <%
            }
            catch(SQLException sqe)
            { 
            out.println(sqe);
            }
%>
        </form>
    </body>
</html>
