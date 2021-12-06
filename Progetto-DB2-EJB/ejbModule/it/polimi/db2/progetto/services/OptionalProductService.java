package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.OptionalProduct;
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
		OptionalProduct op = em.find(OptionalProduct.class, idOP);
		if(op==null) throw new IdException("Could not find optional product");
		return op;
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
