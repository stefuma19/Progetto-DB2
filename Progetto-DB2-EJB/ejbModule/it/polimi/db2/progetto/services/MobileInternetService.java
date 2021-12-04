package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.MobileInternet;

@Stateless
public class MobileInternetService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public MobileInternetService() {
	}
	
	public List<MobileInternet> findAllMIServices() {
		return em.createNamedQuery("MobileInternet.findAll", MobileInternet.class).getResultList();
	}

}
