<%-- 
    Document   : ViewHouses
    Created on : 11 25, 17, 12:49:53 PM
    Author     : Jayvee Gabriel
--%>

<%@page import="model.dao.getHouses"%>
<%@page import="model.dao.getMapPointCategory"%>
<%@page import="Objects.MapPointCategory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Objects.House"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList <House> houses = new ArrayList <House> ();
    ArrayList <MapPointCategory> categories = new ArrayList <MapPointCategory> ();

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <style>
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
      #floating-panel {
        position: absolute;
        top: 50px;
        left: 2%;
        width: 200px;
        height: 500px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
        text-align: left;
        font-family: 'Roboto','sans-serif';
        line-height: 30px;
        padding-left: 10px;
        overflow-x: auto;
      }
      
      
     
    </style>
  </head>
  <body>
    <div id="floating-panel">
        <h2>Homies</h2>
        
        <%
             try{
                getMapPointCategory gmpc1 = new getMapPointCategory();
                ResultSet rs = gmpc1.getCategories();
                while(rs.next()){
                    MapPointCategory m1 = new MapPointCategory();
                    m1.setId(rs.getInt(1));
                    m1.setName(rs.getString(2));
                    
                    categories.add(m1);
                    
                }   
            }
            catch(Exception e){
                System.out.println(e);
            }
        %>
       
    <b>Filter by:</b>
    <select id = "filter" onchange="filter()">
        <%
            
            for(int i = 0; i < categories.size(); i++){
                out.print("<option value = '"+ categories.get(i).getId() + "'>" +  categories.get(i).getName()+" </option>");
            }
           
        %>
    </select>
        <form action="getDirection" method="post">
            <div class = 'filter'>
        <%
            try{
                getHouses g1 = new getHouses();
                
                ResultSet rs = g1.getHouses();
                while(rs.next()){
                    House h1 = new House();
                    h1.setMappointID(rs.getString(1));
                    h1.setxAxis(rs.getString(2));
                    h1.setyAxis(rs.getString(3));
                    h1.setfName(rs.getString(4));
                    h1.setlName(rs.getString(5));
                    
                    houses.add(h1);
                    
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            out.print("<table border='1'><thead>"
                    + "<tr style='text-align: center;'><td style='width: 75%;'><b>Name</b></td><td style='width: 25%;'><b>View</b></td></tr></thead><tbody>");
            for(int i = 0; i < houses.size(); i++){
                out.print("<tr>");
                String id = houses.get(i).getxAxis()+","+ houses.get(i).getyAxis();
                out.print("<td><div style='display: inline-block;'><a href='#'><p id = '" + id + "' >" + houses.get(i).getfName() + " "+houses.get(i).getlName() + "</p></a></td>");
                out.print("<td><button type='submit' id='" + id + "' name = 'getDirection' value = '" + id + "'>Get</button></td>");
                out.print("</tr>"); 
                
                
            }
            out.print("</tbody></table>");
        %>
            </div>
        </form>
        
    </div>
    <div id="map"></div>
    <script src="jquery-3.2.1.min.js"></script>
    
    <script>
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 100,
          center: {lat: 40.731, lng: -73.997}
        });
        var geocoder = new google.maps.Geocoder;
        var infowindow = new google.maps.InfoWindow;
        
        $("p").click(function(){
            
          geocodeLatLng(geocoder, map, infowindow,this.id);
        });
            
        if(navigator.geolocation){
            navigator.geolocation.getCurrentPosition(function(position){
              var pos = {
                  
                  lat: position.coords.latitude,
                  lng: position.coords.longitude
              };
              infowindow.setPosition(pos);
              infowindow.setContent("Location found!");
              infowindow.open(map);
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
      }

      function geocodeLatLng(geocoder, map, infowindow, id) {
        var input = id;
        var latlngStr = input.split(',', 2);
        var latlng = {lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1])};
        geocoder.geocode({'location': latlng}, function(results, status) {
          if (status === 'OK') {
            if (results[0]) {
              map.setZoom(11);
              var marker = new google.maps.Marker({ 
                position: latlng,
                map: map
              });
              infowindow.setContent(results[0].formatted_address);
              infowindow.open(map, marker);
            } else {
              window.alert('No results found');
            }
          } else {
            window.alert('Geocoder failed due to: ' + status);
          }
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
            $.post("filter",{val: val} ,function(response){
                
                $(".filter").html(response);
                console.log(response);
                 $("p").click(function(){
                     
                var map = new google.maps.Map(document.getElementById('map'), {
                          zoom: 100,
                          center: {lat: 40.731, lng: -73.997}
                        });
                        var geocoder = new google.maps.Geocoder;
                        var infowindow = new google.maps.InfoWindow;
                        geocodeLatLng(geocoder, map, infowindow,this.id);

                  });
            });
            
            
        }
        function geocodeLatLng(geocoder, map, infowindow, id) {
        var input = id;
        var latlngStr = input.split(',', 2);
        var latlng = {lat: parseFloat(latlngStr[0]), lng: parseFloat(latlngStr[1])};
        geocoder.geocode({'location': latlng}, function(results, status) {
          if (status === 'OK') {
            if (results[0]) {
              map.setZoom(11);
              var marker = new google.maps.Marker({ 
                position: latlng,
                map: map
              });
              infowindow.setContent(results[0].formatted_address);
              infowindow.open(map, marker);
            } else {
              window.alert('No results found');
            }
          } else {
            window.alert('Geocoder failed due to: ' + status);
          }
        });
      }
    </script>
  </body>
</html>
      