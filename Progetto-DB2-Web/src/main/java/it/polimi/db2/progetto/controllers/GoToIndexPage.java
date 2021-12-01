package it.polimi.db2.progetto.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

@WebServlet("/GoToIndexPage")
public class GoToIndexPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GoToIndexPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String remember = request.getParameter("rememberOrder");
		if(remember!=null && (remember.equals("yes") || remember.equals("no"))) {
			request.getSession().setAttribute("rememberOrder", remember);
		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request parameter bad formed");
			return;
		}

		String path = getServletContext().getContextPath() + "/index.html";
		response.sendRedirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
