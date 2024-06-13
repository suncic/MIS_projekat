package entity;

public class Proizvod {
	private String ime;

	public Proizvod(String ime) {
		this.ime = ime;
	}

	public String getIme() {
		return ime;
	}

	@Override
	public String toString() {
		return ime;
	}
	
	
}
