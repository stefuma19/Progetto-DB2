package it.polimi.db2.progetto.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.OptionalProduct;
import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServiceActivationSchedule;
import it.polimi.db2.progetto.entities.ServicePackage;
import it.polimi.db2.progetto.entities.ValidityPeriod;
import it.polimi.db2.progetto.exceptions.IdException;

@Stateless
public class SasService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public SasService() {
	}
	
public void createSas(Order o) {
		
		ServiceActivationSchedule sas = new ServiceActivationSchedule();
		sas.setOrder(o);
		sas.setActDate(o.getStartDate());
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(o.getStartDate());               //TODO: non aggiunge i giorni in modo corretto
        cal.add(Calendar.DATE, o.getValidityPeriod().getNumMonth()*30);
		sas.setDeactDate(cal.getTime());
		
		o.setServiceActivationSchedule(sas);
		
		em.persist(sas);  //
		
		
	}
}
