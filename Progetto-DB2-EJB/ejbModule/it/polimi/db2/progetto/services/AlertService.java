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
	
}
