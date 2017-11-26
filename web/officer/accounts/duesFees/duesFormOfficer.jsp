<%-- 
    Document   : duesOfficer
    Created on : 11 5, 17, 9:53:16 PM
    Author     : Anne Charlene Cipres
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Set Monthly Dues | Officer</title>
        
        <link rel="stylesheet" href="dues.css" type="text/css">
    </head>
    <body>
        <div class="navbar">
            <h2>MENU</h2>
            <a href="OfficerMain?action=dues"><button type="button">Home</button></a>
            <a href="OfficerMain?action=duesForm"><button type="button">Set Monthly Dues</button></a>
            <a href="OfficerMain?action=duesView"><button type="button">View All Monthly Dues</button></a>
        </div>
        <div class="container">
            <form action="/hoamis/duesOfficerServlet" method="post">
                <h2>SET MONTHLY DUES</h2>
                <div class="row">
                    <div>
                        <label>Start Month and Year: </label><br> <!-- start month-->
                        <select name="startMonth">
                            <option selected value="1">January</option>
                            <option value="2">February</option>
                            <option value="3">March</option>
                            <option value="4">April</option>
                            <option value="5">May</option>
                            <option value="6">June</option>
                            <option value="7">July</option>
                            <option value="8">August</option>
                            <option value="9">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select name="startYear">
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                        </select>
                    </div>
                    <div>
                        <label>End Month and Year: </label><br> <!-- start month-->
                        <select name="endMonth">
                            <option selected value="1">January</option>
                            <option value="2">February</option>
                            <option value="3">March</option>
                            <option value="4">April</option>
                            <option value="5">May</option>
                            <option value="6">June</option>
                            <option value="7">July</option>
                            <option value="8">August</option>
                            <option value="9">September</option>
                            <option value="10">October</option>
                            <option value="11">November</option>
                            <option value="12">December</option>
                        </select>
                        <select name="endYear">
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                        </select>
                    </div>
                </div>

                <div class="row">
                    <label>Total Amount Approved: </label> <!--amount-->
                    <input type="number" name="amount" step='0.01' value='1000.00'><br>

                    <button type="submit" name="submit">SUBMIT</button>
                </div>
            </form>
        </div>
    </body>
    <script>
        <%
            if(request != null && request.getAttribute("message") != null){
                out.print("alert(\"" + request.getAttribute("message") + "\");");
            }
        %>
    </script>
</html>
