package entity;

import java.io.Serializable;

public class Usluga implements Serializable {
	private String opis;

	public Usluga(String opis) {
		this.opis = opis;
	}

	public Usluga() {
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@Override
	public String toString() {
		return opis;
	}
	
	
}
