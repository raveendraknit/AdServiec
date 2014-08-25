    <%
        String olat=request.getParameter("olat");
 	    String olon=request.getParameter("olon");
        String lat=request.getParameter("lat");
	   String lon=request.getParameter("lon");
	   String ad=request.getParameter("ad");
	%>
       

<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js ie6 oldie" lang="en"> <![endif]-->
<!--[if IE 7]>    <html class="no-js ie7 oldie" lang="en"> <![endif]-->
<!--[if IE 8]>    <html class="no-js ie8 oldie" lang="en"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->
<head>
    <title>::: Walk :::</title>
    <meta charset="utf-8" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="../images/favicon.ico" />
  

    <style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
      #panel {
        position: absolute;
        top: 5px;
        left: 50%;
        margin-left: -180px;
        z-index: 5;
        background-color: #fff;
        padding: 5px;
        border: 1px solid #999;
      }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
var directionsDisplay;
var directionsService = new google.maps.DirectionsService();
var map;
var haight = new google.maps.LatLng(<%=olat%>, <%=olon%>);
var oceanBeach = new google.maps.LatLng(<%=lat%>, <%=lon%>);

function initialize() {
  directionsDisplay = new google.maps.DirectionsRenderer();
  var mapOptions = {
    zoom: 14,
    center: haight
  };
  map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
  
  
  
  
  directionsDisplay.setMap(map);
  calcRoute();
}

function calcRoute() {
  var selectedMode ="WALKING";
  var request = {
      origin: haight,
      destination: oceanBeach,
      // Note that Javascript allows us to access the constant
      // using square brackets and a string value as its
      // "property."
      travelMode: google.maps.TravelMode[selectedMode],
     
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
    }
  });
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
  </head>
  <body>
  
    <div id="map-canvas"></div>
  </body>
</html>