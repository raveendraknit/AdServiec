package com.sdk.service;
// Generated Aug 1, 2014 11:59:03 AM by Hibernate Tools 3.2.1.GA


import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AspnetPaths generated by hbm2java
 */
@Entity
@Table(name="aspnet_paths"
    ,schema="public"
)
public class AspnetPaths  implements Serializable {


     private String pathid;
     private AspnetApplications aspnetApplications;
     private String path;
     private String loweredpath;
     private Set<AspnetPersonalizationallusers> aspnetPersonalizationalluserses = new HashSet<AspnetPersonalizationallusers>(0);
     private Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers = new HashSet<AspnetPersonalizationperuser>(0);

    public AspnetPaths() {
    }

	
    public AspnetPaths(String pathid, AspnetApplications aspnetApplications, String path, String loweredpath) {
        this.pathid = pathid;
        this.aspnetApplications = aspnetApplications;
        this.path = path;
        this.loweredpath = loweredpath;
    }
    public AspnetPaths(String pathid, AspnetApplications aspnetApplications, String path, String loweredpath, Set<AspnetPersonalizationallusers> aspnetPersonalizationalluserses, Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers) {
       this.pathid = pathid;
       this.aspnetApplications = aspnetApplications;
       this.path = path;
       this.loweredpath = loweredpath;
       this.aspnetPersonalizationalluserses = aspnetPersonalizationalluserses;
       this.aspnetPersonalizationperusers = aspnetPersonalizationperusers;
    }
   
     @Id 
    
    @Column(name="pathid", unique=true, nullable=false)
    public String getPathid() {
        return this.pathid;
    }
    
    public void setPathid(String pathid) {
        this.pathid = pathid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="applicationid", nullable=false)
    public AspnetApplications getAspnetApplications() {
        return this.aspnetApplications;
    }
    
    public void setAspnetApplications(AspnetApplications aspnetApplications) {
        this.aspnetApplications = aspnetApplications;
    }
    
    @Column(name="path", nullable=false, length=256)
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    @Column(name="loweredpath", nullable=false, length=256)
    public String getLoweredpath() {
        return this.loweredpath;
    }
    
    public void setLoweredpath(String loweredpath) {
        this.loweredpath = loweredpath;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetPaths")
    public Set<AspnetPersonalizationallusers> getAspnetPersonalizationalluserses() {
        return this.aspnetPersonalizationalluserses;
    }
    
    public void setAspnetPersonalizationalluserses(Set<AspnetPersonalizationallusers> aspnetPersonalizationalluserses) {
        this.aspnetPersonalizationalluserses = aspnetPersonalizationalluserses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetPaths")
    public Set<AspnetPersonalizationperuser> getAspnetPersonalizationperusers() {
        return this.aspnetPersonalizationperusers;
    }
    
    public void setAspnetPersonalizationperusers(Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers) {
        this.aspnetPersonalizationperusers = aspnetPersonalizationperusers;
    }




}


