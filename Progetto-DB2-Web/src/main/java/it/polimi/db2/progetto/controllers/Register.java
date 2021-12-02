package it.polimi.db2.progetto.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.exceptions.CredentialsException;
import it.polimi.db2.progetto.services.ConsumerService;

@WebServlet("/Register")
public class Register extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/ConsumerService")
	private ConsumerService consumerService;
	
	public Register() {
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
		String username = null;
		String password = null;
		String email = null;		

		String path = "/index.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		
		try {
			username = StringEscapeUtils.escapeJava(request.getParameter("username"));
			password = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
			email = StringEscapeUtils.escapeJava(request.getParameter("email"));
			
			if (username == null || password == null || email == null || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
				throw new Exception("Missing or empty credential value");
			}
			if(email.length()>64) {
				ctx.setVariable("errorMsgReg", "Email too long");
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}
			if(!email.contains("@") || email.indexOf('@')==email.length()-1) {
				ctx.setVariable("errorMsgReg", "Email has wrong format");
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}
			if(username.length()>32) {
				ctx.setVariable("errorMsgReg", "Username too long");
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}
			if(password.length()>16) {
				ctx.setVariable("errorMsgReg", "Password too long");
				templateEngine.process(path, ctx, response.getWriter());
				return;
			}

		} catch (Exception e) {
			// for debugging only e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		boolean isRegistrated = false;
		try {
			// query db to authenticate for user
			isRegistrated = consumerService.register(email, username, password);
		} catch (CredentialsException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not registrate");
			return;
		}

		// If the user exists, add info to the session and go to home page, otherwise
		// show login page with error message

		if (!isRegistrated) {
			ctx.setVariable("errorMsgReg", "Impossible to registrate with these credentials");
		} else {
			ctx.setVariable("errorMsgReg", "Registration completed, please login");
		}
		templateEngine.process(path, ctx, response.getWriter());
	}

	public void destroy() {
	}
}
