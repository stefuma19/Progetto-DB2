package it.polimi.db2.progetto.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;

@WebServlet("/GoToConfirmPage")
public class GoToConfirmPage extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
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
		}
		
		//ctx.setVariable("servicePackages", servicePackages);
		//ctx.setVariable("invalidOrders", invalidOrders);
		
		FixedPhone fp = new FixedPhone();
		fp.setIdService(0);
		MobileInternet mi = new MobileInternet();
		mi.setIdService(1);
		mi.setNumGigaMI(10);
		mi.setExtraGigaFeeMI(9.99f);
		ServicePackage servicePackage = new ServicePackage();
		servicePackage.setIdServicePackage(0);
		servicePackage.setName("Nome del service pkg 1");
		servicePackage.setFixedPhone(fp);
		servicePackage.setMobileInternet(mi);
		servicePackage.setOptionalProducts(new ArrayList<>());
		ValidityPeriod vp = new ValidityPeriod();
		vp.setIdValidityPeriod(0);
		vp.setMonthlyFee(5.70f);
		vp.setNumMonth(3);
		request.getSession().setAttribute("sp", servicePackage);
		request.getSession().setAttribute("vp", vp);
		request.getSession().setAttribute("tp", 80.0f);
		
		templateEngine.process(path, ctx, response.getWriter());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
