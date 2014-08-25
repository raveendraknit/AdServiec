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
String pr=request.getParameter("pr");
String ar=request.getParameter("ar");
String lat=request.getParameter("lat");
String lon=request.getParameter("lon");
String wid=request.getParameter("wid");
String heit=request.getParameter("heit");
String type=request.getParameter("type");

%>

<script type="text/javascript">
var rootURL = "<%=SERVER_URL%>/Api/xml-api";
GetAd();
function GetAd() {
$.ajax({
		type: 'POST',
		contentType: 'application/xml',
		url: rootURL+"/getAd",
		dataType: "xml",
		data: formToXml(),
		success: function (data) {                   
            $(data).find('rAdContent').each(function () {
                var html = $(this).find('iframe').text();
               
                $("#getad").html(html);                 
            });
        },
        error: function (xhr) {
        	$("#getad").html("Ad not found ! Please contact to Ad Manager");
       }
  

});
}




function formToXml() {
	
	 var xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
            + "<AdRequest>"
            + "<type><%=type%></type><pubReference><%=pr%></pubReference><advReference><%=ar%></advReference>"
            + "<ScreenSize></ScreenSize><bannerWidth><%=wid%></bannerWidth><bannerHeight><%=heit%></bannerHeight>"
            + "<virtSize>0</virtSize><horiSize>?</horiSize><formatUrl>?</formatUrl>"
            + "<refresh>30</refresh><longitude><%=lon%></longitude><latitude><%=lat%></latitude>"
            + "<encryptType>?</encryptType><networkId>?</networkId><imei>357559048224673</imei>"
            + "<msisdn>27828781462</msisdn><socialType>FB</socialType><orientation>bottom</orientation>"
            + "</AdRequest>";
	
	return xmlStr;
}



</script>
</head>
<body>
<div id=getad></div>
</body>
</html>