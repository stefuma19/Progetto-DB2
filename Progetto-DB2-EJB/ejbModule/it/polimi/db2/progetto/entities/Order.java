package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="order", schema ="db2progetto")
@NamedQueries({ @NamedQuery(name = "Order.getInvalidOrders", query = "SELECT o FROM Order o  WHERE o.userConsumer = ?1 and o.isValid = false"),
	@NamedQuery(name = "Order.getNotPaidAmount", query = "SELECT SUM(o.totValue) FROM Order o  WHERE o.userConsumer = ?1 and o.isValid = false")})
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	private float totValue;
	private boolean isValid;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="op_in_order"
		, joinColumns= {
				@JoinColumn(name="IdOrder")
		}
		, inverseJoinColumns= {
				@JoinColumn(name="IdOP")
		}
			)
	private List<OptionalProduct> optionalProductsOrdered;
	
	@OneToOne(fetch =FetchType.LAZY, mappedBy = "order")
	private ServiceActivationSchedule serviceActivationSchedule;

	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name = "UserConsumer")
	private Consumer userConsumer;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "IdSP")
	private ServicePackage servicePackage;
	
	@ManyToOne(fetch =FetchType.EAGER)
	@JoinColumn(name = "IdValidity")
	private ValidityPeriod validityPeriod;
	
	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
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

	public List<OptionalProduct> getOptionalProductsOrdered() {
		return optionalProductsOrdered;
	}

	public void setOptionalProductsOrdered(List<OptionalProduct> optionalProducts) {
		this.optionalProductsOrdered = optionalProducts;
	}

	public ServiceActivationSchedule getServiceActivationSchedule() {
		return serviceActivationSchedule;
	}

	public void setServiceActivationSchedule(ServiceActivationSchedule serviceActivationSchedule) {
		this.serviceActivationSchedule = serviceActivationSchedule;
	}

	public Consumer getUserConsumer() {
		return userConsumer;
	}

	public void setUserConsumer(Consumer userConsumer) {
		this.userConsumer = userConsumer;
	}

	public ServicePackage getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(ServicePackage servicePackage) {
		this.servicePackage = servicePackage;
	}

	public ValidityPeriod getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(ValidityPeriod validityPeriod) {
		this.validityPeriod = validityPeriod;
	}
	
	
}
