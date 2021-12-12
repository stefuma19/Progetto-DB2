package it.polimi.db2.progetto.views;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="v_tot_pur_sp_vp", schema ="db2progetto")

@NamedQuery(name="TotPurSpVpView.findAll", query="SELECT v FROM TotPurSpVpView v ORDER BY v.idSP")

@IdClass(TotPurSpVpViewId.class)
public class TotPurSpVpView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 
	private int idSP;
	@Id
	private int idVP;
	private String name;
	private int numMonth;
	private float monthlyFee;
	private int totalPurchase;
	
	public TotPurSpVpView() {
		super();
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public int getIdVP() {
		return idVP;
	}

	public void setIdVP(int idVP) {
		this.idVP = idVP;
	}

	public int getNumMonth() {
		return numMonth;
	}

	public void setNumMonth(int numMonth) {
		this.numMonth = numMonth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public int getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
}
