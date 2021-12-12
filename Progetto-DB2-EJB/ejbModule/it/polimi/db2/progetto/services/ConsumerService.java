package it.polimi.db2.progetto.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.NonUniqueResultException;

import it.polimi.db2.progetto.entities.Consumer;
import it.polimi.db2.progetto.exceptions.*;
import java.util.List;

@Stateless
public class ConsumerService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public ConsumerService() {
	}

	public Consumer checkLogin(String username, String password) throws CredentialsException, NonUniqueResultException {
		List<Consumer> cList = null;
		try {
			cList = em.createNamedQuery("Consumer.checkCredentials", Consumer.class)
					.setParameter(1, username).setParameter(2, password)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (cList.isEmpty())
			return null;
		else if (cList.size() == 1)
			return cList.get(0);
		throw new NonUniqueResultException("More than one user registered with same credentials");
	}

	
	public boolean register(String email, String username, String password) throws CredentialsException{
	
		if(em.find(Consumer.class, username) != null) {
			return false;
		}
		else {
			Consumer c = new Consumer();
			c.setEmail(email);
			c.setUsername(username);
			c.setPassword(password);
			c.setInsolvent(false);
			c.setNumFailedPayments(0);
			em.persist(c);
			return true;
		}
	}
}
