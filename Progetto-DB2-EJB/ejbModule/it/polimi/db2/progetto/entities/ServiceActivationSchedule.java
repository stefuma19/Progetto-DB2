package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="service_activation_schedule", schema ="db2progetto")

public class ServiceActivationSchedule implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSAS;
	
	private Date actDate;
	private Date deactDate;
	
	@OneToOne(fetch =FetchType.LAZY/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}*/)
	@JoinColumn(name = "IdOrder")
	private Order order;
	
	public int getIdSAS() {
		return idSAS;
	}
	
	public void setIdSAS(int idSAS) {
		this.idSAS = idSAS;
	}
	
	public Date getActDate() {
		return actDate;
	}
	
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	
	public Date getDeactDate() {
		return deactDate;
	}
	
	public void setDeactDate(Date deactDate) {
		this.deactDate = deactDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	

}
