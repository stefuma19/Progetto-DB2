package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.services.MobileInternetService;

@WebServlet("/CreateMI")
public class CreateMI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.progetto.services/MobileInternetService")
	private MobileInternetService miService;
	
	public CreateMI() {
		super();
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setSuffix(".html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("errorMsgMI");	
		String path = getServletContext().getContextPath() + "/GoToHomePage";

		if (request.getParameter("numGigaMI") == null || request.getParameter("numGigaMI") == "" ||
				request.getParameter("extraGigaFeeMI") == null || request.getParameter("extraGigaFeeMI") == "") {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}

		Integer numGigaMI;
		Float extraGigaFeeMI;
		try {
			numGigaMI = Integer.parseInt(request.getParameter("numGigaMI"));
			extraGigaFeeMI = Float.parseFloat(request.getParameter("extraGigaFeeMI"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
			return;
		}

		if(numGigaMI<=0) {
			request.getSession().setAttribute("errorMsgMI", "Non positive giga");
			response.sendRedirect(path);
			return;
		}
		if(extraGigaFeeMI<=0) {
			request.getSession().setAttribute("errorMsgMI", "Non positive fee");
			response.sendRedirect(path);
			return;
		}

		if(!miService.createMI(numGigaMI, extraGigaFeeMI))
			request.getSession().setAttribute("errorMsgMI", "Already existent service");
		
		response.sendRedirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}