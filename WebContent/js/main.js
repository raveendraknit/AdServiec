function GetAd(rootURL) {
$.ajax({
		type: 'POST',
		contentType: 'application/xml',
		url: rootURL+"/getAd",
		dataType: "xml",
		data: formToXml(),
		success: function (data) {                   
            $(data).find('rAdContent').each(function () {
                var html = $(this).find('html').text();
               
                $("#getad").html(html);                 
            });
        },
        error: function (xhr) {
            alert(xhr.responseText);
       }
  

});
}

function GetAd(lat,lon) {
	$.ajax({
			type: 'POST',
			contentType: 'application/xml',
			url: rootURL+"/getAd",
			dataType: "xml",
			data: formToXml(),
			success: function (data) {                   
	            $(data).find('rAdContent').each(function () {
	                var html = $(this).find('html').text();
	               
	                $("#getad").html(html);                 
	            });
	        },
	        error: function (xhr) {
	        	$("#getad").html("Ad not found ! Please contact to Ad Manager");
	       }
	  

	});
	}

function GetAction(rootURL,pr,ar,bi,li,ai) {

	$.ajax({
			type: 'POST',
			contentType: 'application/xml',
			url: rootURL+"/getAction",
			dataType: "xml",
			data: actionToXml(pr,ar,bi,li,ai),
			success: function (data) {                   
	            $(data).find('actions').each(function () {
	                   var actionValue = $(this).text();
	               
	                   $("#getad").html(actionValue);            
	            });
	        },
	        error: function (xhr) {
	            alert(xhr.responseText);
	       }
	  

	});
	}



function formToXml() {
	
	 var xmlStr ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
            + "<AdRequest>"
            + "<type>html</type><pubReference>E1F72E92-0AAD-4535-9689-EE284EF696AA</pubReference>dfhfdhfdh<advReference>F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB</advReference>"
            + "<ScreenSize>430x400</ScreenSize><bannerWidth></bannerWidth><bannerHeight></bannerHeight>"
            + "<virtSize>0</virtSize><horiSize>?</horiSize><formatUrl>?</formatUrl>"
            + "<refresh>30</refresh><longitude>18.681930</longitude><latitude>-33.492447</latitude>"
            + "<encryptType>?</encryptType><networkId>?</networkId><imei>357559048224673</imei>"
            + "<msisdn>27828781462</msisdn><socialType>FB</socialType><orientation>bottom</orientation>"
            + "</AdRequest>";
	
	return xmlStr;
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
