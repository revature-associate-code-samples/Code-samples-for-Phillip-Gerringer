package com.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.beans.EmployeeBean;
import com.ers.exceptions.DuplicateUserNameException;
import com.ers.io.Log;
import com.ers.services.EmployeeService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 4946094994161850492L;
	Logger log = Log.getInstance(this);
	static EmployeeService service = new EmployeeService();
	ObjectMapper mapper = new ObjectMapper();
          
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get all employees and turn them into a JSONObj
		String json = mapper.writeValueAsString(service.getAll());
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
		String retJson = "";
		int status = HttpServletResponse.SC_OK;

		try {
			// input should be json
			String json = ToolBelt.getBody(request);
			log.debug(json);
			
			// json should translate to the EmployeeBean
			EmployeeBean bean = mapper.readValue(json, EmployeeBean.class);
			log.debug(bean);
			
			try {
				bean = service.put(bean);
				log.debug(bean);
				//convert to JSON
				retJson = mapper.writeValueAsString(bean);
				status = HttpServletResponse.SC_OK;
			}
			catch (DuplicateUserNameException de) {
				retJson = mapper.writeValueAsString(de);
				status = de.getCode();
			}
			
			// send back the update/inserted row
			PrintWriter writer = response.getWriter();
			response.setStatus(status);
			response.setContentType(ToolBelt.CONTENT_TYPE);
			response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
			writer.write(retJson);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String retJson = "";
		int status = HttpServletResponse.SC_OK;

		try {
			// input should be json
			String json = ToolBelt.getBody(request);
			log.debug(json);

			// json should translate to the EmployeeBean
			EmployeeBean bean = mapper.readValue(json, EmployeeBean.class);
			log.debug(bean);

			// insert/update the employee
			try {
				bean = service.put(bean);
				log.debug(bean);
				//convert to JSON
				retJson = mapper.writeValueAsString(bean);
				status = HttpServletResponse.SC_OK;
			}
			catch (DuplicateUserNameException de) {
				retJson = mapper.writeValueAsString(de);
				status = de.getCode();
			}
			
			// send back the update/inserted row
			PrintWriter writer = response.getWriter();
			response.setStatus(status);
			response.setContentType(ToolBelt.CONTENT_TYPE);
			response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
			writer.write(retJson);
		} catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}
