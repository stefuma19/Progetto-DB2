package it.polimi.db2.progetto.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;

@Stateful
public class CartService {

	@PersistenceContext(unitName = "Progetto-DB2-EJB", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private String username = "";
	private int idSP = 0, idVP = 0;
	private float tp = 0.0f;
	private List<Integer> idOPs = new ArrayList<>();
	private String sd = "";
	private boolean isEmpty = true;

	public CartService() {
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public ServicePackage getSP() {
		return em.find(ServicePackage.class, idSP);
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public ValidityPeriod getVP() {
		return em.find(ValidityPeriod.class, idVP);
	}

	public void setIdVP(int idVP) {
		this.idVP = idVP;
	}

	public float getTp() {
		return tp;
	}

	public void setTp(float tp) {
		this.tp = tp;
	}

	public List<OptionalProduct> getOPs() {
		List<OptionalProduct> ops = new ArrayList<>();
		for(Integer idOP : idOPs) {
			ops.add(em.find(OptionalProduct.class, idOP));
		}
		return ops;
	}

	public void setIdOPs(List<Integer> idOPs) {
		this.idOPs = idOPs;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	@Remove
	public void remove() {}
}
