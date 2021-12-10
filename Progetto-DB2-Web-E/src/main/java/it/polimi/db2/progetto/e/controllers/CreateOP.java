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

import it.polimi.db2.progetto.services.OptionalProductService;

@WebServlet("/CreateOP")
public class CreateOP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.progetto.services/OptionalProductService")
	private OptionalProductService opService;
	
	public CreateOP() {
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

		request.getSession().removeAttribute("errorMsgOP");	
		String path = getServletContext().getContextPath() + "/GoToHomePage";

		if (request.getParameter("opName") == null || request.getParameter("opName") == "" ||
				request.getParameter("opMonthlyFee") == null || request.getParameter("opMonthlyFee") == "") {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}
		
		String opName = request.getParameter("opName");
		Float opMonthlyFee;
		try {
			opMonthlyFee = Float.parseFloat(request.getParameter("opMonthlyFee"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
			return;
		}

		if(opName.length()>32) {
			request.getSession().setAttribute("errorMsgOP", "Name too long");
			response.sendRedirect(path);
			return;
		}
		if(opMonthlyFee<=0) {
			request.getSession().setAttribute("errorMsgOP", "Non positive fee");
			response.sendRedirect(path);
			return;
		}

		if(!opService.createOP(opName, opMonthlyFee))
			request.getSession().setAttribute("errorMsgOP", "Already existent optional product");
		
		response.sendRedirect(path);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
