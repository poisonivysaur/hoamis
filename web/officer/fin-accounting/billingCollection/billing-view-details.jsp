<%-- 
    Document   : dashboard
    Created on : Oct 29, 2017, 4:59:47 PM
    Author     : Ivy
--%>

<%@page import="model.Billing"%>
<%@page import="model.User"%>
<%@page import="model.Homeowner"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.BillingDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
String hoId = request.getParameter("userid");
String fname = request.getParameter("fname");
String lname = request.getParameter("lname");
String mname = request.getParameter("mname");
ArrayList<Billing> bills = BillingDAO.getBillings(hoId);

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
                <a href="billing-view.jsp"><h4><<< Back to View Billings of Homeowners</h4></a>
            <h2 style="text-align: center; float: left;">View Billings of <%= lname+", "+fname+" "+mname %></h2>
            
            </div>
            <div class="w3-container">
            <table class="w3-table w3-striped w3-white w3-hoverable" id="userTable">
                <tr>
                    <!--
                    <th>Billing ID</th>
                    <th>Block</th>
                    <th>Lot</th>
                    <th>Precedent Billing</th>
                    <th>Total Due</th>
                    <th>Total Paid</th>
                    -->
                    <th>Billing ID</th>
                    <th>Precedent Billing ID</th>
                    <th>Total Due</th>
                    <th>Total Paid</th>
                    
                </tr>
            <% 
                
                for(int i = 0; i < bills.size(); i++){     
            %>
                <tr>
                    <td><%= bills.get(i).getBillingID()%></td>
                    <td><%= bills.get(i).getPrecedentBilling() %></td>
                    <td><%= bills.get(i).getTotalDue()%></td>
                    <td><%= bills.get(i).getTotalPaid()%></td>
                </tr>
            
            <% } %>
            </table>
            </div>
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
