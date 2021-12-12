package it.polimi.db2.progetto.services;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.Order;

@Stateless
public class SasService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public SasService() {
	}
	
	//TODO: eliminare?
}
