package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="service_package", schema ="db2progetto")

public class ServicePackage implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idServicePackage;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="op_in_sp"
		, joinColumns= {
				@JoinColumn(name="IdSP")
		}
		, inverseJoinColumns= {
				@JoinColumn(name="IdOP")
		}
			)
	private List<OptionalProduct> optionalProducts;

	public int getIdServicePackage() {
		return idServicePackage;
	}

	public void setIdServicePackage(int idServicePackage) {
		this.idServicePackage = idServicePackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OptionalProduct> getOptionalProducts() {
		return optionalProducts;
	}

	public void setOptionalProducts(List<OptionalProduct> optionalProducts) {
		this.optionalProducts = optionalProducts;
	}
	
	//TODO: potremmo aggiungere un addOptionalProduct
	
}
