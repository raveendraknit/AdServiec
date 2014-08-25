package com.sdk.service.services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServiceInfoResponse {
	
	private  String message;
	
	private KeyValuePair[] serviceInfoItems;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public KeyValuePair[] getServiceInfoItems() {
		return serviceInfoItems;
	}

	public void setServiceInfoItems(KeyValuePair[] serviceInfoItems) {
		this.serviceInfoItems = serviceInfoItems;
	}

	

	
}
