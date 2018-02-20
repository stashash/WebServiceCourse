package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Mirror extends HttpServlet {
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
        String value = request.getParameter("key");
        
        if (value == null || value.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        

        response.getWriter().println(value);
		
	}
	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
	               
	        	}

}
