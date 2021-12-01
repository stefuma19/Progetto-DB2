package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.entities.FixedPhone;
import it.polimi.db2.progetto.entities.MobileInternet;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.services.OptionalProductService;
import it.polimi.db2.progetto.services.OrderService;
import it.polimi.db2.progetto.services.ServicePackageService;
import it.polimi.db2.progetto.services.ValidityPeriodService;

@WebServlet("/GoToConfirmPage")
public class GoToConfirmPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	@EJB(name = "it.polimi.db2.progetto.services/ServicePackageService")
	private ServicePackageService servicePackageService;
	
	@EJB(name = "it.polimi.db2.progetto.services/ValidityPeriodService")
	private ValidityPeriodService validityPeriodService;	
	
	@EJB(name = "it.polimi.db2.progetto.services/OptionalProductService")
	private OptionalProductService optionalProductService;
	
	@EJB(name = "it.polimi.db2.progetto.services/OrderService")
	private OrderService orderService;
	
	public GoToConfirmPage() {
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
		
		String path = "/WEB-INF/confirm.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());	
		
		ServicePackage sp;
		ValidityPeriod vp;
		float tp = 0;
		List<OptionalProduct> ops = new ArrayList<>();
		
		if(StringEscapeUtils.escapeJava(request.getParameter("orderId")) != null){
			//abbiamo l'ordine da pagare
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Order order = orderService.findOrderById(Integer.parseInt(request.getParameter("orderId")));
			
			sp = order.getServicePackage();
			vp = order.getValidityPeriod();
			tp = order.getTotValue();
			ops = order.getOptionalProductsOrdered();
			request.getSession().setAttribute("sd", format.format(order.getStartDate()));
			request.getSession().setAttribute("orderId", order.getIdOrder());
			
			
		}else {
			//dobbiamo mostrare i vari valori che prendiamo da request
			
			
			int idSP = Integer.parseInt(request.getParameter("idSP"));
			sp = servicePackageService.findServicePackagesById(idSP);
			
			int idVP = Integer.parseInt(request.getParameter(request.getParameter("idSP") + "_validityPeriod"));
			vp = validityPeriodService.findValidityPeriodById(idVP);
			tp += vp.getNumMonth() * vp.getMonthlyFee();

			
			String[] ids = request.getParameterValues(idSP + "_optionalProducts");
			if(ids != null) {
				for(int i = 0; i < ids.length; i++) {
					int idOP = Integer.parseInt(ids[i]);
					OptionalProduct op = optionalProductService.findOptionalProductById(idOP);
					ops.add(op);
					tp += vp.getNumMonth() * op.getMonthlyFeeOP();
				}
			}
			
			
			
			

			request.getSession().setAttribute("sd", request.getParameter("startDate"));
			
		}
		
		request.getSession().setAttribute("sp", sp);
		request.getSession().setAttribute("vp", vp);
		request.getSession().setAttribute("ops", ops);
		request.getSession().setAttribute("tp", tp);
		
		
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
