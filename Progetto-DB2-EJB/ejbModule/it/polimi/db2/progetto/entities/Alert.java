package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="alert", schema ="db2progetto")

public class Alert implements Serializable{ //TODO: eliminare?
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlert;
	private float amount;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastRejection;
	
	@OneToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "UserConsumer")
	private Consumer userConsumer;
	
	public int getIdAlert() {
		return idAlert;
	}
	
	public void setIdAlert(int idAlert) {
		this.idAlert = idAlert;
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

	public Consumer getUserConsumer() {
		return userConsumer;
	}

	public void setUserConsumer(Consumer userConsumer) {
		this.userConsumer = userConsumer;
	}
}
