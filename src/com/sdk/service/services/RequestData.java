package com.sdk.service.services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RequestData {
	
	
	
	private String pubReference;
	private String advReference;
	private String ipAddr;
	private String bannerId;
	private String locationId;
	private String type;
	private String actionId;
	private String orientation;
	private Integer bannerWidth=0;
	private Integer bannerHeight=0;
	private Integer virtSize;
	private String horiSize;
	private String formatUrl;
	private Integer refresh;
	private String longitude;
	private String latitude;
	private String encryptType;
	private String networkId;
	private String imei;
	private String msisdn;
	private String socialType;
	
	
	
	
	


	public RequestData(String pubReference, String advReference, String type,
			Integer bannerWidth, Integer bannerHeight, String longitude,
			String latitude) {
		super();
		this.pubReference = pubReference;
		this.advReference = advReference;
		this.type = type;
		this.bannerWidth = bannerWidth;
		this.bannerHeight = bannerHeight;
		this.longitude = longitude;
		this.latitude = latitude;
	}


	public RequestData(String pubReference, String advReference, String ipAddr,
			String bannerId, String locationId, String type, String actionId,
			String socialType, String longitude,
			String latitude) {
		super();
		this.pubReference = pubReference;
		this.advReference = advReference;
		this.ipAddr = ipAddr;
		this.bannerId = bannerId;
		this.locationId = locationId;
		this.type = type;
		this.actionId = actionId;
		this.socialType = socialType;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	/**
	 * @return the bannerId
	 */
	public String getBannerId() {
		return bannerId;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	public RequestData(String pubReference,String advReference, String ipAddr,String type,String actionId, String orientation,
			Integer bannerWidth, Integer bannerHeight, Integer virtSize,
			String horiSize, String formatUrl, Integer refresh,
			String longitude, String latitude, String encryptType,
			String networkId, String imei, String msisdn, String socialType) {
		super();
	    this.pubReference=pubReference;
	    this.advReference=advReference;
		this.ipAddr = ipAddr;
		this.type=type;
		this.actionId=actionId;
		this.orientation = orientation;
		this.bannerWidth = bannerWidth;
		this.bannerHeight = bannerHeight;
		this.virtSize = virtSize;
		this.horiSize = horiSize;
		this.formatUrl = formatUrl;
		this.refresh = refresh;
		this.longitude = longitude;
		this.latitude = latitude;
		this.encryptType = encryptType;
		this.networkId = networkId;
		this.imei = imei;
		this.msisdn = msisdn;
		this.socialType = socialType;
	}
	/**
	 * @return the pubReference
	 */
	public String getPubReference() {
		return pubReference;
	}
	/**
	 * @return the advReference
	 */
	public String getAdvReference() {
		return advReference;
	}
	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the orientation
	 */
	public String getOrientation() {
		return orientation;
	}
	/**
	 * @return the bannerWidth
	 */
	public Integer getBannerWidth() {
		return bannerWidth;
	}
	/**
	 * @return the bannerHeight
	 */
	public Integer getBannerHeight() {
		return bannerHeight;
	}
	/**
	 * @return the virtSize
	 */
	public Integer getVirtSize() {
		return virtSize;
	}
	/**
	 * @return the horiSize
	 */
	public String getHoriSize() {
		return horiSize;
	}
	/**
	 * @return the formatUrl
	 */
	public String getFormatUrl() {
		return formatUrl;
	}
	/**
	 * @return the refresh
	 */
	public Integer getRefresh() {
		return refresh;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @return the encryptType
	 */
	public String getEncryptType() {
		return encryptType;
	}
	/**
	 * @return the networkId
	 */
	public String getNetworkId() {
		return networkId;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}
	/**
	 * @return the socialType
	 */
	public String getSocialType() {
		return socialType;
	}
	/**
	 * @return the actionId
	 */
	public String getActionId() {
		return actionId;
	}
	
	
}