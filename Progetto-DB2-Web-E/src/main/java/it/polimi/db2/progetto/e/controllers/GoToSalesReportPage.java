package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import it.polimi.db2.progetto.e.views.AlertView;
import it.polimi.db2.progetto.e.views.AvgOpSpView;
import it.polimi.db2.progetto.e.views.BestSellerOpView;
import it.polimi.db2.progetto.e.views.InsConsView;
import it.polimi.db2.progetto.e.views.SusOrdView;
import it.polimi.db2.progetto.e.views.TotPurSpView;
import it.polimi.db2.progetto.e.views.TotPurSpVpView;
import it.polimi.db2.progetto.e.views.TotSaleSpOpView;


@WebServlet("/GoToSalesReportPage")
public class GoToSalesReportPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TemplateEngine templateEngine;
	
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//////////////////////////
		TotPurSpView tpsv = new TotPurSpView();
		tpsv.setIdSP(0); tpsv.setName("tpsv_Name"); tpsv.setTotalPurchase(30.4f);
		TotPurSpView tpsv1 = new TotPurSpView();
		tpsv1.setIdSP(10); tpsv1.setName("tpsv1_Name"); tpsv1.setTotalPurchase(130.4f);
		List<TotPurSpView> l1 = new ArrayList<>();
		l1.add(tpsv1);l1.add(tpsv);

		TotPurSpVpView tpsvv = new TotPurSpVpView();
		tpsvv.setIdSP(0); tpsvv.setIdVP(1); tpsvv.setNumMonth(2); tpsvv.setName("tpsv_Name"); tpsvv.setMonthlyFee(18.0f); tpsvv.setTotalPurchase(240.5f);
		TotPurSpVpView tpsvv1 = new TotPurSpVpView();
		tpsvv1.setIdSP(10); tpsvv1.setIdVP(11); tpsvv1.setNumMonth(12); tpsvv1.setName("tpsv1_Name"); tpsvv1.setMonthlyFee(118.0f); tpsvv1.setTotalPurchase(1240.5f);
		List<TotPurSpVpView> l2 = new ArrayList<>();
		l2.add(tpsvv);l2.add(tpsvv1);
		
		TotSaleSpOpView tssov = new TotSaleSpOpView();
		tssov.setIdSP(0); tssov.setNameSP("tssov_Name"); tssov.setTotWithOP(180.0f); tssov.setTotWithoutOP(100.0f);
		TotSaleSpOpView tssov1 = new TotSaleSpOpView();
		tssov1.setIdSP(10); tssov1.setNameSP("tssov1_Name"); tssov1.setTotWithOP(1180.0f); tssov1.setTotWithoutOP(1100.0f);
		List<TotSaleSpOpView> l3 = new ArrayList<>();
		l3.add(tssov1);l3.add(tssov);
		
		AvgOpSpView aosv = new AvgOpSpView();
		aosv.setIdSP(0); aosv.setNameSP("aosv_Name"); aosv.setNumAvgOP(1.25f);
		AvgOpSpView aosv1 = new AvgOpSpView();
		aosv1.setIdSP(10); aosv1.setNameSP("aosv1_Name"); aosv1.setNumAvgOP(11.25f);
		List<AvgOpSpView> l4 = new ArrayList<>();
		l4.add(aosv);l4.add(aosv1);
		
		InsConsView icv = new InsConsView();
		icv.setUsername("icv_Username"); icv.setEmail("icv_email"); icv.setNumFailedPayments(4);
		InsConsView icv1 = new InsConsView();
		icv1.setUsername("icv1_Username"); icv1.setEmail("icv1_email"); icv1.setNumFailedPayments(14);
		List<InsConsView> l5 = new ArrayList<>();
		l5.add(icv);l5.add(icv1);
		
		SusOrdView sov = new SusOrdView();
		sov.setIdOrder(0); sov.setIdSP(1); sov.setIdValidity(3); sov.setTotValue(40.9f); sov.setStartDate(new Date()); sov.setCreationDate(new Date()); sov.setUserConsumer("sov_Username");
		SusOrdView sov1 = new SusOrdView();
		sov1.setIdOrder(10); sov1.setIdSP(11); sov1.setIdValidity(13); sov1.setTotValue(410.9f); sov1.setStartDate(new Date()); sov1.setCreationDate(new Date()); sov1.setUserConsumer("sov1_Username");
		List<SusOrdView> l6 = new ArrayList<>();
		l6.add(sov1);l6.add(sov);
		
		AlertView av = new AlertView();
		av.setAmount(476.85f); av.setLastRejection(new Date()); av.setUserConsumer("av_Username"); av.setEmail("av_Email");
		AlertView av1 = new AlertView();
		av1.setAmount(1476.85f); av1.setLastRejection(new Date()); av1.setUserConsumer("av1_Username");
		List<AlertView> l7 = new ArrayList<>();
		l7.add(av);l7.add(av);
		
		BestSellerOpView bsov = new BestSellerOpView();
		bsov.setIdOP(0); bsov.setNameOP("bsov_Name"); bsov.setNumSell(19);
		BestSellerOpView bsov1 = new BestSellerOpView();
		bsov1.setIdOP(10); bsov1.setNameOP("bsov1_Name"); bsov1.setNumSell(119);
		List<BestSellerOpView> l8 = new ArrayList<>();
		l8.add(bsov1);l8.add(bsov);
//////////////////////////
		
		String path = "/WEB-INF/salesReport.html";
		ServletContext servletContext = getServletContext();
		final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
		ctx.setVariable("tpsv", l1);
		ctx.setVariable("tpsvv", l2);
		ctx.setVariable("tssov", l3);
		ctx.setVariable("aosv", l4);
		ctx.setVariable("icv", l5);
		ctx.setVariable("sov", l6);
		ctx.setVariable("av", l7);
		ctx.setVariable("bsov", l8);
		templateEngine.process(path, ctx, response.getWriter());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
