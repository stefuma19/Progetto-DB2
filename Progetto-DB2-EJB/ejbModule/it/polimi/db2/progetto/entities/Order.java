package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="order", schema ="db2progetto")

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idOrder;
	private float totValue;
	private boolean isValid;
	private Date startDate;
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
	
}
