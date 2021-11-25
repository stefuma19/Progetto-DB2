package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="validity_period", schema ="db2progetto")

public class ValidityPeriod implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idValidityPeriod;
	private int numMonth;
	private float monthlyFee;
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

}
