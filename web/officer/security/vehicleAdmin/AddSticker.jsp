<%-- 
    Document   : AddSticker
    Created on : Nov 24, 2017, 12:32:21 PM
    Author     : Patrisha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Sticker</title>
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
        <h2> Add a Sticker</h2>
        <form action="AddStickerServlet" method="POST">
        <br> Sticker Year: <!--sticker year-->
        <br> <input type="text" name="year" required>
        <br>
        <br> Cost: <!--sticker cost-->
        <br><input type="text" name="cost" required>
        <br>
        <input type="submit" value="Submit">
        
    </form>
    </body>
</html>
