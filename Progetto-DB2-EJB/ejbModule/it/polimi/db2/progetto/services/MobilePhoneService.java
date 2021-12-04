package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.MobilePhone;

@Stateless
public class MobilePhoneService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public MobilePhoneService() {
	}
	
	public List<MobilePhone> findAllMPServices() {
		return em.createNamedQuery("MobilePhone.findAll", MobilePhone.class).getResultList();
	}
}
