package com.sdk.service.services.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdk.common.manager.ServicesManager;
import com.sdk.common.util.ServicesDAO;
import com.sdk.service.exception.BusinessException;

/**
 * Servlet implementation class LandingPageAction
 */
public class LandingPageAction extends HttpServlet {
	
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		
	

		String pubReference=request.getParameter("pr");
		String advReference=request.getParameter("ar");
		String bannerId=request.getParameter("bi");
		String locationId=request.getParameter("li");
		String latitude=request.getParameter("lat");
		String longitude=request.getParameter("lon");
		String distance=request.getParameter("dis");
		String width=request.getParameter("wd");
		String height =request.getParameter("ht");
		ServicesDAO servicesDAO=new ServicesManager();
		String html = "";
		try {
			html = servicesDAO.getLandingPageAd(pubReference,advReference,bannerId,locationId,width,height,latitude,longitude,distance);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	
		request.setAttribute("html",html);
		RequestDispatcher rd = request.getRequestDispatcher("go.jsp");
		rd.include(request, response);
		

	}
}
