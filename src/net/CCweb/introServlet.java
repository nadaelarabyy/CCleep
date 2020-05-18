package net.CCweb;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

/**
 * Servlet implementation class introServlet
 */
public class introServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public introServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("intro.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String lang=request.getParameter("user_lang");
		System.out.println("Lang: "+lang);
		if(lang==null)
			lang="en";
		request.getSession().setAttribute("lang", lang);
		boolean submitButtonPressed = request.getParameter("submitIntro") != null;
		if(submitButtonPressed) {
		
		
		response.sendRedirect(request.getContextPath() + "/chooseHabits");
		}
	}

}
