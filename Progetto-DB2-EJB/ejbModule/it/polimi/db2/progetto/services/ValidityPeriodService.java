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
	
	public List<ValidityPeriod> findAllValidityPeriods() {
		return em.createNamedQuery("ValidityPeriod.findAll", ValidityPeriod.class).getResultList();
	}
	
	public ValidityPeriod findValidityPeriodById(int idVP) throws IdException {
		try {
			return em.createNamedQuery("ValidityPeriod.findId", ValidityPeriod.class).setParameter(1, idVP).getResultList().get(0);
		} catch (Exception e) {
			throw new IdException("Could not find validity period");
		}
	}
}
