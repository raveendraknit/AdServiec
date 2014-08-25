package com.sdk.service.exception;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "errorResponse")
public class ErrorResponse {

	private String error;
	private String[] errorList;
	/**
	 * @return the error
	 */
	@XmlElement(name="Status")
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the errorList
	 */
	@XmlElement(name="ErrorList")
	public String[] getErrorList() {
		return errorList;
	}
	/**
	 * @param errorList the errorList to set
	 */
	public void setErrorList(String[] errorList) {
		this.errorList = errorList;
	}

	
}