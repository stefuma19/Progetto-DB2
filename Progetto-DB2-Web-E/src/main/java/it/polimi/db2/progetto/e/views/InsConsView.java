package it.polimi.db2.progetto.e.views;

public class InsConsView {
	
	String username, email, password;
	boolean isInsolvent;
	int numFailedPayments;
	
	public InsConsView() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isInsolvent() {
		return isInsolvent;
	}

	public void setInsolvent(boolean isInsolvent) {
		this.isInsolvent = isInsolvent;
	}

	public int getNumFailedPayments() {
		return numFailedPayments;
	}

	public void setNumFailedPayments(int numFailedPayments) {
		this.numFailedPayments = numFailedPayments;
	}
}
