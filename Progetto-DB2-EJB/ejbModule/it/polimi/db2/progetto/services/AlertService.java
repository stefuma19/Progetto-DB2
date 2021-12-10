package it.polimi.db2.progetto.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlertService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public AlertService() {
	}
	
	//TODO: eliminare?
}
