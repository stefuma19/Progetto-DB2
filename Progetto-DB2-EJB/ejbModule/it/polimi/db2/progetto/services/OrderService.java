package it.polimi.db2.progetto.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class OrderService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public OrderService() {
	}
	
	public Order createOrder(String consUser, ServicePackage sp, 
			ValidityPeriod vp, boolean valid, float tp, Date sd, List<OptionalProduct> ops) {
		
		Order o = new Order();
		o.setUserConsumer(em.find(Consumer.class, consUser));
		o.setTotValue(tp);
		o.setServicePackage(sp);
		o.setValidityPeriod(vp);
		o.setOptionalProductsOrdered(ops);
		o.setValid(valid);
		o.setStartDate(sd);
		o.setCreationDate(new Date());
		
		em.persist(o);
		return o;
	}
	
	public Order validateOrder(int idOrder, boolean valid) throws IdException {
		
		Order o = em.find(Order.class, idOrder);
		if(o==null) throw new IdException("Could not find order");
		o.setValid(valid);
		//em.persist(o);    //TODO: crea duplicati nel db, anche con merge e senza questa riga
		return o;
	}
	
	public Order findOrderById(int idOrder) throws IdException {
		Order o = em.find(Order.class , idOrder);
		if(o==null) throw new IdException("Could not find order");
		return o;
	}
	
	public List<Order> getInvalidOrders(String username){

		Consumer c = em.find(Consumer.class, username);
		
		if(c != null) {
			return em.createNamedQuery("Order.getInvalidOrders", Order.class).setParameter(1, c).getResultList();
		}
		else return null;
	}
	
	public boolean mismatchConsumerOrder(String username, int id){
		//return true if the order "id" for the user "username" doesn't exist
		return em.createNamedQuery("Order.getConsumerOrder", Order.class)
				.setParameter(1, em.find(Consumer.class, username))
				.setParameter(2, id).getResultList().isEmpty();
	}
}
