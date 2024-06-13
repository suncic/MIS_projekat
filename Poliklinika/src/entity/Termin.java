package entity;

public class Termin {
	private String datum, dan;

	public Termin(String datum, String dan) {
		this.datum = datum;
		this.dan = dan;
	}

	public String getDatum() {
		return datum;
	}

	public String getDan() {
		return dan;
	}

	@Override
	public String toString() {
		return datum + " " + dan;
	}
	
	
	
}
