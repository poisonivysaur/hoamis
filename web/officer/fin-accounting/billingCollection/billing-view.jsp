<%-- 
    Document   : dashboard
    Created on : Oct 29, 2017, 4:59:47 PM
    Author     : Ivy
--%>

<%@page import="model.User"%>
<%@page import="model.Homeowner"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.BillingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String feedback = "";
String isSuccess = (String)request.getAttribute("isSuccess");
String message = (String)request.getAttribute("message");
message = (message == null)? "":message;
if(isSuccess != null)
if(isSuccess.equals("true")){
    feedback = "Billings successfully generated for everyone.";
}

BillingDAO.getUserHomeowners();
ArrayList<Homeowner> ho = BillingDAO.getHomeowners();
ArrayList<User> users = BillingDAO.getUsers();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@ include file="../../base-officer.jsp" %>
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
            <h2 style="text-align: center; float: left;">View Billings of Homeowners</h2>
            
            <form style="float: right; margin: 10px 20px;">
                    <!-- SEARCH BAR-->
                    <input type="text" id="myInput" onkeyup="filter()" placeholder="Search for homeowner" size="50">
            </form>
            
            </div>
            
            <div class="w3-container">
                <h3><%= feedback %></h3>
                <h3><%= message %></h3>
                <a href="/hoamis/GenerateBilling"><input class="w3-button w3-teal w3-round" type="submit" name="generate" value="Generate Billings for All Homeowners" style="float:none; margin:0px;"></a>
                <br><br>
            <table class="w3-table w3-striped w3-white w3-hoverable" id="userTable">
                <tr class="w3-dark-gray">
                    <!--
                    <th>Billing ID</th>
                    <th>Block</th>
                    <th>Lot</th>
                    <th>Precedent Billing</th>
                    <th>Total Due</th>
                    <th>Total Paid</th>
                    -->
                    <th>Homeowner ID</th>
                    <th>Name</th>
                    <th>Block</th>
                    <th>Lot</th>
                    
                </tr>
                <tr class="w3-red">
                    <th>Homeowners with overdue billings</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            <% 
                
                for(int i = 0; i < users.size(); i++){  
                    if(BillingDAO.isOverdue(users.get(i).getUserID())){
            %>
                <tr onmouseover="this.style.cursor='pointer'" onclick="location.href='/hoamis/OfficerMain?action=billingViewDetails&userid=<%= users.get(i).getUserID() %>&fname=<%= users.get(i).getfName() %>&lname=<%= users.get(i).getlName() %>&mname=<%= users.get(i).getmName() %>'">
                    <td><%= users.get(i).getUserID() %></td>
                    <td><%= users.get(i).getlName()+", "+users.get(i).getfName()+" "+users.get(i).getmName()+"." %></td>
                    <td><%= ho.get(i).getBlocknum() %></td>
                    <td><%= ho.get(i).getLotnum() %></td>
                </tr>
            
            <%      } 
                }
            %>
            <tr class="w3-teal">
                    <th>All homeowners</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            <% 
                
                for(int i = 0; i < users.size(); i++){     
            %>
                <tr onmouseover="this.style.cursor='pointer'" onclick="location.href='/hoamis/OfficerMain?action=billingViewDetails&userid=<%= users.get(i).getUserID() %>&fname=<%= users.get(i).getfName() %>&lname=<%= users.get(i).getlName() %>&mname=<%= users.get(i).getmName() %>'">
                    <td><%= users.get(i).getUserID() %></td>
                    <td><%= users.get(i).getlName()+", "+users.get(i).getfName()+" "+users.get(i).getmName()+"." %></td>
                    <td><%= ho.get(i).getBlocknum() %></td>
                    <td><%= ho.get(i).getLotnum() %></td>
                </tr>
            
            <% } %>
            </table>
            </div>
            <br><br><br><br><br>
    <script>
       function filter() {
            // Declare variables 
            var input, filter, table, tr, td, i;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("userTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[1];
                if (td) {
                    if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                } 
            }
        }
    </script>
    </body>
</html>
