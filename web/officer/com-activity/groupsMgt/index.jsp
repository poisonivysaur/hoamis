
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="assets/uts.png">
        <title>LOGING DOGING</title>
    </head>
    <body>
 <script>
 // This is called with the results from from FB.getLoginStatus().
 function statusChangeCallback(response) {
 console.log('statusChangeCallback');
 console.log(response);
 // The response object is returned with a status field that lets the
 // app know the current login status of the person.
 // Full docs on the response object can be found in the documentation
 // for FB.getLoginStatus().
 if (response.status === 'connected') {
 // Logged into your app and Facebook.
 testAPI();
 } else if (response.status === 'not_authorized') {
 // The person is logged into Facebook, but not your app.
 document.getElementById('status').innerHTML = 'Login with Facebook ';
 } else {
 // The person is not logged into Facebook, so we're not sure if
 // they are logged into this app or not.
 document.getElementById('status').innerHTML = 'Login with Facebook ';
 }
 }
 // This function is called when someone finishes with the Login
 // Button. See the onlogin handler attached to it in the sample
 // code below.
 function checkLoginState() {
 FB.getLoginStatus(function(response) {
 statusChangeCallback(response);
 });
 }
 window.fbAsyncInit = function() {
 FB.init({ 
 appId : '1758929507513526',
 cookie : true, // enable cookies to allow the server to access 
 // the session
 xfbml : true, // parse social plugins on this page
 version : 'v2.8' // use version 2.2
 });
 // Now that we've initialized the JavaScript SDK, we call 
 // FB.getLoginStatus(). This function gets the state of the
 // person visiting this page and can return one of three states to
 // the callback you provide. They can be:
 //
 // 1. Logged into your app ('connected')
 // 2. Logged into Facebook, but not your app ('not_authorized')
 // 3. Not logged into Facebook and can't tell if they are logged into
 // your app or not.
 //
 // These three cases are handled in the callback function.

 FB.getLoginStatus(function(response) {
 statusChangeCallback(response);
 });
 };
 // Load the SDK asynchronously
 (function(d, s, id) {
 var js, fjs = d.getElementsByTagName(s)[0];
 if (d.getElementById(id)) return;
 js = d.createElement(s); js.id = id;
 js.src = "//connect.facebook.net/en_US/sdk.js";
 fjs.parentNode.insertBefore(js, fjs);
 }(document, 'script', 'facebook-jssdk')); 

 // Here we run a very simple test of the Graph API after login is
 // successful. See statusChangeCallback() for when this call is made.
 function testAPI() { 
 console.log('Welcome! Fetching your information.... ');
 FB.api('/me?fields=name,email', function(response) {
 console.log('Successful login for: ' + response.name); 
 
 document.getElementById("un").value = response.name;
 document.getElementById("ue").value = response.email;
 document.getElementById("status").innerHTML = '<p>Welcome '+response.name+'! <a href="#" onclick="fbLog()">Continue with facebook login</a></p>'

 
 });

 }
 function fbLog(){
     document.getElementById("btn").click();
 }
</script> 

        <h1>Hello User!</h1>
            <form action="LoginProcess" method="POST">
            <fieldset>
                <legend>Community Group Management</legend>
                Username: <input type="text" name="username"/><br>
                Password:<input type="text" name="password"/><br>
                <input type="submit" value="submit"/>
            </fieldset>    
            </form>
        <fb:login-button 
            scope="public_profile,email"
            onlogin="checkLoginState();">
        </fb:login-button>
        <div id="status">
        </div> 
        <b style="color: red;"><% if (request.getAttribute("gago")!=null){ out.println("Incorrect Password!");}%></b>
        <a href="register.jsp"> No account? sign up here!</a>
        
        <form action="LoginFB" method="POST">
            <input value="" id="un" name="un" type="hidden">
            <input value="" id="ue" name="ue" type="hidden">
            <button type="submit" style="display: none" id="btn"></button>
        </form>
    </body>
</html>

