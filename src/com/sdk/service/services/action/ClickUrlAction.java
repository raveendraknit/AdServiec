package com.sdk.service.services.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.exception.BusinessException;

/**
 * Servlet implementation class ClickUrlAction
 */
public class ClickUrlAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickUrlAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pubReference=request.getParameter("pr");
		String bannerId=request.getParameter("bi");
		String locationId=request.getParameter("li");
		ServicesDAO servicesDAO=new ServicesManager();
		
		synchronized (locationId) {
							try{
					servicesDAO.updateClick(pubReference,bannerId,locationId);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
