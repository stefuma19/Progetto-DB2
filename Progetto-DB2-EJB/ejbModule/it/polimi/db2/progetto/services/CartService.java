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
public class CartService { //per memorizzare l'ordine dell'utente 

	@PersistenceContext(unitName = "Progetto-DB2-EJB", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	private String username = "";
	private ServicePackage sp;
	private ValidityPeriod vp;
	private float tp = 0.0f;
	private List<OptionalProduct> ops = new ArrayList<>();
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
		return sp;
	}

	public void setSP(int idSP) {
		this.sp = em.find(ServicePackage.class, idSP);
	}

	public ValidityPeriod getVP() {
		return vp;
	}

	public void setVP(int idVP) {
		this.vp = em.find(ValidityPeriod.class, idVP);
	}

	public float getTp() {
		return tp;
	}

	public void setTp(float tp) {
		this.tp = tp;
	}

	public List<OptionalProduct> getOPs() {
		return ops;
	}

	public void setOPs(List<Integer> idOPs) {
		for(Integer idOP : idOPs) {
			ops.add(em.find(OptionalProduct.class, idOP));
		}
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
	public void remove() {}  //l'oggetto viene rimosso al termine della sessione (logout)
}
