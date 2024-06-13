package entity;

public final class MedicinskaSestra extends Zaposleni {
	private String username, password;

	public MedicinskaSestra(String ime, String prezime, String username, String password) {
		super(ime, prezime);
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
