package it.polimi.db2.progetto.views;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="v_best_seller_op", schema ="db2progetto")

@NamedQuery(name="BestSellerOpView.findAll", query="SELECT v FROM BestSellerOpView v")

public class BestSellerOpView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idOP;
	private String nameOP;
	private int numSell;
	
	public BestSellerOpView() {
		super();
	}

	public int getIdOP() {
		return idOP;
	}

	public void setIdOP(int idOP) {
		this.idOP = idOP;
	}

	public String getNameOP() {
		return nameOP;
	}

	public void setNameOP(String nameOP) {
		this.nameOP = nameOP;
	}

	public int getNumSell() {
		return numSell;
	}

	public void setNumSell(int numSell) {
		this.numSell = numSell;
	}
}
