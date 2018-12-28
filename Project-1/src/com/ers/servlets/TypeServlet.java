package com.ers.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ers.io.Log;
import com.ers.services.TypeService;
import com.ers.utils.ToolBelt;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class TypeServlet
 */
@WebServlet("/type")
public class TypeServlet extends HttpServlet {
	private static final long serialVersionUID = -3795492293179025004L;
	Logger log = Log.getInstance(this);
	TypeService service = new TypeService();
	ObjectMapper mapper = new ObjectMapper();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public TypeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = mapper.writeValueAsString(service.getAll());
		log.debug(json);

		// whether none or not, return what you get
    	response.setStatus(HttpServletResponse.SC_OK);
    	response.setContentType(ToolBelt.CONTENT_TYPE);
	    response.setCharacterEncoding(ToolBelt.CHARACTER_ENCODING);
	    response.getWriter().write(json.toString());	
	}
}
