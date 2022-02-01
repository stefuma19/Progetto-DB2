package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.MobileInternet;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class MobileInternetService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public MobileInternetService() {
	}
	
	//recupera tutti i servizi mobile internet 
	public List<MobileInternet> findAllMIServices() {
		return em.createNamedQuery("MobileInternet.findAll", MobileInternet.class)
				.getResultList();
	}

	//crea un nuovo servizio mobile internet (false se esiste gi�)
	public boolean createMI(int num, float fee) {
		if(!em.createNamedQuery("MobileInternet.findMI", MobileInternet.class)
				.setParameter(1, num).setParameter(2, fee).getResultList().isEmpty())
			return false;
	
		MobileInternet mi = new MobileInternet();
		mi.setNumGigaMI(num);
		mi.setExtraGigaFeeMI(fee);
		em.persist(mi);
		return true;
	}
	
	//ritorna un servizio mobile internet dato l'id (eccezione se non esiste)
	public MobileInternet findMobileInternetById(int id) throws IdException {
		MobileInternet mi = em.find(MobileInternet.class, id);
		if(mi==null) throw new IdException("Could not find service");
		return mi;
	}
}
