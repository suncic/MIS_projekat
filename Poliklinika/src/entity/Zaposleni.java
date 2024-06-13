package entity;

public class Zaposleni {
	private String ime, prezime; /
	
	public Zaposleni() {	
	}
	
	public Zaposleni(String ime, String prezime) {
		this();
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String toString() {
		return "Zaposleni: " + ime + " " + prezime;
	}
	
	
	
	
}
