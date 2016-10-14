package ca.sheridancollege.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.sheridancollege.beans.Student;
import ca.sheridancollege.dao.DAO;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.sendRedirect("form.jsp");
		
		/*DAO dao=new DAO();
		
		//dao.populate();
		
				
		for (Student s : dao.studentList()) 
		{
			response.getWriter().append("<h3>" + s.getName() + "</h3>");
		}
		*/
		//response.getWriter().append("It Worked!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao=new DAO();
		
		

		if(request.getParameter("query")!=null){
			request.setAttribute("personList", dao.queryPerson(request.getParameter("firstName"), request.getParameter("lastName")));
			response.getWriter().append("It worked!");

			request.getRequestDispatcher("form.jsp").forward(request, response);
					}
		else{
			
			dao.savePerson(request.getParameter("fName"),request.getParameter("lName"),
			Integer.parseInt(request.getParameter("pNumber")),request.getParameter("email"));
					

			request.setAttribute("personList", dao.retreivePerson());
			response.getWriter().append("It worked!");

			request.getRequestDispatcher("form.jsp").forward(request, response);


		}	
	}

}
