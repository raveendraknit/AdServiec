package com.sdk.service.services;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.sdk.common.util.Location;




@XmlRootElement

@XmlType(name = "RAdContent", propOrder = {
	    "type",
	    "pubReference",
	    "adReference",
	    "name",
	    "bannerId",
	    "locationId",
	    "distance",
	    "address",
	    "text",
	    "landingurl",
	    "html",
	    "bannerhtml",
	    "images",
	    "actions",
	    "clickUrl",
	    "trackUrl",
	    "clickType",
	    "urlType",
	    "refresh",
	    "scale",
	    "skippreflight"
	})
public class RAdContent {
	/**
	 * @return the pubReference
	 */
	public String getPubReference() {
		return pubReference;
	}
	/**
	 * @param pubReference the pubReference to set
	 */
	public void setPubReference(String pubReference) {
		this.pubReference = pubReference;
	}
	/**
	 * @return the adReference
	 */
	public String getAdReference() {
		return adReference;
	}
	/**
	 * @param adReference the adReference to set
	 */
	public void setAdReference(String adReference) {
		this.adReference = adReference;
	}
	private String pubReference;
	private String adReference;;
	private String name;
	private String type;
	private String text;
	private String landingurl;
	private Double distance;
	private Location address;
	private String html;
	private String bannerhtml;
	/**
	 * @return the bannerhtml
	 */
	public String getBannerhtml() {
		return bannerhtml;
	}
	/**
	 * @param bannerhtml the bannerhtml to set
	 */
	public void setBannerhtml(String bannerhtml) {
		this.bannerhtml = bannerhtml;
	}
	private String bannerId;
	private String locationId;
	private String clickUrl;
	private Image[] images;
	private Actions[] actions;
    private String trackUrl;
	private String clickType;
	private String urlType;
	private Integer refresh;
	private boolean scale;
	private boolean skippreflight;
	

	
	/**
	 * @return the landingurl
	 */
	public String getLandingurl() {
		return landingurl;
	}
	/**
	 * @param landingurl the landingurl to set
	 */
	public void setLandingurl(String landingurl) {
		this.landingurl = landingurl;
	}
	/**
	 * @return the distance
	 */
	public Double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
	
	/**
	 * @return the address
	 */
	public Location getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Location address) {
		this.address = address;
	}
	/**
	 * @return the html
	 */
	public String getHtml() {
		return html;
	}
	/**
	 * @param html the html to set
	 */
	public void setHtml(String html) {
		this.html = html;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the actions
	 */
	public Actions[] getActions() {
		return actions;
	}
	/**
	 * @param actions the actions to set
	 */
	public void setActions(Actions[] actions) {
		this.actions = actions;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the clickUrl
	 */
	public String getClickUrl() {
		return clickUrl;
	}
	/**
	 * @param clickUrl the clickUrl to set
	 */
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	/**
	 * @return the images
	 */
	public Image[] getImages() {
		return images;
	}
	/**
	 * @param images the images to set
	 */
	public void setImages(Image[] images) {
		this.images = images;
	}
	/**
	 * @return the trackUrl
	 */
	public String getTrackUrl() {
		return trackUrl;
	}
	/**
	 * @param trackUrl the trackUrl to set
	 */
	public void setTrackUrl(String trackUrl) {
		this.trackUrl = trackUrl;
	}
	/**
	 * @return the clickType
	 */
	public String getClickType() {
		return clickType;
	}
	/**
	 * @param clickType the clickType to set
	 */
	public void setClickType(String clickType) {
		this.clickType = clickType;
	}
	/**
	 * @return the urlType
	 */
	public String getUrlType() {
		return urlType;
	}
	/**
	 * @param urlType the urlType to set
	 */
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	/**
	 * @return the refresh
	 */
	public Integer getRefresh() {
		return refresh;
	}
	/**
	 * @param refresh the refresh to set
	 */
	public void setRefresh(Integer refresh) {
		this.refresh = refresh;
	}
	/**
	 * @return the scale
	 */
	public boolean isScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(boolean scale) {
		this.scale = scale;
	}
	/**
	 * @return the skippreflight
	 */
	public boolean isSkippreflight() {
		return skippreflight;
	}
	/**
	 * @return the bannerId
	 */
	public String getBannerId() {
		return bannerId;
	}
	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
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
	 * @param skippreflight the skippreflight to set
	 */
	public void setSkippreflight(boolean skippreflight) {
		this.skippreflight = skippreflight;
	}
	
	
	
}
