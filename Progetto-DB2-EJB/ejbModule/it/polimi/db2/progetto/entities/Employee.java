package it.polimi.db2.progetto.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "employee", schema = "db2progetto")
@NamedQuery(name = "Employee.checkCredentials", 
			query = "SELECT e FROM Employee e  WHERE e.username = ?1 and e.password = ?2")

public class Employee implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String username;
	private String password;
	private String email;


	public Employee() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
