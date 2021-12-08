package it.polimi.db2.progetto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import it.polimi.db2.progetto.entities.Order;
import it.polimi.db2.progetto.entities.ServiceActivationSchedule;

@Stateless
public class SasService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public SasService() {
	}
	
public void createSas(Order o) {
		
		/*ServiceActivationSchedule sas = new ServiceActivationSchedule();
		sas.setOrder(o);
		sas.setActDate(o.getStartDate());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Calendar cal = Calendar.getInstance();
        try {
			cal.setTime(sdf.parse(sdf.format(o.getStartDate())));
		} catch (ParseException e) {
			e.printStackTrace();
		}               
        cal.add(Calendar.MONTH, o.getValidityPeriod().getNumMonth());
		sas.setDeactDate(cal.getTime());
		
		o.setServiceActivationSchedule(sas);
		
		em.persist(sas);  */
	}
}
