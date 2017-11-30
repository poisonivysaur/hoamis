<%-- 
    Document   : revisedRegistration
    Created on : 11 30, 17, 11:42:36 AM
    Author     : Yuta
--%>

<%@page import="model.Homeowner"%>
<%@page import="model.Property"%>
<%@page import="model.dao.RegistrationDAO"%>
<%@page import="model.Occupation"%>
<%@page import="model.MapPoint"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<Occupation> availOccupations = RegistrationDAO.getAllOccupations();
ArrayList<User> availHomeowner = RegistrationDAO.getAllHomeowners();
ArrayList<Homeowner> ho = RegistrationDAO.getHomeowners();
ArrayList<Property> availProperty = RegistrationDAO.getAvailableProperty();
String msg = (String)session.getAttribute("msg");
%>  
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <%@include file="../../base-officer.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Registration</h1>
            <% if(msg != null){ %>
            <h2><%= msg %></h2>
            <% session.removeAttribute("msg");} %>
            <form action="UserRegistration" method="post" id="mainForm">
                <input type="hidden" name="action" value="userRegister">
                <p>Register as: 
                    <select name="userTypes" id="userType" onchange="checkUserType(this.value)">
                        <option value="1" selected>Homeowner</option>
                        <option value="4">System Admin</option>
                        <!--<option value="2">Officer</option>-->
                        <option value="3">Security Staff</option>
                        <option value="5">Home member</option>
                        <option value="6">Kasambahay</option>
                    </select>
                </p><!--user type-->
                <p>Username: <input name="username" type="text" minlength="1" maxlength="45" required="" id="username"></p>
                <div id="pass"><p>Password: <input id="passwordf" required="" name="password" minlength="8" maxlength="14" type="password" id="password"></p></div>
                <p>First Name:  <input name="fName" type="text" required="" id="firstName"></p>
                <p>Middle Name: <input name="mName" type="text" required="" id="middleName"></p>
                <p>Last Name: <input name="lName" type="text" required="" id="lastName"></p>
                <p>Birthday: <input name="bDay" type="date" class="bDay" required="" id="b"></p><div id="berror"></div>
                <p>Occupation: 
                    <select id="occ" name="occupation" onchange="checkOccupation(this.value)">
                        <% for(Occupation o : availOccupations){ %>
                        <option value="<%= o.getOccupationID()%>"><%= o.getOccupation()%></option>
                        <% } %>
                        <option value="others">Others</option>
                    </select>
                </p><!-- occupation -->
                <div id="others" hidden="true"><p>Input Occupation: <input name="otherOccupation" type="text" maxlength="40"></p></div>
                <div id="map">
                    <p>Select place to rent
                        <select name="blockLot">
                            <% for(Property p : availProperty){ %>
                            <option value="<%= p.getBlocknum() %>,<%= p.getLotnum() %>"><%= p.getMapppoint().getTitle() + " (Block " + p.getBlocknum() + " : Lot " + p.getLotnum() + ")" %></option>
                            <% } %>
                        </select>
                    </p>
                </div>
                <a href="OfficerMain?action=addMap" class="w3-button w3-teal w3-round">Add New House</a>
                <div id="homeowners" hidden="true">
                    <p>Select Homeowner to Stay with
                        <select name="homeowner">
                            <% for(Homeowner h : ho){ %>
                            <option value="<%= h.getBlocknum()%>,<%= h.getLotnum()%>"><%= h.getUserID() %></option>
                            <% } %>
                        </select>
                    </p>
                </div>
                <p id="p">Homeowner Registration Fee is P250</p>
                <input type="submit" value="Register User" class="w3-button w3-teal w3-round">
            </form>
        </div><!--/.w3-container-->
    </body>
    <script>
        $('#mainForm').submit(function(event){
            var now = new Date().getFullYear();
            now = parseInt(now);
            var bDate = $('#b').val().split("-")[0];
            bDate = parseInt(bDate);
            var diff = now - bDate;
            if(diff < 0){
                $('#berror').html("<p>Age cannot be less than 0.</p>");
                event.preventDefault();
                return false;
            }else if(diff > 100){
                $('#berror').html("<p>Age cannot be greater than 100.</p>");
                event.preventDefault();
                return false;
            }
        });
        
        function checkUserType(userTypeId){
            if(userTypeId == "1"){
                $('#p').attr('hidden', false);
                $('#map').attr('hidden', false);
            }else{
                $('#p').attr('hidden', true);
                $('#map').attr('hidden', true);
                if(userTypeId == "5" || userTypeId == "6"){
                    $('#pass').attr('hidden', true);
                    $('#passwordf').attr('value', 'ADSADMSAKDMA');
                    $('#homeowners').attr('hidden', false);
                }else{
                    $('#pass').attr('hidden', false)
                    $('#passwordf').attr('value', '');
                    $('#homeowners').attr('hidden', true);
                }
            }
        }
        
        function checkOccupation(occupation){
            if(occupation == "others"){
                $('#others').attr('hidden', false);
            }else{
                $('#others').attr('hidden', true);
            }
        }
    </script>
</html>
