package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.FixedInternet;
import it.polimi.db2.progetto.entities.FixedPhone;
import it.polimi.db2.progetto.entities.MobileInternet;
import it.polimi.db2.progetto.entities.MobilePhone;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class ServicePackageService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;
	
	public ServicePackageService() {
	}
	
	//ritorna tutti i service packages
	public List<ServicePackage> findAllServicePackages() {
		return em.createNamedQuery("ServicePackage.findAll", ServicePackage.class)
				.getResultList();
	}
	
	//ritorna il service package dato l'id (eccezione se non esiste)
	public ServicePackage findServicePackagesById(int idSP) throws IdException {
		ServicePackage sp = em.find(ServicePackage.class, idSP);
		if(sp==null) throw new IdException("Could not find service package");
		return sp;
	}

	//ritorna true se esiste già un service package con il nome "name"
	public boolean existsServicePackageByName(String name) {
		return !em.createNamedQuery("ServicePackage.findByName", ServicePackage.class)
				.setParameter(1, name).getResultList().isEmpty();
	}
	
	//crea un service package 
	public boolean createServicePackage(String name, FixedPhone fixedPhone, 
			FixedInternet fixedInternet, MobilePhone mobilePhone, 
			MobileInternet mobileInternet, List<OptionalProduct> optionalProducts) {
		
		List<ServicePackage> existentSPs = em.createNamedQuery(
				"ServicePackage.findSP", ServicePackage.class)
				.setParameter(1, fixedPhone).setParameter(2, fixedInternet)
				.setParameter(3, mobilePhone).setParameter(4, mobileInternet).getResultList();
		
		//if existent SPs contain the same services to add to the new SP
		if(!existentSPs.isEmpty()) { 
			//check if the new sp contains different OPs than the already existent ones
			for(ServicePackage sp : existentSPs) { 
				if(sp.getOptionalProducts().size()!=optionalProducts.size()) {
					break;
				}
				
				int numEquals = 0;	
				
				//count the number of op in common between the 2 sp
				for(OptionalProduct opInSP : sp.getOptionalProducts()) { 
					for(OptionalProduct opToAdd : optionalProducts) {
						if(opInSP.getIdOP()==opToAdd.getIdOP()) {
							numEquals++;
							break;
						}
					}
				}
				
				if(numEquals == sp.getOptionalProducts().size())
					return false;
			}
		}
		
		ServicePackage sp = new ServicePackage();
		sp.setName(name);
		sp.setFixedPhone(fixedPhone);
		sp.setFixedInternet(fixedInternet);
		sp.setMobilePhone(mobilePhone);
		sp.setMobileInternet(mobileInternet);
		sp.setOptionalProducts(optionalProducts);
		
		for(OptionalProduct op : optionalProducts) { 
			op.addServicePackage(sp);
		}
		
		em.persist(sp);
		return true;
	}
}
