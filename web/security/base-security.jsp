<%-- 
    Document   : base
    Created on : Oct 29, 2017, 2:18:19 PM
    Author     : Ivy Lim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Comfortaa">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Base Page</title>
        <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Comfortaa", sans-serif}
        </style>
    </head>
    <body class="w3-light-grey">

        <!-- Top container -->
        <div class="w3-bar w3-top w3-green w3-large" style="z-index:4">
          <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
          <span class="w3-bar-item w3-right">HOAMIS</span>
        </div>

        <!-- Side bar-->
        <nav class="w3-sidebar w3-collapse w3-dark-gray w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
          <div class="w3-container w3-row">
            <div class="w3-col s4">
              <img src="https://poorishaadi.com/user-icon-png-pnglogocom.png" class="w3-circle w3-margin-right" style="width:46px; margin-top:20px;">
            </div>
            <div class="w3-col s8 w3-bar">
                <h4><span>Welcome, <strong><%= "user goes here"%></strong></span></h4>
              <p>Security Officer account</p>
              <a href="#" class="w3-bar-item w3-button">Log out</a>
            </div>
          </div>
          <hr>
          <div class="w3-container">
            <h5>Dashboard</h5>
          </div>
          <!-- SIDE BAR MENU OPTIONS -->
          <div class="w3-bar-block">
            <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
            <a href="#" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>  Overview</a>
            <a href="#" class="w3-bar-item w3-button">Visitor Pass</a>
            <a href="#" class="w3-bar-item w3-button">Vehicle Pass</a>
            <a href="#" class="w3-bar-item w3-button">Security Reports & Violations</a>
            
        </nav>


        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">
            

            
          <!-- End page content -->
        </div>

        <script>
        // Get the Sidebar
        var mySidebar = document.getElementById("mySidebar");

        // Get the DIV with overlay effect
        var overlayBg = document.getElementById("myOverlay");

        // Toggle between showing and hiding the sidebar, and add overlay effect
        function w3_open() {
            if (mySidebar.style.display === 'block') {
                mySidebar.style.display = 'none';
                overlayBg.style.display = "none";
            } else {
                mySidebar.style.display = 'block';
                overlayBg.style.display = "block";
            }
        }

        // Close the sidebar with the close button
        function w3_close() {
            mySidebar.style.display = "none";
            overlayBg.style.display = "none";
        }
        
        // Accordion effect in sidebar 
        function myAccFunc(id) {
            var x = document.getElementById(id);
            if (x.className.indexOf("w3-show") == -1) {
                x.className += " w3-show";
                x.previousElementSibling.className += " w3-dark-grey";
            } else { 
                x.className = x.className.replace(" w3-show", "");
                x.previousElementSibling.className = 
                x.previousElementSibling.className.replace(" w3-dark-grey", "");
            }
        }
        </script>

    </body>
</html>
