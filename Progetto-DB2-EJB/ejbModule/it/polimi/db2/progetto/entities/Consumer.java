package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "consumer", schema = "db2progetto")
@NamedQuery(name = "Consumer.checkCredentials", query = "SELECT c FROM Consumer c  WHERE c.username = ?1 and c.password = ?2")
	


public class Consumer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String password;
	private String email;
	private boolean isInsolvent;
	private int numFailedPayments;
	
	@OneToOne(fetch =FetchType.LAZY, mappedBy = "userConsumer")
	private Alert alert;
	
	@OneToMany(fetch =FetchType.EAGER, mappedBy = "userConsumer")
	private List<Order> orders;

	public Consumer() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isInsolvent() {
		return isInsolvent;
	}

	public void setInsolvent(boolean isInsolvent) {
		this.isInsolvent = isInsolvent;
	}

	public int getNumFailedPayments() {
		return numFailedPayments;
	}

	public void setNumFailedPayments(int numFailedPayments) {
		this.numFailedPayments = numFailedPayments;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}