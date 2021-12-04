package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.FixedInternet;

@Stateless
public class FixedInternetService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public FixedInternetService() {
	}
	
	public List<FixedInternet> findAllFIServices() {
		return em.createNamedQuery("FixedInternet.findAll", FixedInternet.class).getResultList();
	}
}
