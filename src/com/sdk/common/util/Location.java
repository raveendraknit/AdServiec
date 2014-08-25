package com.sdk.common.util;


import com.sdk.service.Tblsysbrands;

public class Location implements Comparable<Location> {
	
	
	private String locationId;
    private Tblsysbrands tblSysBrands;
    private String locationName;
    private String storeCode;
    private String storeName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private String countryCode;
    private String mainPhone;
    private String homePage;
    private Double olatitude;
    private Double olongitude;
    private Double latitude;
    private Double longitude;
    private Integer locationMaxRadius;
    private byte[] location;
    private Boolean approved;
    private Integer distance;
    
    
    
    
	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}




	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}




	/**
	 * @return the tblSysBrands
	 */
	public Tblsysbrands getTblSysBrands() {
		return tblSysBrands;
	}




	/**
	 * @param tblSysBrands the tblSysBrands to set
	 */
	public void setTblSysBrands(Tblsysbrands tblSysBrands) {
		this.tblSysBrands = tblSysBrands;
	}




	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}




	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}




	/**
	 * @return the storeCode
	 */
	public String getStoreCode() {
		return storeCode;
	}




	/**
	 * @param storeCode the storeCode to set
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}




	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}




	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}




	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}




	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}




	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}




	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}




	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}




	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}




	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}




	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}




	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}




	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}




	/**
	 * @return the mainPhone
	 */
	public String getMainPhone() {
		return mainPhone;
	}




	/**
	 * @param mainPhone the mainPhone to set
	 */
	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}




	/**
	 * @return the homePage
	 */
	public String getHomePage() {
		return homePage;
	}




	/**
	 * @param homePage the homePage to set
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}




	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}




	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}




	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}




	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}




	/**
	 * @return the locationMaxRadius
	 */
	public Integer getLocationMaxRadius() {
		return locationMaxRadius;
	}




	/**
	 * @param locationMaxRadius the locationMaxRadius to set
	 */
	public void setLocationMaxRadius(Integer locationMaxRadius) {
		this.locationMaxRadius = locationMaxRadius;
	}




	/**
	 * @return the location
	 */
	public byte[] getLocation() {
		return location;
	}




	/**
	 * @param location the location to set
	 */
	public void setLocation(byte[] location) {
		this.location = location;
	}




	/**
	 * @return the approved
	 */
	public Boolean getApproved() {
		return approved;
	}




	/**
	 * @param approved the approved to set
	 */
	public void setApproved(Boolean approved) {
		this.approved = approved;
	}




	/**
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}




	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}




	/**
	 * @return the olatitude
	 */
	public Double getOlatitude() {
		return olatitude;
	}




	/**
	 * @param olatitude the olatitude to set
	 */
	public void setOlatitude(Double olatitude) {
		this.olatitude = olatitude;
	}




	/**
	 * @return the olongitude
	 */
	public Double getOlongitude() {
		return olongitude;
	}




	/**
	 * @param olongitude the olongitude to set
	 */
	public void setOlongitude(Double olongitude) {
		this.olongitude = olongitude;
	}




	@Override
	public int compareTo(Location o) {
		// TODO Auto-generated method stub
		return this.distance.compareTo(o.distance);
	}




	
	

}
