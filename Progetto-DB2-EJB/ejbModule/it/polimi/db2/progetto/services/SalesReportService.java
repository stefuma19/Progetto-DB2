package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.Alert;
import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.views.AvgOpSpView;
import it.polimi.db2.progetto.views.BestSellerOpView;
import it.polimi.db2.progetto.views.TotPurSpView;
import it.polimi.db2.progetto.views.TotPurSpVpView;
import it.polimi.db2.progetto.views.TotSaleSpOpView;

@Stateless
public class SalesReportService { //per recuperare liste per il report dell'employee
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public SalesReportService() {
	}
	
	public List<Alert> getAlertViewList() {
		return em.createNamedQuery("Alert.findAll", Alert.class).getResultList();
	}
	
	public List<AvgOpSpView> getAvgOpSpViewList() {
		return em.createNamedQuery("AvgOpSpView.findAll", AvgOpSpView.class).getResultList();
	}
	
	public List<BestSellerOpView> getBestSellerOpViewList() {
		return em.createNamedQuery("BestSellerOpView.findAll", BestSellerOpView.class).getResultList();
	}
	
	public List<TotPurSpView> getTotPurSpViewList() {
		return em.createNamedQuery("TotPurSpView.findAll", TotPurSpView.class).getResultList();
	}
	
	public List<TotPurSpVpView> getTotPurSpVpViewList() {
		return em.createNamedQuery("TotPurSpVpView.findAll", TotPurSpVpView.class).getResultList();
	}
	
	public List<TotSaleSpOpView> getTotSaleSpOpViewList() {
		return em.createNamedQuery("TotSaleSpOpView.findAll", TotSaleSpOpView.class).getResultList();
	}
	
	public List<Consumer> getInsConsList() {
		return em.createNamedQuery("Consumer.insCons", Consumer.class).getResultList();
	}
	
	public List<Order> getSusOrderList() {
		return em.createNamedQuery("Order.susOrder", Order.class).getResultList();
	}
}
