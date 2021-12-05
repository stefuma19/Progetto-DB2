package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="mobile_phone", schema ="db2progetto")

@NamedQueries({@NamedQuery(name="MobilePhone.findAll", query="SELECT mp FROM MobilePhone mp"),
			@NamedQuery(name="MobilePhone.findMP", 
			query="SELECT mp FROM MobilePhone mp WHERE mp.numMin = ?1 and mp.numSms = ?2 and mp.minFee = ?3 and mp.smsFee = ?4")})

public class MobilePhone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idService;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "mobilePhone")
	private List<ServicePackage> servicePackages;
	
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

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}	
	
	
}
