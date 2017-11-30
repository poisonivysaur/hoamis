<%-- 
    Document   : dashboard
    Created on : Oct 29, 2017, 4:59:47 PM
    Author     : Ivy
--%>

<%@page import="model.TransactionReference"%>
<%@page import="model.Billing"%>
<%@page import="model.User"%>
<%@page import="model.Homeowner"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.BillingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User loginUser = (User) session.getAttribute("loginUser");
String hoId = request.getParameter("userid");
String fname = request.getParameter("fname");
String lname = request.getParameter("lname");
String mname = request.getParameter("mname");
String dateIssued = request.getParameter("dateIssued");

int billID = Integer.parseInt(request.getParameter("billingID"));
double totalDue = Double.parseDouble(request.getParameter("totalDue"));
double totalPaid = Double.parseDouble(request.getParameter("totalPaid"));


ArrayList<TransactionReference> transactions = BillingDAO.getTrxRef(billID);
ArrayList<String> datesPaid = BillingDAO.getDatesPaid();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="../../base-homeowner.jsp" %>
    </head>
    <body>
        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">
            
            <!-- Links (sit on top) -->
            <div class="w3-container">
                <h1>Billing, Collection & Payments </h1><!--
              <div class="w3-row w3-large w3-light-grey">
                <div class="w3-col s4">
                  <a href="billing-view.jsp" class="w3-button w3-block w3-grey">View Billings</a>
                </div>
                <div class="w3-col s4">
                  <a href="billing-generate.jsp" class="w3-button w3-block">Generate Billings</a>
                </div>
                <div class="w3-col s4">
                  <a href="billing-update.jsp" class="w3-button w3-block">Update Payments</a>
                </div>
                  
              </div>-->
            </div>
            

            <div class="w3-container">
                <a href="/hoamis/HomeownerMain?action=billingViewDetails&userid=<%= hoId %>&fname=<%= fname %>&lname=<%= lname %>&mname=<%= mname %>"><h4><<< Back to View Billings</h4></a>
            <h2 style="text-align: center; float: left;">Billing Details</h2><br><br>
            
            
            </div>
            <div id="print" class="w3-container">
                <h5 style="text-align: center;"><%= "Homeowner: "+lname+", "+fname+" "+mname %></h5>
            <table class="w3-table w3-striped w3-white w3-hoverable" id="userTable">
                <tr>
                    <th>Billing ID: <%= billID %></th>
                    <!--<th>Precedent Billing ID</th>-->
                    <th></th>
                    <th></th>
                    <th>Date Issued: <%= dateIssued %></th>
                    <th></th>
                    <th style="text-align: right;">Total Due: <%= totalDue %></th>
                    <th style="text-align: right;">Total Paid: <%= totalPaid %></th>
                    
                </tr>
                <br>
                <tr>
                    <th>TRX#</th>
                    <th>Amount</th>
                    <th>Interest</th>
                    <th>Total Amount</th>
                    <th>Description</th>
                    <th>Date Created</th>
                    <th>Date Paid</th>
                </tr>
            <% 
                
                for(int i = 0; i < transactions.size(); i++){     
            %>
                <tr>
                    <td><%= transactions.get(i).getTrxID() %></td>
                    <td><%= transactions.get(i).getAmount()%></td>
                    <td><%= transactions.get(i).getInterest()%></td>
                    <td><%= transactions.get(i).getTotalamount()%></td>
                    <td><%= transactions.get(i).getDescription()%></td>
                    <td><%= transactions.get(i).getDate() %></td>
                    <td><%= datesPaid.get(i) %></td>
                </tr>
            
            <% } %>
            </table>
            <h3 style="text-align: right;">Outstanding Balance: <%= totalDue - totalPaid %></h3>
            
            </div>
            <div class="w3-container">
                <input class="w3-button w3-teal w3-round" type="submit" onclick="printPage('print')" name="generate" value="Print" style="float:none; margin:0px;">
                <% if((totalDue - totalPaid > 0) && !(loginUser.getUserTypeString().equalsIgnoreCase("Homeowner"))){%>
                <a href="/hoamis/PayBilling?billID=<%= billID %>&userid=<%= hoId %>&fname=<%= fname %>&lname=<%= lname %>&mname=<%= mname %>"><input class="w3-button w3-teal w3-round" type="submit" name="payBill" value="Pay Bill" style="float:none; margin:0px;"></a>
                <% } %>
            </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
    function printPage(part) {
		var restorepage = $('body').html();
		var printcontent = $('#' + part).clone();
		$('body').empty().html(printcontent);
		window.print();
		$('body').html(restorepage);
    }
    </script>
    </body>
</html>
