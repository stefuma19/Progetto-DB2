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
		
		if(StringEscapeUtils.escapeJava(request.getParameter("idOrder")) != null){
			//abbiamo l'ordine da pagare
		}else {
			//dobbiamo mostrare i vari valori che prendiamo da request
			float tp = 0;
			
			int idSP = Integer.parseInt(request.getParameter("idSP"));
			ServicePackage sp = servicePackageService.findServicePackagesById(idSP);
			
			int idVP = Integer.parseInt(request.getParameter(request.getParameter("idSP") + "_validityPeriod"));
			ValidityPeriod vp = validityPeriodService.findValidityPeriodById(idVP);
			tp += vp.getNumMonth() * vp.getNumMonth();

			List<OptionalProduct> ops = new ArrayList<>();
			String[] ids = request.getParameterValues(request.getParameter("idSP") + "_optionalProducts");
			if(ids != null) {
				for(int i = 0; i < ids.length; i++) {
					int idOP = Integer.parseInt(ids[i]);
					OptionalProduct op = optionalProductService.findOptionalProductById(idOP);
					ops.add(op);
					tp += vp.getNumMonth() * op.getMonthlyFeeOP();
				}
			}
			
			//SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");
			
			request.getSession().setAttribute("sp", sp);
			request.getSession().setAttribute("vp", vp);
			request.getSession().setAttribute("ops", ops);
			request.getSession().setAttribute("tp", tp);
			
				request.getSession().setAttribute("sd", request.getParameter("startDate"));
			
			
		}
		
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
