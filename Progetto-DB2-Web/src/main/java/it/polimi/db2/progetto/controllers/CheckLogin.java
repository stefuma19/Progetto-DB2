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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.services.CartService;
import it.polimi.db2.progetto.services.ConsumerService;
import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.exceptions.CredentialsException;
import javax.persistence.NonUniqueResultException;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/ConsumerService")
	private ConsumerService consumerService;

	public CheckLogin() {
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
		String usrn = null;
		String pwd = null;
		try {
			usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
			pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
			if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) {
				throw new Exception("Missing or empty credential value");
			}

		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing credential value");
			return;
		}
		Consumer consumer = null;
		try {
			consumer = consumerService.checkLogin(usrn, pwd);
		} catch (CredentialsException | NonUniqueResultException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not check credentials");
			return;
		}

		String path;
		if (consumer == null) {
			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
			ctx.setVariable("errorMsg", "Incorrect username or password");
			path = "/index.html";
			templateEngine.process(path, ctx, response.getWriter());
		} else if(request.getSession().getAttribute("cartService") != null &&
				!(((CartService)request.getSession().getAttribute("cartService")).isEmpty()) && 
				(((CartService)request.getSession().getAttribute("cartService")).getUsername().equals("") ||
						((CartService)request.getSession().getAttribute("cartService"))
							.getUsername().equals(consumer.getUsername()))) {
			CartService cs = (CartService)request.getSession().getAttribute("cartService");
			cs.setUsername(consumer.getUsername());
			request.getSession().setAttribute("cartService", cs);
			
			ServletContext servletContext = getServletContext();
			final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
			path = "/WEB-INF/confirm.html";
			templateEngine.process(path, ctx, response.getWriter());
		}
		else {			
			HttpSession session = request.getSession(false);
			if (session != null) {
				CartService cs = (CartService) session.getAttribute("cartService");
				if (cs != null) cs.remove();
				session.invalidate();
			}
		
			CartService cs = null;
			try {
				InitialContext ic = new InitialContext();
				// Retrieve the EJB using JNDI lookup
				cs = (CartService) ic.lookup("java:/openejb/local/CartServiceLocalBean");
			} catch (Exception e) {
				e.printStackTrace();
			}
			cs.setUsername(consumer.getUsername());
			request.getSession().setAttribute("cartService", cs);
			
			path = getServletContext().getContextPath() + "/GoToHomePage";
			response.sendRedirect(path);
		}
	}
}