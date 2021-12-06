package it.polimi.db2.progetto.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.NonUniqueResultException;

import it.polimi.db2.progetto.entities.Alert;
import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.entities.ServiceActivationSchedule;
import it.polimi.db2.progetto.exceptions.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Stateless
public class AlertService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public AlertService() {
	}
	
	public void createAlert(String consUser) {
		
		Alert alert = new Alert();
		Consumer cons = em.find(Consumer.class, consUser);
		alert.setUserConsumer(cons);
		alert.setLastRejection(new Date());
		alert.setAmount((float)((double)em.createNamedQuery("Order.getNotPaidAmount")
				.setParameter(1, cons).getResultList().get(0)));
		
		cons.setAlert(alert);
		
		em.persist(alert);
	}
}
