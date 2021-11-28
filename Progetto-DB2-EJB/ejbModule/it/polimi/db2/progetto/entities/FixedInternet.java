package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="fixed_internet", schema ="db2progetto")

public class FixedInternet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int idService;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "fixedInternet")
	private List<ServicePackage> servicePackages;

	private int numGigaFI;
	
	private float extraGigaFeeFI;

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public int getNumGigaFI() {
		return numGigaFI;
	}

	public void setNumGigaFI(int numGigaFI) {
		this.numGigaFI = numGigaFI;
	}

	public float getExtraGigaFeeFI() {
		return extraGigaFeeFI;
	}

	public void setExtraGigaFeeFI(float extraGigaFeeFI) {
		this.extraGigaFeeFI = extraGigaFeeFI;
	}

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}
	
	

}
