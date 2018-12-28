package com.ers.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.beans.ValidateBean;
import com.ers.exceptions.UserNotFoundException;
import com.ers.io.Log;
import com.ers.services.EmployeeService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ValidateServlet used for server side validation
 */
@WebServlet("/validate")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Log.getInstance(this);
	static EmployeeService service = new EmployeeService();
	ObjectMapper mapper = new ObjectMapper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = "";
		// input should be json
		json = ToolBelt.getBody(request);
		log.debug(json);

		// json should translate to the EmployeeBean
		ValidateBean bean = mapper.readValue(json, ValidateBean.class);
		log.debug(bean);

		if (bean.getType().equals("user_name")) {
			try {
				log.debug(service.getByID(bean.getValue()));
				bean.setStatus(HttpServletResponse.SC_OK);
				json = mapper.writeValueAsString(bean);
				// if we have made it this far then we found the user

			} catch (UserNotFoundException e) {
				bean.setStatus(e.getCode());
				json = mapper.writeValueAsString(bean);
			}
		}

		// send back the update/inserted row
		response.setStatus(bean.getStatus());
		response.setContentType(ToolBelt.CONTENT_TYPE);
		response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
		response.getWriter().write(bean.toString());	
	}

}
