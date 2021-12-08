package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.CredentialsException;
import it.polimi.db2.progetto.exceptions.IdException;
import it.polimi.db2.progetto.services.AlertService;
import it.polimi.db2.progetto.services.CartService;
import it.polimi.db2.progetto.services.ConsumerService;
import it.polimi.db2.progetto.services.OrderService;
import it.polimi.db2.progetto.services.SasService;

@WebServlet("/ConfirmOrder")
public class ConfirmOrder extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "it.polimi.db2.progetto.services/OrderService")
	private OrderService orderService;
	@EJB(name = "it.polimi.db2.progetto.services/ConsumerService")
	private ConsumerService consumerService;
	@EJB(name = "it.polimi.db2.progetto.services/SasService")
	private SasService sasService;
	@EJB(name = "it.polimi.db2.progetto.services/AlertService")
	private AlertService alertService;
	
	public ConfirmOrder() {
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

		boolean valid;
		if(request.getParameter("confirmOrder")!=null) {  //button pressed from confirm page
			if(StringEscapeUtils.escapeJava(request.getParameter("confirmOrder")).equals("Buy")) {
				valid = true;
			} else {
				valid = false;
			}
		}
		else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request parameter bad formed");
			return;
		}
		
		Order order = null;
		
		if(null != request.getSession().getAttribute("orderId")){ //ordine già fatto, va solo pagato
			//only validate order
			
			try {
				order = orderService.validateOrder((int)request.getSession().getAttribute("orderId"), valid);
			} catch (IdException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				return;
			}
				
		}else {			
			//it's a new order
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
			Calendar cal = Calendar.getInstance();
			
			try {
				cal.setTime(formatter.parse(cs.getSd()));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			cal.set(Calendar.HOUR_OF_DAY,0);
			cal.set(Calendar.MINUTE,0);			

			order = orderService.createOrder(
						cs.getUsername(),
						cs.getSP(), 
						cs.getVP(), 
						valid,
						cs.getTp(), 
						cal.getTime(), 
						cs.getOPs()
					);
		}
		
		if(valid) {
			sasService.createSas(order);
			consumerService.checkIsInsolvent(cs.getUsername());
		}
		
		request.getSession().setAttribute("orderId", null);

		cs.setEmpty(true);
		request.getSession().setAttribute("cartService", cs);
		
		String path = getServletContext().getContextPath() + "/GoToHomePage";
		response.sendRedirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
