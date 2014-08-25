package com.sdk.service.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValidateMethod {
	
	private static Pattern pattern;
	private static Matcher matcher;
    private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	
	public static  void validateUUID(String field, String value) throws BusinessException  {
		
		try{
		  UUID fromStringUUID = UUID.fromString(value);
          String toStringUUID = fromStringUUID.toString();
          toStringUUID.equals(value);
         }catch(IllegalArgumentException  ex){
        	List<String> errorMessages = new ArrayList<String>();
 			errorMessages.add(field+": invalid value");
 			throw new BusinessException(errorMessages);	
         }
}

	public static  void validate(String field, String value) throws  BusinessException {
		
		List<String> errorMessages = new ArrayList<String>();
		
		if (value == null || value.length()==0) {
			  errorMessages.add(field+": value required");
			  if(!errorMessages.isEmpty())
					throw new BusinessException(errorMessages);
		}
		if(field.equalsIgnoreCase("ipAddr")){
			boolean valid=validateIpAddr(value);
			if(valid==false)
			  errorMessages.add(field+": invalid");
			
		}else if(field.equalsIgnoreCase("latitude")){
			try {
				Double.parseDouble(value);
			  } catch (NumberFormatException e) {
				 errorMessages.add(field+": invalid");
			}
				
		}else if(field.equalsIgnoreCase("longitude")){
			
			try {
				Double.parseDouble(value);
			  } catch (NumberFormatException e) {
				 errorMessages.add(field+": invalid");
			}
			
			
		}else{
			
			
		}
		
		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);
  }

	private static boolean validateIpAddr(String value) {
		 pattern = Pattern.compile(IPADDRESS_PATTERN);
		 matcher = pattern.matcher(value);
		 return matcher.matches(); 
	}

	public static void validateActionId(String field, String value) throws BusinessException {
     if (value == null||value.length()==0) {
			List<String> errorMessages = new ArrayList<String>();
			errorMessages.add(field+": value required");
			throw new BusinessException(errorMessages);	}
		
	}
	

}
