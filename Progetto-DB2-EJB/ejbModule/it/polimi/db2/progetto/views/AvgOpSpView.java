package it.polimi.db2.progetto.views;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="v_avg_op_sp", schema ="db2progetto")

@NamedQuery(name="AvgOpSpView.findAll", 
			query="SELECT v FROM AvgOpSpView v")

public class AvgOpSpView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int idSP;
	private String nameSP;
	private float numAvgOP;
	
	public AvgOpSpView() {
		super();
	}

	public int getIdSP() {
		return idSP;
	}

	public void setIdSP(int idSP) {
		this.idSP = idSP;
	}

	public String getNameSP() {
		return nameSP;
	}

	public void setNameSP(String nameSP) {
		this.nameSP = nameSP;
	}

	public float getNumAvgOP() {
		return numAvgOP;
	}

	public void setNumAvgOP(float numAvgOP) {
		this.numAvgOP = numAvgOP;
	}
}
