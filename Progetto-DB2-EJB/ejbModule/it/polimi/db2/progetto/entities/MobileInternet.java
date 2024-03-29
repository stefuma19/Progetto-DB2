package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="mobile_internet", schema ="db2progetto")

@NamedQueries({@NamedQuery(name="MobileInternet.findAll", query="SELECT mi FROM MobileInternet mi"),
	@NamedQuery(name="MobileInternet.findMI", 
	query="SELECT mi FROM MobileInternet mi WHERE mi.numGigaMI = ?1 and mi.extraGigaFeeMI = ?2")})

public class MobileInternet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void addServicePackage(ServicePackage servicePackage) {
		this.servicePackages.add(servicePackage);
	}
}
