package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="optional_product", schema ="db2progetto")

public class OptionalProduct implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idOP;
	
	private String nameOP;
	private float monthlyFeeOP;
	
	@ManyToMany(mappedBy = "optionalProducts")
	private List<ServicePackage> servicePackages;
	
	@ManyToMany(mappedBy = "optionalProductsOrdered")
	private List<Order> orders;
	
	public int getIdOP() {
		return idOP;
	}
	
	public void setIdOP(int idOP) {
		this.idOP = idOP;
	}
	
	public String getNameOP() {
		return nameOP;
	}
	
	public void setNameOP(String nameOP) {
		this.nameOP = nameOP;
	}
	
	public float getMonthlyFeeOP() {
		return monthlyFeeOP;
	}
	
	public void setMonthlyFeeOP(float monthlyFeeOP) {
		this.monthlyFeeOP = monthlyFeeOP;
	}

	public List<ServicePackage> getServicePackages() {
		return servicePackages;
	}

	public void setServicePackages(List<ServicePackage> servicePackages) {
		this.servicePackages = servicePackages;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
}