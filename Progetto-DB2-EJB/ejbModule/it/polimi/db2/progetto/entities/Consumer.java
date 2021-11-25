package it.polimi.db2.progetto.entities;

import java.io.Serializable;
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
	
	@OneToOne(mappedBy = "userConsumer")
	private Alert alert;

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
	
	
}