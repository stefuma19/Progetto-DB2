package it.polimi.db2.progetto.e.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.progetto.entities.FixedInternet;
import it.polimi.db2.progetto.entities.FixedPhone;
import it.polimi.db2.progetto.entities.MobileInternet;
import it.polimi.db2.progetto.entities.MobilePhone;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.exceptions.IdException;
import it.polimi.db2.progetto.services.FixedInternetService;
import it.polimi.db2.progetto.services.FixedPhoneService;
import it.polimi.db2.progetto.services.MobileInternetService;
import it.polimi.db2.progetto.services.MobilePhoneService;
import it.polimi.db2.progetto.services.OptionalProductService;
import it.polimi.db2.progetto.services.ServicePackageService;

@WebServlet("/CreateSP")
public class CreateSP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.progetto.services/ServicePackageService")
	private ServicePackageService spService;
	@EJB(name = "it.polimi.db2.progetto.services/OptionalProductService")
	private OptionalProductService opService;
	@EJB(name = "it.polimi.db2.progetto.services/FixedPhoneService")
	private FixedPhoneService fpService;
	@EJB(name = "it.polimi.db2.progetto.services/FixedInternetService")
	private FixedInternetService fiService;
	@EJB(name = "it.polimi.db2.progetto.services/MobilePhoneService")
	private MobilePhoneService mpService;
	@EJB(name = "it.polimi.db2.progetto.services/MobileInternetService")
	private MobileInternetService miService;
	
	public CreateSP() {
		super();
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setSuffix(".html");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getSession().removeAttribute("errorMsgSP");	
		String path = getServletContext().getContextPath() + "/GoToHomePage";

		if (request.getParameter("spName") == null || request.getParameter("spName") == "" || 
				request.getParameterValues("servicePackage") == null || request.getParameter("fixedInternet") == null ||
				request.getParameter("mobilePhone") == null || request.getParameter("mobileInternet") == null ||
				request.getParameterValues("servicePackage") == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}
		
		String spName = request.getParameter("spName");
		String[] services = request.getParameterValues("servicePackage");
		FixedPhone fp=null; FixedInternet fi=null; MobilePhone mp=null; MobileInternet mi=null;
		String[] ops = request.getParameterValues("optionalProducts"); 
		List<OptionalProduct> opList = new ArrayList<>();

		if(spName.length()>32) {
			request.getSession().setAttribute("errorMsgSP", "Name too long");
			response.sendRedirect(path);
			return;
		}
		if (services.length<=0) {
			request.getSession().setAttribute("errorMsgSP", "Select at least one service");
			response.sendRedirect(path);
			return;
		}
		boolean valid=false;
		if(services!=null) {
			for(int i=0; i<services.length; i++) {
				if(services[i].equals("fp") || services[i].equals("fi") || services[i].equals("mp") || services[i].equals("mi")) {
					valid = true;
					break;
				}
			}
		}
		if (!valid) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing parameters");
			return;
		}
		
		try {
			for(int i=0; i<services.length; i++) {
				if(services[i].equals("fp")) {
					fp = fpService.checkFpExists();
				}
				else if(services[i].equals("fi")) {
					fi = fiService.findFixedInternetById(Integer.parseInt(request.getParameter("fixedInternet")));
				}
				else if(services[i].equals("mp")) {
					mp = mpService.findMobilePhoneById(Integer.parseInt(request.getParameter("mobilePhone")));
				}
				else if(services[i].equals("mi")) {
					mi = miService.findMobileInternetById(Integer.parseInt(request.getParameter("mobileInternet")));
				}
			}
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
			return;
		} catch (IdException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
			return;
		}
		
		if(ops!=null) {
			try {
				for(int i=0; i<ops.length; i++) {
					opList.add(opService.findOptionalProductById(Integer.parseInt(ops[i])));
				}
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad parameters format");
				return;
			} catch (IdException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
				return;
			}
		}

		if(spService.existsServicePackageByName(spName)) {
			request.getSession().setAttribute("errorMsgSP", "Already existent service package name");
		}
		else if(!spService.createServicePackage(spName, fp, fi, mp, mi, opList))
			request.getSession().setAttribute("errorMsgSP", "A service package with the same services "
					+ "and optional products already exist");
		
		response.sendRedirect(path);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
