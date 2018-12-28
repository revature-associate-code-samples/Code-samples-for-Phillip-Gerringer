package com.ers.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ers.beans.EmployeeBean;
import com.ers.beans.LoginBean;
import com.ers.exceptions.InvalidCredentialsException;
import com.ers.exceptions.UserNotFoundException;
import com.ers.io.Log;
import com.ers.services.EmployeeService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Log.getInstance(LoginServlet.class);
	private static EmployeeService service = new EmployeeService();
	ObjectMapper mapper = new ObjectMapper();
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Integer employee_id = (Integer) session.getAttribute("employee_id");
		EmployeeBean bean = new EmployeeBean();
		String json = "";
		int status = HttpServletResponse.SC_OK;
		
		log.debug("Employee_ID attribute = " + employee_id);
		if (employee_id != null) {
			// user has already logged in so just get the employee info
			try {
				bean = service.getByID(employee_id.intValue());
				log.debug(bean);
				//convert to JSON
				json = mapper.writeValueAsString(bean);
				status = HttpServletResponse.SC_OK;
				
			} catch (UserNotFoundException e) {
				json = mapper.writeValueAsString(e);
				status = e.getCode();
			}	
		}
		else {
			json = mapper.writeValueAsString(bean);
		}
		
		log.debug(json);
		
		//send response
		PrintWriter writer = response.getWriter();
		response.setStatus(status);
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		writer.write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String json = ToolBelt.getBody(request);
		String jsonStr = "";
		int status = HttpServletResponse.SC_OK;
		log.debug(json);
		
		LoginBean loginBean = mapper.readValue(json, LoginBean.class);
		log.debug(loginBean);

		EmployeeBean bean = new EmployeeBean();
		try {
			bean = service.validateUser(loginBean.getUsername(), loginBean.getPassword());
			log.debug("I've passed validation");
			log.debug(bean);
			
			// write the session attribute if we have found a user
			if(bean.getEmployee_id() > 0)
				session.setAttribute("employee_id", bean.getEmployee_id());
			
			//send response
			jsonStr = mapper.writeValueAsString(bean);
			log.debug(jsonStr);
		} catch (InvalidCredentialsException e) {
			jsonStr = mapper.writeValueAsString(e);
			status = e.getCode();
		}

		PrintWriter writer = response.getWriter();
		response.setStatus(status);
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		writer.write(jsonStr);
	}

}
