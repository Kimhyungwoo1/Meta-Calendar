<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Directions service</title>
    <style type="text/css">
        html, body {
          height: 100%;
          margin: 0;
          padding: 0;
        }
 
        #map-canvas, #map_canvas {
          height: 100%;
        }
 
        @media print {
          html, body {
            height: auto;
          }
 
          #map_canvas {
            height: 650px;
          }
        }
 
        #panel {
          position: absolute;
          top: 50px;
          left: 195px;
          margin-left: -180px;
          z-index: 5;
          background-color: #fff;
          padding: 5px;
          border: 1px solid #999;
        }
    </style>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4P856claPQvdkNkT6JMmslIbARRa-H8k&callback=initialize"></script>
    <script>
    
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;
 
    function initialize() {
		directionsDisplay = new google.maps.DirectionsRenderer();
		var mapCenter = new google.maps.LatLng(37.482,127.003);
		var mapOptions = {
			center: mapCenter,			
		   	zoom:16,
		   	mapTypeId: google.maps.MapTypeId.ROADMAP,
		   	center: mapCenter
		}
		
	  
		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		directionsDisplay.setMap(map);
		
		google.maps.event.addListener(map, 'click', function(event) {
			  placeMarker(map, event.latLng);
		});
    }
 
    function calcRoute() {
      var start = document.getElementById('start').value;
      var end = document.getElementById('end').value;
      var mode = document.getElementById('mode').value;
 
      var request = {
          origin:start,
          destination:end,
          travelMode: eval("google.maps.DirectionsTravelMode." + mode)
      };
      directionsService.route(request, function(response, status) {
    	// 확인용 Alert
    	alert(status);  
        
        if (status == google.maps.DirectionsStatus.OK) {
            directionsDisplay.setDirections(response);
        }
      });
    }
    
    function placeMarker(map, location) {
    	  var marker = new google.maps.Marker({
    	    position: location,
    	    animation: google.maps.Animation.BOUNCE,
    	    map: map
    	  });
    	  var infowindow = new google.maps.InfoWindow({
    	    content: 'Latitude: ' + location.lat() + '<br>Longitude: ' + location.lng()
    	  });
    	  infowindow.open(map,marker);
    	}
 
    google.maps.event.addDomListener(window, 'load', initialize);
 
    </script>
    </head>
    <body>
        <div id="panel" >
            <b>출발: </b>
            <input type="text" id="start" /><br/>
            <b>도착: </b>
            <input type="text" id="end" /><br/>
            <div>
                <strong>이동수단: </strong>
                <select id="mode">
                <option value="TRANSIT">대중교통</option>
                <option value="DRIVING">자동차/택시</option>
                <option value="WALKING">도보</option>
                <option value="BICYCLING">자전거</option>
                </select><br/>
                <input type="button" value="경로 탐색" onclick="Javascript:calcRoute();" />
            </div>
        </div>
        <div id="map-canvas"></div>
    </body>
</html>