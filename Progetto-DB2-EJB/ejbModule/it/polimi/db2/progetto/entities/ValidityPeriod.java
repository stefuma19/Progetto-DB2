package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="validity_period", schema ="db2progetto")

@NamedQuery(name="ValidityPeriod.findAll", query="SELECT vp FROM ValidityPeriod vp")

public class ValidityPeriod implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int idValidityPeriod;
	private int numMonth;
	private float monthlyFee;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "validityPeriod")
	private List<Order> orders;
	
	public int getIdValidityPeriod() {
		return idValidityPeriod;
	}
	
	public void setIdValidityPeriod(int idValidityPeriod) {
		this.idValidityPeriod = idValidityPeriod;
	}
	
	public int getNumMonth() {
		return numMonth;
	}
	
	public void setNumMonth(int numMonth) {
		this.numMonth = numMonth;
	}
	
	public float getMonthlyFee() {
		return monthlyFee;
	}
	
	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	
}
