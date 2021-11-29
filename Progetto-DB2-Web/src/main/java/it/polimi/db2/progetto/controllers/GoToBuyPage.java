package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.services.*;
import it.polimi.db2.progetto.entities.*;

/**
 * Servlet implementation class GoToHomePage
 */
@WebServlet("/GoToBuyPage")
public class GoToBuyPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/ServicePackageService")
	private ServicePackageService spService;
	@EJB(name = "it.polimi.db2.progetto.services/ValidityPeriodService")
	private ValidityPeriodService vpService;
	
	public GoToBuyPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<ServicePackage> servicePackages = spService.findAllServicePackages();
		List<ValidityPeriod> validityPeriods = vpService.findAllValidityPeriods();

		String path = "/WEB-INF/buy.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());		
		ctx.setVariable("servicePackages", servicePackages);
		ctx.setVariable("validityPeriods", validityPeriods);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
