package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="fixed_phone", schema ="db2progetto")

public class FixedPhone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private int idService;

	public int getIdService() {
		return idService;
	}

	public void setIdService(int idService) {
		this.idService = idService;
	}
	
}
