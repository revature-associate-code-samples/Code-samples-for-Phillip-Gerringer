package com.ers.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ers.beans.PseudoReimbBean;
import com.ers.beans.ReimbursementBean;
import com.ers.io.Log;
import com.ers.services.ReimbursementService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ReimbursementServlet
 */
@WebServlet("/reimbursement")
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = -1001730686304516835L;
	Logger log = Log.getInstance(this);
	static ReimbursementService service = new ReimbursementService();
	ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer employee_id = (Integer) session.getAttribute("employee_id");
		// get all reimbursements and turn them into a json string
		String json = mapper.writeValueAsString(service.getAll(employee_id));
		log.debug(json);
		// whether none or not, return what you get
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		response.getWriter().write(json.toString());	
	}
	
	class ReimbObj {
		private int id = 0;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// input should be json
		String json = ToolBelt.getBody(request);
		log.debug(json);

		// json should translate to the EmployeeBean
		PseudoReimbBean obj = mapper.readValue(json, PseudoReimbBean.class);
		//ReimbObj obj = mapper.readValue(json, ReimbObj.class);
		log.debug(obj);

		// insert/update the employee
		ReimbursementBean bean = new ReimbursementBean();
		bean = service.getByID(obj.getId());
		log.debug(bean);

		// send back the update/inserted row
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		response.getWriter().write(bean.toString());
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
			
			// insert/update the employee
			bean = service.put(bean,employee_id);
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
