<%-- 
    Document   : managePet
    Created on : 12 1, 17, 8:08:01 PM
    Author     : Yuta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homeowner Manage Pets</title>
        <%@include file="../../base-homeowner.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Pets Management</h1>
            <form action="Directory" method="post">
                <input type="hidden" name="action" value="addNewPet">
                <p>Pet Name: <input type="text" maxlength="45" name="petName" required=""></p>
                <p>Animal Type: 
                    <select name="petType">
                        <option value="1">Dog</option>
                        <option value="2">Cat</option>
                    </select>
                </p>
                <p>Vaccinated: 
                    <select name="vaccination">
                        <option value="true">Yes</option>
                        <option value="no">No</option>
                    </select>
                </p>
                <input type="submit" value="Add Pet">
            </form>
        </div>
    </body>
    <script>
        function checkPetName(petName){
            
        }
    </script>
</html>
