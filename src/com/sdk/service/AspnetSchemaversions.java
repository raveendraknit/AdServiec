package com.sdk.service;
// Generated Aug 1, 2014 11:59:03 AM by Hibernate Tools 3.2.1.GA


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * AspnetSchemaversions generated by hbm2java
 */
@Entity
@Table(name="aspnet_schemaversions"
    ,schema="public"
)
public class AspnetSchemaversions  implements java.io.Serializable {


     private AspnetSchemaversionsId id;
     private boolean iscurrentversion;

    public AspnetSchemaversions() {
    }

    public AspnetSchemaversions(AspnetSchemaversionsId id, boolean iscurrentversion) {
       this.id = id;
       this.iscurrentversion = iscurrentversion;
    }
   
     @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="feature", column=@Column(name="feature", nullable=false, length=128) ), 
        @AttributeOverride(name="compatibleschemaversion", column=@Column(name="compatibleschemaversion", nullable=false, length=128) ) } )
    public AspnetSchemaversionsId getId() {
        return this.id;
    }
    
    public void setId(AspnetSchemaversionsId id) {
        this.id = id;
    }
    
    @Column(name="iscurrentversion", nullable=false)
    public boolean isIscurrentversion() {
        return this.iscurrentversion;
    }
    
    public void setIscurrentversion(boolean iscurrentversion) {
        this.iscurrentversion = iscurrentversion;
    }




}

