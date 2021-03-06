package com.sdk.service;
// Generated Aug 1, 2014 11:59:03 AM by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AspnetWebeventEvents generated by hbm2java
 */
@Entity
@Table(name="aspnet_webevent_events"
    ,schema="public"
)
public class AspnetWebeventEvents  implements java.io.Serializable {


     private String eventid;
     private Date eventtimeutc;
     private Date eventtime;
     private String eventtype;
     private BigDecimal eventsequence;
     private BigDecimal eventoccurrence;
     private int eventcode;
     private int eventdetailcode;
     private String message;
     private String applicationpath;
     private String applicationvirtualpath;
     private String machinename;
     private String requesturl;
     private String exceptiontype;
     private String details;

    public AspnetWebeventEvents() {
    }

	
    public AspnetWebeventEvents(String eventid, Date eventtimeutc, Date eventtime, String eventtype, BigDecimal eventsequence, BigDecimal eventoccurrence, int eventcode, int eventdetailcode, String machinename) {
        this.eventid = eventid;
        this.eventtimeutc = eventtimeutc;
        this.eventtime = eventtime;
        this.eventtype = eventtype;
        this.eventsequence = eventsequence;
        this.eventoccurrence = eventoccurrence;
        this.eventcode = eventcode;
        this.eventdetailcode = eventdetailcode;
        this.machinename = machinename;
    }
    public AspnetWebeventEvents(String eventid, Date eventtimeutc, Date eventtime, String eventtype, BigDecimal eventsequence, BigDecimal eventoccurrence, int eventcode, int eventdetailcode, String message, String applicationpath, String applicationvirtualpath, String machinename, String requesturl, String exceptiontype, String details) {
       this.eventid = eventid;
       this.eventtimeutc = eventtimeutc;
       this.eventtime = eventtime;
       this.eventtype = eventtype;
       this.eventsequence = eventsequence;
       this.eventoccurrence = eventoccurrence;
       this.eventcode = eventcode;
       this.eventdetailcode = eventdetailcode;
       this.message = message;
       this.applicationpath = applicationpath;
       this.applicationvirtualpath = applicationvirtualpath;
       this.machinename = machinename;
       this.requesturl = requesturl;
       this.exceptiontype = exceptiontype;
       this.details = details;
    }
   
     @Id 
    
    @Column(name="eventid", unique=true, nullable=false, length=32)
    public String getEventid() {
        return this.eventid;
    }
    
    public void setEventid(String eventid) {
        this.eventid = eventid;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="eventtimeutc", nullable=false, length=29)
    public Date getEventtimeutc() {
        return this.eventtimeutc;
    }
    
    public void setEventtimeutc(Date eventtimeutc) {
        this.eventtimeutc = eventtimeutc;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="eventtime", nullable=false, length=29)
    public Date getEventtime() {
        return this.eventtime;
    }
    
    public void setEventtime(Date eventtime) {
        this.eventtime = eventtime;
    }
    
    @Column(name="eventtype", nullable=false, length=256)
    public String getEventtype() {
        return this.eventtype;
    }
    
    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
    }
    
    @Column(name="eventsequence", nullable=false, scale=0)
    public BigDecimal getEventsequence() {
        return this.eventsequence;
    }
    
    public void setEventsequence(BigDecimal eventsequence) {
        this.eventsequence = eventsequence;
    }
    
    @Column(name="eventoccurrence", nullable=false, scale=0)
    public BigDecimal getEventoccurrence() {
        return this.eventoccurrence;
    }
    
    public void setEventoccurrence(BigDecimal eventoccurrence) {
        this.eventoccurrence = eventoccurrence;
    }
    
    @Column(name="eventcode", nullable=false)
    public int getEventcode() {
        return this.eventcode;
    }
    
    public void setEventcode(int eventcode) {
        this.eventcode = eventcode;
    }
    
    @Column(name="eventdetailcode", nullable=false)
    public int getEventdetailcode() {
        return this.eventdetailcode;
    }
    
    public void setEventdetailcode(int eventdetailcode) {
        this.eventdetailcode = eventdetailcode;
    }
    
    @Column(name="message", length=1024)
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Column(name="applicationpath", length=256)
    public String getApplicationpath() {
        return this.applicationpath;
    }
    
    public void setApplicationpath(String applicationpath) {
        this.applicationpath = applicationpath;
    }
    
    @Column(name="applicationvirtualpath", length=256)
    public String getApplicationvirtualpath() {
        return this.applicationvirtualpath;
    }
    
    public void setApplicationvirtualpath(String applicationvirtualpath) {
        this.applicationvirtualpath = applicationvirtualpath;
    }
    
    @Column(name="machinename", nullable=false, length=256)
    public String getMachinename() {
        return this.machinename;
    }
    
    public void setMachinename(String machinename) {
        this.machinename = machinename;
    }
    
    @Column(name="requesturl", length=1024)
    public String getRequesturl() {
        return this.requesturl;
    }
    
    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl;
    }
    
    @Column(name="exceptiontype", length=256)
    public String getExceptiontype() {
        return this.exceptiontype;
    }
    
    public void setExceptiontype(String exceptiontype) {
        this.exceptiontype = exceptiontype;
    }
    
    @Column(name="details")
    public String getDetails() {
        return this.details;
    }
    
    public void setDetails(String details) {
        this.details = details;
    }




}


