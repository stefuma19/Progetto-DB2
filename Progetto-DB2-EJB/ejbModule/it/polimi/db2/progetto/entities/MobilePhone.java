package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="mobile_phone", schema ="db2progetto")

public class MobilePhone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idService;
	
	private int numMin;
	private int numSms;
	
	private float minFee;
	private float smsFee;
	public int getIdService() {
		return idService;
	}
	public void setIdService(int idService) {
		this.idService = idService;
	}
	public int getNumMin() {
		return numMin;
	}
	public void setNumMin(int numMin) {
		this.numMin = numMin;
	}
	public int getNumSms() {
		return numSms;
	}
	public void setNumSms(int numSms) {
		this.numSms = numSms;
	}
	public float getMinFee() {
		return minFee;
	}
	public void setMinFee(float minFee) {
		this.minFee = minFee;
	}
	public float getSmsFee() {
		return smsFee;
	}
	public void setSmsFee(float smsFee) {
		this.smsFee = smsFee;
	}
	
	
}