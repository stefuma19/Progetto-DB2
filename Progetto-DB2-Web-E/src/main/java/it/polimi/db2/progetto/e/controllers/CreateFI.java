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

import it.polimi.db2.progetto.services.FixedInternetService;

@WebServlet("/CreateFI")
public class CreateFI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.progetto.services/FixedInternetService")
	private FixedInternetService fiService;
	
	public CreateFI() {
		super();
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setSuffix(".html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("errorMsgFI");	
		String path = getServletContext().getContextPath() + "/GoToHomePage";

		if (request.getParameter("numGigaFI") == null || request.getParameter("numGigaFI") == "" ||
				request.getParameter("extraGigaFeeFI") == null || request.getParameter("extraGigaFeeFI") == "") {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}

		Integer numGigaFI;
		Float extraGigaFeeFI;
		try {
			numGigaFI = Integer.parseInt(request.getParameter("numGigaFI"));
			extraGigaFeeFI = Float.parseFloat(request.getParameter("extraGigaFeeFI"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
			return;
		}

		if(numGigaFI<=0) {
			request.getSession().setAttribute("errorMsgFI", "Non positive giga");
			response.sendRedirect(path);
			return;
		}
		if(extraGigaFeeFI<=0) {
			request.getSession().setAttribute("errorMsgFI", "Non positive fee");
			response.sendRedirect(path);
			return;
		}

		if(!fiService.createFI(numGigaFI, extraGigaFeeFI))
			request.getSession().setAttribute("errorMsgFI", "Already existent service");
		
		response.sendRedirect(path);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
