package it.polimi.db2.progetto.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.ServicePackage;

@Stateless
public class ServicePackageService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;
	
	public ServicePackageService() {
	}
	
	public List<ServicePackage> findAllServicePackages() {
		return em.createNamedQuery("ServicePackage.findAll", ServicePackage.class).getResultList();
	}
	
	public ServicePackage findServicePackagesById(int idSP) {
		return em.createNamedQuery("ServicePackage.findId", ServicePackage.class).setParameter(1, idSP).getResultList().get(0);
	}
}
