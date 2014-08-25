package com.sdk.service;
// Generated Aug 1, 2014 11:59:03 AM by Hibernate Tools 3.2.1.GA



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AspnetUsers generated by hbm2java
 */
@Entity
@Table(name="aspnet_users"
    ,schema="public"
)
public class AspnetUsers  implements Serializable {


     private String userid;
     private AspnetApplications aspnetApplications;
     private String username;
     private String loweredusername;
     private String mobilealias;
     private boolean isanonymous;
     private Date lastactivitydate;
     private Set<Tblsysusercompanies> tblsysusercompanieses = new HashSet<Tblsysusercompanies>(0);
     private Set<AspnetRoles> aspnetRoleses = new HashSet<AspnetRoles>(0);
     private Set<AspnetProfile> aspnetProfiles = new HashSet<AspnetProfile>(0);
     private Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers = new HashSet<AspnetPersonalizationperuser>(0);
     private Set<AspnetMembership> aspnetMemberships = new HashSet<AspnetMembership>(0);

    public AspnetUsers() {
    }

	
    public AspnetUsers(String userid, AspnetApplications aspnetApplications, String username, String loweredusername, boolean isanonymous, Date lastactivitydate) {
        this.userid = userid;
        this.aspnetApplications = aspnetApplications;
        this.username = username;
        this.loweredusername = loweredusername;
        this.isanonymous = isanonymous;
        this.lastactivitydate = lastactivitydate;
    }
    public AspnetUsers(String userid, AspnetApplications aspnetApplications, String username, String loweredusername, String mobilealias, boolean isanonymous, Date lastactivitydate, Set<Tblsysusercompanies> tblsysusercompanieses, Set<AspnetRoles> aspnetRoleses, Set<AspnetProfile> aspnetProfiles, Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers, Set<AspnetMembership> aspnetMemberships) {
       this.userid = userid;
       this.aspnetApplications = aspnetApplications;
       this.username = username;
       this.loweredusername = loweredusername;
       this.mobilealias = mobilealias;
       this.isanonymous = isanonymous;
       this.lastactivitydate = lastactivitydate;
       this.tblsysusercompanieses = tblsysusercompanieses;
       this.aspnetRoleses = aspnetRoleses;
       this.aspnetProfiles = aspnetProfiles;
       this.aspnetPersonalizationperusers = aspnetPersonalizationperusers;
       this.aspnetMemberships = aspnetMemberships;
    }
   
     @Id 
    
    @Column(name="userid", unique=true, nullable=false)
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="applicationid", nullable=false)
    public AspnetApplications getAspnetApplications() {
        return this.aspnetApplications;
    }
    
    public void setAspnetApplications(AspnetApplications aspnetApplications) {
        this.aspnetApplications = aspnetApplications;
    }
    
    @Column(name="username", nullable=false, length=256)
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column(name="loweredusername", nullable=false, length=256)
    public String getLoweredusername() {
        return this.loweredusername;
    }
    
    public void setLoweredusername(String loweredusername) {
        this.loweredusername = loweredusername;
    }
    
    @Column(name="mobilealias", length=16)
    public String getMobilealias() {
        return this.mobilealias;
    }
    
    public void setMobilealias(String mobilealias) {
        this.mobilealias = mobilealias;
    }
    
    @Column(name="isanonymous", nullable=false)
    public boolean isIsanonymous() {
        return this.isanonymous;
    }
    
    public void setIsanonymous(boolean isanonymous) {
        this.isanonymous = isanonymous;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="lastactivitydate", nullable=false, length=29)
    public Date getLastactivitydate() {
        return this.lastactivitydate;
    }
    
    public void setLastactivitydate(Date lastactivitydate) {
        this.lastactivitydate = lastactivitydate;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetUsers")
    public Set<Tblsysusercompanies> getTblsysusercompanieses() {
        return this.tblsysusercompanieses;
    }
    
    public void setTblsysusercompanieses(Set<Tblsysusercompanies> tblsysusercompanieses) {
        this.tblsysusercompanieses = tblsysusercompanieses;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="aspnet_usersinroles", schema="public", joinColumns = { 
        @JoinColumn(name="userid", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="roleid", nullable=false, updatable=false) })
    public Set<AspnetRoles> getAspnetRoleses() {
        return this.aspnetRoleses;
    }
    
    public void setAspnetRoleses(Set<AspnetRoles> aspnetRoleses) {
        this.aspnetRoleses = aspnetRoleses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetUsers")
    public Set<AspnetProfile> getAspnetProfiles() {
        return this.aspnetProfiles;
    }
    
    public void setAspnetProfiles(Set<AspnetProfile> aspnetProfiles) {
        this.aspnetProfiles = aspnetProfiles;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetUsers")
    public Set<AspnetPersonalizationperuser> getAspnetPersonalizationperusers() {
        return this.aspnetPersonalizationperusers;
    }
    
    public void setAspnetPersonalizationperusers(Set<AspnetPersonalizationperuser> aspnetPersonalizationperusers) {
        this.aspnetPersonalizationperusers = aspnetPersonalizationperusers;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="aspnetUsers")
    public Set<AspnetMembership> getAspnetMemberships() {
        return this.aspnetMemberships;
    }
    
    public void setAspnetMemberships(Set<AspnetMembership> aspnetMemberships) {
        this.aspnetMemberships = aspnetMemberships;
    }




}

