package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.FixedPhone;

@Stateless
public class FixedPhoneService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public FixedPhoneService() {
	}
	
	//recupera tutti i servizi fixed phone 
	public List<FixedPhone> findAllFPServices() {
		return em.createNamedQuery("FixedPhone.findAll", FixedPhone.class)
				.getResultList();
	}
	
	//controlla se esiste un fixed phone (in caso contrario lo crea)
	public FixedPhone checkFpExists() {
		List<FixedPhone> fis = findAllFPServices();
		if(fis.isEmpty()) {
			FixedPhone fi = new FixedPhone();
			em.persist(fi);
			return fi;
		}
		else return fis.get(0);
	}
}
