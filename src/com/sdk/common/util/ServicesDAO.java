
package com.sdk.common.util;

import java.util.List;

import com.sdk.service.Tblbannerimages;
import com.sdk.service.exception.BusinessException;
import com.sdk.service.services.Actions;
import com.sdk.service.services.RAd;
import com.sdk.service.services.RAdContent;
import com.sdk.service.services.RequestData;





public interface ServicesDAO {

	RAd getRAD(String rAdId) throws  BusinessException;

	List<RAd> getRAdlist();

	RAdContent getRAdContentById(RequestData requestData) throws BusinessException;

	Tblbannerimages getBannerImages(String file) throws BusinessException;

	Actions getAction(RequestData requestData) throws BusinessException;

	void updateClick(String pubReference, String bannerId,String locationId) throws BusinessException;

	void updateImpression(String pubReference,String bannerId, String locationId) throws BusinessException;

	String getauthInfo(Integer pubReference) throws BusinessException;

	String getLandingPageAd(String pubReference, String advReference,
			String bannerId, String locationId, String width, String height, String latitude, String longitude, String distance) throws BusinessException;

	
	
	
}
