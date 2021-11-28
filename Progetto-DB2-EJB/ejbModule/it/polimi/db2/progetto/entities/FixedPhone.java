package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="fixed_phone", schema ="db2progetto")

public class FixedPhone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int idService;
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "fixedPhone")
	private List<ServicePackage> servicePackages;

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}
	
	
}
