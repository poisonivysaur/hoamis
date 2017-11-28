<%-- 
    Document   : IssueSticker
    Created on : Nov 25, 2017, 12:09:43 AM
    Author     : Asus
--%>

<%@page import="model.dao.DatabaseUtils"%>
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
        <title>Issue Sticker</title>
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
        
         <a href ="OfficerMain"> << Go back home </a>
        <h2> Issue Sticker </h2>
        <form action="IssueStickerServlet" method="POST">
            <%
            Connection con = DatabaseUtils.retrieveConnection();
            PreparedStatement ps = null;
            try
            {
           
            String sql = "SELECT * FROM stickerinventory "
                    + "WHERE stickerinventory.dateReleased <= CURDATE()";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(); 
            %>
         <select name = "stickers">
            <option value=""> --Available Stickers-- </option>
            <%
            while(rs.next()){
            String unclaimed = rs.getString("stickerID"); 
            %>
            <option value="<%=unclaimed %>"><%=unclaimed %></option>
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