<%-- 
    Document   : ViewStickers
    Created on : Nov 25, 2017, 8:46:57 PM
    Author     : Patrisha
--%>

<%@ page import="java.util.*" %>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Records</title>
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
        <h2> View All Records </h2>
        <!--shows a table which contains all the vehicles with the stickers-->
        <table width="700px" align="center"
               style="border:1px solid #000000;">
            <tr>
                <td colspan=7 align="center"
                    style="background-color:teal">
                    <b>Vehicle Records</b></td>
            </tr>
           
            <tr style="background-color:lightgrey;">
                <td><b>Owner</b></td>
                <td><b>Plate No.</b></td>
                <td><b>Sticker ID</b></td>
                <td><b>Sticker Year</b></td>
                <td><b>Date Issued</b></td>
                
            </tr>
            <%
            Connection con = null;
            PreparedStatement ps = null;
            Class.forName(driverName);
            con = DriverManager.getConnection(url,user,psw);
            String sql = "SELECT uv.userid, uv.platenum, uv.stickerid, s.stickeryear, s.datereleased FROM stickerinventory s join user_vehicles uv on s.stickerid=uv.stickerid";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            %>
            <%
            while(rs.next()){
            String user = rs.getString("userid");
            String platenum = rs.getString("platenum"); 
            int sticker = rs.getInt("stickerid");
            String year = rs.getString("stickeryear");
            String date = rs.getString("datereleased");
            %>
             <tr style="background-color:#eeffee;">
                <td><%=user%></td>
                <td><%=platenum%></td>
                <td><%=sticker%></td>
                <td><%=year%></td>
                <td><%=date%></td>
            </tr>
            <%
            }
            %>
</html>

