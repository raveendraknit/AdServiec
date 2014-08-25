package com.sdk.service.services.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sdk.service.services.ParseXmlValue;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class LandingPageAction
 */
public class ActionsAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String SERVER_URL=com.sdk.common.util.PropertiesUtil.getProperty("SERVER_URL");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		response.setContentType("text/html");
		String pubReference=request.getParameter("pr");
		String advReference=request.getParameter("ar");
		String bannerId=request.getParameter("bi");
		String locationId=request.getParameter("li");
		String actionId=request.getParameter("ai");
		String latitude=request.getParameter("lat");
		String longitude=request.getParameter("lon");

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
				+ "<AdRequest>"
				+ "<pubReference>"+pubReference+"</pubReference><advReference>"+advReference+"</advReference><bannerId>"+bannerId+"</bannerId><locationId>"+locationId+"</locationId>"
				+ "<ipAddr>123.33.44.4</ipAddr><actionId>"+actionId+"</actionId><latitude>"+latitude+"</latitude><longitude>"+longitude+"</longitude>"
				+ "<socialType>FB</socialType>"
				+ "</AdRequest>";

		ClientResponse response1 = service.path("xml-api/getAction").accept(MediaType.APPLICATION_XML).post(ClientResponse.class,xmlStr);  //.path("getAd").accept(MediaType.APPLICATION_XML).post(ClientResponse.class,xmlStr);

		String xmlResponse = response1.getEntity(String.class);


		DocumentBuilderFactory builderFactory =
				DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = builderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {

		}

		Document document = null;
		try {
			document = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));
		}catch (SAXException ex) {
			

		}catch (IOException e) {
			
		}
		String actionName=ParseXmlValue.getValue(document, "actionName");




		if(actionName.equals("Web")){

			String url=ParseXmlValue.getValue(document, "actionValue");
			if (url.matches("^(http|https|ftp)://.*$")==false) {
				url = "http://" + url;
			}
			response.sendRedirect(url);

		}else if(actionName.equals("Coupon")){

			String url=ParseXmlValue.getValue(document, "actionValue");
			if (url.matches("^(http|https|ftp)://.*$")==false) {
				url = "http://" + url;
			}
			response.sendRedirect(url);

		}else if(actionName.equals("Show on Map")){

			String lat=ParseXmlValue.getValue(document, "latitude");
			String lon=ParseXmlValue.getValue(document, "longitude");
			String ad = ParseXmlValue.getValue(document, "storeName").replace("&", "and")+" "+ParseXmlValue.getValue(document, "addressLine1").replace("&", "and")+","+ParseXmlValue.getValue(document, "addressLine2").replace("&", "and")+" "+ParseXmlValue.getValue(document, "city").replace("&", "and")+"-"+ParseXmlValue.getValue(document, "postalCode").replace("&", "and");
			String  mapurl = SERVER_URL+"/map/map.jsp?lat="+lat+"&lon="+lon+"&ad="+ad;
			response.sendRedirect(mapurl);
		}else if(actionName.equals("Call")){
			String call = ParseXmlValue.getValue(document, "actionValue");
			response.sendRedirect("tel:"+call);

		} else if(actionName.equals("Walk to")){

			String olat=ParseXmlValue.getValue(document, "olatitude");
			String olon=ParseXmlValue.getValue(document, "olongitude");
			String lat=ParseXmlValue.getValue(document, "latitude");
			String lon=ParseXmlValue.getValue(document, "longitude");
			String ad =ParseXmlValue.getValue(document, "storeName").replace("&", "and")+" "+ParseXmlValue.getValue(document, "addressLine1").replace("&", "and")+","+ParseXmlValue.getValue(document, "addressLine2").replace("&", "and")+" "+ParseXmlValue.getValue(document, "city").replace("&", "and")+"-"+ParseXmlValue.getValue(document, "postalCode").replace("&", "and");
			//String  mapurl = SERVER_URL+"/map/walkto.jsp?olat="+olat+"&olon="+olon+"&lat="+lat+"&lon="+lon+"&ad="+ad;
			
		//	String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", olat, olon, "My Location", lat, lon,ad);
		        
		  String	uri="http://maps.google.com/maps?saddr="+olat+","+olon+"&daddr="+lon+","+lon+"";
		  response.sendRedirect(uri);
		}else if(actionName.equals("Drive to")){

			String olat=ParseXmlValue.getValue(document, "olatitude");
			String olon=ParseXmlValue.getValue(document, "olongitude");
			String lat=ParseXmlValue.getValue(document, "latitude");
			String lon=ParseXmlValue.getValue(document, "longitude");
			String ad =ParseXmlValue.getValue(document, "storeName").replace("&", "and")+" "+ParseXmlValue.getValue(document, "addressLine1").replace("&", "and")+","+ParseXmlValue.getValue(document, "addressLine2").replace("&", "and")+" "+ParseXmlValue.getValue(document, "city").replace("&", "and")+"-"+ParseXmlValue.getValue(document, "postalCode").replace("&", "and");
			String  mapurl = SERVER_URL+"/map/driveto.jsp?olat="+olat+"&olon="+olon+"&lat="+lat+"&lon="+lon+"&ad="+ad;
			response.sendRedirect(mapurl);
		}









	}

	private static URI getBaseURI() {
		//return UriBuilder.fromUri(SERVER_URL+"/Api/").build();
		return UriBuilder.fromUri("http://localhost:8084/AdService/Api/").build();
	}
}
