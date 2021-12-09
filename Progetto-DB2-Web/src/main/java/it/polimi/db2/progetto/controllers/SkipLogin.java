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

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.services.CartService;

@WebServlet("/SkipLogin")
public class SkipLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	public SkipLogin() {
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
		HttpSession session = request.getSession(false);
		if (session != null) {
			CartService cs = (CartService) session.getAttribute("cartService");
			if (cs != null) cs.reset();
			session.invalidate();
		}
		
		CartService cs = null;
		try {
			InitialContext ic = new InitialContext();
			// Retrieve the EJB using JNDI lookup
			//cs = (CartService) ic.lookup("java:/openejb/local/Progetto-DB2-Web/Progetto-DB2-Web/CartServiceLocalBean");
			cs = (CartService) ic.lookup("java:/openejb/local/CartServiceLocalBean");
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("cartService", cs);
		
		request.getSession().setAttribute("consIsInsolvent", false);
		String path = getServletContext().getContextPath() + "/GoToHomePage";
		response.sendRedirect(path);
	}
}
