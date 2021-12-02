package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
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

import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.CredentialsException;
import it.polimi.db2.progetto.exceptions.IdException;
import it.polimi.db2.progetto.services.ConsumerService;
import it.polimi.db2.progetto.services.OrderService;

@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "it.polimi.db2.progetto.services/OrderService")
	private OrderService orderService;
	@EJB(name = "it.polimi.db2.progetto.services/ConsumerService")
	private ConsumerService consumerService;
	//dopo aver cliccato il pulsante per creare (pagare) ordine, invoca l'orderservice.createOrder
	
	public ConfirmOrder() {
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
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean valid;
		if(request.getParameter("confirmOrder")!=null) {
			if(StringEscapeUtils.escapeJava(request.getParameter("confirmOrder")).equals("Buy")) {
				valid = true;
			} else {
				valid = false;
				try {
					consumerService.updateIsInsolvent((String)request.getSession().getAttribute("consUsername"), !valid);
				} catch (CredentialsException e) {
					e.printStackTrace();
					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Could not find consumer");
					return;
				}
			}
		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request parameter bad formed");
			return;
		}
		
		if(null != request.getSession().getAttribute("orderId")){
			//only validate order
			
			try {
				orderService.validateOrder((int)request.getSession().getAttribute("orderId"), valid);
			} catch (IdException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				return;
			}
				
		}else {
			
			//it's a new order
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			try {
				orderService.createOrder(consumerService.findConsumerById((String)request.getSession().getAttribute("consUsername")), 
						(ServicePackage)request.getSession().getAttribute("sp"), 
						(ValidityPeriod)request.getSession().getAttribute("vp"), 
						valid, 
						(float)request.getSession().getAttribute("tp"), 
						formatter.parse((String)request.getSession().getAttribute("sd")), 
						(List<OptionalProduct>)request.getSession().getAttribute("ops"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		request.getSession().setAttribute("orderId", null);
		
		String path = getServletContext().getContextPath() + "/GoToHomePage";
		response.sendRedirect(path);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
