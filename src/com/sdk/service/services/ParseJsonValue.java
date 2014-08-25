package com.sdk.service.services;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;

public class ParseJsonValue {
	
 
	
 static	String  getValue(JSONObject jsonObject, final String name) {

        String value = null;
		try {
			value = jsonObject.getString(name);
		} catch (JSONException e) {
			
		}
       
        if (value != null) {
               
                        return value;
        }
        return null;
}

boolean getValueAsBoolean(JSONObject jsonObject, final String name) {
        return "yes".equalsIgnoreCase(ParseJsonValue.getValue(jsonObject, name));
}

static int getValueAsInt(JSONObject jsonObject, final String name) {
        return getInteger(ParseJsonValue.getValue(jsonObject, name));
}

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

public static RequestData getRequestData(JSONObject jsonObject) {
	
	String pubReference=ParseJsonValue.getValue(jsonObject, "pubReference");
	String advReference=ParseJsonValue.getValue(jsonObject, "advReference");
	String ipAddr=ParseJsonValue.getValue(jsonObject, "ipAddr");
	String type=ParseJsonValue.getValue(jsonObject, "type");
	String actionId=ParseJsonValue.getValue(jsonObject, "actionId");
	String orientation=ParseJsonValue.getValue(jsonObject, "orientation");
	String screenSize=ParseJsonValue.getValue(jsonObject, "ScreenSize");
	Integer bannerWidth=0;
	Integer bannerHeight=0;
	if(screenSize==null || screenSize.length()==0){
	bannerWidth=getValueAsInt(jsonObject, "bannerWidth");	
	bannerHeight=getValueAsInt(jsonObject, "bannerHeight");
	}else{
		String[] ar = screenSize.toLowerCase().split("x");
		bannerWidth=Integer.parseInt(ar[0]);
		bannerHeight=Integer.parseInt(ar[1]);
		
	}
	if(bannerWidth.intValue()==0)
		bannerWidth=300;
	if(bannerHeight.intValue()==0)
		bannerHeight=300;
	Integer virtSize=getValueAsInt(jsonObject, "virtSize");
	String horiSize=ParseJsonValue.getValue(jsonObject, "horiSize");
	String formatUrl=ParseJsonValue.getValue(jsonObject, "formatUrl");
	Integer refresh=getValueAsInt(jsonObject, "refresh");
	String longitude=ParseJsonValue.getValue(jsonObject, "longitude");
	String latitude=ParseJsonValue.getValue(jsonObject, "latitude");
	String encryptType=ParseJsonValue.getValue(jsonObject, "encryptType");
	String networkId=ParseJsonValue.getValue(jsonObject, "networkId");
	String imei=ParseJsonValue.getValue(jsonObject, "imei");
	String msisdn=ParseJsonValue.getValue(jsonObject, "msisdn");
	String socialType=ParseJsonValue.getValue(jsonObject, "socialType");
	RequestData requestData=new RequestData(pubReference, advReference,  ipAddr, type,actionId, orientation, bannerWidth, bannerHeight, virtSize, horiSize, formatUrl, refresh, longitude, latitude, encryptType, networkId, imei, msisdn, socialType);
	
	return requestData;
}

public static RequestData getRequestDataForAction(JSONObject jsonObject) throws BusinessException {
	String pubReference=ParseJsonValue.getValue(jsonObject, "pubReference");
	String advReference=ParseJsonValue.getValue(jsonObject, "advReference");
	String ipAddr=ParseJsonValue.getValue(jsonObject, "ipAddr");
	String type=ParseJsonValue.getValue(jsonObject, "type");
	String actionId=ParseJsonValue.getValue(jsonObject, "actionId");
	String bannerId=ParseJsonValue.getValue(jsonObject, "bannerId");
	String locationId=ParseJsonValue.getValue(jsonObject, "locationId");
	String socialType=ParseJsonValue.getValue(jsonObject, "socialType");
	String latitude=ParseJsonValue.getValue(jsonObject, "latitude");
	String longitude=ParseJsonValue.getValue(jsonObject, "longitude");
	if(latitude!=null)
	ValidateMethod.validate("latitude", ParseJsonValue.getValue(jsonObject, "latitude"));
	else
	latitude="-33.492447";
	if(longitude!=null)
	ValidateMethod.validate("longitude", ParseJsonValue.getValue(jsonObject, "longitude"));
	else
	longitude="18.681930";
	RequestData requestData=new RequestData(pubReference, advReference, ipAddr, bannerId, locationId, type, actionId, socialType,longitude,latitude);
	return requestData;
}
}
