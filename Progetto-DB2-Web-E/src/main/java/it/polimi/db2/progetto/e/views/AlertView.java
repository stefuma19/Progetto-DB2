package it.polimi.db2.progetto.e.views;

import java.util.Date;

public class AlertView {

	float amount;
	Date lastRejection;
	String userConsumer, email;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}