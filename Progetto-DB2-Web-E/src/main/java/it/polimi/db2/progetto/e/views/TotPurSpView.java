package it.polimi.db2.progetto.e.views;

public class TotPurSpView {

	int idSP;
	String name;
	float totalPurchase;
	
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

	public float getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(float totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
}