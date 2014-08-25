package com.sdk.service.services;

import java.util.Date;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlRootElement;

import com.sdk.common.util.BannerImages;

import com.sdk.service.Tblsysbrands;
import com.sdk.service.Tblsyscategories;
@XmlRootElement
public class RAd {
	
	private String bannerId;
	private Tblsyscategories tblSysCategories;
    private Tblsysbrands tblSysBrands;
    private Boolean catExclusive;
    private Integer subCategoryId;
    private Boolean exclusive;
    private String bannerName;
    private Integer statusId;
    private String campaignType;
    private Date startDate;
    private Date endDate;
    private Double budget;
    private Double dailyBudget;
    private Double curDailyBudget;
    private Date dailyBudgetDate;
    private Double balance;
    private Double cpm;
    private Double cpc;
    private Double cpa;
    private Double cpmlimit;
    private Double cpclimit;
    private Double cpalimit;
    private Long impressions;
    private Long clicks;
    private Long convertions;
    private Integer priority;
    private String keywords;
    private String url;
    private Boolean enabled;
    private Date dateCreated;
    private Boolean archived;
    private Date dateArchived;
    
    private BannerImages [] bannerImages;
    
	private SortedSet<BannerImages> sortedSet;
	

	/**
	 * @return the sortedSet
	 */
	public SortedSet<BannerImages> getSortedSet() {
		return sortedSet;
	}
	/**
	 * @param sortedSet the sortedSet to set
	 */
	public void setSortedSet(SortedSet<BannerImages> sortedSet) {
		this.sortedSet = sortedSet;
	}
	public BannerImages[] getBannerImages() {
		return bannerImages;
	}
	public void setBannerImages(BannerImages[] bannerImages) {
		this.bannerImages = bannerImages;
	}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public Tblsyscategories getTblSysCategories() {
		return tblSysCategories;
	}
	public void setTblSysCategories(Tblsyscategories tblSysCategories) {
		this.tblSysCategories = tblSysCategories;
	}
	public Tblsysbrands getTblSysBrands() {
		return tblSysBrands;
	}
	public void setTblSysBrands(Tblsysbrands tblSysBrands) {
		this.tblSysBrands = tblSysBrands;
	}
	public Boolean getCatExclusive() {
		return catExclusive;
	}
	public void setCatExclusive(Boolean catExclusive) {
		this.catExclusive = catExclusive;
	}
	public Integer getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	public Boolean getExclusive() {
		return exclusive;
	}
	public void setExclusive(Boolean exclusive) {
		this.exclusive = exclusive;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getCampaignType() {
		return campaignType;
	}
	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Double getBudget() {
		return budget;
	}
	public void setBudget(Double budget) {
		this.budget = budget;
	}
	public Double getDailyBudget() {
		return dailyBudget;
	}
	public void setDailyBudget(Double dailyBudget) {
		this.dailyBudget = dailyBudget;
	}
	public Double getCurDailyBudget() {
		return curDailyBudget;
	}
	public void setCurDailyBudget(Double curDailyBudget) {
		this.curDailyBudget = curDailyBudget;
	}
	public Date getDailyBudgetDate() {
		return dailyBudgetDate;
	}
	public void setDailyBudgetDate(Date dailyBudgetDate) {
		this.dailyBudgetDate = dailyBudgetDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getCpm() {
		return cpm;
	}
	public void setCpm(Double cpm) {
		this.cpm = cpm;
	}
	public Double getCpc() {
		return cpc;
	}
	public void setCpc(Double cpc) {
		this.cpc = cpc;
	}
	public Double getCpa() {
		return cpa;
	}
	public void setCpa(Double cpa) {
		this.cpa = cpa;
	}
	public Double getCpmlimit() {
		return cpmlimit;
	}
	public void setCpmlimit(Double cpmlimit) {
		this.cpmlimit = cpmlimit;
	}
	public Double getCpclimit() {
		return cpclimit;
	}
	public void setCpclimit(Double cpclimit) {
		this.cpclimit = cpclimit;
	}
	public Double getCpalimit() {
		return cpalimit;
	}
	public void setCpalimit(Double cpalimit) {
		this.cpalimit = cpalimit;
	}
	public Long getImpressions() {
		return impressions;
	}
	public void setImpressions(Long impressions) {
		this.impressions = impressions;
	}
	public Long getClicks() {
		return clicks;
	}
	public void setClicks(Long clicks) {
		this.clicks = clicks;
	}
	public Long getConvertions() {
		return convertions;
	}
	public void setConvertions(Long convertions) {
		this.convertions = convertions;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Boolean getArchived() {
		return archived;
	}
	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	public Date getDateArchived() {
		return dateArchived;
	}
	public void setDateArchived(Date dateArchived) {
		this.dateArchived = dateArchived;
	}
    
    
    

}
