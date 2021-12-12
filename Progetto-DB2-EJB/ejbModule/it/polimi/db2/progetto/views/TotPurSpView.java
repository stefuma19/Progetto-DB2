package it.polimi.db2.progetto.views;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="v_tot_pur_sp", schema ="db2progetto")

@NamedQuery(name="TotPurSpView.findAll", query="SELECT v FROM TotPurSpView v")

public class TotPurSpView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idSP;
	private String name;
	private int totalPurchase;
	
	public TotPurSpView() {
		super();
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
}
