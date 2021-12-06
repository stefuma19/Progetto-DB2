package it.polimi.db2.progetto.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.NonUniqueResultException;

import it.polimi.db2.progetto.entities.Employee;
import it.polimi.db2.progetto.exceptions.*;
import java.util.List;

@Stateless
public class EmployeeService {
	@PersistenceContext(unitName = "Progetto-DB2-EJB")
	private EntityManager em;

	public EmployeeService() {
	}
	
	public Employee checkLogin(String username, String password) throws CredentialsException, NonUniqueResultException {
		List<Employee> eList = null;
		try {
			eList = em.createNamedQuery("Employee.checkCredentials", Employee.class).setParameter(1, username).setParameter(2, password)
					.getResultList();
		} catch (PersistenceException e) {
			throw new CredentialsException("Could not verify credentals");
		}
		if (eList.isEmpty())
			return null;
		else if (eList.size() == 1)
			return eList.get(0);
		throw new NonUniqueResultException("More than one employee registered with same credentials");
	}
}
