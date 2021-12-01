package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.OptionalProduct;



@Stateless
public class OptionalProductService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;
	
	public OptionalProductService() {
	}
	
	public OptionalProduct findOptionalProductById(int idOP) {
		return em.createNamedQuery("OptionalProduct.findId", OptionalProduct.class).setParameter(1, idOP).getResultList().get(0);
	}
}
