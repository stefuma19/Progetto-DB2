package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="fixed_internet", schema ="db2progetto")

public class FixedInternet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private int idService;

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
	
	

}
