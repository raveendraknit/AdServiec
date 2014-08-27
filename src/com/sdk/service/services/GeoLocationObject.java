package com.sdk.service.services;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import antlr.StringUtils;

import com.sdk.common.util.HibernateUtil;
import com.sdk.service.TblAdvRefTemp;
import com.sdk.service.TblAdvRefTempId;
import com.sdk.service.Tblbannerlocations;
import com.sdk.service.Tblbanners;
import com.sdk.service.Tbllocations;
import com.sdk.service.exception.BusinessException;

public class GeoLocationObject {

	private static final double AVERAGE_RADIUS_OF_EARTH = 6371.01;
	public static SortedSet<TblAdvRefTemp> findPlacesWithinDistance(
			double radius, GeoLocation location, double distance,
			Session session, String pubReference) throws BusinessException {

		SortedSet<TblAdvRefTemp> sortSetTblAdvRefTemp=new TreeSet<TblAdvRefTemp>();
	    try{
        Date currentDate=new Date();
       	
Calendar c = Calendar.getInstance();
c.setTime(currentDate);
int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
Query  banneridByPublisher=session.createSQLQuery("Select tdd.bannerid  FROM Tblcompanybanner tdd where tdd.companyid="+pubReference+" ");

List<String> myList=banneridByPublisher.list();

String inClauseArg =getMySqlStatements(myList);;


SQLQuery  q=session.createSQLQuery("Select * FROM Tblbanners tb INNER JOIN Tblbannerlocations tbl ON tbl.bannerId = tb.bannerId INNER JOIN Tbllocations tl ON tl.locationId = tbl.locationID"
+" Left JOIN Tblbannerdayofweeks tbDW ON tbDW.bannerid = tb.bannerid"
+" Left JOIN Tblbannertimes tbT ON tbT.bannerId = tb.bannerId  where tbl.geoMaxRadius/1000 >=(((acos(sin((:latitude*pi()/180)) * sin((tl.latitude*pi()/180))+cos((:latitude*pi()/180)) * cos((tl.latitude*pi()/180)) * cos(((:longitude - tl.longitude)*pi()/180))))*180/pi())*60*1.1515*1.6093)"
+" and tb.balance <= tb.budget  and (tb.curdailybudget <= tb.dailybudget OR tb.dailybudgetdate <> :currentDate) and ((tb.campaignType = 'CPM' and tb.impressions <= tb.cpmlimit) OR (tb.campaignType = 'CPC' and tb.clicks <= tb.cpclimit) OR (tb.campaignType = 'CPA' and tb.convertions <= tb.cpalimit) OR (tb.campaignType = 'CPG' and (tb.clicks <= tb.cpclimit OR tb.convertions <= tb.cpalimit)))"
+" AND tb.enabled = B'1' AND tb.statusID = 1 AND :currentDate between tb.startDate and tb.endDate"
+" AND tl.approved = B'1' AND (tbDW.bannerId is null OR tbDW.dayOfWeek = :dayOfWeek)"
+" AND (tbT.bannerId is null OR :currentDate between tbT.startTime and tbT.endTime) and tb.archived = B'0' and tb.bannerid IN ("+inClauseArg+") ORDER BY tb.priority DESC, (tb.impressions + 1) * (100 - tb.priority) ASC, tb.impressions ASC").addEntity(Tblbanners.class).addEntity(Tbllocations.class).addEntity(Tblbannerlocations.class);
        
        q.setParameter("latitude", location.getLatitudeInDegrees());
        q.setParameter("longitude", location.getLongitudeInDegrees());
      	q.setDate("currentDate", currentDate);
        q.setInteger("dayOfWeek", dayOfWeek);
      	if(q.list().isEmpty()){
       		
       	 q=session.createSQLQuery("Select * FROM Tblbanners tb INNER JOIN Tblbannerlocations tbl ON tbl.bannerId = tb.bannerId INNER JOIN Tbllocations tl ON tl.locationId = tbl.locationID"
       			 +" Left JOIN Tblbannerdayofweeks tbDW ON tbDW.bannerid = tb.bannerid"
       			 +" Left JOIN Tblbannertimes tbT ON tbT.bannerId = tb.bannerId where 10000 >=(((acos(sin((:latitude*pi()/180)) * sin((tl.latitude*pi()/180))+cos((:latitude*pi()/180)) * cos((tl.latitude*pi()/180)) * cos(((:longitude - tl.longitude)*pi()/180))))*180/pi())*60*1.1515*1.6093)"
       			 +" and tb.balance <= tb.budget and (tb.curdailybudget <= tb.dailybudget OR tb.dailybudgetdate <> :currentDate) and ((tb.campaignType = 'CPM' and tb.impressions <= tb.cpmlimit) OR (tb.campaignType = 'CPC' and tb.clicks <= tb.cpclimit) OR (tb.campaignType = 'CPA' and tb.convertions <= tb.cpalimit) OR (tb.campaignType = 'CPG' and (tb.clicks <= tb.cpclimit OR tb.convertions <= tb.cpalimit)))"
       			 +" AND tb.enabled = B'1' AND tb.statusID = 1 AND :currentDate between tb.startDate and tb.endDate"
       			 +" AND tl.approved = B'1' AND (tbDW.bannerId is null OR tbDW.dayOfWeek = :dayOfWeek)"
       			 +" AND (tbT.bannerId is null OR :currentDate between tbT.startTime and tbT.endTime) and tb.archived = B'0' and tb.bannerid IN ("+inClauseArg+")  ORDER BY tb.priority DESC, (tb.impressions + 1) * (100 - tb.priority) ASC, tb.impressions ASC").addEntity(Tblbanners.class).addEntity(Tbllocations.class).addEntity(Tblbannerlocations.class);
       			         
           q.setParameter("latitude", location.getLatitudeInDegrees());
           q.setParameter("longitude", location.getLongitudeInDegrees());
           q.setDate("currentDate", currentDate);
           q.setInteger("dayOfWeek", dayOfWeek);
           }
     	  	
            List resultList = new ArrayList();
    		resultList=q.list();
    	
    		for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				TblAdvRefTemp  tblAdvRefTemp=new TblAdvRefTemp();
			    Tblbannerlocations tblBannerLocations=(Tblbannerlocations) object[2];
			    Double distance1 = getDistance(location,tblBannerLocations.getTbllocations().getLatitude(),tblBannerLocations.getTbllocations().getLongitude());
        	    BigDecimal eventMillis = null;
				String advRefId = null;
				String advReferenceId = null;
				//System.out.println(distance1+" priority "+tblBanners.getPriority()+"  "+tblBannerLocations.getTblLocations().getLocationId()+" ssssssssssssss "+tblBannerLocations.getTblLocations().getLatitude()+" "+tblBannerLocations.getTblLocations().getLongitude());
				tblAdvRefTemp.setId(new TblAdvRefTempId(advReferenceId, tblBannerLocations.getTblbanners().getBannerid().toString(), distance1, tblBannerLocations.getTblbanners().getPriority(), advRefId, eventMillis, tblBannerLocations.getTbllocations().getLocationid().toString(), tblBannerLocations.getGeomaxradius()));
				sortSetTblAdvRefTemp.add(tblAdvRefTemp);  
			    } 
	          }    catch(HibernateException he){
	        	 session.getTransaction().rollback();
	             System.err.println(he);
	       	
				
	    } 
	          
		
		
		return sortSetTblAdvRefTemp;
	}
	
	
	public static String getMySqlStatements(List<String> myList) throws BusinessException {
		
		List<String> errorMessages = new ArrayList<String>();
		
        if ((myList == null) || (myList.size() == 0)) {
        	errorMessages.add("No any ad is assign to this publisher");
        }
        if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);	
     
        StringBuilder colValues = null;
        for (int i=0, j=0, length= myList.size(); i < length; i++) {
            if (colValues == null) {
                colValues = new StringBuilder();
            }
            j++;
            String data = myList.get(i);
            if(i==myList.size()-1)
            colValues.append("'").append(data).append("'");
            else
            colValues.append("'").append(data).append("',");
          
        }
        return colValues.toString();
    }
	
	
	private static Double getDistance(GeoLocation location, Double venueLat,
			Double venueLng) throws BusinessException {
		   
	        Double lat1 =location.getLatitudeInDegrees(); //Double.parseDouble(args[0]);
	        Double lon1 = location.getLongitudeInDegrees(); //Double.parseDouble(args[1]);
	        Double lat2 = venueLat; //Double.parseDouble(args[2]);
	        Double lon2 = venueLng; // Double.parseDouble(args[3]);
	        Double latDistance = toRad(lat2-lat1);
	        Double lonDistance = toRad(lon2-lon1);
	        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
	                   Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
	                   Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	        Double distance = AVERAGE_RADIUS_OF_EARTH * c;
	        
	  return distance;
	         
	      
	
	}
	 private static Double toRad(Double value) {
	        return value * Math.PI / 180;
	    }
	 

	public static void main(String[] args) throws BusinessException {

		double earthRadius = 6371.01;
		GeoLocation myLocation = GeoLocation.fromDegrees(-26.132554,28.231997);
		double distance = 100;
		Session session = HibernateUtil.getSession();
        SortedSet<TblAdvRefTemp> resultList = findPlacesWithinDistance(
				earthRadius, myLocation, distance, session,"21");
      
       System.out.println(resultList.size());
        
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
			TblAdvRefTemp tblAdvRefTemp = (TblAdvRefTemp) iterator.next();
						System.out.println(tblAdvRefTemp.getId().getBannerId()+" dd "+tblAdvRefTemp.getId().getLocationId()+"  "+tblAdvRefTemp.getId().getDistance());
			
		}
//       for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
//		TblLocations tblLocations = (TblLocations) iterator.next();
//		
//		System.out.println(tblLocations.getLocationId()+"  "+tblLocations.getLatitude()+"  "+tblLocations.getLongitude());
//		double userLat=-26.1954;
//		double venueLat=28.03187;
//		double latDistance = Math.toRadians(userLat - tblLocations.getLatitude());
//	    double lngDistance = Math.toRadians(venueLat - tblLocations.getLongitude());
//
//	   
//		double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
//	                    (Math.cos(Math.toRadians(userLat))) *
//	                    (Math.cos(Math.toRadians(venueLat))) *
//	                    (Math.sin(lngDistance / 2)) *
//	                    (Math.sin(lngDistance / 2));
//
//	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//		
//	  System.out.println(Math.round(earthRadius * c));
//		
//	}

	}

}
