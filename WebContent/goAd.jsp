<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dynamic Ad</title>

<script src="js/jquery-1.7.1.min.js"></script>
<% 
String SERVER_URL=com.sdk.common.util.PropertiesUtil.getProperty("SERVER_URL");


%>
<script type="text/javascript">
var rootURL = "<%=SERVER_URL%>/Api/xml-api";
function GetAction(rootURL,pr,ar,bi,li,ai) {

	$.ajax({
			type: 'POST',
			contentType: 'application/xml',
			url: rootURL+"/getAction",
			dataType: "xml",
			data: actionToXml(pr,ar,bi,li,ai),
			success: function (data) {                   
	            $(data).find('actions').each(function () {
	           
	            	   
	            	   var actionName = $(this).find('actionName').text();
	                   if(actionName=="Web"){
	                	   var url = $(this).find('actionValue').text();
	                	   if (url.search(/^http[s]?\:\/\//) == -1) {
	                		   url = 'http://' + url;
	                	    }
	            		   window.open(url, "_parent"); 
	            	   exit;
	                   }else if(actionName=="Show on Map"){
	                	     var lat = $(this).find('latitude').text();
	                	     var lon = $(this).find('longitude').text();
	                	     var ad = $(this).find('storeName').text().replace("&", "and")+" "+$(this).find('addressLine1').text().replace("&", "and")+","+$(this).find('addressLine2').text().replace("&", "and")+" "+$(this).find('city').text().replace("&", "and")+"-"+$(this).find('postalCode').text().replace("&", "and");
	                	     var  mapurl = "<%=SERVER_URL%>/map/map.jsp?lat="+lat+"&lon="+lon+"&ad="+ad;
	                	     window.open(mapurl, "_parent"); 
	            	         exit;
	                   }else if(actionName=="Call"){
	                	     var call = $(this).find('actionValue').text();
	                	     window.location.href = 'tel:'+call;
	            	         exit;
	                   } else if(actionName=="Walk to"){
	                	     var olat = $(this).find('olatitude').text();
	                	     var olon = $(this).find('olongitude').text();
	                	     var lat = $(this).find('latitude').text();
	                	     var lon = $(this).find('longitude').text();
	                	     var ad = $(this).find('storeName').text().replace("&", "and")+" "+$(this).find('addressLine1').text().replace("&", "and")+","+$(this).find('addressLine2').text().replace("&", "and")+" "+$(this).find('city').text().replace("&", "and")+"-"+$(this).find('postalCode').text().replace("&", "and");
	                	     var  mapurl = "<%=SERVER_URL%>/map/walkto.jsp?olat="+olat+"&olon="+olon+"&lat="+lat+"&lon="+lon+"&ad="+ad;
	                	     window.open(mapurl, "_parent"); 
	            	         exit;
	                   }else if(actionName=="Drive to"){
	                	     var olat = $(this).find('olatitude').text();
	                	     var olon = $(this).find('olongitude').text();
	                	     var lat = $(this).find('latitude').text();
	                	     var lon = $(this).find('longitude').text();
	                	     var ad = $(this).find('storeName').text().replace("&", "and")+" "+$(this).find('addressLine1').text().replace("&", "and")+","+$(this).find('addressLine2').text().replace("&", "and")+" "+$(this).find('city').text().replace("&", "and")+"-"+$(this).find('postalCode').text().replace("&", "and");
	                	     var  mapurl = "<%=SERVER_URL%>/map/walkto.jsp?olat="+olat+"&olon="+olon+"&lat="+lat+"&lon="+lon+"&ad="+ad;
	                	     window.open(mapurl, "_parent"); 
	            	         exit;
	                   }
	            });
	        },
	        error: function (xhr) {
	        	$("#getad").html("There are Unknown Error! Please contact to Ad Manager");
	       }
	  

	});
	}


function actionToXml(pr,ar,bi,li,ai) {
	
	  var xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
  		+ "<AdRequest>"
  		+ "<pubReference>"+pr+"</pubReference><advReference>"+ar+"</advReference><bannerId>"+bi+"</bannerId><locationId>"+li+"</locationId>"
  		+ "<ipAddr>123.33.44.4</ipAddr><actionId>"+ai+"</actionId>"
  		+ "<socialType>FB</socialType>"
  		+ "</AdRequest>";
	
	return xmlStr;
}
</script>
</head>
<body>
<%=request.getParameter("query") %>
</body>
</html>