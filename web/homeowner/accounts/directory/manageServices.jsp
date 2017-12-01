<%-- 
    Document   : manageServices
    Created on : 12 1, 17, 8:08:10 PM
    Author     : Yuta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Services</title>
        <%@include file="../../base-homeowner.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class="w3-container" style="margin-left:300px;margin-top:43px;">
            <h1>Manage your services</h1>
            <form action="#" method="post">
                <input type="hidden" name="action" value="serviceManager">
                <p>Select your Home Member: 
                    <select name="userId">
                        <option value="">Test</option>
                    </select>
                </p>
                
            </form>
        </div>
    </body>
</html>
