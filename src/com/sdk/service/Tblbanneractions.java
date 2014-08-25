package com.sdk.service;
// Generated Aug 1, 2014 11:59:03 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tblbanneractions generated by hbm2java
 */
@Entity
@Table(name="tblbanneractions"
    ,schema="public"
)
public class Tblbanneractions  implements Serializable {


     private String banneractionid;
     private Tblactions tblactions;
     private Tblbanners tblbanners;

    public Tblbanneractions() {
    }

    public Tblbanneractions(String banneractionid, Tblactions tblactions, Tblbanners tblbanners) {
       this.banneractionid = banneractionid;
       this.tblactions = tblactions;
       this.tblbanners = tblbanners;
    }
   
     @Id 
    
    @Column(name="banneractionid", unique=true, nullable=false)
    public String getBanneractionid() {
        return this.banneractionid;
    }
    
    public void setBanneractionid(String banneractionid) {
        this.banneractionid = banneractionid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="actionid", nullable=false)
    public Tblactions getTblactions() {
        return this.tblactions;
    }
    
    public void setTblactions(Tblactions tblactions) {
        this.tblactions = tblactions;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="bannerid", nullable=false)
    public Tblbanners getTblbanners() {
        return this.tblbanners;
    }
    
    public void setTblbanners(Tblbanners tblbanners) {
        this.tblbanners = tblbanners;
    }




}


