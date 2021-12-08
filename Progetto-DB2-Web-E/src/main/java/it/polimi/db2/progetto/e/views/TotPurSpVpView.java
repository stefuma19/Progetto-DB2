package it.polimi.db2.progetto.e.views;

public class TotPurSpVpView {

	int idSP, idVP, numMonth;
	String name;
	float monthlyFee, totalPurchase;
	
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

	public float getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(float totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
}