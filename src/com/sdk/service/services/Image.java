package com.sdk.service.services;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Image {
	
    private String fileName;
    private String imageId;
	/**
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	private Integer width;
	private Integer height;
	private String imgSrc;
	private String imgInHtml;
	private String groupName;
	/**
	 * @return the groupName
	 */
	@XmlAttribute(name="groupName")
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getImgInHtml() {
		return imgInHtml;
	}
	public void setImgInHtml(String imgInHtml) {
		this.imgInHtml = imgInHtml;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	
	

}
