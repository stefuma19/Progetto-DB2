package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;

import it.polimi.db2.progetto.services.*;
import it.polimi.db2.progetto.entities.*;

import java.util.List;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@WebServlet("/GoToHomePage")
public class GoToHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/FixedInternetService")
	private FixedInternetService fiService;
	@EJB(name = "it.polimi.db2.progetto.services/FixedPhoneService")
	private FixedPhoneService fpService;
	@EJB(name = "it.polimi.db2.progetto.services/MobileInternetService")
	private MobileInternetService miService;
	@EJB(name = "it.polimi.db2.progetto.services/MobilePhoneService")
	private MobilePhoneService mpService;
	@EJB(name = "it.polimi.db2.progetto.services/OptionalProductService")
	private OptionalProductService opService;

	public GoToHomePage() {
		super();
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
		List<FixedInternet> fis = fiService.findAllFIServices();
		List<FixedPhone> fps = fpService.findAllFPServices(); //TODO: serve?
		List<MobileInternet> mis = miService.findAllMIServices();
		List<MobilePhone> mps = mpService.findAllMPServices();
		List<OptionalProduct> ops = opService.findAllOptionalProducts();
		
		String path = "/WEB-INF/home.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("fis", fis);
		ctx.setVariable("fps", fps); //TODO: serve? per ora nell'home.html non lo aggiungo
		ctx.setVariable("mis", mis);
		ctx.setVariable("mps", mps);
		ctx.setVariable("ops", ops);
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
