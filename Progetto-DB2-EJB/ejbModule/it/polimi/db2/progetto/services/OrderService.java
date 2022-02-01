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
	
	//crea un nuovo ordine e lo ritorna
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
	
	//setter per il campo isValid dell'ordine (eccezione se l'ordine passato non esiste)
	public Order validateOrder(int idOrder, boolean valid) throws IdException {		
		Order o = em.find(Order.class, idOrder);
		if(o==null) throw new IdException("Could not find order");
		o.setCreationDate(new Date());
		o.setValid(valid);
		return o;
	}
	
	//ritorna ordine dato il suo id (eccezione se non esiste)
	public Order findOrderById(int idOrder) throws IdException {
		Order o = em.find(Order.class , idOrder);
		if(o==null) throw new IdException("Could not find order");
		return o;
	}
	
	//ritorna lista di ordini non validi di un dato consumer
	public List<Order> getInvalidOrders(String username){
		Consumer c = em.find(Consumer.class, username);
		if(c != null) {
			return em.createNamedQuery("Order.getInvalidOrders", Order.class).setParameter(1, c).getResultList();
		}
		else return null;
	}
	
	//ritorna true se l'id dell'ordine per il dato consumer non esiste
	public boolean mismatchConsumerOrder(String username, int id){
		return em.createNamedQuery("Order.getConsumerOrder", Order.class)
				.setParameter(1, em.find(Consumer.class, username))
				.setParameter(2, id).getResultList().isEmpty();
	}
}
