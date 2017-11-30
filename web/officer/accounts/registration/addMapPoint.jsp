<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.MapPoint"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Property"%>
<%@page import="model.dao.RegistrationDAO"%>
<%
ArrayList<Property> availProperty = RegistrationDAO.getRentedProperty();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
        <meta charset="utf-8">
        <title>Add Map Point</title>
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
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBiePa21baFFyOUj_IPEigoYQK-pwexOa8"></script>
        <script>
            var markers = [];
            var labelIndex = 0;
            function getCord() {
                document.getElementById('cord').innerHTML = "Marker: " + markers[markers.length - 1].getPosition().lat() + ", " + markers[markers.length - 1].getPosition().lng();
            }

            // In the following example, markers appear when the user clicks on the map.
            // Each marker is labeled with a single alphabetical character.
            function initialize() {
                var stMary = {lat: 14.4327408, lng: 121.01957419};
                var map = new google.maps.Map(document.getElementById('map'), {
                    zoom: 19,
                    center: stMary,
                    mapTypeId: google.maps.MapTypeId.HYBRID
                });

                // This event listener calls addMarker() when the map is clicked.
                google.maps.event.addListener(map, 'click', function (event) {
                    addMarker(event.latLng, map);
                });
                var initMarker;
                
                <% for(Property p : availProperty){ %>
                initMarker = new google.maps.Marker({
                    position: {lat: <%= p.getMapppoint().getxAxis()%> , lng: <%= p.getMapppoint().getyAxis()%>},
                    label: "R",
                    map: map
                });
                
                <% } %>
                // Add a marker at the center of the map.
                //addMarker(bangalore, map);
            }

            // Adds a marker to the map.
            function addMarker(location, map) {
                // Add the marker at the clicked location, and add the next-available label
                // from the array of alphabetical characters.
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
                var marker = new google.maps.Marker({
                    position: location,
                    label: "!",
                    map: map
                });
                markers.push(marker);
                $('#xAxis').val(markers[markers.length - 1].getPosition().lat());
                $('#yAxis').val(markers[markers.length - 1].getPosition().lng());
            }

            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
    </head>
    <body>
        <a href="OfficerMain?action=register">Back</a>
        <h1>Add New Map</h1>
        <form action="UserRegistration" method="post">
            <input type="hidden" name="action" value="mapPoint">
            <p>Title: <input type="text" name="mapTitle" required=""></p>
            <p>Description: <input type="text" name="mapDesc"></p>
            <p>Street: <input type="text" name="street"></p>
            <p>Block Num: <input type="number" name="block"></p>
            <p>Lot Num: <input type="number" name="lot"></p>
            <input type="hidden" name="xAxis" id="xAxis">
            <input type="hidden" name="yAxis" id="yAxis">
            <input type="submit" value="Add Place">
        </form>
        <br /><br />
        <div id="map"></div>
    </body>
</html>