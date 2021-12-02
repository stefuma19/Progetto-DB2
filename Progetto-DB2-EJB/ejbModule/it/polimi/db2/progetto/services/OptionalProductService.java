package it.polimi.db2.progetto.services;

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
	
	public OptionalProduct findOptionalProductById(int idOP) throws IdException {
		try {
			return em.createNamedQuery("OptionalProduct.findId", OptionalProduct.class).setParameter(1, idOP).getResultList().get(0);
		} catch (Exception e) {
			throw new IdException("Could not find optional product");
		}
	}
}
