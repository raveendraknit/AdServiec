package com.sdk.service.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;

public class ParseXmlValue {

	
	 private static int getInteger(final String text) {
         if (text == null)
                 return 0;
         try {
                 return Integer.parseInt(text);
         } catch (final NumberFormatException ex) {
                 // do nothing, 0 is returned
         }
         return 0;
 }

	 public static String getAttribute(Document document, String elementName, String attributeName) {

         NodeList nodeList = document.getElementsByTagName(elementName);
         final Element element = (Element) nodeList.item(0);
         if (element != null) {
                 String attribute = element.getAttribute(attributeName);
                 if(attribute.length() !=0){
                         return attribute;
                 }
         }
         return null;
 }
 
 public static String getValue(Document document, String name) {

         NodeList nodeList = document.getElementsByTagName(name);
         final Element element = (Element) nodeList.item(0);
         if (element != null) {
                 nodeList = element.getChildNodes();
                 if (nodeList.getLength() > 0)
                         return nodeList.item(0).getNodeValue();
         }
         return "";
 }

 public static  boolean getValueAsBoolean(final Document document, final String name) {
         return "yes".equalsIgnoreCase(getValue(document, name));
 }

 public static int getValueAsInt(final Document document, final String name) {
         return getInteger(getValue(document, name));
 }

 public static String convertStreamToString(java.io.InputStream is) {
         try {
                 return new java.util.Scanner(is).useDelimiter("\\A").next();
         } catch (java.util.NoSuchElementException e) {
                 return "";
         }
 }

public static RequestData getRequestData(Document document) {
	
	String pubReference=ParseXmlValue.getValue(document, "pubReference");
	String advReference=ParseXmlValue.getValue(document, "advReference");
	String ipAddr=ParseXmlValue.getValue(document, "ipAddr");
	String type=ParseXmlValue.getValue(document, "type");
	String actionId=ParseXmlValue.getValue(document, "actionId");
	String orientation=ParseXmlValue.getValue(document, "orientation");
	String screenSize=ParseXmlValue.getValue(document, "ScreenSize");
	
	Integer bannerWidth=0;
	Integer bannerHeight=0;
	if(screenSize==null || screenSize.length()==0){
	bannerWidth=getValueAsInt(document, "bannerWidth");	
	bannerHeight=getValueAsInt(document, "bannerHeight");
	}else{
		String[] ar = screenSize.toLowerCase().split("x");
		bannerWidth=Integer.parseInt(ar[0]);
		bannerHeight=Integer.parseInt(ar[1]);
		
	}
	if(bannerWidth.intValue()==0)
		bannerWidth=300;
	if(bannerHeight.intValue()==0)
		bannerHeight=300;
	
	Integer virtSize=getValueAsInt(document, "virtSize");
	String horiSize=ParseXmlValue.getValue(document, "horiSize");
	String formatUrl=ParseXmlValue.getValue(document, "formatUrl");
	Integer refresh=getValueAsInt(document, "refresh");
	String longitude=ParseXmlValue.getValue(document, "longitude");
	String latitude=ParseXmlValue.getValue(document, "latitude");
	
	
	
	String encryptType=ParseXmlValue.getValue(document, "encryptType");
	String networkId=ParseXmlValue.getValue(document, "networkId");
	String imei=ParseXmlValue.getValue(document, "imei");
	String msisdn=ParseXmlValue.getValue(document, "msisdn");
	String socialType=ParseXmlValue.getValue(document, "socialType");
	RequestData requestData=new RequestData(pubReference, advReference,  ipAddr, type,actionId, orientation, bannerWidth, bannerHeight, virtSize, horiSize, formatUrl, refresh, longitude, latitude, encryptType, networkId, imei, msisdn, socialType);
	
	return requestData;
	}

public static RequestData getRequestDataForAction(Document document) throws BusinessException {
	String pubReference=ParseXmlValue.getValue(document, "pubReference");
	String advReference=ParseXmlValue.getValue(document, "advReference");
	String ipAddr=ParseXmlValue.getValue(document, "ipAddr");
	String type=ParseXmlValue.getValue(document, "type");
	String actionId=ParseXmlValue.getValue(document, "actionId");
	String bannerId=ParseXmlValue.getValue(document, "bannerId");
	String locationId=ParseXmlValue.getValue(document, "locationId");
	String socialType=ParseXmlValue.getValue(document, "socialType");
	String latitude=ParseXmlValue.getValue(document, "latitude");
	String longitude=ParseXmlValue.getValue(document, "longitude");
	if(latitude!=null)
	ValidateMethod.validate("latitude", ParseXmlValue.getValue(document, "latitude"));
	else
	latitude="-33.492447";
	if(longitude!=null)
	ValidateMethod.validate("longitude", ParseXmlValue.getValue(document, "longitude"));
	else
	longitude="18.681930";
	RequestData requestData=new RequestData(pubReference, advReference, ipAddr, bannerId, locationId, type, actionId, socialType,longitude,latitude);
	return requestData;
}



}
