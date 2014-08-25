package com.sdk.service.services.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.Tblbannerimages;
import com.sdk.service.exception.BusinessException;



/**
 * Servlet implementation class FileHandleAction
 */
public class FileHandleAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileHandleAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		try{
			String file=request.getParameter("file");
			validate("rAdId", file);
			
			ServicesDAO servicesDAO=new ServicesManager();
			OutputStream out = response.getOutputStream();
			Tblbannerimages  bannerImages=servicesDAO.getBannerImages(file);
			
			byte[] imageBytes = bannerImages.getImage();
			response.setContentType(bannerImages.getContenttype());
			response.setContentLength(imageBytes.length); // imageBytes - image in bytes
			response.getOutputStream().write(imageBytes);// 
			out.flush();   
			out.close(); 
			
			}catch(Exception ex){
				
				
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
	}

	private void validate(String field, String value) throws BusinessException {
		List<String> errorMessages = new ArrayList<String>();
		if (value == null) {
			  errorMessages.add(field+": value required");
	    }
		
		try{
		  UUID fromStringUUID = UUID.fromString(value);
          String toStringUUID = fromStringUUID.toString();
          toStringUUID.equals(value);
         }catch(IllegalArgumentException  ex){
        	  errorMessages.add(field+": illegal arguments");		
		}
		
		if(!errorMessages.isEmpty())
			throw new BusinessException(errorMessages);
		
	}

}
