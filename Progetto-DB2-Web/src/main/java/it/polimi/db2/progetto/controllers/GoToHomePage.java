package it.polimi.db2.progetto.controllers;

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

import java.util.ArrayList;
import java.util.List;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Servlet implementation class GoToHomePage
 */
@WebServlet("/GoToHomePage")
public class GoToHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/ConsumerService")
	private ConsumerService consumerService;

	public GoToHomePage() {
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
		Consumer c = null;
		
		//TODO copiato da codice veccchio, da togliere
		/*int userId = (int) request.getSession().getAttribute("userId");
		t = tService.findById(userId);
		if (chosen != null)
			course = consumerService.findById(chosen);
		if (chosen == null | course == null)
			course = consumerService.findDefault();*/

		String path = "/WEB-INF/home.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		//TODO copiato da codice veccchio, da togliere
		/*if (t != null)
			ctx.setVariable("responsible", t);
		if (course != null)
			ctx.setVariable("chosencourse", course);*/
		
		List<ServicePackage> servicePackages = new ArrayList<>();
		
		//AGGIUNTO PER TESTARE home.html
		/*FixedPhone fp = new FixedPhone();
		fp.setIdService(0);
		
		ServicePackage servicePackage = new ServicePackage();
		servicePackage.setIdServicePackage(0);
		servicePackage.setName("Nome del service pkg 1");
		servicePackage.setFixedPhone(fp);
		
		servicePackages.add(servicePackage);
		
		MobileInternet mi = new MobileInternet();
		mi.setIdService(1);
		mi.setNumGigaMI(10);
		mi.setExtraGigaFeeMI(9.99f);
		servicePackage = new ServicePackage();
		servicePackage.setIdServicePackage(1);
		servicePackage.setName("Nome del service pkg 2");
		servicePackage.setMobileInternet(mi);
		servicePackages.add(servicePackage);*/
		
		ctx.setVariable("servicePackages", servicePackages);
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
