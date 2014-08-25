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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tblbannersizes generated by hbm2java
 */
@Entity
@Table(name="tblbannersizes"
    ,schema="public"
)
public class Tblbannersizes  implements Serializable {


     private String bannersizeid;
     private Integer width;
     private Integer height;
     private String groupname;
     private Set<Tblbannerimages> tblbannerimageses = new HashSet<Tblbannerimages>(0);

    public Tblbannersizes() {
    }

	
    public Tblbannersizes(String bannersizeid) {
        this.bannersizeid = bannersizeid;
    }
    public Tblbannersizes(String bannersizeid, Integer width, Integer height, String groupname, Set<Tblbannerimages> tblbannerimageses) {
       this.bannersizeid = bannersizeid;
       this.width = width;
       this.height = height;
       this.groupname = groupname;
       this.tblbannerimageses = tblbannerimageses;
    }
   
     @Id 
    
    @Column(name="bannersizeid", unique=true, nullable=false)
    public String getBannersizeid() {
        return this.bannersizeid;
    }
    
    public void setBannersizeid(String bannersizeid) {
        this.bannersizeid = bannersizeid;
    }
    
    @Column(name="width")
    public Integer getWidth() {
        return this.width;
    }
    
    public void setWidth(Integer width) {
        this.width = width;
    }
    
    @Column(name="height")
    public Integer getHeight() {
        return this.height;
    }
    
    public void setHeight(Integer height) {
        this.height = height;
    }
    
    @Column(name="groupname", length=20)
    public String getGroupname() {
        return this.groupname;
    }
    
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="tblbannersizes")
    public Set<Tblbannerimages> getTblbannerimageses() {
        return this.tblbannerimageses;
    }
    
    public void setTblbannerimageses(Set<Tblbannerimages> tblbannerimageses) {
        this.tblbannerimageses = tblbannerimageses;
    }




}


