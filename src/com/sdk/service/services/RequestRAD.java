package com.sdk.service.services;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;

public class RequestRAD {
	

	
	public ServiceInfoResponse getServiceInfo(String username) throws  BusinessException{
		
		ValidateMethod.validate("username", username);
		validate(username);
		
		ServiceInfoResponse serviceInfoResponse=new ServiceInfoResponse();
		
		serviceInfoResponse.setMessage("Ad Services is live");
			
		KeyValuePair keyValuePair1=new KeyValuePair("apiVersion ","2.0.1");
		
		KeyValuePair keyValuePair2=new KeyValuePair("apiName ","Ad Services");
		
		KeyValuePair [] serviceInfoItems={keyValuePair1,keyValuePair2};
		
		serviceInfoResponse.setServiceInfoItems(serviceInfoItems);
		
		return serviceInfoResponse;
		
		
	}
	
	
	public RAd getRAd(String username,String pubReference,String advReference,String rAdId,String ipAddr) throws  BusinessException{
		
		    List<String> errorMessages = new ArrayList<String>();
		
		    ValidateMethod.validate("username", username);
		    ValidateMethod.validate("pubReference", pubReference);
		    ValidateMethod.validate("advReference", advReference);
		    ValidateMethod.validate("ipAddr", ipAddr);
		    ValidateMethod.validateUUID("rAdId", rAdId);
			
			validate(username);
		
		    ServicesDAO servicesDAO=new ServicesManager();
		    
		    RAd rAd=servicesDAO.getRAD(rAdId);
		
		    if(rAd==null){
			  errorMessages.add("Banner not found");		
			  }
		    if(!errorMessages.isEmpty())
				throw new BusinessException(errorMessages);
		
	     	return rAd;
		
		
		
	}
	
	
private void validate(String username) throws BusinessException{
	ServicesDAO servicesDAO=new ServicesManager();
	List<String> errorMessages = new ArrayList<String>();
	String auInfo=servicesDAO.getauthInfo(Integer.parseInt(username));
	
	if(auInfo==null){
		  errorMessages.add(username+": invalid user");	
    }
	if(!errorMessages.isEmpty())
		throw new BusinessException(errorMessages);
		
	}


public RAdContent getRAdContentById(String pubReference,String advReference, String ipAddr,String type, String orientation,
		Integer bannerWidth, Integer bannerHeight, Integer virtSize,
		String horiSize, String formatUrl, Integer refresh,
		String longitude, String latitude, String encryptType,
		String networkId, String imei, String msisdn, String socialType) throws JSONException, BusinessException{
	    List<String> errorMessages = new ArrayList<String>();
	    
	    ValidateMethod.validate("pubReference", pubReference);
	    ValidateMethod.validate("advReference", advReference);
	    ValidateMethod.validate("ipAddr", ipAddr);
	    ValidateMethod.validate("type", type);
	
	   	
		
		RequestData requestData=new RequestData(pubReference, advReference,  ipAddr, type,"", orientation, bannerWidth, bannerHeight, virtSize, horiSize, formatUrl, refresh, longitude, latitude, encryptType, networkId, imei, msisdn, socialType);
		ServicesDAO servicesDAO=new ServicesManager();
		
        RAdContent rAdContent=servicesDAO.getRAdContentById(requestData);
		
		if(rAdContent==null){
			  errorMessages.add("banner not found");	
	    }
		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);
			
		
		return rAdContent;
		
		
		
	}
	
	
	public java.util.List<RAd> getRAdList(String username) throws  BusinessException{
		List<String> errorMessages = new ArrayList<String>();
		ValidateMethod.validate("username", username);
		validate(username);
		ServicesDAO servicesDAO=new ServicesManager();
		java.util.List<RAd> rAdList=servicesDAO.getRAdlist();
		
		if(rAdList.isEmpty()){
			  errorMessages.add("banner not found");		
		}
		
		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);
			
		
		return rAdList;
		
		
		
	}
	
	


	
	
	public static void main(String[] args) throws BusinessException {
		
		ServicesDAO servicesDAO=new ServicesManager();
		String auInfo=servicesDAO.getauthInfo(3);
		System.out.println("Call auth Info ");
		List<String> errorMessages = new ArrayList<String>();
		
		
		servicesDAO.getRAD("F1AAE8E6-39CB-47F0-BAFF-8BBC15751DCB");
		
		if (auInfo == null) {
			errorMessages.add("pubReference: not valid");
		}
		

		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);
		
	    }
		
	}


	
	


