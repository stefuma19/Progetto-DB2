package it.polimi.db2.progetto.e.views;

public class TotSaleSpOpView {
	
	int idSP;
	String nameSP;
	float totWithOP, totWithoutOP;
	
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
