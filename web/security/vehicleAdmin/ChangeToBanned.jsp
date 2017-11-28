<%-- 
    Document   : ChangeToBanned
    Created on : Nov 24, 2017, 7:37:22 PM
    Author     : Patrisha
--%>

<%@page import="model.dao.DatabaseUtils"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ban a Vehicle</title>
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
         <a href ="SecurityMain"> << Go back home </a>
        <h2> Ban a Vehicle </h2>
        <!--to connect to the db-->
        <%! String driverName = "com.mysql.jdbc.Driver";%>
        <%!String url = "jdbc:mysql://localhost:3306/hoamis";%>
        <%!String user = "root";%>
        <%!String psw = "password";%>
        <form action="/hoamis/ChangeToBannedServlet" method="POST">
            <!--code to get the contents of the dropdown-->
            <%
            Connection con = null;
            PreparedStatement ps = null;
            try
            {
            Class.forName(driverName);
            con = DatabaseUtils.retrieveConnection();
            String sql = "SELECT * FROM vehicles where banned = 0";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            %>
            <!--dropdown-->
         <select name = "vehicles">
            Banned Vehicles: 
            <option value=""> --Select a Vehicle-- </option>
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
