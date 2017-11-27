<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
 
<html>
     <style>
        input[type="text"], [type="date"]{
            width: 20%;
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
      <center>
          <a href="OfficerMain"> << Go back home </a>
      <h2> Search Vehicle </h2><!--search bar to search for a plate number-->
      
    <form action="SearchServlet" method="POST">
      Plate No:
         <input  type="text" name="pid" id="pid">
        <input  type="submit" name="submit" value="Search"></td></tr>
        </center>
      </table>
    </form>
      <a href="OfficerMain?action=allVehicles"> View All Vehicle Records </a><br>
      <a href="OfficerMain?action=sticker"> View Stickers </a>
  </body>
</html>