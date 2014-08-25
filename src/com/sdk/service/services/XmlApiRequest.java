package com.sdk.service.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;



@Path("/xml-api")
public class XmlApiRequest {
private final static Object lock = new Object();


@GET

@Produces({ MediaType.APPLICATION_XML})
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
@Produces({MediaType.APPLICATION_XML })
public Response getRAd(@PathParam("pr")  String pubReference, @PathParam("ar") String advReference,@PathParam("ty") String type,@PathParam("lat") String latitude,@PathParam("lon") String longitude) throws BusinessException{
	


	List<String> errorMessages = new ArrayList<String>();
  
    ValidateMethod.validate("pubReference", pubReference);
    validate(pubReference);
    ValidateMethod.validate("advReference", advReference);
    //ValidateMethod.validate("ipAddr", ipAddr);
    ValidateMethod.validate("type",type );
    ValidateMethod.validate("latitude", latitude);
    ValidateMethod.validate("longitude",longitude );
  //  String ipAddr = null;
	Integer bannerWidth=300;
	Integer bannerHeight=300;
  
    RAdContent rAdContent;
    synchronized (lock) {  
      final  RequestData requestData=new RequestData(pubReference, advReference,type,bannerWidth, bannerHeight,longitude,latitude);
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

@Produces({MediaType.APPLICATION_XML})
public Response getRAd(String xmlRquest) throws  BusinessException{
	List<String> errorMessages = new ArrayList<String>();
	DocumentBuilderFactory builderFactory =
	        DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = null;
	try {
	    builder = builderFactory.newDocumentBuilder();
	} catch (ParserConfigurationException e) {
	    errorMessages.add("invalid xml");
	}
	
	Document document = null;
	try {
		document = builder.parse(new ByteArrayInputStream(xmlRquest.getBytes()));
	}catch (IOException ex) {
		 errorMessages.add("invalid xml");

	}catch ( SAXException exd) {
		 errorMessages.add("invalid xml");
	}
	

	String pubReference=ParseXmlValue.getValue(document, "pubReference");	
    ValidateMethod.validate("pubReference", pubReference);
    validate(pubReference);
	//ValidateMethod.validate("advReference", ParseXmlValue.getValue(document, "advReference"));
	//ValidateMethod.validate("ipAddr", ParseXmlValue.getValue(document, "ipAddr"));
	ValidateMethod.validate("type", ParseXmlValue.getValue(document, "type"));
	ValidateMethod.validate("latitude", ParseXmlValue.getValue(document, "latitude"));
	ValidateMethod.validate("longitude", ParseXmlValue.getValue(document, "longitude"));
	RAdContent rAdContent;
	synchronized (lock) {  
	final RequestData requestData=ParseXmlValue.getRequestData(document);
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


@Path("/getAction")
@POST
@Produces({MediaType.APPLICATION_XML})
public Response getAction(String xmlRquest) throws SystemException, BusinessException{
	List<String> errorMessages = new ArrayList<String>();
	DocumentBuilderFactory builderFactory =
	        DocumentBuilderFactory.newInstance();
	DocumentBuilder builder = null;
	try {
	    builder = builderFactory.newDocumentBuilder();
	} catch (ParserConfigurationException e) {
	    errorMessages.add("invalid xml");
	}
	
	Document document = null;
	try {
		document = builder.parse(new ByteArrayInputStream(xmlRquest.getBytes()));
	} catch (SAXException ex) {
		// TODO Auto-generated catch block
		 errorMessages.add("invalid xml");
	}catch (IOException e) {
		 errorMessages.add("invalid xml");
	}
	
	
	String pubReference=ParseXmlValue.getValue(document, "pubReference");	
    ValidateMethod.validate("pubReference", pubReference);
    validate(pubReference);
    
    String bannerId=ParseXmlValue.getValue(document, "bannerId");	
    ValidateMethod.validate("bannerId", bannerId);
    
    String locationId=ParseXmlValue.getValue(document, "locationId");	
    ValidateMethod.validate("locationId", locationId);
    
	//ValidateMethod.validate("advReference", ParseXmlValue.getValue(document, "advReference"));
	//ValidateMethod.validate("ipAddr", ParseXmlValue.getValue(document, "ipAddr"));
	ValidateMethod.validateActionId("actionId", ParseXmlValue.getValue(document, "actionId"));
	//ValidateMethod.validate("latitude", ParseXmlValue.getValue(document, "latitude"));
	//ValidateMethod.validate("longitude", ParseXmlValue.getValue(document, "longitude"));
	Actions actions;
	synchronized (lock) {
		
	
	final RequestData requestData=ParseXmlValue.getRequestDataForAction(document);
    ServicesDAO servicesDAO=new ServicesManager();
    actions =servicesDAO.getAction(requestData);
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
@Produces({MediaType.APPLICATION_XML })
public Response getAction(@PathParam("pr")  String pubReference,@PathParam("ar")  String adReference, @PathParam("bi") String bannerId,@PathParam("li") String locationId,@PathParam("ai") String actionId) throws BusinessException{
	

  List<String> errorMessages = new ArrayList<String>();
  ValidateMethod.validate("pubReference", pubReference);
  validate(pubReference);
  ValidateMethod.validate("bannerId", bannerId);
  ValidateMethod.validate("locationId", locationId);
  ValidateMethod.validateActionId("actionId", actionId);
  Actions actions;
  synchronized (lock) {
  final RequestData requestData=new RequestData(pubReference, adReference, "", bannerId, locationId, "", actionId, "","18.681930","-33.492447");
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
