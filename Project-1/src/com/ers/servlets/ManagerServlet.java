package com.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ers.beans.ReimbursementBean;
import com.ers.io.Log;
import com.ers.services.ManageService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ManagerServlet
 */
@WebServlet("/manage")
public class ManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 8618684787465212419L;
	Logger log = Log.getInstance(this);
	static ManageService service = new ManageService();
	ObjectMapper mapper = new ObjectMapper();

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get all reimbursements and turn them into a json string
		String json = mapper.writeValueAsString(service.getAllSubmitted());
		log.debug(json);
		// whether none or not, return what you get
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		response.getWriter().write(json.toString());	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Integer employee_id = (Integer) session.getAttribute("employee_id");
			
			// input should be json
			String json = ToolBelt.getBody(request);
			log.debug(json);

			// json should translate to the ReimbursementBean
			ReimbursementBean bean = mapper.readValue(json, ReimbursementBean.class);
			log.debug(bean);
			
			// update the resolver
			Date today = new Date();
			bean.getResolver().setEmployee_id(employee_id);
			bean.setResolve_date(today);
			
			// insert/update the employee
			bean = service.put(bean);
			log.debug(bean);
			
			// send back the update/inserted row
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(ToolBelt.CONTENT_TYPE);
			response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
			response.getWriter().write(bean.toString());	
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
