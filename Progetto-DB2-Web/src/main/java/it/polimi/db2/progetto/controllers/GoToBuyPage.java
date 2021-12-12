package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
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
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CartService cs = (CartService) request.getSession().getAttribute("cartService");
		if(cs==null) {
			try {
				InitialContext ic = new InitialContext();
				// Retrieve the EJB using JNDI lookup
				cs = (CartService) ic.lookup("java:/openejb/local/Progetto-DB2-Web/Progetto-DB2-Web/CartServiceLocalBean");
				//cs = (CartService) ic.lookup("java:/openejb/local/CartServiceLocalBean");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cs.setEmpty(true);
		request.getSession().setAttribute("cartService", cs);

		List<ServicePackage> servicePackages = spService.findAllServicePackages();
		List<ValidityPeriod> validityPeriods = vpService.findAllValidityPeriods();

		String path = "/WEB-INF/buy.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());		
		ctx.setVariable("servicePackages", servicePackages);
		ctx.setVariable("validityPeriods", validityPeriods);
		ctx.setVariable("todayDate", new Date());
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
