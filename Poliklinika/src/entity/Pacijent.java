package entity;

public class Pacijent {
	private String username, password;

	public Pacijent(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return username + ", password: " + password;
	}
	
	
}
