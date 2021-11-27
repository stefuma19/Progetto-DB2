package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="mobile_internet", schema ="db2progetto")

public class MobileInternet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int idService;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "mobileInternet")
	private List<ServicePackage> servicePackages;

	private int numGigaMI;
	
	private float extraGigaFeeMI;

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public int getNumGigaMI() {
		return numGigaMI;
	}

	public void setNumGigaMI(int numGigaMI) {
		this.numGigaMI = numGigaMI;
	}

	public float getExtraGigaFeeMI() {
		return extraGigaFeeMI;
	}

	public void setExtraGigaFeeMI(float extraGigaFeeMI) {
		this.extraGigaFeeMI = extraGigaFeeMI;
	}

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}

	
}
