package it.polimi.db2.progetto.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OrderService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public OrderService() {
	}
	
	public boolean createOrder(String username, int idServicePackage, int idValidity, boolean valid) {
		//chiamerà la named query che aggiunge l'ordine al db
		if(valid) {
			//invoca validateOrder
		}
		return false;
	}
	
	public boolean validateOrder(int idOrder) {
		//findOrderbyId.set valid a true
		return false;
	}
}
