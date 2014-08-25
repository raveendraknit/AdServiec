package com.sdk.service.exception;

import java.util.List;


public class BusinessException extends Exception {
	 
    private static final long serialVersionUID = 1L;
    
    
 
    private List<String> messages;
 
    public BusinessException(List<String> messages) {
    	
        super();
        
        this.messages = messages;
    }
 
    public List<String> getMessages() {
        return messages;
    }
}
