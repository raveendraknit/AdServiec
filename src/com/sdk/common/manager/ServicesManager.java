package com.sdk.common.manager;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;


import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.BigIntegerType;

import com.sdk.common.util.ApplicationConstants;
import com.sdk.common.util.BannerImages;
import com.sdk.common.util.HibernateUtil;
import com.sdk.common.util.Location;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.TblAdvRefTemp;
import com.sdk.service.Tblbanneractions;
import com.sdk.service.Tblbannerimages;
import com.sdk.service.Tblbannerlogs;
import com.sdk.service.Tblbanners;
import com.sdk.service.Tblbannersizes;
import com.sdk.service.Tbllocations;
import com.sdk.service.exception.BusinessException;
import com.sdk.service.exception.ValidateMethod;
import com.sdk.service.services.Actions;
import com.sdk.service.services.GeoLocation;
import com.sdk.service.services.GeoLocationObject;
import com.sdk.service.services.Image;
import com.sdk.service.services.RAd;
import com.sdk.service.services.RAdContent;
import com.sdk.service.services.RequestData;





public class ServicesManager implements ServicesDAO {
	
	
	private static Logger logger=Logger.getLogger("ServicesManager");
	ValidateMethod validateMethod=new ValidateMethod();
	private static final String SERVER_URL=com.sdk.common.util.PropertiesUtil.getProperty("SERVER_URL");
	
	@Override
	public RAd getRAD(String rAdId) throws BusinessException{
		 Session session = HibernateUtil.getSession();
         Transaction tx = null;
     	 List<String> errorMessages = new ArrayList<String>();
         RAd rAd=new RAd();
         try {
         tx=session.beginTransaction();
         Tblbanners tblBanners=null;
         
         Query q=session.createQuery("from Tblbanners b where b.bannerid=:bannerid");
         q.setParameter("bannerid", rAdId);
       
         
         tx.commit();
         }
         catch(HibernateException hbx)
         {
             session.getTransaction().rollback();
             System.err.println(hbx);
 	    }
        
 	   finally
         {
                  session.close();
         }
		return rAd;
	}
	@Override
	public List<RAd> getRAdlist() {
		// TODO Auto-generated method stub
		
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        
        List<RAd> rAdList=new ArrayList<RAd>();
       
        try {
        tx=session.beginTransaction();
       
      
        Query q=session.createQuery("from Tblbanners b");
       
        List<Tblbanners> bannersList=q.list();
        
        for (Iterator<Tblbanners> iterator = bannersList.iterator(); iterator.hasNext();) {
			Tblbanners tblBanners = iterator.next();
			RAd rAd=new RAd();
			rAd.setBannerId(tblBanners.getBannerid().toString());
			rAd.setClicks(tblBanners.getClicks());
	        rAd.setImpressions(tblBanners.getImpressions());
	        rAd.setUrl(tblBanners.getUrl());
	        rAd.setBannerName(tblBanners.getBannername());
	        rAd.setCampaignType(tblBanners.getCampaigntype());
	        rAd.setKeywords(tblBanners.getKeywords());
	        Set<Tblbannerimages> bannerImages = tblBanners.getTblbannerimageses();
	        BannerImages[] bannerImagesArray=new BannerImages[bannerImages.size()];
	        int i=0;
	        for (Iterator<Tblbannerimages> iterator1 = bannerImages.iterator(); iterator1.hasNext();) {
	             BannerImages bannerImage=new BannerImages();
	  		   Tblbannerimages bi = iterator1.next();
	  		   bannerImage.setImageId(bi.getImageid());
	  		   bannerImage.setBannerSizeId(bi.getTblbannersizes().getBannersizeid());
	  		   bannerImage.setWidth(bi.getTblbannersizes().getWidth());
	  		   bannerImage.setHeight(bi.getTblbannersizes().getHeight());
	  		   bannerImage.setFileName(bi.getFilename());
	  		   bannerImage.setContentType(bi.getContenttype());
	  		   bannerImage.setDateCreated(bi.getDatecreated());
	  		   bannerImage.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bi.getImageid().toString()));
	  		   bannerImagesArray[i]=bannerImage;
	  		 
	  		   i++;
	  	     }
	        
	         rAd.setBannerImages(bannerImagesArray);
	        
	         rAdList.add(rAd);
		}
   
        
        tx.commit();
        }
        catch(HibernateException hbx)
        {
            session.getTransaction().rollback();
            System.err.println(hbx);
	    }
       
	   finally
        {
                 session.close();
        }
		
		return rAdList;
	}
	@Override
	public RAdContent getRAdContentById(RequestData requestData) throws BusinessException {
	
		 
		Session session = HibernateUtil.getSession();
		Tblbanners tblBanners;
		Actions actions1=new Actions();
		Tbllocations  tblLocations;
        Transaction tx = null;
        String rAdId = requestData.getAdvReference();
     	List<String> errorMessages = new ArrayList<String>();
        RAdContent rAdContent=new RAdContent();
        try {
        tx=session.beginTransaction();
        double earthRadius = 6371.01;
        Double userLat=Double.parseDouble(requestData.getLatitude());
	    Double userLng=Double.parseDouble(requestData.getLongitude());
 		GeoLocation myLocation = GeoLocation.fromDegrees(userLat, userLng);
 		double distance = 10;
 		
         SortedSet<TblAdvRefTemp> resultList = GeoLocationObject.findPlacesWithinDistance(
 				earthRadius, myLocation, distance, session,requestData.getPubReference());
         if(resultList.isEmpty()){
        	 errorMessages.add("Ad not found"); 
        	 throw new BusinessException(errorMessages);
         }
         
         TblAdvRefTemp tblAdvRefTemp=resultList.first();
         StringBuffer logInfo=new StringBuffer();
         logInfo.append("<<<<--------------------- Process Start for the Ad------------------------------");
         logInfo.append("PubReference: ").append(requestData.getPubReference()).append(" , ");
         logInfo.append("AdReference: ").append(requestData.getAdvReference()).append(" , ");
         logInfo.append("BannerId: ").append(tblAdvRefTemp.getId().getBannerId()).append(" , ");
         logInfo.append("LocationId: ").append(tblAdvRefTemp.getId().getLocationId()).append(" , ");
         logInfo.append("OriLatitude: ").append(requestData.getLatitude()).append(" , ");
         logInfo.append("OriLongitude: ").append(requestData.getLongitude()).append(" , ");
         logInfo.append("Distance: ").append(Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0).append(" km , ");
         
       
         
         //System.out.println(tblAdvRefTemp.getId().getBannerId());
      
         
         tblBanners= (Tblbanners) session.createQuery("from Tblbanners tb where tb.bannerid='"+tblAdvRefTemp.getId().getBannerId()+"'").uniqueResult();
       
         if(tblBanners==null){
        	
        	 errorMessages.add("Ad not found");	
         }
         if(!errorMessages.isEmpty())
 			throw new BusinessException(errorMessages);
         
        
         actions1.setActionId(14);
         
         List<Tblbanneractions> bannerActionsSet = new ArrayList<Tblbanneractions>();
         Query qa=session.createQuery("from Tblbanneractions ta where ta.tblbanners.bannerid='"+tblAdvRefTemp.getId().getBannerId()+"'");
         
        
         bannerActionsSet=qa.list();      
   
        
         Actions[] actionArray=new Actions[bannerActionsSet.size()];
         int j=0;
         for (Iterator<Tblbanneractions> iterator = bannerActionsSet.iterator(); iterator
				.hasNext();) {
        	 
        	Actions actions=new Actions();
			Tblbanneractions tblBannerActions = iterator.next();
			actions.setActionId(tblBannerActions.getTblactions().getActionid());
			//actions.setBannerActionId(tblBannerActions.getBanneractionid());
			actions.setActionName(tblBannerActions.getTblactions().getActionname());
			if(requestData.getType().equals("landinurlcountclick")){
			String parameters=SERVER_URL.concat("/actions?pr="+requestData.getPubReference()+"&ar="+requestData.getAdvReference()+"&bi="+rAdContent.getBannerId()+"&li="+rAdContent.getLocationId()+"&ai="+actions.getActionId()+"&lat="+userLat+"&lon="+userLng);
		    actions.setActionurl(parameters);
			}
			actionArray[j]=actions;
			j++;
		}
         
         rAdContent.setType(requestData.getType());
         rAdContent.setPubReference(requestData.getPubReference());
         rAdContent.setAdReference(requestData.getAdvReference());
         
         
         List<Tblbannerimages> bannerImages = new ArrayList<Tblbannerimages>();
         Query qimg=session.createQuery("from Tblbannerimages ta where ta.tblbanners.bannerid='"+tblAdvRefTemp.getId().getBannerId()+"'");
                
         bannerImages=qimg.list();
         
         Image[] bannerImagesArray=new Image[2];
        
    
         SortedSet<BannerImages> sortSetIntroBanner=new TreeSet<BannerImages>();
         for (Iterator<Tblbannerimages> iterator = bannerImages.iterator(); iterator.hasNext();) {
           BannerImages bannerImage=new BannerImages();
		   Tblbannerimages bi = iterator.next();
		   
		   Tblbannersizes tblbannersizes=(Tblbannersizes) session.createQuery("from Tblbannersizes ta where ta.bannersizeid='"+bi.getTblbannersizes().getBannersizeid()+"'").uniqueResult();
	       
		  
		   Integer width = tblbannersizes.getWidth();
		  // Integer height=bi.getTblBannerSizes().getHeight();
		   if (requestData.getBannerWidth().intValue()>=width.intValue() && tblbannersizes.getGroupname().equalsIgnoreCase("Intro Banner")) {
			   bannerImage.setImageId(bi.getImageid());
			   bannerImage.setBannerSizeId(bi.getTblbannersizes().getBannersizeid());
			   bannerImage.setWidth(tblbannersizes.getWidth());
			   bannerImage.setHeight(tblbannersizes.getHeight());
			   bannerImage.setFileName(bi.getFilename());
			   bannerImage.setContentType(bi.getContenttype());
			   bannerImage.setDateCreated(bi.getDatecreated());
			   bannerImage.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bi.getImageid().toString()));
			   bannerImage.setMixSize(tblbannersizes.getWidth());
			   bannerImage.setGroupName(tblbannersizes.getGroupname());
			   sortSetIntroBanner.add(bannerImage);
			   }
		     }
         
         SortedSet<BannerImages> sortSetLandingBanner=new TreeSet<BannerImages>();
         for (Iterator<Tblbannerimages> iterator = bannerImages.iterator(); iterator.hasNext();) {
           BannerImages bannerImage=new BannerImages();
		   Tblbannerimages bi = iterator.next();
		   Tblbannersizes tblbannersizes=(Tblbannersizes) session.createQuery("from Tblbannersizes ta where ta.bannersizeid='"+bi.getTblbannersizes().getBannersizeid()+"'").uniqueResult();
	       Integer width = tblbannersizes.getWidth();
		//   Integer height=bi.getTblBannerSizes().getHeight();
		   if (requestData.getBannerWidth().intValue()>=width.intValue() && bi.getTblbannersizes().getGroupname().equalsIgnoreCase("Landing Page")) {
			   bannerImage.setImageId(bi.getImageid());
			   bannerImage.setBannerSizeId(tblbannersizes.getBannersizeid());
			   bannerImage.setWidth(tblbannersizes.getWidth());
			   bannerImage.setHeight(tblbannersizes.getHeight());
			   bannerImage.setFileName(bi.getFilename());
			   bannerImage.setContentType(bi.getContenttype());
			   bannerImage.setDateCreated(bi.getDatecreated());
			   bannerImage.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bi.getImageid().toString()));
			   bannerImage.setMixSize(tblbannersizes.getWidth());
			   bannerImage.setGroupName(tblbannersizes.getGroupname());
			   sortSetLandingBanner.add(bannerImage);
			  }
		     }
         
         
         if(!sortSetIntroBanner.isEmpty()){
        	 
        	   BannerImages bannerImage=sortSetIntroBanner.last();
        	 
        	   Image image=new Image();
        	   image.setGroupName(bannerImage.getGroupName());
			   String img="<img src=\""+SERVER_URL.concat("/fileHandleAction?file=").concat(bannerImage.getImageId().toString())+"\" width=\""+bannerImage.getWidth()+"\" height=\""+bannerImage.getHeight()+"\" />";
			   image.setFileName(bannerImage.getFileName());
			   image.setImageId(bannerImage.getImageId().toString());
			   image.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bannerImage.getImageId().toString().toString()));
			   image.setWidth(bannerImage.getWidth());
			   image.setHeight(bannerImage.getHeight());
			   image.setImgInHtml(img);
		       bannerImagesArray[0]=image;
        	 
        	 
         }
         
         if(!sortSetLandingBanner.isEmpty()){
        	 
       	  BannerImages bannerImage=sortSetLandingBanner.last();
       	 
       	   Image image=new Image();
       	       image.setGroupName(bannerImage.getGroupName());
			   String img="<img src=\""+SERVER_URL.concat("/fileHandleAction?file=").concat(bannerImage.getImageId().toString().toString())+"\" width=\""+bannerImage.getWidth()+"\" height=\""+bannerImage.getHeight()+"\" />";
			   image.setFileName(bannerImage.getFileName());
			   image.setImageId(bannerImage.getImageId().toString());
			   image.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bannerImage.getImageId().toString()));
			   image.setWidth(bannerImage.getWidth());
			   image.setHeight(bannerImage.getHeight());
			   image.setImgInHtml(img);
			   
		       bannerImagesArray[1]=image;
       	 
       	 
        }
		

		
        
       
        
        // tblLocations =(Tbllocations) session.load(Tbllocations.class, tblAdvRefTemp.getId().getLocationId());
         
         tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+tblAdvRefTemp.getId().getLocationId()+"'").uniqueResult();
       
         Location location=new Location();
         
         location.setOlatitude(userLat);
         location.setOlongitude(userLng);
         location.setAddressLine1(tblLocations.getAddressline1());
			location.setAddressLine2(tblLocations.getAddressline2());
			location.setCity(tblLocations.getCity());
			location.setCountryCode(tblLocations.getCountrycode());
			location.setHomePage(tblLocations.getHomepage());
			location.setLatitude(tblLocations.getLatitude());
			//location.setLocation(tblLocations.getLocation());
			location.setLocationId(tblLocations.getLocationid().toString());
			location.setLocationMaxRadius(tblLocations.getLocationmaxradius());
			location.setLocationName(tblLocations.getLocationname());
			location.setLongitude(tblLocations.getLongitude());
			location.setMainPhone(tblLocations.getMainphone());
			location.setPostalCode(tblLocations.getPostalcode());
			location.setStoreName(tblLocations.getStorename());
         
        
         if(requestData.getType().equals("banner")){
             rAdContent.setBannerId(tblAdvRefTemp.getId().getBannerId());
             rAdContent.setDistance(Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0);
             rAdContent.setLocationId(tblAdvRefTemp.getId().getLocationId());
        	 rAdContent.setAddress(location);
        	 rAdContent.setImages(bannerImagesArray);
        	 rAdContent.setClickUrl(SERVER_URL.concat("/clickAction?bi=").concat(tblAdvRefTemp.getId().getBannerId()).concat("&li="+tblAdvRefTemp.getId().getLocationId()).concat("&pr="+requestData.getPubReference()).concat("&ar="+requestData.getAdvReference()));
             rAdContent.setTrackUrl(SERVER_URL.concat("/trackAction?bi=").concat(tblAdvRefTemp.getId().getBannerId()).concat("&li="+tblAdvRefTemp.getId().getLocationId()).concat("&pr="+requestData.getPubReference()).concat("&ar="+requestData.getAdvReference()).concat("&t=track.gif"));
             rAdContent.setName(tblLocations.getStorename());
         
            
             rAdContent.setClickType("INAPP");
             rAdContent.setUrlType("link");
             rAdContent.setRefresh(30);
             rAdContent.setScale(false);
             rAdContent.setSkippreflight(true);
             rAdContent.setActions(actionArray);
        	 
         
             }else  if(requestData.getType().equals("stringbanner")){
        	 rAdContent.setBannerId(tblAdvRefTemp.getId().getBannerId());
        	 rAdContent.setDistance(Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0);
             rAdContent.setLocationId(tblAdvRefTemp.getId().getLocationId());
        	 rAdContent.setText("<body style=\"text-align:center;margin:0;padding:0;\"><div align=\"center\"><a id=\"PhoneClickAdLink\" href=\""+SERVER_URL.concat("/clickAction?rAdId=").concat(rAdId).concat("&url=").concat(tblLocations.getHomepage())+"\""
             		+ " target=\"_self\">"+tblLocations.getStorename()+"</a></div></body>");
        	 
        	 rAdContent.setAddress(location);
             rAdContent.setClickUrl(SERVER_URL.concat("/clickAction?bi=").concat(tblAdvRefTemp.getId().getBannerId()).concat("&li="+tblAdvRefTemp.getId().getLocationId()).concat("&pr="+requestData.getPubReference()).concat("&ar="+requestData.getAdvReference()));
             rAdContent.setTrackUrl(SERVER_URL.concat("/trackAction?bi=").concat(tblAdvRefTemp.getId().getBannerId()).concat("&li="+tblAdvRefTemp.getId().getLocationId()).concat("&pr="+requestData.getPubReference()).concat("&ar="+requestData.getAdvReference()).concat("&t=track.gif"));
             rAdContent.setName(tblLocations.getStorename());
             if(requestData.getType().equals("stringbanner"))
             rAdContent.setText(tblLocations.getStorename());
             rAdContent.setClickType("INAPP");
             rAdContent.setUrlType("link");
             rAdContent.setRefresh(30);
             rAdContent.setScale(false);
             rAdContent.setSkippreflight(true);
             rAdContent.setActions(actionArray);
        	 
         
         }else if(requestData.getType().equals("html")){
        	 rAdContent.setDistance(Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0);
        	 StringBuffer landingurl=new StringBuffer();
        	 landingurl.append(SERVER_URL.concat("/landingpage?pr=").concat(requestData.getPubReference()).concat("&ar="+requestData.getAdvReference()).concat("&bi="+tblAdvRefTemp.getId().getBannerId()).concat("&li="+tblAdvRefTemp.getId().getLocationId()).concat("&lat="+userLat).concat("&lon="+userLng).concat("&dis="+Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0).concat("&wd="+bannerImagesArray[1].getWidth()).concat("&ht="+bannerImagesArray[1].getHeight()));
           	 rAdContent.setLandingurl(landingurl.toString());
        	 Image[] bannerImagesArrayh=new Image[1];
        	 bannerImagesArrayh[0]=bannerImagesArray[0];
        	 rAdContent.setImages(bannerImagesArrayh);
        	
        	         
         }else if(requestData.getType().equals("landinurlcountclick")){
        	 
        	 rAdContent.setAddress(location);            
             actions1.setActionId(84);
             StringBuffer buffer=new StringBuffer();
             buffer.append("<style>");
             buffer.append("img{ max-width: "+bannerImagesArray[1].getWidth()+"px;}");
             buffer.append("</style>");
           //  buffer.append("<div id=\"introbanner\" style=\"padding:10px;\"><a href=\"javascript:show('"+tblAdvRefTemp.getId().getBannerId()+"','"+tblAdvRefTemp.getId().getLocationId()+"')\">").append(bannerImagesArray[0].getImgInHtml()).append("</a></div>");
             buffer.append("<div id=\"landingbanner\" style=\"padding:10px;font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:normal;font-size:16px;width:"+bannerImagesArray[1].getWidth().intValue()+"px\">");
             buffer.append("<div style=\"padding-bottom:5px;\">").append(bannerImagesArray[1].getImgInHtml()).append("</div>");
             buffer.append("<div style=\"text-align: center;\">");
             buffer.append("<div>").append(tblLocations.getStorename()).append(",</div>");
             buffer.append("<div>").append(tblLocations.getAddressline1()).append("</div>");
             buffer.append("<div>").append(tblLocations.getAddressline2()).append("</div>");
             buffer.append("<div>").append(tblLocations.getCity()).append("</div>");
             buffer.append("<div>").append(Math.round(tblAdvRefTemp.getId().getDistance()*10.0)/10.0).append("Km from your location</div>");
            
    			 
             for (int i = 0; i < actionArray.length; i++) {
            	 Actions actions = actionArray[i];
            	 String parameters=SERVER_URL.concat("/actions?pr="+requestData.getPubReference()+"&ar="+requestData.getAdvReference()+"&bi="+tblAdvRefTemp.getId().getBannerId()+"&li="+tblAdvRefTemp.getId().getLocationId()+"&ai="+actions.getActionId()+"&lat="+userLat+"&lon="+userLng);
        		 buffer.append("<div style=\"padding:10px;\">");
    			 buffer.append("<a href=\""+parameters+"\"><img src=\""+SERVER_URL+"/images/"+actions.getActionId()+".png\"  /></a>");
    			 buffer.append("</div>");
    			
    		}
            buffer.append("</div>"); 
            // buffer.append("<img src=\""+rAdContent.getTrackUrl()+"\" width=\"1\" height=\"1\" />");
             buffer.append("</div>");
             rAdContent.setHtml(buffer.toString());
             
         }
          
         logInfo.append("DestiLatitude: ").append(location.getLatitude()).append(" , ");
         logInfo.append("DestiLongitude: ").append(location.getLongitude()).append(" , ");
         logInfo.append("StoreName: ").append(location.getStoreName()).append(" , ");
         logInfo.append("Address: ").append(location.getAddressLine1()+", "+location.getAddressLine2()).append(" , ");
         logInfo.append("Phone Number: ").append(location.getMainPhone()).append(" , ");
         logInfo.append(" -------------------------------- Process End  for the Ad----------------------/>>>");
         logger.info(logInfo.toString());
         
        
        tx.commit();
         }
         catch(HibernateException hbx)
         {
            session.getTransaction().rollback();
            errorMessages.add("Unknow error");
            hbx.printStackTrace();
            throw new BusinessException(errorMessages);
 	    }
        
 	   finally
         {
                  session.close();
         }
        
       trackUpdate(requestData.getPubReference(),tblBanners,tblLocations,actions1);
		return rAdContent;
	}
	
	
	
	private  void trackUpdate(String pubReference, Tblbanners tblBanners, Tbllocations tblLocations, Actions actions) throws BusinessException {

					
		    Tblbannerlogs bannerLogs=new Tblbannerlogs();
    	    Session session = HibernateUtil.getSession();
    	    Transaction tx = null;
    	    String uuid="";
    	    boolean isEmpty=false;
    	    
    	    
    	    try {
    	    tx=session.beginTransaction();
    	  
    	    Date date=new Date();
    	    Calendar cal = Calendar.getInstance();
    	    cal.setTime(date);
    	    tblBanners=(Tblbanners) session.createQuery("from Tblbanners lg where lg.bannerid='"+tblBanners.getBannerid()+"'").uniqueResult();
    	    tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+tblLocations.getLocationid()+"'").uniqueResult();
    	     
    	  
    	    
    	    
    	    Query query=session.createSQLQuery("select * from Tblbannerlogs lg where lg.companyid=:companyId and lg.bannerid='"+tblBanners.getBannerid()+"' and lg.locationid='"+tblLocations.getLocationid()+"' and EXTRACT (day from lg.datehour) = :date and EXTRACT (month from lg.datehour)= :month and EXTRACT (year from lg.datehour) = :year and EXTRACT (hour from lg.datehour)=:hours order by lg.datehour desc").addEntity(Tblbannerlogs.class);
    	   
    	    query.setInteger("companyId", Integer.parseInt(pubReference));
    	    query.setInteger("date", cal.get(Calendar.DATE));
    		query.setInteger("month",cal.get(Calendar.MONTH)+1);
    		query.setInteger("year", cal.get(Calendar.YEAR));
    		query.setInteger("hours", cal.get(Calendar.HOUR_OF_DAY));
    		Calendar calfirst = Calendar.getInstance();
    		calfirst.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),00,00);
    		Calendar callast = Calendar.getInstance();
    		//System.out.println(calfirst.getTime());
    		callast.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY),59,59);
    	   
    		Calendar calday = Calendar.getInstance();
    		calday.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 00,00,00);
    	   
    	    if(query.list().isEmpty()){
    	    isEmpty=true;
    	    bannerLogs.setDatehour(calfirst.getTime());
    	    bannerLogs.setCompanyid(Integer.parseInt(pubReference));
    	    uuid=UUID.randomUUID().toString();
    	    }
    	    else{
    	    bannerLogs=(Tblbannerlogs) query.list().get(0);
    	    isEmpty=false;
    	    uuid=bannerLogs.getLogid();
    	    }
    	    
    		StringBuffer logInfo=new StringBuffer();
    	    logInfo.append("<<<<-----------------------------Last tracking details for the Ad----------------- ");
    	    logInfo.append("Pubreference: ").append(pubReference).append(" , ");
    	    logInfo.append("BannerId: ").append(tblBanners.getBannerid()).append(" , ");
    	    logInfo.append("LocationId: ").append(tblLocations.getLocationid()).append(" , ");
    	    logInfo.append("Impressions: ").append(tblBanners.getImpressions()).append(" , ");
    	    logInfo.append("Conversions: ").append(tblBanners.getConvertions()).append(" , ");
    	    logInfo.append("Clicks: ").append(tblBanners.getClicks()).append(" , ");
    	    logInfo.append("Call: ").append(bannerLogs.getCall()).append(" , ");
    	    logInfo.append("Web: ").append(bannerLogs.getWeb()).append(" , ");
    	    logInfo.append("WalkTo: ").append(bannerLogs.getWalkto()).append(" , ");
    	    logInfo.append("DriveTo: ").append(bannerLogs.getDriveto()).append(" , ");
    	    logInfo.append("Map: ").append(bannerLogs.getShowonmap()).append(" , ");
    	    logInfo.append("Coupon: ").append(bannerLogs.getCoupon()).append(" , ");
    	    logInfo.append(" ----------------------complete----------------------------/>>>");
    	    logger.info(logInfo.toString());
    	   
    	   
    	
    		bannerLogs.setImpressions(bannerLogs.getImpressions()+1);
    		Long value=tblBanners.getImpressions();
    		tblBanners.setImpressions(tblBanners.getImpressions()+1);
    		
//    		Criteria criteria = session.createCriteria(TblbannerLogs.class,"lg");
//    		criteria.add(Restrictions.between("lg.dateHour", date, callast.getTime()));
//    		criteria.add(Restrictions.eq("lg.tblBanners.bannerId", tblBanners.getBannerId()));
//    		criteria.setProjection(Projections.sum("lg.impressions"));
//    		criteria.setProjection(Projections.sum("lg.clicks"));
//    		criteria.setProjection(Projections.sum("lg.convertions"));
//    		System.out.println(criteria.list().size()+"  @@@@@@@@@");
    		
    		
    		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
    		 
    		
    		
    		SQLQuery q=session.createSQLQuery("select sum(impressions) as a ,sum(clicks) as b ,sum(convertions) as c from tblbannerlogs   where bannerid='"+tblBanners.getBannerid()+"'  and datehour between '"+dt.format(calday.getTime())+"' and '"+dt.format(callast.getTime())+"' group by bannerid");
    		q.addScalar("a",BigIntegerType.INSTANCE);
			q.addScalar("b",BigIntegerType.INSTANCE);
			q.addScalar("c",BigIntegerType.INSTANCE);
			
    		Object[] row=(Object[]) q.uniqueResult();
    		
    		//System.out.println(row.length+"  "+row[0]+"  "+row[1]+"  "+row[2]);
    	

    		Long impressions=new Long(0);
    		Long clicks=new Long(0);
    		Long convertions=new Long(0);
    		if(row!=null){
    		if(row[0]!=null)
    		impressions=new Long(((BigInteger) row[0]).longValue());
    		if(row[1]!=null)
    		clicks=new Long(((BigInteger) row[1]).longValue());
    		if(row[2]!=null)
    		convertions=new Long(((BigInteger) row[2]).longValue());
    		}
    		
    	    //System.out.println(impressions+" "+clicks+" "+convertions );
    		if(tblBanners.getCampaigntype().equals("CPM")){
    			tblBanners.setCurdailybudget(tblBanners.getCpm()*impressions/1000);
    			tblBanners.setBalance(tblBanners.getBudget()-(tblBanners.getCpm() / 1000) * (value));
    			
    		}else if(tblBanners.getCampaigntype().equals("CPC")){
    			tblBanners.setCurdailybudget(tblBanners.getCpc()*clicks);
    			tblBanners.setBalance(tblBanners.getBudget() - (tblBanners.getCpc() * (tblBanners.getClicks())));
    			
    		}else if(tblBanners.getCampaigntype().equals("CPA")){
    			tblBanners.setCurdailybudget(tblBanners.getCpa()*convertions);
    			tblBanners.setBalance(tblBanners.getBudget() - (tblBanners.getCpa() * (tblBanners.getConvertions())));
    			
    		}else if(tblBanners.getCampaigntype().equals("CPG")){
    			tblBanners.setCurdailybudget(tblBanners.getCpc()*clicks+tblBanners.getCpa()*convertions);
    			tblBanners.setBalance(tblBanners.getBudget() - (tblBanners.getCpc() * (tblBanners.getClicks())) + (tblBanners.getCpa() * (tblBanners.getConvertions())));
    			
    		}

    	 
    		if(tblBanners.getBalance()<=0){
    			tblBanners.setBalance(new Double(0));
    			tblBanners.setStatusid(3);
    			
    		}else{
    			
    			
    			if(actions.getActionId()==Integer.parseInt(ApplicationConstants.CALL)){
    				bannerLogs.setCall(bannerLogs.getCall()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+value);
    				}
    				else if(actions.getActionId()==Integer.parseInt(ApplicationConstants.WEB)){
    				bannerLogs.setWeb(bannerLogs.getWeb()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+value);
    				
    				}
    				else if(actions.getActionId()==Integer.parseInt(ApplicationConstants.COUPON)){
    				bannerLogs.setCoupon(bannerLogs.getCoupon()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+1);
    				}
    				else if(actions.getActionId()==Integer.parseInt(ApplicationConstants.DRIVE_TO)){
    				bannerLogs.setDriveto(bannerLogs.getDriveto()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+1);
    				}
    				else if(actions.getActionId()==Integer.parseInt(ApplicationConstants.SHOW_ON_MAP)){
    				bannerLogs.setShowonmap(bannerLogs.getShowonmap()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+1);
    				
    				}
    				else if(actions.getActionId()==Integer.parseInt(ApplicationConstants.WALK_TO)){
    				bannerLogs.setWalkto(bannerLogs.getWalkto()+1);
    				bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    				tblBanners.setConvertions(tblBanners.getConvertions()+1);
    				
    				}
    				else if(actions.getActionId()==84){
    					bannerLogs.setClicks(bannerLogs.getClicks()+1);
    					bannerLogs.setConvertions(bannerLogs.getConvertions()+1);
    					tblBanners.setClicks(tblBanners.getClicks()+1);
    				}else if(actions.getActionId()==85){
    					bannerLogs.setImpressions(bannerLogs.getImpressions()+1);
    					tblBanners.setImpressions(tblBanners.getImpressions()+1);
    				}else{
    					
    			}
    			
    		}
    		StringBuffer logInfoc=new StringBuffer();
    	    logInfoc.append("<<<<-----------------------------Current tracking details for the Ad-----------------");
    	    logInfoc.append("Pubreference: ").append(pubReference).append(" , ");
    	    logInfoc.append("BannerId: ").append(tblBanners.getBannerid()).append(" , ");
    	    logInfoc.append("LocationId: ").append(tblLocations.getLocationid()).append(" , ");
    	    logInfoc.append("Impressions: ").append(tblBanners.getImpressions()).append(" , ");
    	    logInfoc.append("Conversions: ").append(tblBanners.getConvertions()).append(" , ");
    	    logInfoc.append("Clicks: ").append(tblBanners.getClicks()).append(" , ");
    	    logInfoc.append("Call: ").append(bannerLogs.getCall()).append(" , ");
    	    logInfoc.append("Web: ").append(bannerLogs.getWeb()).append(" , ");
    	    logInfoc.append("WalkTo: ").append(bannerLogs.getWalkto()).append(" , ");
    	    logInfoc.append("DriveTo: ").append(bannerLogs.getDriveto()).append(" , ");
    	    logInfoc.append("Map: ").append(bannerLogs.getShowonmap()).append(" , ");
    	    logInfoc.append("Coupon: ").append(bannerLogs.getCoupon()).append(" , ");
    	    logInfoc.append("-------------------------complete-------------------------------------- />>>");
    		logger.info(logInfoc.toString());
    		session.close();
           
    		session = HibernateUtil.getSession();
    		tx=session.beginTransaction();
    	
    		//String aa="insert into tblbannerlogs (call, clicks, companyid, convertions, coupon, datehour, driveto, impressions, showonmap, bannerid, locationid, walkto, web, logid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		  String tblbannersUpdate="update tblbanners  set  balance="+tblBanners.getBalance()+",budget="+tblBanners.getBudget()+" ,clicks="+tblBanners.getClicks()+", convertions="+tblBanners.getConvertions()+", curdailybudget="+tblBanners.getCurdailybudget()+", dailybudgetdate='"+new Date()+"', impressions="+tblBanners.getImpressions()+", statusid="+tblBanners.getStatusid()+" where bannerid='"+tblBanners.getBannerid()+"'";
        	  Query tblbannersUpdateQuery=session.createSQLQuery(tblbannersUpdate).addEntity(Tblbanners.class);
        	  tblbannersUpdateQuery.executeUpdate();
             
    		
    	   if(isEmpty==true){
    	    	String tblbannerlogsInsert="insert into tblbannerlogs (logid, call, clicks, companyid, convertions, coupon, datehour, driveto, impressions, showonmap, bannerid, locationid, walkto, web) values ('"+uuid+"',"+bannerLogs.getCall()+","+bannerLogs.getClicks()+" , "+bannerLogs.getCompanyid()+", "+bannerLogs.getConvertions()+", "+bannerLogs.getCoupon()+", '"+bannerLogs.getDatehour()+"', "+bannerLogs.getDriveto()+", "+bannerLogs.getImpressions()+", "+bannerLogs.getShowonmap()+",'"+tblBanners.getBannerid()+"','"+tblLocations.getLocationid()+"', "+bannerLogs.getWalkto()+", "+bannerLogs.getWeb()+")";
        		Query tblbannerlogsInsertQuery=session.createSQLQuery(tblbannerlogsInsert).addEntity(Tblbannerlogs.class);
        		tblbannerlogsInsertQuery.executeUpdate();
        	  
    	   }else{
    		   
    		String tblbannerlogsUpdate="update tblbannerlogs  set call="+bannerLogs.getCall()+", clicks="+bannerLogs.getClicks()+", convertions="+bannerLogs.getConvertions()+", coupon="+bannerLogs.getCoupon()+", driveto="+bannerLogs.getDriveto()+", impressions="+bannerLogs.getImpressions()+", showonmap="+bannerLogs.getShowonmap()+", bannerid='"+tblBanners.getBannerid()+"', locationid='"+tblLocations.getLocationid()+"', walkto="+bannerLogs.getWalkto()+", web="+bannerLogs.getWeb()+" where logid='"+uuid+"'";
       		Query tblbannerlogsUpdateQuery=session.createSQLQuery(tblbannerlogsUpdate).addEntity(Tblbannerlogs.class);
       		tblbannerlogsUpdateQuery.executeUpdate();  
       	    
       	  
    		   
    	   }
    	   
    	    
    	  tx.commit();
    		
    	    //session.update(tblBanners);
    		//session.saveOrUpdate(bannerLogs);
    		
    		
    		
    		
    
    	



   }
   catch(HibernateException hbx)
   {
      session.getTransaction().rollback();
      hbx.printStackTrace();
     
   }
  
  finally
   {
            session.close();
           
   }
 

}
	@Override
	public Tblbannerimages getBannerImages(String file) throws BusinessException {
		 Session session = HibernateUtil.getSession();
         Transaction tx = null;
         List<String> errorMessages = new ArrayList<String>();
         Tblbannerimages bannerImages=null;
        
         try {
         tx=session.beginTransaction();
         
         
         Query q=session.createQuery("from Tblbannerimages bi where bi.imageid='"+file+"'");
        
         bannerImages= (Tblbannerimages) q.uniqueResult();
        
         if(bannerImages==null){
         	 errorMessages.add("image not found");
        }
     		

        
         tx.commit();
         }catch(HibernateException he){
        	 
        	
             if(bannerImages==null){
             	 errorMessages.add("invalid image id");
          }
         		
        if(!errorMessages.isEmpty())
         			throw new BusinessException(errorMessages);	
         }
        
         finally
         {
                  session.close();
         }
		 return bannerImages;
        
        }
	@Override
	public String getauthInfo(Integer pubReference) throws BusinessException {
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
   
        
        String validUser=null;
        try {
        tx=session.beginTransaction();
     
        Query q=session.createQuery("Select co.companyname from Tblsyscompanies co where co.companyid=:companyid");
        q.setParameter("companyid", pubReference);
      
        

        if(!q.list().isEmpty()){
        	validUser="valid";
        }
        
       
          
         		

      
       
        
        tx.commit();
        }
        catch(HibernateException hbx)
        {
            session.getTransaction().rollback();
            System.err.println(hbx);
	    }
       
	   finally
        {
                 session.close();
        }
		return validUser;
	}
	@Override
	public Actions getAction(RequestData requestData) throws BusinessException {
		List<String> errorMessages = new ArrayList<String>();
		Tblbanners tblBanners = null;
		Tbllocations  tblLocations;
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
        String bannerId = requestData.getBannerId();
        Actions actions=new Actions();
        try {
        tx=session.beginTransaction();
       
        
        
        try{
        Query q=session.createSQLQuery("Select *from Tblbanners b where b.bannerid='"+bannerId+"' and b.enabled=B'1'").addEntity(Tblbanners.class);
        //q.setBoolean("enabled", true);
        tblBanners= (Tblbanners) q.uniqueResult();
        }catch(HibernateException he){
       	 
        	 errorMessages.add("adReference: invalid or not found");
       		
			
        }
       
        if(tblBanners==null){
        	 errorMessages.add("action not found");
        }
    		

         if(!errorMessages.isEmpty())
    			throw new BusinessException(errorMessages);	 
            //tblLocations =(Tbllocations) session.load(Tbllocations.class, requestData.getLocationId());
            tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+requestData.getLocationId()+"'").uniqueResult();
            Location location=new Location();
            Double userLat=Double.parseDouble(requestData.getLatitude());
    	    Double userLng=Double.parseDouble(requestData.getLongitude());
            location.setOlatitude(userLat);
            location.setOlongitude(userLng);
            location.setAddressLine1(tblLocations.getAddressline1());
			location.setAddressLine2(tblLocations.getAddressline2());
			location.setCity(tblLocations.getCity());
			location.setCountryCode(tblLocations.getCountrycode());
			location.setHomePage(tblLocations.getHomepage());
			location.setLatitude(tblLocations.getLatitude());
			//location.setLocation(tblLocations.getLocation());
			location.setLocationId(tblLocations.getLocationid().toString());
			location.setLocationMaxRadius(tblLocations.getLocationmaxradius());
			location.setLocationName(tblLocations.getLocationname());
			location.setLongitude(tblLocations.getLongitude());
			location.setMainPhone(tblLocations.getMainphone());
			location.setPostalCode(tblLocations.getPostalcode());
			location.setStoreName(tblLocations.getStorecode());
			
	  	    actions.setActionId(Integer.parseInt(requestData.getActionId()));
          
            if(requestData.getActionId().equals(ApplicationConstants.WEB)){
			actions.setActionId(Integer.parseInt(requestData.getActionId()));
		    actions.setActionName("Web");
		    actions.setActionValue(location.getHomePage());
            }else if(requestData.getActionId().equals(ApplicationConstants.CALL)){
    			actions.setActionId(Integer.parseInt(requestData.getActionId()));
    		    actions.setActionName("Call");
    		    actions.setActionValue(location.getMainPhone());
            }else if(requestData.getActionId().equals(ApplicationConstants.WALK_TO)){
    			actions.setActionId(Integer.parseInt(requestData.getActionId()));
    		    actions.setActionName("Walk to");
    		    Location[] locationsarray=new Location[1];
    		   
    		    locationsarray[0]=location;
    		    actions.setLocation(locationsarray);
    		    
                }else if(requestData.getActionId().equals(ApplicationConstants.DRIVE_TO)){
    			actions.setActionId(Integer.parseInt(requestData.getActionId()));
    		    actions.setActionName("Drive to");
    		    Location[] locationsarray=new Location[1];
    		  
    		    locationsarray[0]=location;
    		    actions.setLocation(locationsarray);
     		    }else if(requestData.getActionId().equals(ApplicationConstants.SHOW_ON_MAP)){
        			actions.setActionId(Integer.parseInt(requestData.getActionId()));
        		    actions.setActionName("Show on Map");
        		    Location[] locationsarray=new Location[1];
        		   
        		    locationsarray[0]=location;
        		    actions.setLocation(locationsarray);
         	
                }else if(requestData.getActionId().equals(ApplicationConstants.COUPON)){
    			actions.setActionId(Integer.parseInt(requestData.getActionId()));
    		    actions.setActionName("Coupon");
    		    actions.setActionValue(location.getHomePage());
            }
           
        	
        tx.commit();
        }
        catch(HibernateException hbx)
        {
           session.getTransaction().rollback();
           errorMessages.add("Unkown Error");
           throw new BusinessException(errorMessages);
          
	    }
       
	   finally
        {
                 session.close();
        }
        
        
       trackUpdate(requestData.getPubReference(),tblBanners, tblLocations, actions);	
		
        return actions;
	
	}
	@Override
	public void updateClick(String pubReference,String bannerId,String locationId) throws BusinessException {
		Tblbanners tblBanners=null;
		Tbllocations  tblLocations;
		List<String> errorMessages = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
        Transaction tx = null;
       
        Actions actions=new Actions();
        actions.setActionId(84);
     try{
    	   
       tx=session.beginTransaction();
       Query q=session.createQuery("from Tblbanners b where b.bannerid='"+bannerId+"' and b.enabled=:enabled ");
   
       q.setBoolean("enabled", true);
       tblBanners= (Tblbanners) q.uniqueResult();
       if(tblBanners==null){
       	 errorMessages.add("action not found");
       }
   		

        if(!errorMessages.isEmpty())
   			throw new BusinessException(errorMessages);	 
      //  tblLocations =(Tbllocations) session.load(Tbllocations.class, locationId);
        tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+locationId+"'").uniqueResult();
      
		tx.commit();
       }
       catch(HibernateException hbx)
       {
          session.getTransaction().rollback();
          errorMessages.add("Unkown Error");
          throw new BusinessException(errorMessages);
         
	    }
      
	     finally
         {
                session.close();
         }
      trackUpdate(pubReference,tblBanners, tblLocations, actions);	
	}
	@Override
	public void updateImpression(String pubReference,String bannerId, String locationId)
			throws BusinessException {
		
		Tblbanners tblBanners=null;
		Tbllocations  tblLocations;
		List<String> errorMessages = new ArrayList<String>();
		Session session = HibernateUtil.getSession();
       Transaction tx = null;
       
       Actions actions=new Actions();
       actions.setActionId(85);
       try {
       tx=session.beginTransaction();
 
       Query q=session.createQuery("from Tblbanners b where b.bannerid='"+bannerId+"' and b.enabled=:enabled");
       q.setBoolean("enabled", true);
       tblBanners= (Tblbanners) q.uniqueResult();
      
      
       if(tblBanners==null){
       	 errorMessages.add("action not found");
       }
   		

        if(!errorMessages.isEmpty())
   			throw new BusinessException(errorMessages);	 
         //  tblLocations =(Tbllocations) session.load(Tbllocations.class, locationId);
        tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+locationId+"'").uniqueResult();
			
         
          
       	
        tx.commit();
	}
       catch(HibernateException hbx)
       {
          session.getTransaction().rollback();
          errorMessages.add("Unkown Error");
          throw new BusinessException(errorMessages);
         
	    }
      
	   finally
       {
                session.close();
       }
      trackUpdate(pubReference,tblBanners, tblLocations, actions);	
	}
	@Override
	public String getLandingPageAd(String pubReference, String advReference,
			String bannerId, String locationId, String width, String height,String latitude, String longitude, String distance) throws BusinessException {
		Tblbanners tblBanners;
		Actions actions=new Actions();
		Tbllocations  tblLocations;
       String html="";
       List<String> errorMessages = new ArrayList<String>();
       Session session = HibernateUtil.getSession();
       Transaction tx = null;
       try{     		
    	       
         tx=session.beginTransaction();
         Query q=session.createQuery("from Tblbanners b where b.bannerid='"+bannerId+"'");
        
    
         tblBanners= (Tblbanners) q.uniqueResult();
       
         if(tblBanners==null){
        	 errorMessages.add("Ad not found");	
         }
         if(!errorMessages.isEmpty())
 			throw new BusinessException(errorMessages);
         
         
         
         
         List<Tblbannerimages> bannerImages = new ArrayList<Tblbannerimages>();
         Query qimg=session.createQuery("from Tblbannerimages ta where ta.tblbanners.bannerid='"+bannerId+"'");
                
         bannerImages=qimg.list();
       
         SortedSet<BannerImages> sortSetLandingBanner=new TreeSet<BannerImages>();
         for (Iterator<Tblbannerimages> iterator = bannerImages.iterator(); iterator.hasNext();) {
           BannerImages bannerImage=new BannerImages();
		   Tblbannerimages bi = iterator.next();
		   
		   Tblbannersizes tblbannersizes=(Tblbannersizes) session.createQuery("from Tblbannersizes ta where ta.bannersizeid='"+bi.getTblbannersizes().getBannersizeid()+"'").uniqueResult();
	  
		   
		   Integer widthO = tblbannersizes.getWidth();
		   Integer heightO=tblbannersizes.getHeight();
		   if (Integer.parseInt(width)>=widthO.intValue() && Integer.parseInt(height)>=heightO.intValue() && bi.getTblbannersizes().getGroupname().equalsIgnoreCase("Landing Page")) {
			   bannerImage.setImageId(bi.getImageid());
			   bannerImage.setBannerSizeId(bi.getTblbannersizes().getBannersizeid());
			   bannerImage.setWidth(bi.getTblbannersizes().getWidth());
			   bannerImage.setHeight(bi.getTblbannersizes().getHeight());
			   bannerImage.setFileName(bi.getFilename());
			   bannerImage.setContentType(bi.getContenttype());
			   bannerImage.setDateCreated(bi.getDatecreated());
			   bannerImage.setImgSrc(SERVER_URL.concat("/fileHandleAction?file=").concat(bi.getImageid().toString()));
			   bannerImage.setMixSize(widthO);
			   bannerImage.setGroupName(bi.getTblbannersizes().getGroupname());
			   sortSetLandingBanner.add(bannerImage);
			  }
		     }
         
         

         BannerImages bannerImage=sortSetLandingBanner.last();
         String img="<img src=\""+SERVER_URL.concat("/fileHandleAction?file=").concat(bannerImage.getImageId().toString())+"\" width=\""+bannerImage.getWidth()+"\" height=\""+bannerImage.getHeight()+"\" />";
	   
         actions.setActionId(84);
        // tblLocations =(Tbllocations) session.load(Tbllocations.class, locationId);
         tblLocations= (Tbllocations) session.createQuery("from Tbllocations tb where tb.locationid='"+locationId+"'").uniqueResult();
         StringBuffer buffer=new StringBuffer();
         buffer.append("<style>");
         buffer.append("img{ max-width: "+width+"px;}");
         buffer.append("</style>");
       //  buffer.append("<div id=\"introbanner\" style=\"padding:10px;\"><a href=\"javascript:show('"+tblAdvRefTemp.getId().getBannerId()+"','"+tblAdvRefTemp.getId().getLocationId()+"')\">").append(bannerImagesArray[0].getImgInHtml()).append("</a></div>");
         buffer.append("<div id=\"landingbanner\" style=\"padding:10px;font-family:Verdana, Arial, Helvetica, sans-serif;font-weight:normal;font-size:16px;width:"+width+"px\">");
         buffer.append("<div style=\"padding-bottom:5px;\">").append(img).append("</div>");
         buffer.append("<div style=\"text-align: center;\">");
         buffer.append("<div>").append(tblLocations.getStorename()).append(",</div>");
         buffer.append("<div>").append(tblLocations.getAddressline1()).append("</div>");
         buffer.append("<div>").append(tblLocations.getAddressline2()).append("</div>");
         buffer.append("<div>").append(tblLocations.getCity()).append("</div>");
         buffer.append("<div>").append(distance).append("Km from your location</div>");
        
         
         List<Tblbanneractions> bannerActionsSet = new ArrayList<Tblbanneractions>();
         Query qa=session.createQuery("from Tblbanneractions ta where ta.tblbanners.bannerid='"+bannerId+"'");
                
         bannerActionsSet=qa.list();
         for (Iterator<Tblbanneractions> iterator = bannerActionsSet.iterator(); iterator
 				.hasNext();) {
        	 Tblbanneractions tblBannerActions = iterator.next();
 			 String parameters=SERVER_URL.concat("/actions?pr="+pubReference+"&ar="+advReference+"&bi="+bannerId+"&li="+locationId+"&ai="+tblBannerActions.getTblactions().getActionid()+"&lat="+latitude+"&lon="+longitude);
    		 buffer.append("<div style=\"padding:10px;\">");
			 buffer.append("<a href=\""+parameters+"\"><img src=\""+SERVER_URL+"/images/"+tblBannerActions.getTblactions().getActionid()+".png\"  /></a>");
			 buffer.append("</div>");
			
		}
        buffer.append("</div>"); 
        // buffer.append("<img src=\""+rAdContent.getTrackUrl()+"\" width=\"1\" height=\"1\" />");
        buffer.append("</div>");
    
        html=buffer.toString();
      	
        tx.commit();
       
         
       }
       catch(HibernateException hbx)
       {
       session.getTransaction().rollback();
       errorMessages.add("Unkown Error");
       throw new BusinessException(errorMessages);
      
	 }
       finally
    {
             session.close();
    }
       
    trackUpdate(pubReference,tblBanners, tblLocations, actions);	
	return html;
         
	}      
	  
		
	
	
	
}