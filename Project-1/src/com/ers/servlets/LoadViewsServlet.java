package com.ers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoadViewsServlet
 */
public class LoadViewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(this.getClass());
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadViewsServlet() {
        super();
    }

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resource = "views/" + process(request,response) + ".html";
		log.debug(resource);
		request.getRequestDispatcher(resource).forward(request, response); 
	}
	
    /**
     * returns the view to display based on the request
     * @param request
     * @param response
     * @return
     */
	protected String process(HttpServletRequest request, HttpServletResponse response) {	
		log.debug("LOAD VIEW REQUEST SENT TO: " + request.getRequestURI());
	
		switch(request.getRequestURI()) {
		case "/ERS/login.view":
			// !!! this pieces of code doesn't seem to work as expected, not only does the 
			// attribute not get removed but the session doesn't get invalidated
			// clean the session data for a fresh user
			log.debug("employee_id set to " + request.getSession().getAttribute("employee_id"));
			
			request.getSession().setAttribute("employee_id", null);
			
			log.debug("then it is set to " + request.getSession().getAttribute("employee_id"));
			request.getSession().removeAttribute("employee_id");

			log.debug("now it is set to " + request.getSession().getAttribute("employee_id"));
			request.getSession(true).invalidate();
			
			return "login";
		case "/ERS/admin.view":
			return "admin";
		case "/ERS/manager.view":
			return "manager";
		case "/ERS/employee.view":
			return "employee";
		case "/ERS/account.view":
			return "account";
		case "/ERS/history.view":
			return "history";
		}
		
		return null;
	}
}
