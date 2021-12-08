package it.polimi.db2.progetto.e.views;

import java.util.Date;

public class SusOrdView {
	
	int idOrder, idSP, idValidity;
	float totValue;
	boolean isValid;
	Date startDate, creationDate;
	String userConsumer;
	
	public SusOrdView() {
		super();
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public int getIdValidity() {
		return idValidity;
	}

	public void setIdValidity(int idValidity) {
		this.idValidity = idValidity;
	}

	public float getTotValue() {
		return totValue;
	}

	public void setTotValue(float totValue) {
		this.totValue = totValue;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getUserConsumer() {
		return userConsumer;
	}

	public void setUserConsumer(String userConsumer) {
		this.userConsumer = userConsumer;
	}
}
