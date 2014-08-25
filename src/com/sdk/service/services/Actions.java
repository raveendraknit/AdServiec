package com.sdk.service.services;

import javax.xml.bind.annotation.XmlRootElement;

import com.sdk.common.util.Location;

@XmlRootElement
public class Actions {
	 private int actionId;
	 private String bannerActionId;
     private String actionName;
     private String actionValue;
     private Location[] location;
     private String actionurl;
	/**
	 * @return the actionurl
	 */
	public String getActionurl() {
		return actionurl;
	}
	/**
	 * @param actionurl the actionurl to set
	 */
	public void setActionurl(String actionurl) {
		this.actionurl = actionurl;
	}
	/**
	 * @return the location
	 */
	public Location[] getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(Location[] location) {
		this.location = location;
	}
	/**
	 * @return the actionValue
	 */
	public String getActionValue() {
		return actionValue;
	}
	/**
	 * @param actionValue the actionValue to set
	 */
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}
	/**
	 * @return the actionId
	 */
	public int getActionId() {
		return actionId;
	}
	/**
	 * @param actionId the actionId to set
	 */
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	/**
	 * @return the bannerActionId
	 */
	public String getBannerActionId() {
		return bannerActionId;
	}
	/**
	 * @param bannerActionId the bannerActionId to set
	 */
	public void setBannerActionId(String bannerActionId) {
		this.bannerActionId = bannerActionId;
	}
	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * @param actionName the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
     
     
     

}
