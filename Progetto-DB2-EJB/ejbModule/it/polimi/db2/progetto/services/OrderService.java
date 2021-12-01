package it.polimi.db2.progetto.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.CredentialsException;

@Stateless
public class OrderService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public OrderService() {
	}
	
	public void createOrder(Consumer c, ServicePackage sp, ValidityPeriod vp, boolean valid, float tp, Date sd, List<OptionalProduct> ops) {
		
		Order o = new Order();
		o.setUserConsumer(c);
		o.setTotValue(tp);
		o.setServicePackage(sp);
		o.setValidityPeriod(vp);
		o.setOptionalProductsOrdered(ops);
		o.setValid(valid);
		o.setStartDate(sd);
		o.setCreationDate(new Date());
		
		em.persist(o);
	}
	
	public void validateOrder(int idOrder, boolean valid) {
		Order o = findOrderById(idOrder);
		o.setValid(valid);
		em.persist(o);
	}
	
	public Order findOrderById(int idOrder) {
		List<Order> ords = em.createNamedQuery("Order.findOrderById", Order.class).setParameter(1, idOrder).getResultList();
		
		if(ords.size() == 0) {
			return null;
		}else {
			return ords.get(0);
		}
	}
	
	public List<Order> getInvalidOrders(String username){

		List<Consumer> c = em.createNamedQuery("Consumer.findUsername", Consumer.class).setParameter(1, username).getResultList();
		
		if(c.size() > 0) {
			return em.createNamedQuery("Order.getInvalidOrders", Order.class).setParameter(1, c.get(0)).getResultList();
		}
		else return null;
	}
}
