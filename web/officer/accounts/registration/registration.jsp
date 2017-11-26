<%-- 
    Document   : directory
    Created on : 11 24, 17, 2:47:14 PM
    Author     : Yuta
--%>

<%@page import="model.dao.RegistrationDAO"%>
<%@page import="model.Occupation"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<Occupation> availOccupations = RegistrationDAO.getAllOccupations();
ArrayList<User> availHomeowner = RegistrationDAO.getAllHomeowners();
String msg = (String)session.getAttribute("msg");
%>    
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Officer User Registration</title>
        <%@include file="../../base-officer.jsp" %>
        <style>
            .rButtons{
                position: relative;
                top: -15px;
                left: 88px;
            }

            .uName{
                position: relative;
                left: 8px;
            }

            .password{
                position: relative;
                left: 11px;
            }

            .fName{
                position: relative;
                left: 34px;
            }

            .mName{
                position: relative;
                left: 81px;
            }

            .lName{
                position: relative;
                left: 81px;
            }

            .bDay{
                position: relative;
                left: 18px;
                width: 162px;
            }

        </style>
    </head>
    <body>
        <!-- !PAGE CONTENT! -->
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Register</h1>
            <% if(msg != null){ %>
            <h2><%= msg %></h2>
            <% session.removeAttribute("msg");} %>
            <form action="RegisterUser" method="post" id="register">
                Register as: <select name="userTypes" id="userTypes" onchange="changeUserScreen(this.value)">
                    <option value="1" selected>Homeowner</option>
                    <option value="4">System Admin</option>
                    <!--<option value="2">Officer</option>-->
                    <option value="3">Security Staff</option>
                    <option value="5">Home member</option>
                    <option value="6">Kasambahay</option>
                </select><br /><br />
                <div id="scrn">
                    Username: <input name="username" type="text" class = "uName" required="" minlength="1" maxlength="45"><br><br>
                    <div id="password">Password: <input id="passwordf" required="" name="password" minlength="8" maxlength="14" type="password" class="password"><br><br></div>
                
                Name: <input name="fName" type="text" placeholder="First Name" class="fName" required=""><br><br>
                <input name="mName" type="text" placeholder="Middle Name" class="mName" required=""><br><br>
                <input name="lName" type="text" placeholder="Last Name" class="lName" required=""><br><br>
                Birthday: <input name="bDay" type="date" class="bDay" required="" id="b"><br><br><div id="berror"></div>
                <!--If registering as kasambahay, set occupation as kasambahay already -->  
                <div id="occupation">
                Occupation: <select id="occ" name="occupation" onchange="checkOccupation(this.value)">
                    <% for(Occupation o : availOccupations){ %>
                    <option value="<%= o.getOccupationID() %>"><%= o.getOccupation()%></option>
                    <% } %>
                    <option value="others">Others</option>
                    <option id="kakaka" value="9" hidden="true"></option>
                </select><br><br></div>
                <div id="others" hidden="true">Input Occupation: <input name="otherOccupation" type="text"><br><br></div>
                <!-- If registering as homeowner/homemember-->
                <!--Kasambahay: <input name = "kasambahay" type = "text" placeholder = "Optional" class = "kasambahay"><br><br>-->
                <div id="h" hidden="true">
                    Block Number: <input type="number" value="0" placeholder="Enter Block Num" name="block"><br /><br />
                    Lot Number: <input type="number" value="0" placeholder="Enter Lot num" name="lot"><br /><br />
                </div>
                <p id="p" hidden="true">Homeowner Registration Fee is P250</p>
                </div><!--/#scrn-->
                <input type="submit" value="Register User" class="w3-button w3-teal w3-round">
            </form>
        </div><!--/.w3-main-->
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#p').attr("hidden", false);
            $('#h').attr("hidden", false);
        });
        
        $('#register').submit(function(event){
            
            var now = new Date().getFullYear();
            now = parseInt(now);
            var bDate = $('#b').val().split("-")[0];
            bDate = parseInt(bDate);
            var diff = now - bDate;
            if(diff < 0){
                console.log("0");
                $('#berror').html("<p>Age cannot be less than 0.</p>");
                event.preventDefault();
                return false;
            }else if(diff > 100){
                console.log("Bigger 100");
                $('#berror').html("<p>Age cannot be greater than 100.</p>");
                event.preventDefault();
                return false;
            }
        });
        
        function changeUserScreen(usertype){
            console.log(usertype);
            if(usertype == "4" || usertype == "1" || usertype == "2" || usertype == "3"){
                $('#password').attr("hidden", false);
                $('#passwordf').attr("required", true);
                if(usertype == "1"){
                    $('#p').attr("hidden", false);
                }else{
                    $('#p').attr("hidden", true);
                }
                if(usertype == "1"){
                    $('#h').attr("hidden", false);
                }else{
                    $('#h').attr("hidden", true);
                }
            }else{
                if(usertype == "6"){
                    $('#occupation').attr("hidden", true);
                    $('#kakaka').attr("selected", true);
                }else{
                    $('#occupation').attr("hidden", false);
                }
                $('#password').attr("hidden", true);
                $('#passwordf').attr("required", false);
                $('#h').attr("hidden", false);
            }
        }
        
        function checkOccupation(occupation){
            if(occupation == "others"){
                $('#others').attr("hidden", false);
            }else{
                $('#others').attr("hidden", true);
            }
        }
    </script>
</html>
