package it.polimi.db2.progetto.e.views;

public class AvgOpSpView {

	int idSP;
	String nameSP;
	float numAvgOP;
	
	public AvgOpSpView() {
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

	public float getNumAvgOP() {
		return numAvgOP;
	}

	public void setNumAvgOP(float numAvgOP) {
		this.numAvgOP = numAvgOP;
	}
}