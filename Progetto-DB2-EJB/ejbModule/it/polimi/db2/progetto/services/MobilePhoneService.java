package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.MobilePhone;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class MobilePhoneService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public MobilePhoneService() {
	}
	
	public List<MobilePhone> findAllMPServices() {
		return em.createNamedQuery("MobilePhone.findAll", MobilePhone.class)
				.getResultList();
	}
	
	public boolean createMP(int numMin, int numSMS, float feeMin, float feeSMS) {
		
		if(!em.createNamedQuery("MobilePhone.findMP", MobilePhone.class)
				.setParameter(1, numMin)
				.setParameter(2, numSMS)
				.setParameter(3, feeMin)
				.setParameter(4, feeSMS)
				.getResultList().isEmpty())
			return false;
	
		MobilePhone mp = new MobilePhone();
		mp.setNumMin(numMin);
		mp.setNumSms(numSMS);
		mp.setMinFee(feeMin);
		mp.setSmsFee(feeSMS);
		em.persist(mp);
		return true;
	}
	
	public MobilePhone findMobilePhoneById(int id) throws IdException {
		MobilePhone mp = em.find(MobilePhone.class, id);
		if(mp==null) throw new IdException("Could not find service");
		return mp;
	}
}
