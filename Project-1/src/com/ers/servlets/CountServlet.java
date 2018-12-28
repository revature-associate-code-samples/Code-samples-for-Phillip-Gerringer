package com.ers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ers.beans.CountBean;
import com.ers.services.ReimbursementService;
import com.ers.utils.ToolBelt;

/**
 * Servlet implementation class CountServlet
 */
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = -8314110675578985706L;
	private Logger log = Logger.getLogger(this.getClass());
	
	private static final String MANAGER_COUNT = "/ERS/manager.count";
	private static final String EMPLOYEE_COUNT = "/ERS/employee.count";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public CountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// whether none or not, return what you get
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.setContentType(ToolBelt.CONTENT_TYPE);
	    response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
	    response.getWriter().write(process(request,response)); 
	}
	
	/**
	 * based on the URI call, will send back a specific count
	 * @param request
	 * @param response
	 * @return
	 */
	private String process(HttpServletRequest request, HttpServletResponse response) {
		CountBean bean = new CountBean();
		log.debug("COUNT REQUEST SENT TO: " + request.getRequestURI());
		HttpSession session = request.getSession();
		Integer employee_id = (Integer) session.getAttribute("employee_id");
		
		switch(request.getRequestURI()) {
		case MANAGER_COUNT:
			bean = countManager();
			break;
		case EMPLOYEE_COUNT:
			if (employee_id != null) {
				bean = countRequests(employee_id.intValue());
			}
			break;
		}
		
		log.debug("found: " + bean.toString());
		return bean.toString();
	}
	
	/**
	 * returns a count of all submitted requests
	 * @return
	 */
	private CountBean countManager() {
		CountBean bean = new CountBean();
		ReimbursementService service = new ReimbursementService();
		
		bean.setCount(service.getCount());
		bean.setData(MANAGER_COUNT);
		
		log.debug(MANAGER_COUNT + " = " + bean.getCount());
		
		return bean;
	}
	
	/**
	 * counts the Submitted reimbursements for the employee_id 
	 * @param employee_id
	 * @return
	 */
	private CountBean countRequests(int employee_id) {
		CountBean bean = new CountBean();
		ReimbursementService service = new ReimbursementService();
		
		bean.setCount(service.getCount(Integer.valueOf(employee_id)));
		bean.setData(EMPLOYEE_COUNT);
		
		log.debug(EMPLOYEE_COUNT + " = " + bean.getCount());
		
		return bean;
	}
	
	
	
}
