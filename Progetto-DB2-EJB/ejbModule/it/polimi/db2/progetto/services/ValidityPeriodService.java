package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class ValidityPeriodService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;
	
	public ValidityPeriodService() {
	}
	
	//ritorna tutti i  validity periods
	public List<ValidityPeriod> findAllValidityPeriods() {
		return em.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class)
				.getResultList();
	}
	
	//ritorna un validity period dato il suo ID (eccezione se non esiste)
	public ValidityPeriod findValidityPeriodById(int idVP) throws IdException {
		ValidityPeriod vp = em.find(ValidityPeriod.class, idVP);
		if(vp==null) throw new IdException("Could not find validity period");
		return vp;
	}
}
