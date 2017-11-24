<%-- 
    Document   : dashboard
    Created on : Oct 29, 2017, 4:59:47 PM
    Author     : Ivy
--%>

<%@page import="model.Billing"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.BillingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
ArrayList<Billing> bills = BillingDAO.getBillings();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="base-officer.jsp" %>
    </head>
    <body>
        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">
            
            <!-- Links (sit on top) -->
            <div class="w3-container">
                <h1>Billing, Collection & Payments </h1>
              <div class="w3-row w3-large w3-light-grey">
                  
                  <!--
                <div class="w3-col s2">
                  <a href="#" class="w3-button w3-block">Menu Tab 1</a>
                </div>
                <div class="w3-col s2">
                  <a href="#" class="w3-button w3-block">Menu Tab 2</a>
                </div>
                <div class="w3-col s2">
                  <a href="#" class="w3-button w3-block">Menu Tab 3</a>
                </div>
                <div class="w3-col s3">
                  <a href="#" class="w3-button w3-block">Menu Tab 4</a>
                </div>
                <div class="w3-col s3">
                  <a href="#" class="w3-button w3-block">Menu Tab 5</a>
                </div>-->
                
                <div class="w3-col s4">
                  <a href="billing-view.jsp" class="w3-button w3-block w3-grey">View Billings</a>
                </div>
                <div class="w3-col s4">
                  <a href="billing-generate.jsp" class="w3-button w3-block">Generate Billings</a>
                </div>
                <div class="w3-col s4">
                  <a href="billing-update.jsp" class="w3-button w3-block">Update Payments</a>
                </div>
                  
              </div>
            </div>
            

            <div class="w3-container">
            <h2>Billings</h2>
            
            </div>
            <div class="w3-container">
            <table class="w3-table w3-striped w3-white">
                <tr>
                    <th>Billing ID</th>
                    <th>Block</th>
                    <th>Lot</th>
                    <th>Precedent Billing</th>
                    <th>Total Due</th>
                    <th>Total Paid</th>
                </tr>
            <% for(Billing b : bills){ %>
                <tr>
                    <td><%= b.getBillingID()%></td>
                    <td><%= b.getBlocknum()%></td>
                    <td><%= b.getLotnum()%></td>
                    <td><%= b.getPrecedentBilling()%></td>
                    <td><%= b.getTotalDue()%></td>
                    <td><%= b.getTotalPaid()%></td>
                </tr>
            
            <% } %>
            </table>
            </div>
          
    </body>
</html>
