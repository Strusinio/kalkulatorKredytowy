package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class MainServlet extends HttpServlet {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private Model model;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		model = new Model();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		System.out.println(request.getParameter("rodzajRaty"));
		model = new Model(request.getParameter("kwota"), request.getParameter("rata"), request.getParameter("procent"),
				request.getParameter("stala"), request.getParameter("rodzajRaty"));

		if (model.getBlad() != null) {
			request.setAttribute("error", model.getBlad());
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else {
			request.getSession().setAttribute("splaty", model.getSplaty());
			RequestDispatcher rd = request.getRequestDispatcher("summary.jsp");
			rd.forward(request, response);
		}

	}

}
