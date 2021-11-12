package com.ibm.temp.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.temp.ejb.ConverterLocal;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet( urlPatterns = "/TestServlet", loadOnStartup=1)
public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(TestServlet.class.getName());
	   
	
    @EJB ConverterLocal local;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("ERROR_MSG");
		session.removeAttribute("SUCCESS_MSG");
		
		String errorMsg = "";
		
		String degree = request.getParameter("degree");
		if ( degree.equals("") ) {
			errorMsg = "Temperature degree can not be empty";
			LOGGER.info("Temperature degree can not be empty");
		}
        if ( !degree.equals("")){
        	try {
				Double.parseDouble(degree);
			} catch (Exception e) {
				if (!errorMsg.equals("")) {
					errorMsg += ", ";
				}
				errorMsg += "Format temperature is invalid";
				LOGGER.info("Format temperature is invalid");
			}
        }
		
		if (!errorMsg.equals("")) {
			session.setAttribute("ERROR_MSG", errorMsg);
	        response.sendRedirect("index.jsp");;
	        return;
	    }
		
		// Call the EJB
		double result = local.celsiusToFar(Double.parseDouble(degree));
		
		if (result > -1) {	
			session.setAttribute("SUCCESS_MSG", "Process post Temperature: " + degree + " return value: " + result);
		} else {
			session.setAttribute("ERROR_MSG", "Error with Temperature: " + degree + " result: " + result);
		}
	    
		response.sendRedirect("index.jsp");
		return;
	}
}
