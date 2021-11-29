package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.exceptions.CredentialsException;

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
	
	public List<Order> getInvalidOrders(String username){

		List<Consumer> c = em.createNamedQuery("Consumer.findUsername", Consumer.class).setParameter(1, username).getResultList();
		
		if(c.size() > 0) {
			return em.createNamedQuery("Order.getInvalidOrders", Order.class).setParameter(1, c.get(0)).getResultList();
		}
		else return null;
	}
}
