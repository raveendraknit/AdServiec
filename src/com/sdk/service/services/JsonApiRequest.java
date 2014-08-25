package com.sdk.service.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;


@Path("/json-api")
public class JsonApiRequest {
	
	private final static Object lock = new Object();


@GET

@Produces({MediaType.APPLICATION_JSON })
public Response getServiceInfo(){
		
		
		ServiceInfoResponse serviceInfoResponse=new ServiceInfoResponse();
		
		serviceInfoResponse.setMessage("Ad Services is live");
			
		KeyValuePair keyValuePair1=new KeyValuePair("apiVersion ","2.0.6");
		
		KeyValuePair keyValuePair2=new KeyValuePair("apiName ","Ad Services");
		
		KeyValuePair [] serviceInfoItems={keyValuePair1,keyValuePair2};
		
		serviceInfoResponse.setServiceInfoItems(serviceInfoItems);
		
		return Response.status(Response.Status.OK).entity(serviceInfoResponse).build();
		
		
	}
	

//access by browser
@Path("/getAd/{pr}/{ar}/{ty}/{lat}/{lon}")
@GET

@Produces({MediaType.APPLICATION_JSON })
public Response getRAd(@PathParam("pr")  String pubReference, @PathParam("ar") String advReference,@PathParam("ty") String type,@PathParam("lat") String latitude,@PathParam("lon") String longitude) throws BusinessException{
	

	List<String> errorMessages = new ArrayList<String>();
    ValidateMethod.validate("pubReference", pubReference);
    validate(pubReference);
    ValidateMethod.validate("advReference", advReference);
    //ValidateMethod.validate("ipAddr", ipAddr);
    ValidateMethod.validate("type",type );
    ValidateMethod.validate("latitude", latitude);
    ValidateMethod.validate("longitude",longitude );
   // String ipAddr = null;
    Integer bannerWidth=300;
	Integer bannerHeight=300;
	RAdContent rAdContent;
    synchronized (lock) {
	RequestData requestData=new RequestData(pubReference, advReference,type,bannerWidth, bannerHeight,longitude,latitude);
    ServicesDAO servicesDAO=new ServicesManager();
	rAdContent=servicesDAO.getRAdContentById(requestData);	
    }
	if (rAdContent == null) {
			  errorMessages.add("Ad not found");
	}
    if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);	
		
     return Response.status(Response.Status.OK).entity(rAdContent).build();


}


//access by application
@Path("/getAd")
@POST
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public Response getRAd(JSONObject jsonObject) throws BusinessException{
	    List<String> errorMessages = new ArrayList<String>();
	    String pubReference=ParseJsonValue.getValue(jsonObject, "pubReference");
	    ValidateMethod.validate("pubReference", pubReference);
	    validate(pubReference);
	   // ValidateMethod.validate("advReference", ParseJsonValue.getValue(jsonObject, "advReference"));
	    //ValidateMethod.validate("ipAddr", ParseJsonValue.getValue(jsonObject, "ipAddr"));
	    ValidateMethod.validate("type", ParseJsonValue.getValue(jsonObject, "type"));
	    ValidateMethod.validate("latitude", ParseJsonValue.getValue(jsonObject, "latitude"));
	    ValidateMethod.validate("longitude", ParseJsonValue.getValue(jsonObject, "longitude"));
	    
        
        RAdContent rAdContent;
    	synchronized (lock) {  
    		final RequestData requestData=ParseJsonValue.getRequestData(jsonObject);
    		ServicesDAO servicesDAO=new ServicesManager();
    		rAdContent=servicesDAO.getRAdContentById(requestData);
    	}
        if (rAdContent == null) {
			
			  errorMessages.add("Ad not found");
		}
		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);	
		
        return Response.status(Response.Status.OK).entity(rAdContent).build();

	

}

//access by application
@Path("/getAction")
@POST
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public Response getAction(JSONObject jsonObject) throws SystemException, BusinessException{
	    List<String> errorMessages = new ArrayList<String>();
	    String pubReference=ParseJsonValue.getValue(jsonObject, "pubReference");
	    ValidateMethod.validate("pubReference", pubReference);
	    validate(pubReference);
	    String bannerId=ParseJsonValue.getValue(jsonObject, "bannerId");	
	    ValidateMethod.validate("bannerId", bannerId);
	    
	    String locationId=ParseJsonValue.getValue(jsonObject, "locationId");	
	    ValidateMethod.validate("locationId", locationId);
	   // ValidateMethod.validate("advReference", ParseJsonValue.getValue(jsonObject, "advReference"));
	    //ValidateMethod.validate("ipAddr", ParseJsonValue.getValue(jsonObject, "ipAddr"));
	    ValidateMethod.validateActionId("actionId", ParseJsonValue.getValue(jsonObject, "actionId"));
//	    ValidateMethod.validate("latitude", ParseJsonValue.getValue(jsonObject, "latitude"));
//	    ValidateMethod.validate("longitude", ParseJsonValue.getValue(jsonObject, "longitude"));
	    Actions actions;
	    synchronized (lock) { 
	    final  RequestData requestData=ParseJsonValue.getRequestDataForAction(jsonObject);
		ServicesDAO servicesDAO=new ServicesManager();
		actions=servicesDAO.getAction(requestData);
	    }
    
    
    if (actions == null) {
		
		  errorMessages.add("Ad not found");
	}
	if(!errorMessages.isEmpty())
		throw new BusinessException(errorMessages);			
    return Response.status(Response.Status.OK).entity(actions).build();

  }

//access by browser
@Path("/getAction/{pr}/{ar}/{bi}/{li}/{ai}")
@GET
@Produces({MediaType.APPLICATION_JSON })
public Response getAction(@PathParam("pr")  String pubReference,@PathParam("ar")  String adReference, @PathParam("bi") String bannerId,@PathParam("li") String locationId,@PathParam("ai") String actionId) throws BusinessException{
	

List<String> errorMessages = new ArrayList<String>();
ValidateMethod.validate("pubReference", pubReference);
validate(pubReference);
ValidateMethod.validate("bannerId", bannerId);
ValidateMethod.validate("locationId", locationId);
ValidateMethod.validateActionId("actionId", actionId);
Actions actions;
synchronized (lock) { 
RequestData requestData=new RequestData(pubReference, adReference, "", bannerId, locationId, "", actionId, "","18.681930","-33.492447");
ServicesDAO servicesDAO=new ServicesManager();
actions=servicesDAO.getAction(requestData);
}
if (actions == null) {
		  errorMessages.add("Action not found");
	}	
if(!errorMessages.isEmpty())
		throw new BusinessException(errorMessages);			
return Response.status(Response.Status.OK).entity(actions).build();
}


private void validate(String pubReference) throws  BusinessException {
	ServicesDAO servicesDAO=new ServicesManager();
	String auInfo=null;
	List<String> errorMessages = new ArrayList<String>();
	try {
		auInfo=servicesDAO.getauthInfo(Integer.parseInt(pubReference));
	} catch (NumberFormatException e) {
		errorMessages.add("pubReference: invalid format");
	}

	
	if (auInfo == null) {
		errorMessages.add("pubReference: not valid");
	}
	

	if(!errorMessages.isEmpty())
		throw new BusinessException(errorMessages);
	
    }
}


