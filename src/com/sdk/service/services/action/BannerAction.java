package com.sdk.service.services.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sdk.service.services.ParseXmlValue;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class BannerAction
 */
public class BannerAction extends HttpServlet {

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
		response.setContentType("text/html; charset=UTF-8");
		
		String latitude=request.getParameter("lat");
		String longitude=request.getParameter("lon");
		
		System.out.println(latitude+" "+longitude);
		
		try {
			
		
			
			latitude = (latitude != null) ? latitude : getlat_lon(request,"latitude");
			longitude = (longitude != null) ? longitude : getlat_lon(request,"longitude");
			
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
//
//	   JSONObject res = obj.getJSONArray("results").getJSONObject(0);
//	     System.out.println(res.getString("formatted_address"));
//	      JSONObject loc =
//	      res.getJSONObject("geometry").getJSONObject("location");
//	     System.out.println("lat: " + loc.getDouble("lat") +
//	      ", lng: " + loc.getDouble("lng"));
//	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
	       
		String js=request.getParameter("js");
		String ty=(js==null) ? "":js;
		String pubReference=request.getParameter("pr");
		String advReference=request.getParameter("ar");

		String scr=request.getParameter("scr");
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
	            + "<AdRequest>"
	            + "<type>html</type><pubReference>"+pubReference+"</pubReference><advReference>"+advReference+"</advReference>"
	            + "<ScreenSize>"+scr+"</ScreenSize><bannerWidth></bannerWidth><bannerHeight></bannerHeight>"
	            + "<virtSize>0</virtSize><horiSize>?</horiSize><formatUrl>?</formatUrl>"
	            + "<refresh>30</refresh><longitude>"+longitude+"</longitude><latitude>"+latitude+"</latitude>"
	            + "<encryptType>?</encryptType><networkId>?</networkId><imei>357559048224673</imei>"
	            + "<msisdn>27828781462</msisdn><socialType>FB</socialType><orientation>bottom</orientation>"
	            + "</AdRequest>";
	   System.out.println(xmlStr);
	    ClientResponse response1 = service.path("xml-api/getAd").accept(MediaType.APPLICATION_XML).post(ClientResponse.class,xmlStr);  //.path("getAd").accept(MediaType.APPLICATION_XML).post(ClientResponse.class,xmlStr);
	    
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
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			
		}
		String landingurl=ParseXmlValue.getValue(document, "landingurl");
		String imgSrc=ParseXmlValue.getValue(document, "imgSrc");
		String width=ParseXmlValue.getValue(document, "width");
		String height=ParseXmlValue.getValue(document, "height");
		
	
		request.setAttribute("landingurl",landingurl);
		request.setAttribute("imgSrc",imgSrc);
		request.setAttribute("width",width);
		request.setAttribute("height",height);
		String url="";
		if(ty.equals("y"))
			url="jsbanner.jsp";
		else
			url="banner.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.include(request, response);
		
		

//		PrintWriter out = response.getWriter();
//		out.println("document.write('<a target='_blank' href='https://ad1.emediate.dk/eas?camp=12345::cu=0::no=57320::ty=ct::uuid=a7aaf0c6-f6ec-11e3-919d-002590af8fc1'><img src='https://ad1.emediate.dk/media.1/9/8148/12345/emediatetag.gif' alt='Click here' width='728' height='90' style='border:0px'/></a>')");





	}

	
	

	
	private String getlat_lon(HttpServletRequest request, String lat_lon) throws IOException, JSONException {
		  String ip = request.getHeader("X-Forwarded-For");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        
	       String Url="https://context.skyhookwireless.com/accelerator/ip?version=2.0&ip="+ip+"&prettyPrint=true&key=eJwVwcENwDAIA8B3hkHCBJm3Q5ulou5e5Q4DfqGyxnE-M2pNY3NbCW2hcKOayv2iU98PD8QLLg&user=eval&timestamp="+System.currentTimeMillis();
	    
	       
	   
	       URL url3 = new URL(Url);
	   
	      
	       Scanner scan = new Scanner(url3.openStream());
	       String str = new String();
	       while (scan.hasNext())
	       str += scan.nextLine();
	       scan.close();
	   
	      JSONObject obj = null;
		  try {
			obj = new JSONObject(str);
	      } catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		  
		  lat_lon=obj.getJSONObject("data").getJSONObject("location").get(lat_lon).toString();
		return lat_lon;
		
	}

	private static URI getBaseURI() {
		//return UriBuilder.fromUri("http://localhost:8080/AdService/Api/").build();
		return UriBuilder.fromUri("http://localhost:8084/AdService/Api/").build();
	}
}
