<%-- 
    Document   : BuySticker
    Created on : Nov 24, 2017, 12:32:21 PM
    Author     : Asus
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="dao.Database"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase Sticker</title>
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
        <a href ="OfficerMain"> << Go back home </a>
        <h2> Sticker Purchase</h2>
        <form action="BuyStickerServlet" method="POST">
            
        <%
            Connection con = Database.getDBConnection();
            PreparedStatement ps = null;
            PreparedStatement ps3 = null;
            String firstname = request.getParameter("fname");
            String lastname = request.getParameter("lname");
            try
            {
           
            String sql ="SELECT * FROM users "
                    + "JOIN user_vehicles ON users.userID = user_vehicles.userID "
                    + "JOIN vehicles ON user_vehicles.platenum = vehicles.platenum " 
                    + "WHERE users.fname = 'Mark' AND users.lname = 'First' AND user_vehicles.stickerPaid = 1";
            String sql2 ="SELECT * FROM users "
                    + "JOIN user_vehicles ON users.userID = user_vehicles.userID "
                    + "JOIN vehicles ON user_vehicles.platenum = vehicles.platenum "   
                    + "WHERE user_vehicles.stickerPaid = 1 ";
          
            ps = con.prepareStatement(sql);
            ps3 = con.prepareStatement(sql2);
            ResultSet rs = ps.executeQuery(); 
            ResultSet rs3 = ps3.executeQuery(); 
           /* String first = rs.getString("first");
            String last = rs.getString("last");
            
            ps.setString(1, first);
            ps.setString(2, last);*/
            %>
           
         <% 
            
         %>
         <select name = "vehicles">
            <option value=""> --Select Owned Vehicle-- </option>
            <%
                
            while(rs.next()){
            String platenum = rs.getString("platenum"); 
           
            %>
            <option value="<%=platenum%>"><%=platenum%></option>
            <%
            }
            %>
        </select>
             <%
            }
            catch(SQLException sqe)
            { 
            out.println(sqe);
            }
            %>
            <br><br>
            <%
            PreparedStatement ps2 = null;
            try
            {
           
            String sql2 = "SELECT cost FROM stickerinventory WHERE stickerYear =  YEAR(CURDATE());";
            ps2 = con.prepareStatement(sql2);
            ResultSet rs2 = ps2.executeQuery(); 
            %>
            Sticker Cost (php): <!-- get from db !-->
            <%
            while(rs2.next()){
            String cost = rs2.getString("cost");
            %>
            <input type="text" name="cost" value="<%=rs2.getString("cost")%> "readonly>
          
            <br>
            Amount Received: 
            <input type="text" name="received">
            <br>

            <br>
            <input type="submit" value="Submit">
            <%
            }
            %>
            
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
