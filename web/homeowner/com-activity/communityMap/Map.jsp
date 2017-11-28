<%-- 
    Document   : Map
    Created on : 11 25, 17, 12:06:30 AM
    Author     : Jayvee Gabriel
--%>
<%@page import="Objects.MapPointCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Objects.MapPoint"%>
<%
    
    ArrayList <MapPoint> mappoints = new ArrayList <MapPoint> ();
    ArrayList <MapPointCategory> categories = new ArrayList <MapPointCategory> ();
%> 
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   
    
    <title>Directions service (complex)</title><style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      select{
          width: 100%;
          height: 30px;
          padding: 2px;
      }
      #floating-panel {
        border-radius: 5px;
        position: absolute;
        top: 50px;
        left: 2%;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: center;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        
        padding: 10px;
      }
      #warnings-panel {
        width: 100%;
        height:10%;
        text-align: center;
      }
    </style>
  </head>
  <body>
    
    <div id="floating-panel">
        
        
        <%
            
           
          try{
                             Class.forName("com.mysql.jdbc.Driver");
                             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);
                             
                             Statement ps = con.createStatement();
                             ResultSet rs = ps.executeQuery("SELECT mappointID, xAxis, yAxis, title, description, mappointcategoryID FROM mappoint");
                             
                             while(rs.next()){
                                 MapPoint m1 = new MapPoint();
                                 m1.setMappointID(rs.getInt("mappointID"));
                                 m1.setxAxis(rs.getString("xAxis"));
                                 m1.setyAxis(rs.getString("yAxis"));
                                 m1.setTitle(rs.getString("title"));
                                 m1.setDescription(rs.getString("description"));
                                 m1.setMappointcategory(rs.getString("mappointcategoryID"));
                                 
                                 mappoints.add(m1);
                             }
                             
                             
                                

                             con.close();
                         }
                         catch(Exception e){
                             System.out.println(e);
                         }
        
      %>
      
    <b>Start: </b>
    <div class="start">
    <select id="start">
      <option value="De La Salle University">De La Salle University</option>
      
      <%
          for(int i = 0; i < mappoints.size(); i++){
              out.print("<option value='" + mappoints.get(i).getxAxis() + "," + mappoints.get(i).getyAxis()+"'>" + mappoints.get(i).getTitle()+ " </option>");
          }
      
      %>
    </select>
    </div>
    
    <b>End: </b>
    <div class="end">
        <select id="end">

          <%
              
              for(int i = 0; i < mappoints.size(); i++){
                  out.print("<option value='" + mappoints.get(i).getxAxis() + "," + mappoints.get(i).getyAxis()+"'>" + mappoints.get(i).getTitle()+ " </option>");
              }

          %>
        </select>
    </div>
        
        <%
             try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hoamis","SW-ENGG",null);

                Statement ps = con.createStatement();
                ResultSet rs = ps.executeQuery("SELECT ref_mappointcategory.mappointcategoryID, name FROM hoamis.ref_mappointcategory JOIN mappoint on mappoint.mappointcategoryID = ref_mappointcategory.mappointcategoryID GROUP BY name");

                while(rs.next()){
                    MapPointCategory m1 = new MapPointCategory();
                    m1.setId(rs.getInt(1));
                    m1.setName(rs.getString(2));
                    
                    categories.add(m1);
                    
                }
                con.close();
            }
            catch(Exception e){
                System.out.println(e);
            }
        %>
       
    <b>Filter by:</b>
    <select id = "filter" onchange="filter()">
        <option value="All">All</option>
        <%
            
            for(int i = 0; i < categories.size(); i++){
                out.print("<option value = '"+ categories.get(i).getId() + "'>" +  categories.get(i).getName()+" </option>");
            }
           
        %>
    </select>
    </div>
    <div id="map"></div>
    &nbsp;
    <div id="warnings-panel"></div>
    <script>
      function initMap() {
        var markerArray = [];

        // Instantiate a directions service.
        var directionsService = new google.maps.DirectionsService;

        // Create a map and center it on Manhattan.
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 100,
          center: {lat: 14.5643, lng: 120.9932}
        });
        
        
        /*
       var infoWindow = new google.maps.infoWindow;
            
        if(navigator.geolocation){
            navigator.geolocation.getCurrentPosition(function(position){
              var pos = {
                  
                  lat: position.coords.latitude,
                  lng: position.coords.longitude
              };
              infoWindow.setPosition(pos);
              infoWindow.setContent("Location found!");
              infoWindow.open(map);
              map.setCenter(pos);
              
            },
            function(){
                handleLocationError(true, infoWindow, map.getCenter());
            }
            );
        }
        else{
            
            handleLocationError(true, infoWindow, map.getCenter());
        }
        */  
    
        
        // Create a renderer for directions and bind it to the map.
        var directionsDisplay = new google.maps.DirectionsRenderer({map: map});

        // Instantiate an info window to hold step text.
        var stepDisplay = new google.maps.InfoWindow;

        // Display the route between the initial start and end selections.
        calculateAndDisplayRoute(
            directionsDisplay, directionsService, markerArray, stepDisplay, map);
        // Listen to change events from the start and end lists.
        var onChangeHandler = function() {
          calculateAndDisplayRoute(
              directionsDisplay, directionsService, markerArray, stepDisplay, map);
        };
        document.getElementById('start').addEventListener('change', onChangeHandler);
        document.getElementById('end').addEventListener('change', onChangeHandler);
      }
      
      function handleLocationError(browserHasGeolocation, infoWindow, pos){
          infoWindow.setPosition(pos);
          infoWindow.setContent(browserHasGeolocation ? 
                  "Error: Failed":
                  "Error: Your browser does not support geolocation");
          infoWindow.open(map);
      }

      function calculateAndDisplayRoute(directionsDisplay, directionsService,
          markerArray, stepDisplay, map) {
        // First, remove any existing markers from the map.
        for (var i = 0; i < markerArray.length; i++) {
          markerArray[i].setMap(null);
        }

        // Retrieve the start and end locations and create a DirectionsRequest using
        // WALKING directions.
        directionsService.route({
          origin: document.getElementById('start').value,
          destination: document.getElementById('end').value,
          travelMode: 'WALKING'
        }, function(response, status) {
          // Route the directions and pass the response to a function to create
          // markers for each step.
          if (status === 'OK') {
            document.getElementById('warnings-panel').innerHTML =
                '<b>' + response.routes[0].warnings + '</b>';
            directionsDisplay.setDirections(response);
            showSteps(response, markerArray, stepDisplay, map);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }

      function showSteps(directionResult, markerArray, stepDisplay, map) {
        // For each step, place a marker, and add the text to the marker's infowindow.
        // Also attach the marker to an array so we can keep track of it and remove it
        // when calculating new routes.
        var myRoute = directionResult.routes[0].legs[0];
        for (var i = 0; i < myRoute.steps.length; i++) {
          var marker = markerArray[i] = markerArray[i] || new google.maps.Marker;
          marker.setMap(map);
          marker.setPosition(myRoute.steps[i].start_location);
          attachInstructionText(
              stepDisplay, marker, myRoute.steps[i].instructions, map);
        }
      }

      function attachInstructionText(stepDisplay, marker, text, map) {
        google.maps.event.addListener(marker, 'click', function() {
          // Open an info window when the marker is clicked on, containing the text
          // of the step.
          stepDisplay.setContent(text);
          stepDisplay.open(map, marker);
        });
      }
      
      
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD2vGhEA3FGnotr-4TabVcRUfJn8YkRmDM&callback=initMap">
    </script>
    <script src="jquery-3.2.1.min.js"></script>
    <script>
        function filter(){
            
            var val = $("#filter").val();

            $.post("filterStart",{val: val} ,function(response){


                $("#end").html(response);
            });
            
        }
    </script>
  </body>
</html>
      