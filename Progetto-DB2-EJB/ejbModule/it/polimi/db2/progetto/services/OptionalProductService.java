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
public class OptionalProductService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;
	
	public OptionalProductService() {
	}
	
	public List<OptionalProduct> findAllOptionalProducts() {
		return em.createNamedQuery("OptionalProduct.findAll", OptionalProduct.class).getResultList();
	}
	
	public OptionalProduct findOptionalProductById(int idOP) throws IdException {
		try {
			return em.find(OptionalProduct.class, idOP);
		} catch (Exception e) {
			throw new IdException("Could not find optional product");
		}
	}
	
	public boolean createOP(String name, float monthlyFee) {
		
		if(!em.createNamedQuery("OptionalProduct.findOP", OptionalProduct.class)
				.setParameter(1, name).setParameter(2, monthlyFee).getResultList().isEmpty())
			return false;
	
		OptionalProduct op = new OptionalProduct();
		op.setNameOP(name);
		op.setMonthlyFeeOP(monthlyFee);
		em.persist(op);
		return true;
	}
}
