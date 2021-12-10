package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.services.MobilePhoneService;

@WebServlet("/CreateMP")
public class CreateMP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.progetto.services/MobilePhoneService")
	private MobilePhoneService mpService;
	
	public CreateMP() {
		super();
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setSuffix(".html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("errorMsgMP");	
		String path = getServletContext().getContextPath() + "/GoToHomePage";

		if (request.getParameter("numMin") == null || request.getParameter("numMin") == "" ||
				request.getParameter("numSms") == null || request.getParameter("numSms") == "" ||
				request.getParameter("minFee") == null || request.getParameter("minFee") == "" ||
				request.getParameter("smsFee") == null || request.getParameter("smsFee") == "") {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}

		Integer numMin, numSms;
		Float minFee, smsFee;
		try {
			numMin = Integer.parseInt(request.getParameter("numMin"));
			numSms = Integer.parseInt(request.getParameter("numSms"));
			minFee = Float.parseFloat(request.getParameter("minFee"));
			smsFee = Float.parseFloat(request.getParameter("smsFee"));
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
			return;
		}

		if(numMin<=0) {
			request.getSession().setAttribute("errorMsgMP", "Non positive number of minutes");
			response.sendRedirect(path);
			return;
		}
		if(numSms<=0) {
			request.getSession().setAttribute("errorMsgMP", "Non positive number of SMSs");
			response.sendRedirect(path);
			return;
		}
		if(minFee<=0) {
			request.getSession().setAttribute("errorMsgMP", "Non positive fee for minutes");
			response.sendRedirect(path);
			return;
		}
		if(smsFee<=0) {
			request.getSession().setAttribute("errorMsgMP", "Non positive fee for SMSs");
			response.sendRedirect(path);
			return;
		}

		if(!mpService.createMP(numMin, numSms, minFee, smsFee))
			request.getSession().setAttribute("errorMsgMP", "Already existent service");
		
		response.sendRedirect(path);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
