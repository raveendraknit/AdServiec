package com.sdk.common.util;

import java.io.Serializable;
import java.util.Date;


public class BannerImages implements Comparable<BannerImages> {

	
	 private Serializable imageId;
	 private Serializable bannerSizeId;
     private Integer width;
     private Integer height;
     private String imgSrc;
     private String contentType;
     private String fileName;
     private Date dateCreated;
     private Integer mixSize;
     private String groupName;
	 /**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Serializable getImageId() {
		return imageId;
   	}
	public void setImageId(Serializable imageId) {
		this.imageId = imageId;
	}
	
	public Serializable getBannerSizeId() {
		return bannerSizeId;
	}
	public void setBannerSizeId(Serializable bannerSizeId) {
		this.bannerSizeId = bannerSizeId;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
	/**
	 * @return the mixSize
	 */
	public Integer getMixSize() {
		return mixSize;
	}
	/**
	 * @param mixSize the mixSize to set
	 */
	public void setMixSize(Integer mixSize) {
		this.mixSize = mixSize;
	}
	@Override
	public int compareTo(BannerImages o) {
		// TODO Auto-generated method stub
		return this.mixSize-o.mixSize;
	}
	
     
     
	
}
