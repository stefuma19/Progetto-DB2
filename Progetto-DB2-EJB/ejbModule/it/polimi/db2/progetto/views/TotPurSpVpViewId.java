package it.polimi.db2.progetto.views;

import java.io.Serializable;
import java.util.Objects;

public class TotPurSpVpViewId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idSP;
	private int idVP;
	
	public TotPurSpVpViewId() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSP, idVP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TotPurSpVpViewId other = (TotPurSpVpViewId) obj;
		return idSP == other.idSP && idVP == other.idVP;
	}	
}
