package entity;

public final class Lekar extends Zaposleni {
	private String zvanje;

	public Lekar(String ime, String prezime, String zvanje) {
		super(ime, prezime);
		this.zvanje = zvanje;
	}

	public String getZvanje() {
		return zvanje;
	}

	@Override
	public String toString() {
		return  super.getIme() + " " + super.getPrezime();
	}
	
}
