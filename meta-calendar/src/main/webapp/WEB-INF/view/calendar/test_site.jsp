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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC4P856claPQvdkNkT6JMmslIbARRa-H8k&callback=initMap&libraries=places,visualization" async defer></script>
    <script>
    
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;
    var infoWindow;
    var service;
 
    function initMap() {
		directionsDisplay = new google.maps.DirectionsRenderer();
		var mapCenter = new google.maps.LatLng(37.482,127.003);
		var mapOptions = {
			center: mapCenter,			
		   	zoom:16,
		   	mapTypeId: google.maps.MapTypeId.ROADMAP,
			styles: [{
				stylers: [{visibility: 'simplified'}]
			},{
				elementType: 'lebels',
				stylers: [{visibility: 'off'}]			
			}]
		}		
		
		infoWindow = new google.maps.InfoWindow();
		service = new google.maps.places.PlacesService(map);
		
		map.addListener('idle', performSearch);
	  
		map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
		directionsDisplay.setMap(map);
		
        google.maps.event.addListener(marker, 'click', function() {
            service.getDetails(place, function(result, status) {
              if (status !== google.maps.places.PlacesServiceStatus.OK) {
                console.error(status);
                return;
              }
              infoWindow.setContent(result.name);
              infoWindow.open(map, marker);
            });
          });
    }
    
    function performSearch() {
        var request = {
          bounds: map.getBounds(),
          keyword: 'best view'
        };
        service.radarSearch(request, callback);
      }
    
    function callback(results, status) {
        if (status !== google.maps.places.PlacesServiceStatus.OK) {
          console.error(status);
          return;
        }
        for (var i = 0, result; result = results[i]; i++) {
          addMarker(result);
        }
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
    
    function addMarker(place) {
        var marker = new google.maps.Marker({
          map: map,
          position: place.geometry.location,
          icon: {
            url: 'https://developers.google.com/maps/documentation/javascript/images/circle.png',
            anchor: new google.maps.Point(10, 10),
            scaledSize: new google.maps.Size(10, 17)
          }
        });
 
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