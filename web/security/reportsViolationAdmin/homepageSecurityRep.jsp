<%-- 
    Document   : Dashboard
    Created on : 11 25, 17, 4:49:54 PM
    Author     : Jennifer Ibay
--%>
<%@page import="model.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
 String usertypeID = (String) session.getAttribute("hey");
            
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Security Reports & Violations | Dashboard</title>
    </head>
    <body>
    <center>
        <h1>Security Reports & Violations</h1><br>
        <h2><I>What do you want to do today?</I></h3>
        
        <% if (usertypeID.equals("3")) { %> 
        <!-- FOR THE SECURITY PERSONNEL -->
        <!--security/reportsViolationAdmin/securityReportsNavigate.jsp -->
        <!-- security/reportsViolationAdmin/addResolution.jsp-->
        <!-- security/reportsViolationAdmin/ViewSecurityViolations.jsp-->
        <button><a href="SecurityMain?action=createReport">Create Report</button></a>&nbsp; &nbsp; 
        <button><a href="SecurityMain?action=addResolutionReport">Add Resolution for a Report</button></a>&nbsp; &nbsp; 
        <button><a href="SecurityMain?action=viewSecurityViolation">View Security Violation Reports</button></a>
            
        <% } else if (usertypeID.equals("2")) {%>
        <!-- FOR THE BOARD MEMBER-->
        <button><a href="ViewSecurityViolations.jsp">View Security Violation Reports</button></a>&nbsp; &nbsp;
        <button><a href="viewViolations.jsp">View My Violation Reports</button></a>
            
        <% }else if (usertypeID.equals("1")) { %>
        <!-- FOR THE HOMEOWNER-->
        <button><a href="viewViolations.jsp">View My Violation Reports</button></a>
            
        <% } %>
        
        <br><button><a href="${pageContext.request.contextPath}/SecurityMain">Main Menu</a></button>
    </center>
        </body>
</html>