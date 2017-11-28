<%-- 
    Document   : RecordVehicle
    Created on : Nov 24, 2017, 11:40:25 AM
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
            width: 30%;
           padding: 12px 20px;
            border-radius: 4px;
            box-sizing: border-box;
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
        <h2> Record a Vehicle </h2>
        <%! String driverName = "com.mysql.jdbc.Driver";%>
        <%!String url = "jdbc:mysql://localhost:3306/hoamis";%>
        <%!String user = "root";%>
        <%!String psw = "password";%>
        <!--connection to db, gets the first name, middle name, and last name of the users for the dropdown-->
        <%
            Connection con = null;
            PreparedStatement ps = null;
            try
            {
            Class.forName(driverName);
            con = DatabaseUtils.retrieveConnection();
            String sql = "SELECT fname, mname, lname, userid FROM users";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            %>
        <form action ="/hoamis/RecordVehicleServlet" method="POST">
            Vehicle Owner: <!-- get from db !-->
            <br>
            <select name = "owner"> <!--dropdown-->
            <option value=""> --Select a User-- </option>
            <%
            while(rs.next()){
            String fname = rs.getString("fname");
            String mname = rs.getString("mname");
            String lname = rs.getString("lname");
            String userid = rs.getString("userid");
            %>
            <option value="<%=userid %>"><%=fname + " " + mname + " " + lname %></option>
            <%
            }
            %>
            </select> <br>
            <br>Vehicle Plate Number:  <!--platenumber-->
            <br><input type="text" name="platenum" required> 
            <br>
            <br>Model: <!-- model-->
            <br><input type="text" name="model" required>
            <br>
            <br>Make: <!-- make-->
            <br><input type="text" name="make" required>
            <br>
            <br>Year: <!-- year-->
            <br><input type="text" name="year" required>
            <br>
            <br>Banned: <!--radio button-->
            <br><input type="radio" name="banned" value=1> Yes 
            <br><input type="radio" name="banned" value=0> No
            <br>
            
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
