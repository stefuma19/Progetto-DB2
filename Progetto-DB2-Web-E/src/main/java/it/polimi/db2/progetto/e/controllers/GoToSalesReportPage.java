package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;

import javax.ejb.EJB;
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
import it.polimi.db2.progetto.services.SalesReportService;


@WebServlet("/GoToSalesReportPage")
public class GoToSalesReportPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TemplateEngine templateEngine;
	@EJB(name = "it.polimi.db2.progetto.services/SalesReportService")
	private SalesReportService srService;
	
	public GoToSalesReportPage() {
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

		String path = "/WEB-INF/salesReport.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("tpsv", srService.getTotPurSpViewList());
		ctx.setVariable("tpsvv", srService.getTotPurSpVpViewList());
		ctx.setVariable("tssov", srService.getTotSaleSpOpViewList());
		ctx.setVariable("aosv", srService.getAvgOpSpViewList());
		ctx.setVariable("icv", srService.getInsConsList());
		ctx.setVariable("sov", srService.getSusOrderList());
		ctx.setVariable("av", srService.getAlertViewList());
		ctx.setVariable("bsov", srService.getBestSellerOpViewList());
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
