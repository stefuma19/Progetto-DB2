package it.polimi.db2.progetto.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/*
@Entity
@Table(name="v_alert", schema ="db2progetto")*/

public class AlertView /*implements Serializable*/ {
	private static final long serialVersionUID = 1L;
	
	private float amount;
	private Date lastRejection;
	private String userConsumer;
	private String email;
	
	public AlertView() {
		super();
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getLastRejection() {
		return lastRejection;
	}

	public void setLastRejection(Date lastRejection) {
		this.lastRejection = lastRejection;
	}

	public String getUserConsumer() {
		return userConsumer;
	}

	public void setUserConsumer(String userConsumer) {
		this.userConsumer = userConsumer;
	}

	/*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}*/
}
