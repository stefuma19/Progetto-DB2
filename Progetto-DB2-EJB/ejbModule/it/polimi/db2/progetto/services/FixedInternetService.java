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
	
	public boolean createFI(int num, float fee) {
		
		if(!em.createNamedQuery("FixedInternet.findFI", FixedInternet.class)
				.setParameter(1, num).setParameter(2, fee).getResultList().isEmpty())
			return false;
	
		FixedInternet fi = new FixedInternet();
		fi.setNumGigaFI(num);
		fi.setExtraGigaFeeFI(fee);
		em.persist(fi);
		return true;
	}
}
