package com.sdk.service.exception;


import java.util.List;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
@Provider
public class ExceptionHttpStatusResolver implements ExceptionMapper<BusinessException> {

	    public ExceptionHttpStatusResolver() {
	    	
	    }
	    public Response toResponse(BusinessException exception) {
		List<String> errorList = exception.getMessages();
		String[] array = errorList.toArray(new String[errorList.size()]);
		ErrorResponse errorResponse=new ErrorResponse(); 
		errorResponse.setError("Error");
        errorResponse.setErrorList(array);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
	    }
	


}