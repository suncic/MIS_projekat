package entity;

public class Dobavljac {
	private String ime, prezime;
	
	public Dobavljac() {
		
	}

	public Dobavljac(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}
	
	
	
	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setImeIPrezime(String str) {
		String[] token = str.split(" ");
		setIme(token[0].trim());
		setPrezime(token[1].trim());
	}

	public String getPrezime() {
		return prezime;
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	
}
