package it.polimi.db2.progetto.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.InitialContext;
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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@WebServlet("/GoToHomePage")
public class GoToHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/ServicePackageService")
	private ServicePackageService spService;
	@EJB(name = "it.polimi.db2.progetto.services/OrderService")
	private OrderService orderService;
	@EJB CartService cs2;
	
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
		
		CartService cs = (CartService) request.getSession().getAttribute("cartService");
		/*if(cs==null) {
			try {
				InitialContext ic = new InitialContext();
				// Retrieve the EJB using JNDI lookup
				cs = (CartService) ic.lookup("java:/openejb/local/CartServiceLocalBean");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		if(cs==null)
			cs = cs2;
		cs.setEmpty(true);
		request.getSession().setAttribute("cartService", cs);
		
		List<ServicePackage> servicePackages = spService.findAllServicePackages();
		List<Order> invalidOrders = orderService.getInvalidOrders(cs.getUsername());

		String path = "/WEB-INF/home.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());		
		ctx.setVariable("servicePackages", servicePackages);
		ctx.setVariable("invalidOrders", invalidOrders);
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
