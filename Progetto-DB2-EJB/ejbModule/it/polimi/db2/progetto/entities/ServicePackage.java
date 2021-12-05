package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="service_package", schema ="db2progetto")

@NamedQueries({@NamedQuery(name="ServicePackage.findAll", query="SELECT sp FROM ServicePackage sp"),
	@NamedQuery(name="ServicePackage.findByName", query="SELECT sp FROM ServicePackage sp WHERE sp.name = ?1"),
	@NamedQuery(name="ServicePackage.findSP", 
			query="SELECT sp FROM ServicePackage sp WHERE (?1 is null or sp.fixedPhone = ?1)"
			+ " and (?2 is null or sp.fixedInternet = ?2) and (?3 is null or sp.mobilePhone = ?3)"
			+ " and (?4 is null or sp.mobileInternet = ?4)")})

public class ServicePackage implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@OneToMany(fetch =FetchType.LAZY, mappedBy = "servicePackage")
	private List<Order> orders;
	
	@ManyToOne(fetch =FetchType.EAGER/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}*/)
	@JoinColumn(name = "IdFP")
	private FixedPhone fixedPhone;
	
	@ManyToOne(fetch =FetchType.EAGER/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}*/)
	@JoinColumn(name = "IdFI")
	private FixedInternet fixedInternet;
	
	@ManyToOne(fetch =FetchType.EAGER/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}*/)
	@JoinColumn(name = "IdMP")
	private MobilePhone mobilePhone;
	
	@ManyToOne(fetch =FetchType.EAGER/*, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}*/)
	@JoinColumn(name = "IdMI")
	private MobileInternet mobileInternet;

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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public FixedPhone getFixedPhone() {
		return fixedPhone;
	}

	public void setFixedPhone(FixedPhone fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public FixedInternet getFixedInternet() {
		return fixedInternet;
	}

	public void setFixedInternet(FixedInternet fixedInternet) {
		this.fixedInternet = fixedInternet;
	}

	public MobilePhone getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(MobilePhone mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public MobileInternet getMobileInternet() {
		return mobileInternet;
	}

	public void setMobileInternet(MobileInternet mobileInternet) {
		this.mobileInternet = mobileInternet;
	}
	
	
	
	//TODO: potremmo aggiungere un addOptionalProduct
	
}
