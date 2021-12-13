package it.polimi.db2.progetto.views;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="v_tot_sale_sp_op", schema ="db2progetto")

@NamedQuery(name="TotSaleSpOpView.findAll", 
			query="SELECT v FROM TotSaleSpOpView v")

public class TotSaleSpOpView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idSP;
	private String nameSP;
	private float totWithOP, totWithoutOP;
	
	public TotSaleSpOpView() {
		super();
	}
	
	public int getIdSP() {
		return idSP;
	}
	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}
	public String getNameSP() {
		return nameSP;
	}
	public void setNameSP(String nameSP) {
		this.nameSP = nameSP;
	}
	public float getTotWithOP() {
		return totWithOP;
	}
	public void setTotWithOP(float totWithOP) {
		this.totWithOP = totWithOP;
	}
	public float getTotWithoutOP() {
		return totWithoutOP;
	}
	public void setTotWithoutOP(float totWithoutOP) {
		this.totWithoutOP = totWithoutOP;
	}
}
