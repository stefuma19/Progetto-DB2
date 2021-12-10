package it.polimi.db2.progetto.views;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AlertViewId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date lastRejection;
	private String userConsumer;
	
	public AlertViewId() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(lastRejection, userConsumer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertViewId other = (AlertViewId) obj;
		return Objects.equals(lastRejection, other.lastRejection) && Objects.equals(userConsumer, other.userConsumer);
	}
	
	
}
