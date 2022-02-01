package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.FixedInternet;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class FixedInternetService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public FixedInternetService() {
	}
	
	//recupera tutti i servizi fixed internet 
	public List<FixedInternet> findAllFIServices() {
		return em.createNamedQuery("FixedInternet.findAll", FixedInternet.class)
				.getResultList();
	}
	
	//crea un nuovo servizio fixed internet (false se esiste già)
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
	
	//ritorna un servizio fixed internet dato l'id (eccezione se non esiste)
	public FixedInternet findFixedInternetById(int id) throws IdException {
		FixedInternet fi = em.find(FixedInternet.class, id);
		if(fi==null) throw new IdException("Could not find service");
		return fi;
	}
}
